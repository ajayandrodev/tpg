package com.SBTPG.TPGMobile.fragments.eroDepositsReport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.SBTPG.TPGMobile.R;
import com.SBTPG.TPGMobile.activities.Dashboard;
import com.SBTPG.TPGMobile.utils.DateUtils;
import com.SBTPG.TPGMobile.utils.PreferencesManager;


/**
 * Created by Ajay on 28-Oct-17.
 */

public class ReportsEroDepositsDetailsFragment extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    String title;
    TextView titulo, textReportOne, textReportSsn, textReportType, textReportOneData, textReportTitleDetail,
            textReportTwo, textReportTwoData, textReportThree, textReportThreeData, textReportFour, textReportFourData,
            textFiveNextAnother, textFiveNextAnotherData, textEfinData, textEfin;
    RelativeLayout llTwo, llThree, llFour, llFive, llSix, llTwoNext, llFiveNext;
    String userName,
            userSid,
            userDis,
            userMasterID,
            userDepositDate,
            userDepositAmount,
            userReversedDate, efindata;
    PreferencesManager preferencesManager;


    public static ReportsEroDepositsDetailsFragment newInstance(
            String sectionTitle, String firstName, String primarySid, String depositType,
            String masterefin, String depositdate, String depositAmount, String recordcreatedate, String efindata) {
        ReportsEroDepositsDetailsFragment fragment = new ReportsEroDepositsDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        args.putString("report_username", firstName);
        args.putString("report_ssn", primarySid);
        args.putString("report_disb", depositType);
        args.putString("report_masterID", masterefin);
        args.putString("report_depositDate", depositdate);
        args.putString("report_depositAmount", depositAmount);
        args.putString("report_recordCreatedDate", recordcreatedate);
        args.putString("report_efin", efindata);

        fragment.setArguments(args);
        return fragment;
    }

    public ReportsEroDepositsDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Dashboard) getActivity()).setTitle("REPORTS");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_ero_details, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title = getArguments().getString(ARG_SECTION_TITLE);
        titulo = (TextView) getActivity().findViewById(R.id.text_report_details_title);
        textReportTitleDetail = (TextView) getActivity().findViewById(R.id.text_title_report_details);
        preferencesManager = new PreferencesManager();
        userName = getArguments().getString("report_username");
        userSid = getArguments().getString("report_ssn");
        userDis = getArguments().getString("report_disb");
        userMasterID = getArguments().getString("report_masterID");
        userDepositDate = getArguments().getString("report_depositDate");
        userDepositAmount = getArguments().getString("report_depositAmount");
        userReversedDate = getArguments().getString("report_recordCreatedDate");

        efindata = getArguments().getString("report_efin");

        textReportSsn = (TextView) getActivity().findViewById(R.id.text_report_one_ssn);
        textReportType = (TextView) getActivity().findViewById(R.id.text_report_one_type);


        textReportOne = (TextView) getActivity().findViewById(R.id.text_report_one);//ll_six
        textReportOneData = (TextView) getActivity().findViewById(R.id.text_report_one_data);

        textReportTwo = (TextView) getActivity().findViewById(R.id.text_report_two);//ll_five
        textReportTwoData = (TextView) getActivity().findViewById(R.id.text_report_two_data);

        textReportThree = (TextView) getActivity().findViewById(R.id.text_report_three);//ll_four
        textReportThreeData = (TextView) getActivity().findViewById(R.id.text_report_three_data);

        textReportFour = (TextView) getActivity().findViewById(R.id.text_report_four);//ii_three
        textReportFourData = (TextView) getActivity().findViewById(R.id.text_report_four_data);

        textFiveNextAnother = (TextView) getActivity().findViewById(R.id.text_report_five_next_another);//ll_two_next_another
        textFiveNextAnotherData = (TextView) getActivity().findViewById(R.id.text_report_five_next_data_another);

        textEfin = (TextView) getActivity().findViewById(R.id.text_report_one_efin2);
        textEfinData = (TextView) getActivity().findViewById(R.id.text_report_one_efin_data2);

        llSix = (RelativeLayout) getActivity().findViewById(R.id.ll_six);
        llFive = (RelativeLayout) getActivity().findViewById(R.id.ll_five);
        llFour = (RelativeLayout) getActivity().findViewById(R.id.ll_four);
        llThree = (RelativeLayout) getActivity().findViewById(R.id.ll_three);
        llTwo = (RelativeLayout) getActivity().findViewById(R.id.ll_two);
        llTwoNext = (RelativeLayout) getActivity().findViewById(R.id.ll_two_next);
        llFiveNext = (RelativeLayout) getActivity().findViewById(R.id.ll_two_next_another);

        titulo.setText("ERO DEPOSITS-DETAILS");
        textReportTitleDetail.setText("ERO DEPOSITS");

        textReportSsn.setText(userSid);
        textReportType.setText(userDis);

        textReportOne.setText("Master ID:");
        textReportOneData.setText(userMasterID);

        textReportTwo.setText("Deposit Date:");
        textReportTwoData.setText(userDepositDate);

        textReportThree.setText("Deposit Amount:");
        textReportThreeData.setText("$" + userDepositAmount);

        textReportFour.setText("Reversed Date:");

        String changeDateNew= DateUtils.reportDate(userReversedDate);

        textReportFourData.setText(changeDateNew);

        textFiveNextAnother.setText("Comments:");
        textFiveNextAnotherData.setText("");
        if (preferencesManager.gaT(getContext()).equalsIgnoreCase("sb")) {
            textEfin.setText("EFIN:");//ll_two next another
            textEfinData.setText(efindata);
        } else {
            textEfin.setText("");//ll_two next another
            textEfinData.setText("");
        }

    }
}