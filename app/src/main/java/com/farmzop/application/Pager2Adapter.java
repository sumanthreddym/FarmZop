package com.farmzop.application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Pager2Adapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public Pager2Adapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabSubFragment1 tab1 = new TabSubFragment1();
                return tab1;
            case 1:
                TabSubFragment2 tab2 = new TabSubFragment2();
                return tab2;
            case 2:
                TabSubFragment3 tab3 = new TabSubFragment3();
                return tab3;
            case 3:
                TabSubFragment4 tab4 = new TabSubFragment4();
                return tab4;
            case 4:
                TabSubFragment5 tab5 = new TabSubFragment5();
                return tab5;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

