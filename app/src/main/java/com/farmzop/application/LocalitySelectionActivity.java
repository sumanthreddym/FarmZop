package com.farmzop.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmzop.application.JsonParserAndPackager.CustomJsonParsers;
import com.farmzop.application.SpinnerAdapter.CustomSpinnerAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gaurav on 31-03-2016.
 */
public class LocalitySelectionActivity extends AppCompatActivity {

    Spinner citySpinner;
    Spinner areaSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_location_selection_layout);

        //get the reference to the city spinner view
        citySpinner=(Spinner)findViewById(R.id.startup_city_selection_spinner);
        areaSpinner=(Spinner)findViewById(R.id.startup_area_selection_spinner);

        //disable until each spinner is loaded with data
        citySpinner.setEnabled(false);
        areaSpinner.setEnabled(false);

        //first get list of cites from the server
        requestCityData("http://farmzop.com/app_cities");

        Button startShoppingBtn=(Button)findViewById(R.id.start_shopping_button);
        startShoppingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isLocationSelected(citySpinner,areaSpinner))
                {
                    Toast.makeText(getApplicationContext(), "Start Shopping button clicked.", Toast.LENGTH_SHORT).show();

                    //change the app activity to the main activity
                    startMainActivity();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Select the Locality.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


void startMainActivity()
{

    Intent intent = new Intent(LocalitySelectionActivity.this,MainActivity.class);
    startActivity(intent);
}

    /**Sends data from server using volley api and recieves too
     *@param url this is link for the connection to be established(universal resource locator)
     */
    private void requestData(String url) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("city","Bangalore");

        JSONObject jsonObject=new JSONObject(params);
        // Toast.makeText(getActivity(),jsonObject.toString(),Toast.LENGTH_LONG).show();
        Log.d("json", jsonObject.toString());
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("JsonRP", response.toString());
                       // Toast.makeText(getApplication(), response.toString(), Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        RequestQueue queue = Volley.newRequestQueue(getApplication());
        queue.add(jsonObjectRequest);

    }

    /**Requests data from server using volley api
     *@param url this is link for the connection to be established(universal resource locator)
     */
    private void requestCityData(String url) {

        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Json", response.toString());
                        //Toast.makeText(getApplication(), response.toString(), Toast.LENGTH_LONG).show();
                        //initialize the city select spinner
                        initCitySelectSpinner(CustomJsonParsers.parseCityList(response));

                    }

                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplication());
        queue.add(request);
    }

    /**Requests data from server using volley api
     *@param url this is link for the connection to be established(universal resource locator)
     */
    private void requestAreaData(String url) {

        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Json", response.toString());
                        //Toast.makeText(getApplication(), response.toString(), Toast.LENGTH_LONG).show();
                        //initialize the city select spinner
                        initAreaSelectSpinner(CustomJsonParsers.parseAreaList(response));

                    }

                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplication());
        queue.add(request);
    }


    private void initCitySelectSpinner(ArrayList<String> list) {

        String[] cities = list.toArray(new String[list.size()]);

        String defaultText=cities[0];

        //initialize the adapter for it
        CustomSpinnerAdapter spinnerAdapter=new CustomSpinnerAdapter(this, R.layout.dropdown_spinner_row,cities,defaultText);
        //set the adapter
        citySpinner.setAdapter(spinnerAdapter);

        //enable it
        citySpinner.setEnabled(true);

        //set on item Select listener on the city Spinner
         citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                /* Toast.makeText(adapterView.getContext(),
                         "OnItemSelectedListener : " + adapterView.getItemAtPosition(i).toString(),
                         Toast.LENGTH_SHORT).show();*/

                 //get the details for area of the city selected
                 String tmpCity = adapterView.getItemAtPosition(i).toString();

                 if(tmpCity!="Select City") {
                     //get localities from the server
                     requestAreaData("http://farmzop.com/app_location/" + tmpCity);
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
         });




    }

    private void initAreaSelectSpinner(ArrayList<String> list) {

        String[] area = list.toArray(new String[list.size()]);

        String defaultText="Select Area";

        //initialize the adapter for it
        CustomSpinnerAdapter areaSpinnerAdapter=new CustomSpinnerAdapter(this, R.layout.dropdown_spinner_row,area,defaultText);

        //set the adapter
        areaSpinner.setAdapter(areaSpinnerAdapter);

        //enable it
           areaSpinner.setEnabled(true);




    }

    /**
     * Checks if the Locality selection is completly filled
     * @param city consists city spinner reference
     * @param area consists area spinner reference
     * @return true if all items selected , else false
     */

    boolean isLocationSelected(Spinner city,Spinner area)
    {
        Log.d("LocSel",city.getSelectedItem().toString());
        Log.d("LocSel",area.getSelectedItem().toString());
        if(city.getSelectedItem().toString()=="Select City"||area.getSelectedItem().toString()=="Select Area")
            return false;

        return true;
    }


}
