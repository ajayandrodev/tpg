package com.cattechnologies.tpg.fragments.checksToPrintReport;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.activities.Dashboard;
import com.cattechnologies.tpg.fragments.accountDisbursementsReport.ReportsAccountDisbServiceBuroFragment;

/**
 * Created by admin on 1/23/2018.
 */

public class EmptyCheckToPrintFragment extends Fragment {
    public static final String ARG_SECTION_TITLE = "section_number";
    String title = "", srTitle = "";
    TextView sbEro, titulo;
    String userId, userType;

    public static Fragment newInstance(String sectionTitle, String userId, String accountType) {
        EmptyCheckToPrintFragment fragment = new EmptyCheckToPrintFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        args.putString("app_uid", userId);
        args.putString("acc_type", accountType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Dashboard) getActivity()).setTitle("REPORTS");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_chect_to_print_fragment, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        srTitle = getArguments().getString(ARG_SECTION_TITLE);
        titulo = (TextView) getActivity().findViewById(R.id.title);
        titulo.setText(srTitle);
        userId = getArguments().getString("app_uid");
        userType = getArguments().getString("acc_type");
    }
}
