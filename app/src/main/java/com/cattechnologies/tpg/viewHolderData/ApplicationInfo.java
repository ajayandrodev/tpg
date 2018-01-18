package com.cattechnologies.tpg.viewHolderData;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import de.greenrobot.event.EventBus;

/**
 * Created by admin on 10/6/2017.
 */

public class ApplicationInfo extends MultiDexApplication {

/*
    http://www.coderefer.com/android-custom-font-entire-application/
*/
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/Lato-Regular.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Lato-Regular.ttf");
    }

    EventBus myEventBus = EventBus.getDefault();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}