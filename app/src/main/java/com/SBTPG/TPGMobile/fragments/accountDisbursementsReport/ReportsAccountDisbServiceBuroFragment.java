package com.SBTPG.TPGMobile.fragments.accountDisbursementsReport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

import com.SBTPG.TPGMobile.R;
import com.SBTPG.TPGMobile.activities.Dashboard;
import com.SBTPG.TPGMobile.adapters.MyExpandableadapter;
import com.SBTPG.TPGMobile.adapters.accountDisbursementsReportAdapter.SbiAccountDisbListDataAdapter;
import com.SBTPG.TPGMobile.interfaces.RemoveClickListner;
import com.SBTPG.TPGMobile.model.RecyclerData;
import com.SBTPG.TPGMobile.model.ReportsEfinValidCheck;
import com.SBTPG.TPGMobile.model.Response;
import com.SBTPG.TPGMobile.utils.AppInternetStatus;
import com.SBTPG.TPGMobile.utils.NetworkUtil;
import com.SBTPG.TPGMobile.utils.PreferencesManager;
import com.futuremind.recyclerviewfastscroll.FastScroller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ajay on 1/11/2018.
 */

public class ReportsAccountDisbServiceBuroFragment extends Fragment implements RemoveClickListner, ExpandableListView.OnChildClickListener {

    MyExpandableadapter adapter;
    ExpandableListView myexpandable;
    List<String> parent;
    List<String> child;
    HashMap<String, List<String>> bind_and_display;


    TextView sbEro, titulo;
    RecyclerView mRecyclerView;
    SbiAccountDisbListDataAdapter mRecyclerAdapter;
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
    Button sbEroNew;
    JSONArray jsonArray;

    public static Fragment newInstance(String sectionTitle, String userId, String type) {
        ReportsAccountDisbServiceBuroFragment fragment = new ReportsAccountDisbServiceBuroFragment();
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
        View data = inflater.inflate(R.layout.service_buro_account_new, container, false);

        return data;
    }

    @Override
    public void onResume() {
        super.onResume();
        etTitle.setText("");

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
        parent = Arrays.asList(getResources().getStringArray(R.array.Parent_Head_Sb));
        bind_and_display.put(parent.get(0), Arrays.asList(getResources().getStringArray(R.array.Child_Sb)));
        adapter = new MyExpandableadapter(getContext(), parent, bind_and_display);
        myexpandable.setAdapter(adapter);

        myexpandable.setOnChildClickListener(this);
        preferencesManager = new PreferencesManager();
        mSubscriptions = new CompositeSubscription();


        relativeLayout = (RelativeLayout) getActivity().findViewById(R.id.ero_list_data_layout);
        sbEro = (TextView) getActivity().findViewById(R.id.selected_type_sb);

        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        fastScroller = (FastScroller) getActivity().findViewById(R.id.fast_scroller);
        viewReport = (Button) getActivity().findViewById(R.id.view_report);

        fastScroller.setRecyclerView(mRecyclerView);
        etTitle = (EditText) getActivity().findViewById(R.id.etTitle);
        btnAddItem = (ImageButton) getActivity().findViewById(R.id.btnAddItem);


        sbEroNew = (Button) getActivity().findViewById(R.id.selected_type_sb);
        sbEroNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = getResources().getString(R.string.dashboard_account_dis2);
                fragment = ReportsAccountDisbServiceBuroDataFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                        preferencesManager.gaT(getActivity()));
                if (fragment != null) {
                    fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.main_content, fragment)
                            .addToBackStack(null)
                            .commit();

                }
            }
        });

        viewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = getResources().getString(R.string.dashboard_account_dis2);

                if (myList.size() > 0) {
                    jsonArray = new JSONArray();
                    for (int i = 0; i < myList.size(); i++) {
                        String efin = myList.get(i).getTitle();
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("Efin", efin);
                            jsonArray.put(jsonObject);
                        } catch (JSONException e) {
                            Log.e("error", e.getMessage());
                            showToast(e.getMessage());
                        }
                    }

                    fragment = ParticularOfficeSbAccountDisbFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                            preferencesManager.gaT(getActivity()), "1", jsonArray.toString());
                    if (fragment != null) {
                        fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.main_content, fragment)
                                .addToBackStack(null)
                                .commit();
                    }
                } else {
                    showToast("Please Enter ERO EFIN To View Report");
                }


            }

        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                if (!TextUtils.isEmpty(title)) {
                    efinValidCheck(userId, userType, title);
                } else {
                    showToast("Please Enter ERO EFIN");
                }
            }
        });

    }

    private void efinValidCheck(String userId, String userType, String title) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getEfinValidCheck(userId, userType, title)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            showToast("Internet Connection Is Not Available");
        }
    }

    private void handleError(Throwable error) {
        if (error instanceof HttpException) {
            Gson gson = new GsonBuilder().create();
            try {
                String errorBody = ((HttpException) error).response().errorBody().string();
                Response response = gson.fromJson(errorBody, Response.class);
                showToast(response.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!error.getMessage().equalsIgnoreCase("")) {
            showToast("EFIN already exist");
        }
    }

    private void handleResponse(ReportsEfinValidCheck response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerAdapter = new SbiAccountDisbListDataAdapter(myList);
            mRecyclerAdapter.newAddedData(0, title);
            mRecyclerView.setAdapter(mRecyclerAdapter);
            mRecyclerAdapter.notifyDataSetChanged();
            etTitle.setText("");

        } else {
            showToast(response.getMessage());
        }

    }


    private void showToast(String msg) {
        try {
            Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        myList.clear();
    }

    @Override
    public void onStop() {
        super.onStop();
        myList.clear();
    }

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
        return false;
    }

    private void Displayitemclicked(int gposition, int cposition) {
        if (gposition == 0) {

            switch (cposition) {
                case 0:
                    title = getResources().getString(R.string.dashboard_account_dis2);
                    fragment = ReportAccountDisbFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                            preferencesManager.gaT(getActivity()));
                    if (fragment != null) {
                        fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.main_content, fragment)
                                .addToBackStack(null)
                                .commit();
                    }
                    break;
                case 1:
                    relativeLayout.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}

