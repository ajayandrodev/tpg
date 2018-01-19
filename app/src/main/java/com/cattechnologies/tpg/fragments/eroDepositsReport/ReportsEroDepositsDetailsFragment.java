package com.cattechnologies.tpg.fragments.eroDepositsReport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.activities.Dashboard;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by ajay kumar on 28-Oct-17.
 */

public class ReportsEroDepositsDetailsFragment extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    String title;
    TextView titulo, textReportOne, textReportSsn, textReportType, textReportOneData, textReportTitleDetail,
            textReportTwo, textReportTwoData, textReportThree, textReportThreeData, textReportFour, textReportFourData,
            textFiveNextAnother, textFiveNextAnotherData;
    RelativeLayout llTwo, llThree, llFour, llFive, llSix, llTwoNext, llFiveNext;
    String userName,
            userSSN,
            userDis,
            userMasterID,
            userDepositDate,
            userDepositAmount,
            userReversedDate;
    SimpleDateFormat format, format1;


    public static ReportsEroDepositsDetailsFragment newInstance(
            String sectionTitle, String firstName, String primarySsn, String depositType,
            String masterefin, String depositdate, String depositAmount, String recordcreatedate) {
        ReportsEroDepositsDetailsFragment fragment = new ReportsEroDepositsDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        args.putString("report_username", firstName);
        args.putString("report_ssn", primarySsn);
        args.putString("report_disb", depositType);
        args.putString("report_masterID", masterefin);
        args.putString("report_depositDate", depositdate);
        args.putString("report_depositAmount", depositAmount);
        args.putString("report_recordCreatedDate", recordcreatedate);
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

        userName = getArguments().getString("report_username");
        userSSN = getArguments().getString("report_ssn");
        userDis = getArguments().getString("report_disb");
        userMasterID = getArguments().getString("report_masterID");
        userDepositDate = getArguments().getString("report_depositDate");
        userDepositAmount = getArguments().getString("report_depositAmount");
        userReversedDate = getArguments().getString("report_recordCreatedDate");


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

        llSix = (RelativeLayout) getActivity().findViewById(R.id.ll_six);
        llFive = (RelativeLayout) getActivity().findViewById(R.id.ll_five);
        llFour = (RelativeLayout) getActivity().findViewById(R.id.ll_four);
        llThree = (RelativeLayout) getActivity().findViewById(R.id.ll_three);
        llTwo = (RelativeLayout) getActivity().findViewById(R.id.ll_two);
        llTwoNext = (RelativeLayout) getActivity().findViewById(R.id.ll_two_next);
        llFiveNext = (RelativeLayout) getActivity().findViewById(R.id.ll_two_next_another);

        titulo.setText("ERO DEPOSITS-DETAILS");
        textReportTitleDetail.setText("ERO DEPOSITS");

        textReportSsn.setText(userSSN);
        textReportType.setText(userDis);

        textReportOne.setText("Master ID:");
        textReportOneData.setText(userMasterID);

        textReportTwo.setText("Deposit Date:");
        textReportTwoData.setText(userDepositDate);

        textReportThree.setText("Deposit Amount:");
        textReportThreeData.setText(" $ " + userDepositAmount);

        textReportFour.setText("Reversed Date:");
        format = new SimpleDateFormat("yyyyMMdd");
        //format1 = new SimpleDateFormat("MM-dd-yyyy");
        format1 = new SimpleDateFormat("MM-dd-yyyy");

        String chagnedDate = null;
        try {
            chagnedDate = format1.format(format.parse(userReversedDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        textReportFourData.setText(chagnedDate);


        textFiveNextAnother.setText("Comments:");
        textFiveNextAnotherData.setText("");


    }
}