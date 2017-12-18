package com.cattechnologies.tpg.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cattechnologies.tpg.Activities.Dashboard;
import com.cattechnologies.tpg.R;

/**
 * Created by ajay kumar on 28-Oct-17.
 */

public class ReportsEroDepositsDetailsFragment extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    String title;
    TextView titulo, textReportOne, textReportSsn, textReportType, textReportOneData, textReportTitleDetail,
            textReportTwo, textReportTwoData, textReportThree, textReportThreeData, textReportFour, textReportFourData,
            textReportFive, textReportFiveNext, textReportFiveNextData, textReportFiveData, textFiveNextAnother, textFiveNextAnotherData;
    RelativeLayout llTwo, llThree, llFour, llFive, llSix, llTwoNext, llFiveNext;

    public static ReportsEroDepositsDetailsFragment newInstance(String sectionTitle) {
        ReportsEroDepositsDetailsFragment fragment = new ReportsEroDepositsDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
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

      /*  textReportFive = (TextView) getActivity().findViewById(R.id.text_report_five);//ll_two
        textReportFiveData = (TextView) getActivity().findViewById(R.id.text_report_five_data);

        textReportFiveNext = (TextView) getActivity().findViewById(R.id.text_report_five_next);//ll_two_net
        textReportFiveNextData = (TextView) getActivity().findViewById(R.id.text_report_five_next_data);
*/
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
        textReportSsn.setText("XXXX4567");
        textReportType.setText("ACH");
        textReportOne.setText("Master ID:");
        textReportOneData.setText("574018");

        textReportTwo.setText("Deposit Date:");
        textReportTwoData.setText("09-12-2017");

        textReportThree.setText("Deposit Amount:");
        textReportThreeData.setText("$200.00");

        textReportFour.setText("Reversed Date:");
        textReportFourData.setText("10-12-2017");

      /*  textReportFive.setText("Deposit Type:");
        textReportFiveData.setText("ACH");

        textReportFiveNext.setText("DAN:");
        textReportFiveNextData.setText("XXXX4567");
*/
        textFiveNextAnother.setText("Comments:");
        textFiveNextAnotherData.setText("");


    }
}