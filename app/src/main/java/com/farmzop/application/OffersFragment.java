package com.farmzop.application;

import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.farmzop.application.OffersAdapter.OffersItem;
import com.farmzop.application.OffersAdapter.OffersItemType1;
import com.farmzop.application.OffersAdapter.OffersItemType2;
import com.farmzop.application.OffersAdapter.OffersItemTypeMain;
import com.farmzop.application.OffersAdapter.OffersListAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gaurav on 29-02-2016.
 */
public class OffersFragment extends Fragment{

    private static final Integer[] IMAGES= {R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4};
    private ArrayList<Integer> ImagesArray = new ArrayList<>();

    private ArrayList<OffersItem> offersItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.offers_layout, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        offersItems=new ArrayList<>();
        Bitmap offerDay= BitmapFactory.decodeResource(getResources(), R.drawable.g3);

        //offers
        Bitmap img1= BitmapFactory.decodeResource(getResources(), R.drawable.offer1);
        Bitmap img3= BitmapFactory.decodeResource(getResources(),R.drawable.offer3);
        Bitmap img4= BitmapFactory.decodeResource(getResources(),R.drawable.dealoftheday);

        //for grid display
        Bitmap img5= BitmapFactory.decodeResource(getResources(),R.drawable.goffer3);
        Bitmap img6= BitmapFactory.decodeResource(getResources(),R.drawable.goffer2);


        offersItems.add(0,new OffersItemTypeMain(103,img4));
        offersItems.add(1,new OffersItemType1(111,offerDay));
        offersItems.add(2,new OffersItemType2(109,img5,img6));
        offersItems.add(3,new OffersItemType1(101,img1));
        offersItems.add(4,new OffersItemType1(102,img3));


       OffersListAdapter offersListAdapter=new OffersListAdapter(getActivity(),offersItems);

        //setting up adapter for listView
        ListView list = (ListView)getActivity().findViewById(R.id.offers_list);
        list.setAdapter(offersListAdapter);


    }

    /**Sends data from server using volley api
     *@param url this is link for the connection to be established(universal resource locator)
     */
    private void sendData(String url) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("tag", "Sent data ,Recieved it too..");

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(jsonObjectRequest);

    }


}
