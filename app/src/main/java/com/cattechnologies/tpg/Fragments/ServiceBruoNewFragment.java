package com.cattechnologies.tpg.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cattechnologies.tpg.Activities.Dashboard;
import com.cattechnologies.tpg.Adapters.MyExpandableadapter;
import com.cattechnologies.tpg.Adapters.MySbWithErosInfoAdapter;
import com.cattechnologies.tpg.Adapters.SbiEroListDataAdapter;
import com.cattechnologies.tpg.Model.EroInfo;
import com.cattechnologies.tpg.Model.FeesPaidChildInfo;
import com.cattechnologies.tpg.Model.FeesPaidGroupInfo;
import com.cattechnologies.tpg.Model.MySbWithEroInfo;
import com.cattechnologies.tpg.Model.RecyclerData;
import com.cattechnologies.tpg.Model.RemoveClickListner;
import com.cattechnologies.tpg.Model.ReportParticulrFreePaid;
import com.cattechnologies.tpg.Model.ReportParticulrFreePaidNew;
import com.cattechnologies.tpg.Model.ReportsFeePaidNew;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.Utils.AppInternetStatus;
import com.cattechnologies.tpg.Utils.NetworkUtil;
import com.cattechnologies.tpg.Utils.PreferencesManager;
import com.futuremind.recyclerviewfastscroll.FastScroller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by admin on 11/23/2017.
 */

public class ServiceBruoNewFragment extends Fragment implements RemoveClickListner, ExpandableListView.OnChildClickListener {

    MyExpandableadapter adapter;
    ExpandableListView myexpandable;
    List<String> parent;
    List<String> child;
    HashMap<String, List<String>> bind_and_display;


    ArrayList<MySbWithEroInfo> deptListAccount = new ArrayList<MySbWithEroInfo>();
    LinkedHashMap<String, MySbWithEroInfo> subjectsAccount = new LinkedHashMap<String, MySbWithEroInfo>();
    ExpandableListView simpleExpandableListViewThree;
    MySbWithErosInfoAdapter accountListAdapter;
    TextView sbEro, titulo;
    RecyclerView mRecyclerView;
    SbiEroListDataAdapter mRecyclerAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ImageButton btnAddItem;
    ArrayList<RecyclerData> myList = new ArrayList<>();
    EditText etTitle, etDescription;
    String title = "", srTitle = "";
    ImageView crossImage;
    RemoveClickListner listner;
    RelativeLayout relativeLayout;
    int position = 0;
    FastScroller fastScroller;
    public static final String ARG_SECTION_TITLE = "section_number";
    Button viewReport;
    Fragment fragment = null;
    PreferencesManager preferencesManager;
    String userId, userType;
    FragmentManager fragmentManager;
    CompositeSubscription mSubscriptions;


    public static Fragment newInstance(String sectionTitle, String userId, String type) {
        ServiceBruoNewFragment fragment = new ServiceBruoNewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        args.putString("app_uid", userId);
        args.putString("acc_type", type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Dashboard) getActivity()).setTitle("REPORTS");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_buro_new, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        srTitle = getArguments().getString(ARG_SECTION_TITLE);
        titulo = (TextView) getActivity().findViewById(R.id.title);
        titulo.setText(srTitle);
        userId = getArguments().getString("app_uid");
        userType = getArguments().getString("acc_type");
        myexpandable = (ExpandableListView) getActivity().findViewById(R.id.theexpandables);
        bind_and_display = new HashMap<String, List<String>>();
        parent = new ArrayList<String>();
        child = new ArrayList<String>();
        preferencesManager = new PreferencesManager();

        parent = Arrays.asList(getResources().getStringArray(R.array.Parent_head));
        //Adding string array element to the parent list
        // you can also add item one by one like the following
        //parent.add("Animals")
        //parent.add("Birds")


        bind_and_display.put(parent.get(0), Arrays.asList(getResources().getStringArray(R.array.Child_animals)));

