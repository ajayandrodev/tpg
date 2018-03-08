package com.cattechnologies.tpg.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Ajay on 11/17/2017.
 */

public class AppInternetStatus {
    private static AppInternetStatus instance = new AppInternetStatus();
    static Context context;
    ConnectivityManager connectivityManager;
    boolean connected = false;

    public static AppInternetStatus getInstance(Context ctx) {
        context = ctx.getApplicationContext();
        return instance;
    }

    public boolean isOnline() {
        try {
            connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;


        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
        return connected;
    }
}
