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

public class ReportsFeesPaidDetailsFragment extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    String title;
    TextView titulo, textReportFirstName, textReportSsn, textReportType, textReportTitleDetail, textReportOne, textReportOneData,
            textReportTwo, textReportTwoData, textReportThree, textReportThreeData, textReportFour, textReportFourData,
            textReportFive, textReportFiveData,
            textReportSix, textReportSixData;
    RelativeLayout llOne, llTwo, llThree, llFour, llFive, llSix;
    LinearLayout llInfoData, llInfoDetailsData;


    public static ReportsFeesPaidDetailsFragment newInstance(String sectionTitle) {
        ReportsFeesPaidDetailsFragment fragment = new ReportsFeesPaidDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public ReportsFeesPaidDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Dashboard)getActivity()).setTitle("REPORTS");

      /*  setHasOptionsMenu(true);
        dashboard = (Dashboard) getActivity();
        dashboard.getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_arrow_use);
*/
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                int backStackEntryCount = getFragmentManager().getBackStackEntryCount();
                AppCompatActivity activity = (AppCompatActivity) getContext();

                if (backStackEntryCount > 0) {
                    activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                } else {
                    activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_feepaid_details, container, false);


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

        textReportOne = (TextView) getActivity().findViewById(R.id.text_report_one);//ll_six
        textReportOneData = (TextView) getActivity().findViewById(R.id.text_report_one_data);

        textReportTwo = (TextView) getActivity().findViewById(R.id.text_report_two);//ll_five
        textReportTwoData = (TextView) getActivity().findViewById(R.id.text_report_two_data);

        textReportThree = (TextView) getActivity().findViewById(R.id.text_report_three);//ll_foure
        textReportThreeData = (TextView) getActivity().findViewById(R.id.text_report_three_data);

        textReportFour = (TextView) getActivity().findViewById(R.id.text_report_four);//ll_three
        textReportFourData = (TextView) getActivity().findViewById(R.id.text_report_four_data);

        textReportFive = (TextView) getActivity().findViewById(R.id.text_report_five);//ll_two
        textReportFiveData = (TextView) getActivity().findViewById(R.id.text_report_five_data);

        textReportSix = (TextView) getActivity().findViewById(R.id.text_report_six);//ll_one
        textReportSixData = (TextView) getActivity().findViewById(R.id.text_report_six_data);


        llInfoData = (LinearLayout) getActivity().findViewById(R.id.ll_info_data);
        llInfoDetailsData = (LinearLayout) getActivity().findViewById(R.id.ll_info_details_data);

        llSix = (RelativeLayout) getActivity().findViewById(R.id.ll_six);
        llFive = (RelativeLayout) getActivity().findViewById(R.id.ll_five);
        llFour = (RelativeLayout) getActivity().findViewById(R.id.ll_four);
        llThree = (RelativeLayout) getActivity().findViewById(R.id.ll_three);
        llTwo = (RelativeLayout) getActivity().findViewById(R.id.ll_two);
        llOne = (RelativeLayout) getActivity().findViewById(R.id.ll_one);


        titulo.setText("FEES PAID - DETAILS");

        textReportTitleDetail.setText("FEES PAID");

        textReportOne.setText("Disbursement Date:");
        textReportOneData.setText("06-03-2017");

        textReportTwo.setText("Prep Fee:");
        textReportTwoData.setText("$100");

        textReportThree.setText("Electronic Filling Fee:");
        textReportThreeData.setText("$2");

        textReportFour.setText("Doc Prep Fee:");
        textReportFourData.setText("$25");

        textReportFive.setText("Other Fee:");
        textReportFiveData.setText("");

        textReportSix.setText("Total Fee Amount:");
    }
}