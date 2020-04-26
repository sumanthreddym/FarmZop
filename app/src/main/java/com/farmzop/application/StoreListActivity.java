package com.farmzop.application;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.farmzop.application.StoreListAdapter.ListStoreDetailsItem;
import com.farmzop.application.StoreListAdapter.StoreItemsAdapter;
import com.google.android.gms.maps.model.LatLng;


import java.util.ArrayList;

/**
 * Created by Gaurav on 07-02-2016.
 */
public class StoreListActivity extends AppCompatActivity{

    Bitmap sampleImage;

    ArrayList<ListStoreDetailsItem> listStoreDetailsItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.store_list_layout);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.backwhite);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //create the sample image
        sampleImage = BitmapFactory.decodeResource(getResources(),R.drawable.store);

        //temp pos
        LatLng pos=new LatLng(12.5,77.5);

        //initialize the list store items
        listStoreDetailsItem = new ArrayList<>();
        listStoreDetailsItem.add(0,new ListStoreDetailsItem(100,sampleImage,
                "Marathahalli","#56, 5th Cross, Ashwath Nagar\nMarathahalli\nBangalore - 560037","9am to 10pm",778945612,new LatLng(12.9477297,77.7000652)));
        listStoreDetailsItem.add(1,new ListStoreDetailsItem(100,sampleImage,
                "Bellandur","#56, 1st Cross, Sanjay Nagar\nBellandur\nBangalore - 560049","9am to 10pm",778941112,new LatLng(12.9263546,77.6759305)));

        listStoreDetailsItem.add(2,new ListStoreDetailsItem(100,sampleImage,
                "Domlur","#66, 9th Cross, Karthik Nagar\nDomlur\nBangalore - 560089","9am to 10pm",778785612,new LatLng(12.9630376,77.6268656)));

        listStoreDetailsItem.add(3,new ListStoreDetailsItem(100,sampleImage,
                "HSR Layout","#156, 8th Cross, Ashwath Nagar\nHSR Layout\nBangalore - 560025","9am to 10pm",888945612,new LatLng(12.9102997,77.6281508)));


        //initiallize the Store Item adapter
        StoreItemsAdapter storeItemsAdapter=new StoreItemsAdapter(getApplicationContext(),0, listStoreDetailsItem);


        //get the list view to display the items
        ListView list = (ListView)findViewById(R.id.store_list);
        list.setAdapter(storeItemsAdapter);

    }
}
