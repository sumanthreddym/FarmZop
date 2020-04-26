package com.farmzop.application;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.farmzop.application.FAQsAdapter.FAQsExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gaurav on 27-02-2016.
 */
public class FAQsActivity extends AppCompatActivity{

    FAQsExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setSharedPref("AppStartLayoutType", 0);
        //check which layout to display at the start

        setContentView(R.layout.faqs_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.faqs_list);

        // preparing list data
        prepareListData();

        listAdapter = new FAQsExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data i.e questions
        listDataHeader.add("How do I register ?");
        listDataHeader.add("Are there any charges for registration" + "?");
        listDataHeader.add("Do I have to necessarily register to " +
                "shop on your site ?");
        listDataHeader.add("Are there any delivery costs ?");
        listDataHeader.add("What are the modes of payment ?");

        // Adding child data
        List<String> ans1 = new ArrayList<String>();
        ans1.add("You can Register via your phone number.");

        List<String> ans2= new ArrayList<String>();
        ans2.add("There are no Registration charges..Its completely Free.");


        List<String> ans3 = new ArrayList<String>();
        ans3.add("Yes, Registration is required for shopping.");

        List<String> ans4 = new ArrayList<String>();
        ans4.add("Delivery is Free for a cart value of Rs. 250 and Above.");

        List<String> ans5 = new ArrayList<String>();
        ans5.add("COD and Online.");


        //create the hashmap
        listDataChild.put(listDataHeader.get(0), ans1); // Header, Child data
        listDataChild.put(listDataHeader.get(1),ans2);
        listDataChild.put(listDataHeader.get(2), ans3);
        listDataChild.put(listDataHeader.get(3), ans4);
        listDataChild.put(listDataHeader.get(4), ans5);
    }
}
