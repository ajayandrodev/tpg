package com.cattechnologies.tpg.fragments.feepaidReport;

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
import com.cattechnologies.tpg.utils.PreferencesManager;

/**
 * Created by admin on 2/1/2018.
 */

public class ReportsFeesPaidServiceBuroDataDetailsFragment  extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    String title;
    TextView titulo, textReportFirstName, textReportSsn, textReportType, textReportTitleDetail, textReportOne, textReportOneData,
            textReportTwo, textReportTwoData, textReportThree, textReportThreeData, textReportFour, textReportFourData,
            textReportFive, textReportFiveData,
            textReportSix, textReportSixData, textEfinData, textEfin;
    RelativeLayout llOne, llTwo, llThree, llFour, llFive, llSix;
    LinearLayout llInfoData, llInfoDetailsData;
    String userName, userSSN, userDis, userDate, userPrep, userEle, userDoc, userTotal, userOther, efindata;

    PreferencesManager preferencesManager;

    public static ReportsFeesPaidServiceBuroDataDetailsFragment newInstance(String sectionTitle, String username,
                                                             String ssn, String disbursType, String date,
                                                             String prepFee, String electFee, String docFee,
                                                             String totalfee, String otherfee, String efindata) {
        ReportsFeesPaidServiceBuroDataDetailsFragment fragment = new ReportsFeesPaidServiceBuroDataDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        args.putString("report_username", username);
        args.putString("report_ssn", ssn);
        args.putString("report_disb", disbursType);
        args.putString("report_date", date);
        args.putString("report_prepfee", prepFee);
        args.putString("report_elecfee", electFee);
        args.putString("report_docfee", docFee);
        args.putString("report_totalfee", totalfee);
        args.putString("report_otherfee", otherfee);
        args.putString("report_efin", efindata);


        fragment.setArguments(args);
        return fragment;
    }

    public ReportsFeesPaidServiceBuroDataDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Dashboard) getActivity()).setTitle("REPORTS");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_feepaid_service_service_buro_details, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title = getArguments().getString(ARG_SECTION_TITLE);
        preferencesManager = new PreferencesManager();
        userName = getArguments().getString("report_username");
        userSSN = getArguments().getString("report_ssn");
        userDis = getArguments().getString("report_disb");
        userDate = getArguments().getString("report_date");
        userPrep = getArguments().getString("report_prepfee");
        userEle = getArguments().getString("report_elecfee");
        userDoc = getArguments().getString("report_docfee");
        userTotal = getArguments().getString("report_totalfee");
        userOther = getArguments().getString("report_otherfee");

        efindata = getArguments().getString("report_efin");

        titulo = (TextView) getActivity().findViewById(R.id.text_report_details_title);
        textReportFirstName = (TextView) getActivity().findViewById(R.id.text_report_one_firstname);
        textReportFirstName.setText(userName);
        textReportSsn = (TextView) getActivity().findViewById(R.id.text_report_one_ssn);
        textReportSsn.setText(userSSN);
        textReportType = (TextView) getActivity().findViewById(R.id.text_report_one_type);

        textReportType.setText(userDis);
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

        textEfin = (TextView) getActivity().findViewById(R.id.text_report_one_efin);
        textEfinData = (TextView) getActivity().findViewById(R.id.text_report_one_efin_data);

        llSix = (RelativeLayout) getActivity().findViewById(R.id.ll_six);
        llFive = (RelativeLayout) getActivity().findViewById(R.id.ll_five);
        llFour = (RelativeLayout) getActivity().findViewById(R.id.ll_four);
        llThree = (RelativeLayout) getActivity().findViewById(R.id.ll_three);
        llTwo = (RelativeLayout) getActivity().findViewById(R.id.ll_two);
        llOne = (RelativeLayout) getActivity().findViewById(R.id.ll_one);


        titulo.setText("FEES PAID - DETAILS");

        textReportTitleDetail.setText("FEES PAID");

        textReportOne.setText("Disbursement Date:");

        textReportOneData.setText(userDate);

        textReportTwo.setText("Prep Fee:");
        textReportTwoData.setText("$" + userPrep);

        textReportThree.setText("Electronic Filling Fee:");
        textReportThreeData.setText("$" + userEle);

        textReportFour.setText("Doc Prep Fee:");
        textReportFourData.setText("$" + userDoc);

        textReportFive.setText("Other Fee:");
        textReportFiveData.setText("$" + userOther);

        textReportSix.setText("Total Fee Amount:");
        textReportSixData.setText("$" + userTotal);

        if (preferencesManager.getAccountType(getContext()).equalsIgnoreCase("sb")) {
            textEfin.setText("EFIN:");//ll_two next another
            textEfinData.setText(efindata);
        } else {
            textEfin.setText("");//ll_two next another
            textEfinData.setText("");
        }
    }
}