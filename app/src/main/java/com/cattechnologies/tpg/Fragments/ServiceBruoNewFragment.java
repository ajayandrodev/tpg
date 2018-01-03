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
import com.cattechnologies.tpg.Adapters.MySbWithErosInfoAdapter;
import com.cattechnologies.tpg.Adapters.SbiEroListDataAdapter;
import com.cattechnologies.tpg.Model.EroInfo;
import com.cattechnologies.tpg.Model.FeesPaidChildInfo;
import com.cattechnologies.tpg.Model.FeesPaidGroupInfo;
import com.cattechnologies.tpg.Model.MySbWithEroInfo;
import com.cattechnologies.tpg.Model.RecyclerData;
import com.cattechnologies.tpg.Model.RemoveClickListner;
import com.cattechnologies.tpg.Model.ReportsFeePaidNew;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.Utils.PreferencesManager;
import com.futuremind.recyclerviewfastscroll.FastScroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by admin on 11/23/2017.
 */

public class ServiceBruoNewFragment extends Fragment implements RemoveClickListner {


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

    FragmentManager fragmentManager;

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
        preferencesManager = new PreferencesManager();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        srTitle = getArguments().getString(ARG_SECTION_TITLE);
        titulo = (TextView) getActivity().findViewById(R.id.title);
        titulo.setText(srTitle);

        loadData();

        relativeLayout = (RelativeLayout) getActivity().findViewById(R.id.ero_list_data_layout);
        simpleExpandableListViewThree = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView_one);
        sbEro = (TextView) getActivity().findViewById(R.id.selected_type_sb);
        accountListAdapter = new MySbWithErosInfoAdapter(getContext(), deptListAccount);
        simpleExpandableListViewThree.setAdapter(accountListAdapter);
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
                fragment = ReportsFeesPaidFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                        preferencesManager.getAccountType(getActivity()));
                if (fragment != null) {
                    fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.main_content, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();

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
        simpleExpandableListViewThree.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
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
        });
    }


    private void loadData() {

        addProductAccount("ERO", "SSN");

    }

    private int addProductAccount(String department, String product) {
        int groupPosition = 0;

        //check the hash map if the group already exists
        MySbWithEroInfo headerInfo = subjectsAccount.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new MySbWithEroInfo();
            headerInfo.setName(department);
            subjectsAccount.put(department, headerInfo);
            deptListAccount.add(headerInfo);
        }

        //get the children for the group
        ArrayList<EroInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        EroInfo detailInfo = new EroInfo();
        detailInfo.setName(product);
       /* detailInfo.setName("All Offices");
        detailInfo.setName("Particular Offices");*/
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = deptListAccount.indexOf(headerInfo);
        return groupPosition;
    }

    @Override
    public void OnRemoveClick(int index) {
        myList.remove(index);
        mRecyclerAdapter.notifyData(myList);
    }
}
