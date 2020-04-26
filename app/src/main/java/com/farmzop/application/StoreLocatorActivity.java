package com.farmzop.application;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class StoreLocatorActivity extends AppCompatActivity {

    RelativeLayout progressLayout;
    RelativeLayout findStoreLayout;
    Button findStores;
    Button listStores;

    LocationListener locationListner;
    LocationManager locationManager;

    LatLng currentUserLocation;

    TextView or_text;

    TextView info_text1;
    TextView info_text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_locator_layout);


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


        //get references to the views for  or text and progressLayout
       progressLayout=(RelativeLayout)findViewById(R.id.waiting_contents);
       or_text=(TextView)findViewById(R.id.or_text);

        //get reference to findstore button

        //set the find stores button enabled
        findStores= (Button)findViewById(R.id.button_nearest_store);

        //get references to the info texts bellow the  buttons
        info_text1=(TextView)findViewById(R.id.findstores_infotext);
        info_text2=(TextView)findViewById(R.id.liststores_infotext);

        //initially display store locate options
        displayStoreLocateOptions();

       findStoreLayout=(RelativeLayout)findViewById(R.id.store_finder_layout);

        //set up the location manager
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);



        findStores=(Button)findViewById(R.id.button_nearest_store);
        listStores=(Button)findViewById(R.id.button_offline_store);

               //set onclicklisteners for the find stores button
        findStores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Find Stores Clicked",Toast.LENGTH_SHORT).show();

                //check if already location listner already set up
                if(locationListner!=null) {
                    locationManager.removeUpdates(locationListner);
                }

                 //get the current location via the GPS
                  getCurrentLocation();




            }
        });

        //set onclicklisteners for the list stores button
        listStores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //do something
                Toast.makeText(getApplicationContext(),"List Stores Clicked",Toast.LENGTH_SHORT).show();

                //chamge the screen to store list activity
                Intent intent=new Intent(StoreLocatorActivity.this,StoreListActivity.class);
                startActivity(intent);

            }
        });

    }

    /**
     * This function is used to get the current location through gps
     */
    void getCurrentLocation()
    {


        //check if GPS is already Enabled
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            //to display the progress bar only if provider is enabled
            displayProgress();
        }


        //set the location Listner
        locationListner=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.d("CurLoc", location.getLatitude() + ", " + location.getLongitude());

                //set the current location
                currentUserLocation = new LatLng(location.getLatitude(), location.getLongitude());

                //change the activity to nearest store activity once gotr the location through gps
                //change the screen to store list activity
                Intent intent=new Intent(StoreLocatorActivity.this,NearestStoreActivity.class);
                //used to send user location to the other activity
                intent.putExtra("UserLatitude",location.getLatitude());
                intent.putExtra("UserLongitude",location.getLongitude());
                startActivity(intent);


                locationManager.removeUpdates(locationListner);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

               //to display the progress bar only if provider is enabled
                displayProgress();

            }

            @Override
            public void onProviderDisabled(String provider) {

                Intent intent=new Intent((Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                startActivity(intent);

                //display find store select options
                displayStoreLocateOptions();
            }
        };


        //request for current location only once
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListner);



    }

    /**
     * This function is used to display the progress bar
     */
    void displayProgress()
    {
        //remove the or text
         or_text.setVisibility(View.GONE);

        //display the progress bar layout
        progressLayout.setVisibility(View.VISIBLE);

        //don't display info texts
        info_text1.setVisibility(View.GONE);
        info_text2.setVisibility(View.GONE);

        //set the find stores button enabled
        findStores.setEnabled(false);


        /*
        TextView wait_text=(TextView)findViewById(R.id.waiting_long_text);

        progressLayout.setVisibility(View.VISIBLE);

        //remove the list store button from findStoreLayout
        findStoreLayout.removeView(listStores);

        // create the layout params that will be used to define how your
        // button will be displayed
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


        params.addRule(RelativeLayout.BELOW, wait_text.getId());
        // add the rule that places your button below your EditText object
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        //add top marign
        params.setMargins(0,50,0,0);

        // set the layoutParams on the button
        listStores.setLayoutParams(params);

        //add this view to progressLayout
        progressLayout.addView(listStores);

        //remove click event from find stores button
        findStores.setOnClickListener(null);
        */
    }


    /**
     * TODO:Once gps is switched on an we switch screens and then return back the gps remains onn,need to rectify it..
     */

    void displayStoreLocateOptions()
    {
        //remove the or text
        or_text.setVisibility(View.VISIBLE);

        //display the progress bar layout
        progressLayout.setVisibility(View.INVISIBLE);

        //display info texts
        info_text1.setVisibility(View.VISIBLE);
        info_text2.setVisibility(View.VISIBLE);


        //set the find stores button disabled
        findStores.setEnabled(true);

    }
}
