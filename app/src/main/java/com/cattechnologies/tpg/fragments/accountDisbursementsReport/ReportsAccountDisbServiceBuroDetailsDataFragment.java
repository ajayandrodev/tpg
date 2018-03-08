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
import com.cattechnologies.tpg.utils.DateUtils;
import com.cattechnologies.tpg.utils.PreferencesManager;


/**
 * Created by Ajay on 2/2/2018.
 */

public class ReportsAccountDisbServiceBuroDetailsDataFragment extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    String title;
    TextView titulo, textReportFirstName, textReportSsn, textReportType, textReportTitleDetail, textReportOne, textReportOneData,
            textReportTwo, textReportTwoData, textReportThree, textReportThreeData, textReportFour, textReportFourData,
            textReportFive, textReportFiveNext, textReportFiveNextData, textReportFiveData, textFiveNextAnother, textFiveNextAnotherData;
    RelativeLayout llTwo, llThree, llFour, llFive, llSix, llTwoNext, llFiveNext;
    LinearLayout llInfoData, llInfoDetailsData;
    String name, primarySid, disbursType, expectedRefund, reportsExpecteddepdate,
            productType, disbursementDate, disbursmentamount, expecteddepdate, efindata;
    PreferencesManager preferencesManager;

    public static ReportsAccountDisbServiceBuroDetailsDataFragment newInstance(
            String title, String name,
            String primarySid, String disbursType,
            String expectedRefund, String reportsExpecteddepdate,
            String productType, String disbursementDate, String disbursmentamount,
            String expecteddepdate, String sectionTitle) {
        ReportsAccountDisbServiceBuroDetailsDataFragment fragment = new ReportsAccountDisbServiceBuroDetailsDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        args.putString("report_username", name);
        args.putString("report_ssn", primarySid);
        args.putString("report_disb_type", disbursType);
        args.putString("report_expectedRefund", expectedRefund);
        args.putString("report_reportsExpecteddepdate", reportsExpecteddepdate);
        args.putString("report_productType", productType);
        args.putString("report_disbursementDate", disbursementDate);
        args.putString("report_disbursmentamount", disbursmentamount);
        args.putString("report_expecteddepdate", expecteddepdate);
        fragment.setArguments(args);
        return fragment;
    }

    public ReportsAccountDisbServiceBuroDetailsDataFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Dashboard) getActivity()).setTitle("REPORTS");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_account_disb_service_buro_detail, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title = getArguments().getString(ARG_SECTION_TITLE);
        preferencesManager = new PreferencesManager();
        name = getArguments().getString("report_username");
        primarySid = getArguments().getString("report_ssn");
        disbursType = getArguments().getString("report_disb_type");
        expectedRefund = getArguments().getString("report_expectedRefund");
        reportsExpecteddepdate = getArguments().getString("report_reportsExpecteddepdate");
        productType = getArguments().getString("report_productType");
        disbursementDate = getArguments().getString("report_disbursementDate");
        disbursmentamount = getArguments().getString("report_disbursmentamount");
        expecteddepdate = getArguments().getString("report_expecteddepdate");


        titulo = (TextView) getActivity().findViewById(R.id.text_report_details_title);

        /*textEfin = (TextView) getActivity().findViewById(R.id.text_report_one_efin);
        textEfinData = (TextView) getActivity().findViewById(R.id.text_report_one_efin_data);*/

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

        textReportFirstName = (TextView) getActivity().findViewById(R.id.text_report_one_firstname);
        textReportFirstName.setText(name);

        textReportSsn = (TextView) getActivity().findViewById(R.id.text_report_one_ssn);
        textReportSsn.setText(primarySid);

        textReportType = (TextView) getActivity().findViewById(R.id.text_report_one_type);
        textReportType.setText(disbursType);


        titulo.setText("ACCOUNT DISBURSEMENTS - DETAILS");
        textReportTitleDetail.setText("ACCOUNT DISBURSEMENTS");
        textReportType.setText(disbursType);

        textReportOne.setText("Expected Refund($):");//ll_six
        textReportOneData.setText("$" + expectedRefund);

        textReportTwo.setText("Expected Date:");//ll_five

        String changeDateNew= DateUtils.reportDate(reportsExpecteddepdate);
        textReportTwoData.setText(changeDateNew);

        textReportThree.setText("Product Type:");//ll_four
        textReportThreeData.setText(productType);

        textReportFour.setText("Disbursed  Date:");//ll_three
        textReportFourData.setText(disbursementDate);

        textReportFive.setText("Disbursed  Amount:");//ll_two
        textReportFiveData.setText("$" + disbursmentamount);

        textReportFiveNext.setText("Disbursement Type:");//ll_two next
        textReportFiveNextData.setText(disbursType);

        textFiveNextAnother.setText("Disbursement Info:");//ll_two next another
        textFiveNextAnotherData.setText("");


    }
}