package com.farmzop.application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity  {
    private ViewPager viewPager;
    private Toolbar toolbar;
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle drawerToggle;



    private RelativeLayout cartBottomLayout;
    private TextView cartCountText;
    private TextView cartValueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       initMainAppLayout();

    }



    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, dlDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //used to start search activity
        if (id == R.id.action_search) {
            openSearchActivity();
            return true;
        }
        //used to start StoreLocator activity
        if (id == R.id.action_location) {
           openStoreLocatorActivity();
            return true;
        }
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:

                dlDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    // Make sure this is the method with just `Bundle` as the signature
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();

    }
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        Fragment fragment = null;
        switch(menuItem.getItemId()) {
            case R.id.first_fragment:
                viewPager.setCurrentItem(0);
                break;
            case R.id.second_fragment:
                viewPager.setCurrentItem(1);
                break;
            case R.id.third_fragment:
                viewPager.setCurrentItem(2);
                break;
            case R.id.four_fragment:
                viewPager.setCurrentItem(3);
                break;
            case R.id.five_fragment:
                viewPager.setCurrentItem(4);
                break;
            case R.id.seven_fragment:
                    rateUsDialog();
                break;
            case R.id.eight_fragment:
                FAQIntent();
                break;
            case R.id.nine_fragment:
                //creates a share app intent
                 shareAppIntent();
                break;
            case R.id.ten_fragment:
                //creates a share app intent
                AboutFZIntent();
                break;


        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContent, fragment);
            ft.commit();
        }


        dlDrawer.closeDrawer(GravityCompat.START);

    }

    /**
     * This method is used to create a FAQs Intent
     */
    private void FAQIntent(){
        Intent intent = new Intent(this, FAQsActivity.class);
        startActivity(intent);
    }
    private void AboutFZIntent(){
        Intent intent = new Intent(this, AboutFZ.class);
        startActivity(intent);
    }
     private void shareAppIntent() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "FarmZop is the india's only platform to buy and sell goods directly from farmers.\n" +
                "Download Now from Play Store:" +
                "\nhttps://play.google.com";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "FarmZop");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    /**
     * Used to create Rate us Dialog box
     */
    private void rateUsDialog()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setTitle("Rate this app");
        alertDialogBuilder.setMessage("If you enjoy this app, please take a moment to rate this app.");

        alertDialogBuilder.setPositiveButton("Rate now",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {

            }
        });

        alertDialogBuilder.setNegativeButton("Remind later",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {

            }
        });

        alertDialogBuilder.setNeutralButton("No, thanks", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }

    /**
     * get the number items in the cart through shared preferences
     */
    private int getSharedPref(String tag) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int temp = sharedPref.getInt(tag, 0);

        return temp;
    }


    /**
     * set the number items in the cart through through shared prefs
     */
    private void setSharedPref(String tag,int n)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(tag,n);
        editor.commit();
    }

    void hideBottomCartLayout()
    {

        cartBottomLayout.setVisibility(View.GONE);
    }
void checkBottomCartVisibility()
{
    int numCartItems=SharedPreferencesHelper.getNumCartItemsSharedPref(getApplicationContext());
    int numCartPrice=SharedPreferencesHelper.getValCartItemsSharedPref(getApplicationContext());

    //set the text for cart counter
    cartCountText=(TextView)findViewById(R.id.cart_count);
    //set the cart value
    cartValueText=(TextView)findViewById(R.id.total_cart_amount);

    if(numCartItems==0)
    {
        hideBottomCartLayout();
    }
    else
    {
        cartCountText.setText(String.valueOf(numCartItems));
        cartValueText.setText(String.valueOf(numCartPrice));
    }
}

    void initMainAppLayout()
    {
        setContentView(R.layout.activity_main);

        //next time start with main app layout
        //setSharedPref("AppStartLayoutType",1);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Find our drawer view

        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);
        // Find our drawer view
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        //find the bottom cart view
        cartBottomLayout=(RelativeLayout)findViewById(R.id.cart_bottom_drawer);

        //set OnclickListener to The bottom cart layout
        cartBottomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCartActivity();
            }
        });


        // Tie DrawerLayout events to the ActionBarToggle
        dlDrawer.setDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(false);
        // toolbar.setNavigationIcon(R.drawable.ic_drawer);

        drawerToggle = new ActionBarDrawerToggle(this,
                dlDrawer,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        // Set the drawer toggle as the DrawerListener
        dlDrawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_selector1));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_selector2));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_selector3));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_selector4));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_selector5));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        toolbar.setTitle("Farmzop Exclusives");
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        toolbar.setTitle("Non Farmzop Products");
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        toolbar.setTitle("Offers");
                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        toolbar.setTitle("Orders");
                        break;
                    case 4:
                        viewPager.setCurrentItem(4);
                        toolbar.setTitle("Profile");
                        break;

                    default:

                        viewPager.setCurrentItem(tab.getPosition());
                        toolbar.setTitle("Farmzop Exclusives");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    /**
     * Opens a new Screen Window to display the cart Items
     */
    private void openCartActivity() {

        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);

    }


    public void openSearchActivity()
    {
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);

    }

    public void openStoreLocatorActivity()
    {
        Intent intent = new Intent(this,StoreLocatorActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //check if the bottom cart  visibility
        checkBottomCartVisibility();
    }
}