package com.farmzop.application;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.ArrayList;

import com.farmzop.application.NonFarmzopProductAdapter.ProductInfo;
import com.farmzop.application.NonFarmzopProductAdapter.ProductListAdapter;

/**
 * Created by Gaurav on 06-01-2016.
 */
public class NonFarmzopProductsFragment extends Fragment {


    ArrayList<ProductInfo> productInfo;
    public NonFarmzopProductsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          View rootView = inflater.inflate(R.layout.non_farmzop_products_layout, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        productInfo=new ArrayList<>();
        Bitmap img1= BitmapFactory.decodeResource(getResources(),R.drawable.g3);
        Bitmap img2= BitmapFactory.decodeResource(getResources(),R.drawable.g4);
        Bitmap img3= BitmapFactory.decodeResource(getResources(),R.drawable.g1);

        productInfo.add(0,new ProductInfo("SPICES",img1));
        productInfo.add(1,new ProductInfo("BEVERAGES",img2));
        productInfo.add(2,new ProductInfo("BRANDED PRODUCTS",img3));
        productInfo.add(3,new ProductInfo("KETCHUP",img3));
        productInfo.add(4,new ProductInfo("COOL DRINKS",img2));

        ProductListAdapter productListAdapter=new ProductListAdapter(getActivity(),productInfo);

        //setting up adapter for listView
        ListView list = (ListView)getActivity().findViewById(R.id.non_farmzop_product_list);
        list.setAdapter(productListAdapter);


    }
}
