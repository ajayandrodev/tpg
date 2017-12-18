package com.cattechnologies.tpg.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.cattechnologies.tpg.R;


/**
 * Created by admin on 10/10/2017.
 */

public class ForgotPasswordUserDetails extends Fragment {
    public static final String ARG_SELECTION_USER = "secleted_user_forgot";

    public static ForgotPasswordUserDetails newInstance(String forgotPasswordData) {
        ForgotPasswordUserDetails fragment = new ForgotPasswordUserDetails();
        Bundle args = new Bundle();
        args.putString(ARG_SELECTION_USER, forgotPasswordData);
        fragment.setArguments(args);
        return fragment;
    }

    public ForgotPasswordUserDetails() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_forgot_info, container, false);


        return view;
    }


}
