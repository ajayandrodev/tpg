package com.cattechnologies.tpg.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.viewHolderData.AnalyticsApplication;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class MainActivity extends FragmentActivity implements View.OnClickListener {


    private static final String TAG = "screen tracker";
    Button getStarted;
    Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getStarted = (Button) findViewById(R.id.main_get_started);
        getStarted.setOnClickListener(this);
        // Obtain the shared Tracker instance.
      /*  AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
*/
    }

   /* @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Setting screen name: " + getPackageName());
        mTracker.setScreenName("Image~" + getPackageName());
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
      *//*  mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());*//*

    }*/

    @Override
    public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, LoginScreen.class);
        startActivity(i);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
