package com.farmzop.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Gaurav on 20-03-2016.
 */

//TODO: app crashes sometime because of shared preferences
public class SharedPreferencesHelper {


    /**
     * get the number items in the cart through shared preferences
     */
    public static int getNumCartItemsSharedPref(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        int temp = sharedPref.getInt("NumCartItems", 0);

        return temp;
    }


    /**
     * set the number items in the cart through through shared prefs
     */
    public static void setNumCartItemsSharedPref(Context context,int n)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("NumCartItems",n);
        editor.commit();
    }

    /**
     * get the value items in the cart through shared preferences
     */
    public static int getValCartItemsSharedPref(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        int temp = sharedPref.getInt("ValCartItems", 0);

        return temp;
    }


    /**
     * set the value items in the cart through through shared prefs
     */
    public static void setValCartItemsSharedPref(Context context,int n)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("ValCartItems",n);
        editor.commit();
    }
}
