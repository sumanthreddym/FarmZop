package com.farmzop.application;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmzop.application.NetworkController.NetworkStatus;
import com.farmzop.application.ProductDetailsAdapter.ProductDetailsListAdapter;
import com.farmzop.application.ProductDetailsAdapter.ProductItemType1;
import com.farmzop.application.JsonParserAndPackager.CustomJsonParsers;


import java.util.ArrayList;
import java.util.List;

//TODO: reinitialize the activity on resume so that changes in the cart activity gets affected in the main activity
public class TabSubFragment1 extends Fragment {
  /*  String[] menutitle;
    TypedArray menuIcon;
    String[] menudesc;
    String[] menuonedesc;
    String[] menutwodesc;


    TypedArray menuonebutton;
    TypedArray menutwobutton;
    String[] menubuttondesc;
    String[] menubag;
*/
   private ProgressBar loadingCircle;
    ProductDetailsListAdapter adapter;
    private List<ProductItemType1> rowItems;

    //retry button ImageView
    ImageView retry_btn;

   View view;
    LayoutInflater inflater;
    ViewGroup container;

    //used to display the list
    ListView listItemsView;

    //get reference to cart count badge
    RelativeLayout cartBottomLayout;

    //Url to get the details of the products in json format
    final private String URL = "http://farmzop.com";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     this.inflater=inflater;
     this.container=container;
        if(NetworkStatus.isOnline(getActivity()))
            {
                this.view = inflater.inflate(R.layout.tab_sub_fragment_1, container, false);
               cartBottomLayout=(RelativeLayout)getActivity().findViewById(R.id.cart_bottom_drawer);

                listItemsView=(ListView)view.findViewById(R.id.sub_fragment_one_list);
                rowItems = new ArrayList<>();

                initLoading();
                requestData(URL);
            }
            else
            {
                this.view = inflater.inflate(R.layout.no_network_connection_layout, container, false);
            }
        return this.view;
    }
/**
 * Display the loading spinner layout and hides other details
 */
    private void initLoading() {
        listItemsView.setVisibility(View.GONE);
        loadingCircle=(ProgressBar)view.findViewById(R.id.loading_circle);
        loadingCircle.setVisibility(View.VISIBLE);

        //change the cart badge count
        //updatecartBadgeCountDisplay(20);


        //change the color of spinner progress to color code #58c672(green)
       /* spinnerProgress.getIndeterminateDrawable().setColorFilter(0xFF58c672,
                android.graphics.PorterDuff.Mode.MULTIPLY);
      */
    }


  /*  @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);


/*
        menutitle = getResources().getStringArray(R.array.titles);
        menuonedesc = getResources().getStringArray(R.array.onedescs);
        menutwodesc = getResources().getStringArray(R.array.twodescs);
        menuIcon = getResources().obtainTypedArray(R.array.icons);
        menudesc = getResources().getStringArray(R.array.descs);
        menubuttondesc = getResources().getStringArray(R.array.buttondescs);


        menuonebutton = getResources().obtainTypedArray(R.array.onebuttons);
        menubag = getResources().getStringArray(R.array.bags);
        menutwobutton = getResources().obtainTypedArray(R.array.twobuttons);
*/



        /*
        for (int i = 0; i < menutitle.length; i++) {
            ProductItemType1 items = new ProductItemType1(menutitle[i],menuonedesc[i],menutwodesc[i],menuIcon.getResourceId(
                    i, -1),menudesc[i],menubuttondesc[i],
                    menuonebutton.getResourceId(
                    i, -1),menutwobutton.getResourceId(
                    i, -1),menubag[i]);

            rowItems.add(items);
        }*/

        //request data from the server



    //if theres no internet connection
    // Toast.makeText(getActivity(), "Network Not Available...Pls check ur settings and try again..", Toast.LENGTH_LONG).show();*/



    /**Requests data from server using volley api
     *@param url this is link for the connection to be established(universal resource locator)
     */
    private void requestData(String url) {

        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Json", response.toString());

                        //call the server and get the data
                        rowItems = CustomJsonParsers.parseProductObject(response);
                        //display the contents
                        updateDisplay();
                    }

                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
             }
        });

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

    /**
     * Updates the items (Display)after receiving  successfull response from the server
     */
    private void updateDisplay() {

        adapter = new ProductDetailsListAdapter(getActivity(), rowItems,cartBottomLayout);

        //hide the progress circle
        loadingCircle.setVisibility(View.GONE);

        //display the data in list
        listItemsView.setVisibility(View.VISIBLE);

        listItemsView.setAdapter(adapter);

    }

/**
 * This function check whether the the shopping cart is empty or not
 * @return true if cart empty
 * @return false if cart is not empty
 */
boolean isShoppingCartEmpty()
{
return false;
}



    @Override
    public void onResume() {
            super.onResume();

        int cartSize=SharedPreferencesHelper.getNumCartItemsSharedPref(getActivity());

        if(cartSize==0)
            cartBottomLayout.setVisibility(View.GONE);

        if(adapter!=null)
        {
        //update the product list after changes in cart
        listItemsView.setAdapter(adapter);
        }
    }

}


