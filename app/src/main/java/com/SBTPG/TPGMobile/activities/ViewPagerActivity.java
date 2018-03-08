package com.SBTPG.TPGMobile.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.SBTPG.TPGMobile.adapters.SlidingImage_Adapter;
import com.SBTPG.TPGMobile.viewHolderData.CirclePageIndicator;
import com.SBTPG.TPGMobile.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ajay on 11/1/2017.
 */

public class ViewPagerActivity extends FragmentActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static Integer[] IMAGES = {R.drawable.onboarding_bg_new, R.drawable.onboarding_bg_new,
            R.drawable.onboarding_bg_new, R.drawable.onboarding_bg_new};
    Display display;
    SlidingImage_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.viewpager_activity);
        init();
        display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // init();

    }

    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        adapter = new SlidingImage_Adapter(this);
        mPager.setAdapter(adapter);
        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setStrokeWidth(2);
        indicator.setRadius(8 * density);
        NUM_PAGES = IMAGES.length;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, 1000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                if (position == 3) {
                    swipeTimer.cancel();
                    indicator.setVisibility(View.GONE);
                } else {
                    indicator.setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {


            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });


    }


}