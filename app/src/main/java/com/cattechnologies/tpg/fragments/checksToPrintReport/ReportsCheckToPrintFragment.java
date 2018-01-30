package com.cattechnologies.tpg.fragments.checksToPrintReport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.cattechnologies.tpg.adapters.checkToPrintReportAdapter.ReportsCheckListToPrintAdapter;
import com.cattechnologies.tpg.adapters.checkToPrintReportAdapter.ReportsExpandableListCheckListToPrintAdapter;
import com.cattechnologies.tpg.model.checkToPrintModel.CheckToPrintChildInfo;
import com.cattechnologies.tpg.model.checkToPrintModel.CheckToPrintGroupInfo;
import com.cattechnologies.tpg.model.Reports;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.activities.Dashboard;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by ajay kumar on 28-Oct-17.
 */

public class ReportsCheckToPrintFragment extends Fragment {


    public static final String ARG_SECTION_TITLE = "section_number";


    List<Reports> reportsList = new ArrayList<>();
    RecyclerView recyclerView;
    ReportsCheckListToPrintAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    TextView titulo, expand;
    private LinkedHashMap<String, CheckToPrintGroupInfo> subjects = new LinkedHashMap<String, CheckToPrintGroupInfo>();
    private ArrayList<CheckToPrintGroupInfo> deptList = new ArrayList<CheckToPrintGroupInfo>();
    String title, dashboardTitle;
    ReportsExpandableListCheckListToPrintAdapter listAdapter;
    ExpandableListView simpleExpandableListView;

    public ReportsCheckToPrintFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Dashboard)getActivity()).setTitle("REPORTS");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_chect_to_print_fragment, container, false);
        return view;
    }

    public static Fragment newInstance(String sectionTitle) {
        ReportsCheckToPrintFragment fragment = new ReportsCheckToPrintFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title = getArguments().getString(ARG_SECTION_TITLE);
        titulo = (TextView) getActivity().findViewById(R.id.title);

//        loadData();
       // simpleExpandableListView = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView);

       /* listAdapter = new ReportsExpandableListCheckListToPrintAdapter(getActivity(), deptList);
        simpleExpandableListView.setAdapter(listAdapter);*/
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        reportsList = new ArrayList<>();
        prepareReportData();
        mAdapter = new ReportsCheckListToPrintAdapter(getActivity(), reportsList, title);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.my_custom_divider));
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.setAdapter(mAdapter);


        //expand all the Groups
        // expandAll();

        // setOnChildClickListener listener for child row click
     /*   simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                CheckToPrintGroupInfo headerInfo = deptList.get(groupPosition);
                //get the child info
                CheckToPrintChildInfo detailInfo = headerInfo.getProductList().get(childPosition);
              *//*  //display it or do something with it
                Toast.makeText(getActivity(), " Clicked on :: " + headerInfo.getName()
                        + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();*//*
                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                CheckToPrintGroupInfo headerInfo = deptList.get(groupPosition);
                //display it or do something with it
             *//*   Toast.makeText(getActivity(), " Header is :: " + headerInfo.getName(),
                        Toast.LENGTH_LONG).show();
*//*


                return false;
            }
        });*/
    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            simpleExpandableListView.expandGroup(i);
        }
    }

    private void loadData() {
        titulo.setText("CHECKS TO PRINT");
        addProduct("CHECKS TO PRINT", "SSN");
        addProduct("CHECKS TO PRINT", "LAST NAME");
        addProduct("CHECKS TO PRINT", "TYPE OF FUNDING");

    }


    private int addProduct(String department, String product) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        CheckToPrintGroupInfo headerInfo = subjects.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new CheckToPrintGroupInfo();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<CheckToPrintChildInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        CheckToPrintChildInfo detailInfo = new CheckToPrintChildInfo();
        detailInfo.setName(product);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }


    private void prepareReportData() {
        Reports reports = new Reports("roethlisberger", "$125", "XXX-XX-0123", "IRS | ", "06-04-2017");
        reportsList.add(reports);

        reports = new Reports("John Doe", "$195", "XXX-XX-0193", "State | ", "09-03-2017");
        reportsList.add(reports);
        reports = new Reports("roethlisberger", "$125", "XXX-XX-0123", "IRS | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("John Doe", "$195", "XXX-XX-0193", "State | ", "09-03-2017");
        reportsList.add(reports);
        reports = new Reports("roethlisberger", "$125", "XXX-XX-0123", "IRS , State | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("John Doe", "$195", "XXX-XX-0193", "State | ", "09-03-2017");
        reportsList.add(reports);
        reports = new Reports("roethlisberger", "$125", "XXX-XX-0123", "IRS | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("John Doe", "$195", "XXX-XX-0193", "State | ", "09-03-2017");
        reportsList.add(reports);
        reports = new Reports("roethlisberger", "$125", "XXX-XX-0123", "IRS , State | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("John Doe", "$195", "XXX-XX-0193", "State | ", "09-03-2017");
        reportsList.add(reports);
        reports = new Reports("roethlisberger", "$125", "XXX-XX-0123", "IRS | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("John Doe", "$195", "XXX-XX-0193", "State | ", "09-03-2017");
        reportsList.add(reports);
        reports = new Reports("roethlisberger", "$125", "XXX-XX-0123", "IRS , State | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("John Doe", "$195", "XXX-XX-0193", "State | ", "09-03-2017");
        reportsList.add(reports);
        reports = new Reports("roethlisberger", "$125", "XXX-XX-0123", "IRS | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("John Doe", "$195", "XXX-XX-0193", "State | ", "09-03-2017");
        reportsList.add(reports);
        reports = new Reports("roethlisberger", "$125", "XXX-XX-0123", "IRS , State | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("John Doe", "$195", "XXX-XX-0193", "State | ", "09-03-2017");
        reportsList.add(reports);
        reports = new Reports("roethlisberger", "$125", "XXX-XX-0123", "IRS | ", "06-04-2017");
        reportsList.add(reports);


    }
}
