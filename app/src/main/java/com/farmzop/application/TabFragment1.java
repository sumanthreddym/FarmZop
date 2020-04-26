package com.farmzop.application;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

public class TabFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.tab_fragment_1, container, false);

        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tab_frag2_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Wheat"));
        tabLayout.addTab(tabLayout.newTab().setText("Rice"));
        tabLayout.addTab(tabLayout.newTab().setText("Pulses"));
        tabLayout.addTab(tabLayout.newTab().setText("Cereals"));
        tabLayout.addTab(tabLayout.newTab().setText("Spices"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) root.findViewById(R.id.pager2);
        final Pager2Adapter adapter = new Pager2Adapter
                (getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return root;
    }

}