        // Here we bind child list data under the particular heading
        // you can also bind data like this
        //List<String> anim= new ArrayList<String>();
        // anim.add("Lion");
        //anim.add("Tiger");
        // bind_and_display(Parent.get(0),anim);
        //so what happened now is "lion, tiger" is placed under heading "Animals"
        mSubscriptions = new CompositeSubscription();


        //   bind_and_display.put(parent.get(1), Arrays.asList(getResources().getStringArray(R.array.child_birds)));

        adapter = new MyExpandableadapter(getContext(), parent, bind_and_display);
        // passing our current application context , parent data, and child data to the custom adapter class

        myexpandable.setAdapter(adapter);
        //setting the Expandable listview with our custom adapter, which populates the data inside the Expandable Listview.

        myexpandable.setOnChildClickListener(this);
        //your class should implemet ExpandableListView.OnChildClickListener
        //  loadData();

        relativeLayout = (RelativeLayout) getActivity().findViewById(R.id.ero_list_data_layout);
        // simpleExpandableListViewThree = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView_one);
        sbEro = (TextView) getActivity().findViewById(R.id.selected_type_sb);
        /*accountListAdapter = new MySbWithErosInfoAdapter(getContext(), deptListAccount);
        simpleExpandableListViewThree.setAdapter(accountListAdapter);*/
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        fastScroller = (FastScroller) getActivity().findViewById(R.id.fast_scroller);
        viewReport = (Button) getActivity().findViewById(R.id.view_report);

        fastScroller.setRecyclerView(mRecyclerView);
        etTitle = (EditText) getActivity().findViewById(R.id.etTitle);
        //  etDescription = (EditText) findViewById(R.id.etDescription);
        btnAddItem = (ImageButton) getActivity().findViewById(R.id.btnAddItem);

        viewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = getResources().getString(R.string.dashboard_fee_paid);

                feePaidReportsParticularData(userId, userType, "1",
                        preferencesManager.getParticularPerson(getContext()),
                        "2018");

