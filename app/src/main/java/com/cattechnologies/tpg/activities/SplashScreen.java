package com.cattechnologies.tpg.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.cattechnologies.tpg.utils.Constants;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.utils.PreferencesManager;

/**
 * Created by Ajay on 9/29/2017.
 */

public class SplashScreen extends Activity {
    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        preferencesManager = new PreferencesManager();
        if (preferencesManager.getUserId(getApplicationContext()) != null) {
            System.out.println("SplashScreen.onCreate===isLOging" + preferencesManager.getUserId(getApplicationContext()));
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
            finish();
        } else {
            System.out.println("SplashScreen.onCreate===is not login==");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, ViewPagerActivity.class);
                    startActivity(i);
                    finish();
                }
            }, Constants.SPLASH_TIME_OUT);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finish();
    }
}

