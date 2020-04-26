package com.farmzop.application;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmzop.application.JsonParserAndPackager.CustomJsonParsers;
import com.farmzop.application.OrdersDisplayAdapter.OrderDetails;
import com.farmzop.application.OrdersDisplayAdapter.OrderDetailsCancelled;
import com.farmzop.application.OrdersDisplayAdapter.OrderDetailsNormal;
import com.farmzop.application.OrdersDisplayAdapter.OrdersListAdapter;

import java.util.ArrayList;

/**
 * Created by Gaurav on 06-01-2016.
 *
 * This is Orders Fragment
 */
public class OrdersFragment extends Fragment {

    ArrayList<OrderDetails> orderDetails;
    private final String ordersURL="http://www.farmzop.com/app/orders.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.orders_layout, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        /*sample input*/
        orderDetails=new ArrayList<>();
        orderDetails.add(0,new OrderDetailsNormal(78945652,"27 July 2015",250," Online Payment",true,false,false));
        orderDetails.add(1,new OrderDetailsNormal(78945662,"27 July 2016",350," Online Payment",true,true,false));
        orderDetails.add(2,new OrderDetailsCancelled(78945456,"27 Aug 2015",450," Cash On Delivery"));
        orderDetails.add(3,new OrderDetailsNormal(12345652,"27 Dec 2015",380," Online Payment",false,false,false));
        orderDetails.add(4,new OrderDetailsNormal(78888852,"27 March 2015",950," Cash on Delivery",true,true,true));

        OrdersListAdapter ordersListAdapter=new OrdersListAdapter(getActivity(),orderDetails);

        //setting up adapter for listView
        ListView list = (ListView)getActivity().findViewById(R.id.orders_list);
        list.setAdapter(ordersListAdapter);


    }

    /**Requests data from server using volley api
     *@param url this is link for the connection to be established(universal resource locator)
    **/
    private void requestData(String url) {

        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //call the server and get the data
                        orderDetails = CustomJsonParsers.parseOrdersObject(response);
                        //display the contents
                       // updateDisplay();
                    }

                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

}
