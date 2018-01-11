package com.cattechnologies.tpg.fragments.accountDisbursementsReport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.activities.Dashboard;

/**
 * Created by ajay kumar on 28-Oct-17.
 */

public class ReportsAccountDisbursementDetailsFragment extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    String title;
    TextView titulo, textReportFirstName, textReportSsn, textReportType, textReportTitleDetail, textReportOne, textReportOneData,
            textReportTwo, textReportTwoData, textReportThree, textReportThreeData, textReportFour, textReportFourData,
            textReportFive, textReportFiveNext, textReportFiveNextData, textReportFiveData, textFiveNextAnother, textFiveNextAnotherData;
    RelativeLayout llTwo, llThree, llFour, llFive, llSix, llTwoNext, llFiveNext;
    LinearLayout llInfoData, llInfoDetailsData;


    public static ReportsAccountDisbursementDetailsFragment newInstance(String sectionTitle) {
        ReportsAccountDisbursementDetailsFragment fragment = new ReportsAccountDisbursementDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public ReportsAccountDisbursementDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Dashboard)getActivity()).setTitle("REPORTS");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_account_disbursement_details, container, false);


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
        textReportTwo = (TextView) getActivity().findViewById(R.id.text_report_two);
        textReportTwoData = (TextView) getActivity().findViewById(R.id.text_report_two_data);
        textReportThree = (TextView) getActivity().findViewById(R.id.text_report_three);
        textReportThreeData = (TextView) getActivity().findViewById(R.id.text_report_three_data);
        textReportFour = (TextView) getActivity().findViewById(R.id.text_report_four);
        textReportFourData = (TextView) getActivity().findViewById(R.id.text_report_four_data);
        textReportFive = (TextView) getActivity().findViewById(R.id.text_report_five);
        textReportFiveData = (TextView) getActivity().findViewById(R.id.text_report_five_data);
        textReportFiveNext = (TextView) getActivity().findViewById(R.id.text_report_five_next);
        textReportFiveNextData = (TextView) getActivity().findViewById(R.id.text_report_five_next_data);
        textFiveNextAnother = (TextView) getActivity().findViewById(R.id.text_report_five_next_another);
        textFiveNextAnotherData = (TextView) getActivity().findViewById(R.id.text_report_five_next_data_another);
        llInfoData = (LinearLayout) getActivity().findViewById(R.id.ll_info_data);
        llInfoDetailsData = (LinearLayout) getActivity().findViewById(R.id.ll_info_details_data);

        llSix = (RelativeLayout) getActivity().findViewById(R.id.ll_six);
        llFive = (RelativeLayout) getActivity().findViewById(R.id.ll_five);
        llFour = (RelativeLayout) getActivity().findViewById(R.id.ll_four);
        llThree = (RelativeLayout) getActivity().findViewById(R.id.ll_three);
        llTwo = (RelativeLayout) getActivity().findViewById(R.id.ll_two);
        llTwoNext = (RelativeLayout) getActivity().findViewById(R.id.ll_two_next);
        llFiveNext = (RelativeLayout) getActivity().findViewById(R.id.ll_two_next_another);


        titulo.setText("ACCOUNT DISBURSEMENTS - DETAILS");
        textReportTitleDetail.setText("ACCOUNT DISBURSEMENTS");
        textReportType.setText("DT w/ Man Bank");

        textReportOne.setText("Expected Refund($):");//ll_six
        textReportOneData.setText("$5,000.00");

        textReportTwo.setText("Expected Date:");//ll_five
        textReportTwoData.setText("01-25-2017");

        textReportThree.setText("Product Type:");//ll_four
        textReportThreeData.setText("RT");

        textReportFour.setText("Disbursed  Date:");//ll_three
        textReportFourData.setText("12-10-2017");

        textReportFive.setText("Disbursed  Amount:");//ll_two
        textReportFiveData.setText("$5,200");

        textReportFiveNext.setText("Disbursement Type:");//ll_two next
        textReportFiveNextData.setText("ACH");

        textFiveNextAnother.setText("Disbursement Info:");//ll_two next another
        textFiveNextAnotherData.setText("");


    }
}
