package com.farmzop.application.NetworkController;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Gaurav on 11-03-2016.
 */
public class NetworkStatus {

    /**This method is used to check whether network is active or not
     *@param context current context of application
     * @return true if internet connection active
     * @return false if no internet connection
     */
    public static boolean isOnline(Context context)
    {
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if(info!=null&&info.isConnectedOrConnecting())
            return true;
        else
            return false;

    }
}
