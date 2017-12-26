package com.cattechnologies.tpg.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cattechnologies.tpg.Activities.Dashboard;
import com.cattechnologies.tpg.Activities.LoginScreen;
import com.cattechnologies.tpg.Adapters.ReportsExpandableListFeesPaidAdapter;
import com.cattechnologies.tpg.Adapters.ReportsFeesPaidListAdapter;
import com.cattechnologies.tpg.Model.FeesPaidChildInfo;
import com.cattechnologies.tpg.Model.FeesPaidGroupInfo;
import com.cattechnologies.tpg.Model.ForgotUserEmailData;
import com.cattechnologies.tpg.Model.Reports;
import com.cattechnologies.tpg.Model.ReportsFeePaid;
import com.cattechnologies.tpg.Model.Response;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.Utils.AppInternetStatus;
import com.cattechnologies.tpg.Utils.NetworkUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by ajay kumar on 28-Oct-17.
 */

public class ReportsFeesPaidFragment extends Fragment {


    public static final String ARG_SECTION_TITLE = "section_number";


    List<Reports> reportsList = new ArrayList<>();
    RecyclerView recyclerView;
    ReportsFeesPaidListAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    TextView titulo, expand;
    LinkedHashMap<String, FeesPaidGroupInfo> subjects = new LinkedHashMap<String, FeesPaidGroupInfo>();
    ArrayList<FeesPaidGroupInfo> deptList = new ArrayList<FeesPaidGroupInfo>();
    String title, dashboardTitle;
    ReportsExpandableListFeesPaidAdapter listAdapter;
    ExpandableListView simpleExpandableListView;
    CompositeSubscription mSubscriptions;
    ProgressBar progressBar;

    public ReportsFeesPaidFragment() {
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
        ReportsFeesPaidFragment fragment = new ReportsFeesPaidFragment();
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
        mSubscriptions = new CompositeSubscription();
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_login);

        loadData();
        simpleExpandableListView = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView);
        listAdapter = new ReportsExpandableListFeesPaidAdapter(getActivity(), deptList);
        simpleExpandableListView.setAdapter(listAdapter);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        reportsList = new ArrayList<>();
        prepareReportData();
        mAdapter = new ReportsFeesPaidListAdapter(getActivity(), reportsList, title);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.my_custom_divider));
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.setAdapter(mAdapter);

        usingAsynchTa();
        //feePaidReportsData();

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

    private void usingAsynchTa() {


    }

    private void feePaidReportsData() {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidReport("", "")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }


    }

    private void handleError(Throwable error) {
        showToast(error.getMessage());
        progressBar.setVisibility(View.GONE);

        if (error instanceof HttpException) {

            Gson gson = new GsonBuilder().create();

            try {
                String errorBody = ((HttpException) error).response().errorBody().string();
                Response response = gson.fromJson(errorBody, Response.class);
                showToast(response.getMessage());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showToast("Network Error !");
        }

    }

    private void handleResponse(ReportsFeePaid response) {
        System.out.println("ForgotEmailDetails.handleResponse==" + response.getMessage());
        showToast(response.getMessage());
        progressBar.setVisibility(View.GONE);

        if (response.getStatus().equalsIgnoreCase("success")) {
            showToast(response.getMessage());
            //  ReportsFeePaid reportsFeePaid = response.getEfin();


        }
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();

    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            simpleExpandableListView.expandGroup(i);
        }
    }

    private void loadData() {

        titulo.setText("FEES PAID");
        addProduct("DISBURSEMENT DATE", "SSN");
        addProduct("DISBURSEMENT DATE", "LAST NAME");
        addProduct("DISBURSEMENT DATE", "PRODUCT TYPE");

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
        Reports reports = new Reports("smith-alonza", "$125", "XXX-XX-0123", "RT w/ FCA | ", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("jones", "$120", "XXX-XX-0123", "RT w/ FCA w/ State | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("hightower", "$150", "XXX-XX-0123", "RT | ", "08-04-2017");
        reportsList.add(reports);
        reports = new Reports("smith-alonza", "$125", "XXX-XX-0123", "RT w/ FCA | ", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("jones", "$120", "XXX-XX-0123", "RT w/ FCA w/ State | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("hightower", "$150", "XXX-XX-0123", "RT | ", "08-04-2017");
        reportsList.add(reports);
        reports = new Reports("smith-alonza", "$125", "XXX-XX-0123", "RT w/ FCA | ", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("jones", "$120", "XXX-XX-0123", "RT w/ FCA w/ State | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("hightower", "$150", "XXX-XX-0123", "RT | ", "08-04-2017");
        reportsList.add(reports);
        reports = new Reports("smith-alonza", "$125", "XXX-XX-0123", "RT w/ FCA | ", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("jones", "$120", "XXX-XX-0123", "RT w/ FCA w/ State | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("hightower", "$150", "XXX-XX-0123", "RT | ", "08-04-2017");
        reportsList.add(reports);
        reports = new Reports("smith-alonza", "$125", "XXX-XX-0123", "RT w/ FCA | ", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("jones", "$120", "XXX-XX-0123", "RT w/ FCA w/ State | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("hightower", "$150", "XXX-XX-0123", "RT | ", "08-04-2017");
        reportsList.add(reports);
        reports = new Reports("smith-alonza", "$125", "XXX-XX-0123", "RT w/ FCA | ", "05-03-2017");
        reportsList.add(reports);
        reports = new Reports("jones", "$120", "XXX-XX-0123", "RT w/ FCA w/ State | ", "06-04-2017");
        reportsList.add(reports);
        reports = new Reports("hightower", "$150", "XXX-XX-0123", "RT | ", "08-04-2017");
        reportsList.add(reports);
        LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.button_list);

        Button tv[] = new Button[10];
        for (int i = 1; i < 10; i++) {
            tv[i] = new Button(getActivity());

            tv[i].setText(""+i);

            tv[i].setGravity(Gravity.CENTER);

            tv[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //add your code here
                    progressBar.setVisibility(View.VISIBLE);

                    Toast.makeText(getActivity(), "its done", Toast.LENGTH_SHORT).show();
                }
            });
            layout.addView(tv[i]);

        }


    }


}
