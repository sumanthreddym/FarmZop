package com.farmzop.application;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.farmzop.application.JsonParserAndPackager.DirectionMatrixJsonParser;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gaurav on 11-02-2016.
 */
public class NearestStoreActivity extends Activity {

    int pendingRequests=0;
    ArrayList<FindStoreDetailsItem> findStoreDetailsItem;
    TextView pos_text;
    LatLng userPosition;
    RequestQueue queue;
    int minDistStoreIndex;
    ProgressBar loadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nearest_store_layout);

        //initialize the queue
        queue = Volley.newRequestQueue(this);

        //set the stores details
        //initialize the list store items
        findStoreDetailsItem = new ArrayList<>();
        findStoreDetailsItem.add(0,new FindStoreDetailsItem(100, "Marathahalli",new LatLng(12.9477297,77.7000652)));
        findStoreDetailsItem.add(1,new FindStoreDetailsItem(101, "Bellandur",new LatLng(12.9263546,77.6759305)));
        findStoreDetailsItem.add(2, new FindStoreDetailsItem(102,"Domlur",new LatLng(12.9630376, 77.6268656)));
        findStoreDetailsItem.add(3, new FindStoreDetailsItem(103, "HSR Layout", new LatLng(12.9102997, 77.6281508)));

        //get reference to loading progress
        loadingProgress=(ProgressBar)findViewById(R.id.loading_nearest_store_pb);
        loadingProgress.setVisibility(View.VISIBLE);


        //get the location
        userPosition = new LatLng(getIntent().getDoubleExtra("UserLatitude", 12.5), getIntent().getDoubleExtra("UserLongitude", 73.5));

        pos_text = (TextView) findViewById(R.id.store_pos_details_text);
        pos_text.setVisibility(View.INVISIBLE);

        Button get_directions = (Button) findViewById(R.id.button_getDirections);
        get_directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Get Directions Button Clicked", Toast.LENGTH_SHORT).show();
                //get directions to the store from current user position
                openMapsDirections();

            }
        });



        //find the distance between cur pos and all stores
        getDistanceFromCurrentPos();



    }


    private void requestShortestPath() {

        //if no pending requests remaining
        if (pendingRequests == 0) {
            //find the shortest path and display
            getShortestPath();
        }

    }

    /** A method to download json data from url in form of google maps directions matrix using volley
     * Requests data from server using volley api
     *@param url this is link for the connnection to be established(universal resource locator)
     *@param index it is the store id for which the request is made
     */
    private void requestData(String url, int index) {

        final int tmpIndex=index;
        JsonObjectRequest request = new JsonObjectRequest(url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //call the server and get the data
                        findStoreDetailsItem.get(tmpIndex).setDistFromCurrentPos(DirectionMatrixJsonParser.parseObject(response, tmpIndex));

                        //decrement pending request since got one response
                        pendingRequests--;

                        //find the shortest path
                        requestShortestPath();

                    }

                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


        //add req to queue
        queue.add(request);
        //inc pending requests
        pendingRequests++;
    }


    /*get the shortest distant store*/
    void getShortestPath() {
        float min = findStoreDetailsItem.get(0).getDistFromCurrentPos();
        int flag=0;

        for (int i = 1; i < findStoreDetailsItem.size(); i++) {
            if (findStoreDetailsItem.get(i).getDistFromCurrentPos() < min) {
                min = findStoreDetailsItem.get(i).getDistFromCurrentPos();

               //this is used to display the least distant store
                flag=i;
                minDistStoreIndex=i;

                /*We use 2 diff index here coz the volley uses async task and the manner in which the data stored in
                store distance items may vary
                 */
            }
        }

        //call the display text function to display the store with min distance from current pos
        displayNearestStore(flag);

    }

    void displayNearestStore(int minIndex)
    {
        //make pos_text  visible and display the nearest store
        loadingProgress.setVisibility(View.GONE);

        pos_text.setVisibility(View.VISIBLE);
        pos_text.setText("The Nearest Store Is in " + findStoreDetailsItem.get(minIndex).getArea() +
                " at a distance of " + findStoreDetailsItem.get(minIndex).getDistFromCurrentPos()
                + " Km from your current position.");

    }

    /**gets the distances from the offline stores and current pos*/
    void getDistanceFromCurrentPos() {

        //generate url and get distance between current location and the offline stores using google maps api
        for (int i = 0; i < findStoreDetailsItem.size(); i++) {
            String tmpURL = getDirectionsUrl(userPosition, findStoreDetailsItem.get(i).getPosition());
            requestData(tmpURL, i);
        }

    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Sensor enabled
        String sensor = "sensor=false";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + "json" + "?" + parameters;

        return url;
    }

    /*method that open directions in map*/
    private void openMapsDirections() {

        // Origin of route
        String str_origin = userPosition.latitude + "," + userPosition.longitude;
        // Destination of route
        String str_dest = findStoreDetailsItem.get(minDistStoreIndex).getPosition().latitude + "," + findStoreDetailsItem.get(minDistStoreIndex).getPosition().longitude;

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr="+str_origin+"&daddr="+str_dest));
        startActivity(intent);

    }


}