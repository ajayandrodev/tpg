package com.cattechnologies.tpg.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cattechnologies.tpg.Activities.Dashboard;
import com.cattechnologies.tpg.R;

/**
 * Created by ajay kumar on 28-Oct-17.
 */

public class ReportsCheckToPrintDetailsFragment extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    String title;
    TextView titulo, textReportFirstName, textReportSsn, textReportType, textReportTitleDetail, textReportOne, textReportOneData,
            textFiveNextAnother, textFiveNextAnotherData;
    RelativeLayout llSix, llFiveNext;
    LinearLayout llInfoData, llInfoDetailsData;


    public static ReportsCheckToPrintDetailsFragment newInstance(String sectionTitle) {
        ReportsCheckToPrintDetailsFragment fragment = new ReportsCheckToPrintDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public ReportsCheckToPrintDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Dashboard)getActivity()).setTitle("REPORTS");
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_checktoprint_details, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title = getArguments().getString(ARG_SECTION_TITLE);
        titulo = (TextView) getActivity().findViewById(R.id.text_report_details_title);
        textReportFirstName = (TextView) getActivity().findViewById(R.id.text_report_one_firstname);
        textReportSsn = (TextView) getActivity().findViewById(R.id.text_report_one_ssn);
        textReportType = (TextView) getActivity().findViewById(R.id.text_report_one_type);
        textReportTitleDetail = (TextView) getActivity().findViewById(R.id.text_title_report_details);

        textReportOne = (TextView) getActivity().findViewById(R.id.text_report_one);
        textReportOneData = (TextView) getActivity().findViewById(R.id.text_report_one_data);
        textFiveNextAnother = (TextView) getActivity().findViewById(R.id.text_report_five_next_another);
        textFiveNextAnotherData = (TextView) getActivity().findViewById(R.id.text_report_five_next_data_another);

        llSix = (RelativeLayout) getActivity().findViewById(R.id.ll_six);
        llFiveNext = (RelativeLayout) getActivity().findViewById(R.id.ll_two_next_another);


        llInfoData = (LinearLayout) getActivity().findViewById(R.id.ll_info_data);
        llInfoDetailsData = (LinearLayout) getActivity().findViewById(R.id.ll_info_details_data);

        titulo.setText("CHECKS TO PRINT - DETAILS");
        textReportType.setText("Funding Type:");

        textReportTitleDetail.setText("CHECKS TO PRINT");//ll_info_details_data

        textReportOne.setText("Check Amount:");//ll_six
        textReportOneData.setText("$5,573.00");

        textFiveNextAnother.setText("Check Release Date:");//ll_two_next_another
        textFiveNextAnotherData.setText("06-03-2017");

    }
}
