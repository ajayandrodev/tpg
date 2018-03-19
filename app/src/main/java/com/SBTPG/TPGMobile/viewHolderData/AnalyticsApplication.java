package com.SBTPG.TPGMobile.viewHolderData;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.SBTPG.TPGMobile.R;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Ajay on 10/6/2017.
 */

public class AnalyticsApplication extends MultiDexApplication {
    private static AnalyticsApplication instance;
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        instance = this;
        sAnalytics = GoogleAnalytics.getInstance(this);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Lato-Regular.ttf");
    }

    synchronized public Tracker getDefaultTracker() {
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }
        return sTracker;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static AnalyticsApplication getInstance(){
        return instance;
    }


}