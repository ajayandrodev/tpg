package com.cattechnologies.tpg.Fragments;

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

import com.cattechnologies.tpg.Activities.Dashboard;
import com.cattechnologies.tpg.Adapters.ReportsEroListAdapter;
import com.cattechnologies.tpg.Adapters.ReportsExpandableListEroAdapter;
import com.cattechnologies.tpg.Adapters.ReportsExpandableListFeesPaidAdapter;
import com.cattechnologies.tpg.Adapters.ReportsFeesPaidListAdapter;
import com.cattechnologies.tpg.Model.FeesPaidChildInfo;
import com.cattechnologies.tpg.Model.FeesPaidGroupInfo;
import com.cattechnologies.tpg.Model.Reports;
import com.cattechnologies.tpg.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by admin on 11/3/2017.
 */

public class ReportsEroListFragment extends Fragment {


    public static final String ARG_SECTION_TITLE = "section_number";


    List<Reports> reportsList = new ArrayList<>();
    RecyclerView recyclerView;
    ReportsEroListAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    TextView titulo, expand;
    LinkedHashMap<String, FeesPaidGroupInfo> subjects = new LinkedHashMap<String, FeesPaidGroupInfo>();
    ArrayList<FeesPaidGroupInfo> deptList = new ArrayList<FeesPaidGroupInfo>();
    String title, dashboardTitle;
    ReportsExpandableListEroAdapter listAdapter;
    ExpandableListView simpleExpandableListView;

    public ReportsEroListFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Dashboard) getActivity()).setTitle("REPORTS");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_fragment, container, false);
        return view;
    }

    public static Fragment newInstance(String sectionTitle) {
        ReportsEroListFragment fragment = new ReportsEroListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Dashboard) getActivity()).setTitle("REPORTS");

        //  loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((Dashboard) getActivity()).setTitle("REPORTS");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title = getArguments().getString(ARG_SECTION_TITLE);
        titulo = (TextView) getActivity().findViewById(R.id.title);

        loadData();
        simpleExpandableListView = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView);
        listAdapter = new ReportsExpandableListEroAdapter(getActivity(), deptList);
        simpleExpandableListView.setAdapter(listAdapter);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        reportsList = new ArrayList<>();
        prepareReportData();
        mAdapter = new ReportsEroListAdapter(getActivity(), reportsList, title);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.my_custom_divider));
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.setAdapter(mAdapter);


        //expand all the Groups
        // expandAll();

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            /*    //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo = headerInfo.getProductList().get(childPosition);
              *//*  //display it or do something with it
                Toast.makeText(getActivity(), " Clicked on :: " + headerInfo.getName()
                        + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();*//*
        */
                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
               /* //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //display it or do something with it
             *//*   Toast.makeText(getActivity(), " Header is :: " + headerInfo.getName(),
                        Toast.LENGTH_LONG).show();
*//*

*/
                return false;
            }
        });
    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            simpleExpandableListView.expandGroup(i);
        }
    }

    private void loadData() {

        titulo.setText("ERO DEPOSITS");
        addProduct("ERO DISBURSEMENT", "DEPOSIT DATE");
        addProduct("ERO DISBURSEMENT", "REVERSED DATE");
        addProduct("ERO DISBURSEMENT", "DEPOSIT TYPE");
        addProduct("ERO DISBURSEMENT", "DAN");


    }


    private int addProduct(String department, String product) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        FeesPaidGroupInfo headerInfo = subjects.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new FeesPaidGroupInfo();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<FeesPaidChildInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        FeesPaidChildInfo detailInfo = new FeesPaidChildInfo();
        detailInfo.setName(product);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }


    private void prepareReportData() {
        Reports reports = new Reports("XXX0312", "$140", "ACH", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("XXX0256", "$152", "ACH", "08-03-2017");
        reportsList.add(reports);
        reports = new Reports("XXX0312", "$140", "ACH", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("XXX0256", "$152", "ACH", "08-03-2017");
        reportsList.add(reports);reports = new Reports("XXX0312", "$140", "ACH", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("XXX0256", "$152", "ACH", "08-03-2017");
        reportsList.add(reports);reports = new Reports("XXX0312", "$140", "ACH", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("XXX0256", "$152", "ACH", "08-03-2017");
        reportsList.add(reports);reports = new Reports("XXX0312", "$140", "ACH", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("XXX0256", "$152", "ACH", "08-03-2017");
        reportsList.add(reports);reports = new Reports("XXX0312", "$140", "ACH", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("XXX0256", "$152", "ACH", "08-03-2017");
        reportsList.add(reports);reports = new Reports("XXX0312", "$140", "ACH", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("XXX0256", "$152", "ACH", "08-03-2017");
        reportsList.add(reports);


    }
}
