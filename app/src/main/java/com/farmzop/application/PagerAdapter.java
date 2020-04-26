package com.farmzop.application;

import com.farmzop.application.AccountFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {

        switch (position) {
            case 0:
                TabFragment1 tab1 = new TabFragment1();
                return tab1;
            case 1:
                NonFarmzopProductsFragment tab2 = new NonFarmzopProductsFragment();
                return tab2;
            case 2:
                OffersFragment tab3 = new OffersFragment();
                return tab3;
            case 3:
                OrdersFragment tab4 = new OrdersFragment();
                return tab4;
            case 4:
                AccountFragment tab5 = new AccountFragment();
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


