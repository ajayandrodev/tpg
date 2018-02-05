package com.cattechnologies.tpg.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.activities.LoginScreen;

import java.util.ArrayList;

/**
 * Created by admin on 11/1/2017.
 */

public class SlidingImage_Adapter extends PagerAdapter {


    private ArrayList<Integer> IMAGES;
    private LayoutInflater inflater;
    private Context context;
    private LayoutInflater layoutInflater;
    Button main;
    View one = null;
    View two = null;
    View three = null;
    View four = null;

    public SlidingImage_Adapter(Context context) {
        this.context = context;
    }

    int layoutes[] = {R.layout.view_one, R.layout.view_two, R.layout.view_three, R.layout.view_four};

    public SlidingImage_Adapter(Context context, ArrayList<Integer> IMAGES) {
        this.context = context;
        this.IMAGES = IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        //return IMAGES.size();
        return layoutes.length;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (position == 0) {
            one = layoutInflater.inflate(R.layout.view_one, view, false);

        } else if (position == 1) {
            two = layoutInflater.inflate(R.layout.view_two, view, false);

        } else if (position == 2) {
            three = layoutInflater.inflate(R.layout.view_three, view,false);

        } else if (position == 3) {
            four = layoutInflater.inflate(R.layout.view_four, view, false);
            main=four.findViewById(R.id.main_get_started);
            main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("SlidingImage_Adapter.onClick====="+position);
                    Intent intent= new Intent(context, LoginScreen.class);
                    context.startActivity(intent);
                }
            });
        }
        View viewarr[] = {one, two, three, four};
        view.addView(viewarr[position]);
        return viewarr[position];
        // return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}
