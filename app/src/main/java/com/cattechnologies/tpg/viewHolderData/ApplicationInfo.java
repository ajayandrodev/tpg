package com.cattechnologies.tpg.viewHolderData;

import android.app.Application;

import de.greenrobot.event.EventBus;

/**
 * Created by admin on 10/6/2017.
 */

public class ApplicationInfo extends Application {

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
}