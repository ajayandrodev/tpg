package com.cattechnologies.tpg.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cattechnologies.tpg.adapters.SlidingImage_Adapter;
import com.cattechnologies.tpg.viewHolderData.CirclePageIndicator;
import com.cattechnologies.tpg.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 11/1/2017.
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
      /*  for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);
*/
        mPager = (ViewPager) findViewById(R.id.pager);
        adapter = new SlidingImage_Adapter(this);

        mPager.setAdapter(adapter);

        //  mPager.setAdapter(new SlidingImage_Adapter(ViewPagerActivity.this, ImagesArray));


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
             /*   if (adapter.getCount()==currentPage){
                    currentPage = 0;
                    System.out.println("ViewPagerActivity.run====ada=="+adapter.getCount()+"==="+currentPage);
                }else {
                    mPager.setCurrentItem(currentPage++, true);
                    System.out.println("ViewPagerActivity.run==="+mPager.getCurrentItem());

                }*/
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                    System.out.println("ViewPagerActivity.run===" + currentPage);
                }
                mPager.setCurrentItem(currentPage++, true);
                System.out.println("ViewPagerActivity.run===sss==" + mPager.getCurrentItem());
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("ViewPagerActivity.run===timer" + Update);
                handler.post(Update);
            }
        }, 1000, 1000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                System.out.println("ViewPagerActivity.onPageSelected");

                if (position == 3) {
                    swipeTimer.cancel();

                    indicator.setVisibility(View.GONE);
                    System.out.println("ViewPagerActivity.onPageSelected===eee==" + currentPage);

                } else {
                    indicator.setVisibility(View.VISIBLE);
                    System.out.println("ViewPagerActivity.onPageSelected===erer====" + currentPage);

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