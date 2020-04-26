package com.farmzop.application;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.farmzop.application.CartDBHelper.CartDBAdapter;
import com.farmzop.application.ProductDetailsAdapter.ProductDetailsListAdapter;
import com.farmzop.application.ProductDetailsAdapter.ProductItemType1;

import java.sql.SQLException;
import java.util.ArrayList;

public class TabSubFragment3 extends Fragment {

    CartDBAdapter mDBHelper;
    //get reference to cart count badge
    RelativeLayout cartBottomLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_sub_fragment_3, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();

        mDBHelper = new CartDBAdapter(getActivity());
        try {
            mDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

       //mDBHelper.deleteAllProducts();

        /*
        mDBHelper.createProduct(11, "Rice 1", "20", "15", "Ashirvad", "2", R.drawable.rice);
        //mDBHelper.createProduct(21, "Rice 2", "22", "18","Ashirvad","8", R.drawable.rice);
        //mDBHelper.createProduct(61,"Rice 6","30","25","Ashirvad","2", R.drawable.rice);
        mDBHelper.createProduct(71,"Wheat 1","25","20","Old","2", R.drawable.wheat);
        mDBHelper.createProduct(81,"Wheat 2","35","30", "Ashirvad","2",R.drawable.wheat);
        */

        cartBottomLayout=(RelativeLayout)getActivity().findViewById(R.id.cart_bottom_drawer);

        ArrayList<ProductItemType1>  cartItems=mDBHelper.getAllCartItems();
        ListView listItemsView=(ListView)getView().findViewById(R.id.sub_fragment_three_list);

        TextView  cartCountBadge=(TextView)getActivity().findViewById(R.id.cart_count);
        ProductDetailsListAdapter adapter = new ProductDetailsListAdapter(getActivity(), cartItems,cartBottomLayout);

         listItemsView.setAdapter(adapter);
        Log.d("cart", listItemsView.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mDBHelper != null){
            mDBHelper.close();
        }
    }


}