              /*  fragment = ReportsFeesPaidFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                        preferencesManager.getAccountType(getActivity()), "1",
                        preferencesManager.getParticularPerson(getContext()),
                        "2018");
                if (fragment != null) {
                    fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.main_content, fragment)
                            .addToBackStack(null)
                            .commit();*/
                }

        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            title = etTitle.getText().toString();
            preferencesManager.setParticularPerson(getContext(), title);

            //  final ArrayList<RecyclerData> myList = new ArrayList<RecyclerData>();

             /*   RecyclerData mLog = new RecyclerData();
                mLog.setTitle(title);
                myList.add(mLog);*/
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerAdapter = new SbiEroListDataAdapter(myList);
            mRecyclerAdapter.newAddedData(0, title);
            mRecyclerView.setAdapter(mRecyclerAdapter);

             /*   mRecyclerAdapter.notifyItemInserted(myList.size()-1);
                mRecyclerAdapter.notifyDataSetChanged();*/

            // mRecyclerAdapter.addItem(mLog);

            etTitle.setText("");
            // etDescription.setText("");
        }
        });
      /*  simpleExpandableListViewThree.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                sbEro.setVisibility(View.GONE);
                Toast.makeText(getContext(), "group", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        simpleExpandableListViewThree.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view,
                                        int childGroupPosition, int childPosition, long id) {

                relativeLayout.setVisibility(View.VISIBLE);
                //get the group header

                Toast.makeText(getContext(), "child", Toast.LENGTH_SHORT).show();

                return false;
            }
        });*/
}

    private void feePaidReportsParticularData(String userId, String userType, String page, String particularPerson,
                                              String date) {

        System.out.println("ReportsFeesPaidFragment.feePaidReportsData==" + userId + "==" + userType);
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidParticularData
                    (userId, userType, page, particularPerson, date)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }
    }

    private void showToast(String msg) {
        Toast.makeText(getContext(), "" + msg, Toast.LENGTH_SHORT).show();
    }

    private void handleError(Throwable throwable) {

    }

    private void handleResponse(ReportParticulrFreePaid response) {
        System.out.println("ServiceBruoNewFragment.handleResponse==="+response.getFeeReport_data());
        if (response.getStatus().equalsIgnoreCase("success")) {
            // progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            System.out.println("ReportsFeesPaidFragment.handleResponse==" + totalPages);
            preferencesManager.saveReportDetailUserName(getContext(), totalPages);
            List<ReportParticulrFreePaidNew> reportsFeePaidNewList = new ArrayList<>();
            for (int i = 0; i < response.getFeeReport_data().size(); i++) {
                ReportParticulrFreePaidNew reportsFeePaidNew = new ReportParticulrFreePaidNew();
                reportsFeePaidNew.setPrimaryFirstName(response.getFeeReport_data().get(i).getPrimaryFirstName());
                reportsFeePaidNew.setPrimaryLastName(response.getFeeReport_data().get(i).getPrimaryLastName());
                reportsFeePaidNew.setToTalSiteFeeCollected(response.getFeeReport_data().get(i).getToTalSiteFeeCollected());
                reportsFeePaidNew.setPrimarySsn(response.getFeeReport_data().get(i).getPrimarySsn());
                reportsFeePaidNew.setDisbursementType(response.getFeeReport_data().get(i).getDisbursementType());
                reportsFeePaidNew.setRecordcreatedate(response.getFeeReport_data().get(i).getRecordcreatedate());
                reportsFeePaidNew.setPreparationFeesCollected(response.getFeeReport_data().get(i).getPreparationFeesCollected());
                reportsFeePaidNew.setSiteEfFeesCollected(response.getFeeReport_data().get(i).getSiteEfFeesCollected());
                reportsFeePaidNew.setOtherfees(response.getFeeReport_data().get(i).getOtherfees());
                reportsFeePaidNew.setDocumentStorageFeesCollected(response.getFeeReport_data().get(i).
                        getDocumentStorageFeesCollected());
                reportsFeePaidNew.setToTalSiteFeeCollected(response.getFeeReport_data().get(i).getToTalSiteFeeCollected());
                reportsFeePaidNewList.add(reportsFeePaidNew);
                System.out.println("ReportsFeesPaidFragment.handleResponse===" + reportsFeePaidNewList);
            }
            fragment = ReportsFeesPaidFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                    preferencesManager.getAccountType(getActivity()), "1",
                    preferencesManager.getParticularPerson(getContext()),
                    "2018");
            if (fragment != null) {
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .addToBackStack(null)
                        .commit();
            }


        }}


    @Override
    public void OnRemoveClick(int index) {
        myList.remove(index);
        mRecyclerAdapter.notifyData(myList);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        int gposition = groupPosition;
        int cposition = childPosition;

        Displayitemclicked(gposition, cposition);
        //passing the integer value of grouposition and childposition to the above method when an item is clicked
        return false;
    }

    private void Displayitemclicked(int gposition, int cposition) {
        //Display a message with which item is clicked.
        if (gposition == 0) {
            switch (cposition) {
                case 0:
                    Toast.makeText(getContext(), "All Offices", Toast.LENGTH_SHORT).show();
                    title = getResources().getString(R.string.dashboard_fee_paid);
                    fragment = ReportsFeesPaidFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                            preferencesManager.getAccountType(getActivity()));
                    if (fragment != null) {
                        fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.main_content, fragment)
                                .addToBackStack(null)
                                .commit();

                    }
                    break;
                case 1:
                    Toast.makeText(getContext(), "Particular Offices", Toast.LENGTH_SHORT).show();
                    relativeLayout.setVisibility(View.VISIBLE);
                    break;
            }
        }/* else if (gposition == 1) {
            switch (cposition) {
                case 0:
                    Toast.makeText(getContext(), "Parrot", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getContext(), "Dove", Toast.LENGTH_SHORT).show();
                    break;
            }*/
    }


}



