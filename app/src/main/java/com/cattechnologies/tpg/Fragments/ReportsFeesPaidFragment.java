package com.cattechnologies.tpg.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cattechnologies.tpg.Activities.Dashboard;
import com.cattechnologies.tpg.Adapters.ReportsExpandableListFeesPaidAdapter;
import com.cattechnologies.tpg.Adapters.ReportsFeesPaidListAdapter;
import com.cattechnologies.tpg.Adapters.ReportsFeesPaidSearchListAdapter;
import com.cattechnologies.tpg.Adapters.ReportsFeesPaidSortListAdapter;
import com.cattechnologies.tpg.Model.FeesPaidChildInfo;
import com.cattechnologies.tpg.Model.FeesPaidGroupInfo;
import com.cattechnologies.tpg.Model.ReportsFeePaid;
import com.cattechnologies.tpg.Model.ReportsFeePaidNew;
import com.cattechnologies.tpg.Model.ReportsFeePaidSearch;
import com.cattechnologies.tpg.Model.ReportsFeePaidSearchNew;
import com.cattechnologies.tpg.Model.ReportsFeePaidSort;
import com.cattechnologies.tpg.Model.ReportsFeePaidSortNew;
import com.cattechnologies.tpg.Model.Response;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.Utils.AppInternetStatus;
import com.cattechnologies.tpg.Utils.NetworkUtil;
import com.cattechnologies.tpg.Utils.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import android.support.v7.widget.SearchView;

/**
 * Created by ajay kumar on 28-Oct-17.
 */

public class ReportsFeesPaidFragment extends Fragment {


    public static final String ARG_SECTION_TITLE = "section_number";
    RecyclerView recyclerView;
    ReportsFeesPaidListAdapter mAdapter;
    ReportsFeesPaidSearchListAdapter mAdapterSearch;
    ReportsFeesPaidSortListAdapter mAdapterSort;
    RecyclerView.LayoutManager mLayoutManager;
    TextView titulo, expand;
    LinkedHashMap<String, FeesPaidGroupInfo> subjects = new LinkedHashMap<String, FeesPaidGroupInfo>();
    ArrayList<FeesPaidGroupInfo> deptList = new ArrayList<FeesPaidGroupInfo>();
    String title, dashboardTitle;
    ReportsExpandableListFeesPaidAdapter listAdapter;
    ExpandableListView simpleExpandableListView;
    CompositeSubscription mSubscriptions;
    ProgressBar progressBar;
    String userId, userType;
    PreferencesManager preferencesManager;
    ReportsFeePaid reports;
    ReportsFeePaidSort reportsFeePaidSort;
    ReportsFeePaidSearch reportsFeePaidSearch;
    Button prev, next;
    EditText searchData;

    int current_page = 1;
    SearchView searchView;
    int i = 0;
    int totalC = 1;
    // int j = 1;
    int data;

    public ReportsFeesPaidFragment() {
    }

    public static Fragment newInstance(String sectionTitle, String userId, String type) {
        ReportsFeesPaidFragment fragment = new ReportsFeesPaidFragment();
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
        View view = inflater.inflate(R.layout.reports_fragment, container, false);

        return view;
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

        prev = (Button) getActivity().findViewById(R.id.prev);
        next = (Button) getActivity().findViewById(R.id.next);
        searchData = (EditText) getActivity().findViewById(R.id.search_paid);
        //searchView = (SearchView) getActivity().findViewById(R.id.search_paid);

//        searchView.setQueryHint("Search Customer");
        userId = getArguments().getString("app_uid");
        userType = getArguments().getString("acc_type");
        LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.button_list);

        mSubscriptions = new CompositeSubscription();
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_login);
        preferencesManager = new PreferencesManager();
        //reports = new ReportsFeePaidNew();
        loadData();
        simpleExpandableListView = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView);
        listAdapter = new ReportsExpandableListFeesPaidAdapter(getActivity(), deptList);
        simpleExpandableListView.setAdapter(listAdapter);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration divider =
                new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.my_custom_divider));
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        reports = new ReportsFeePaid();
        reportsFeePaidSort = new ReportsFeePaidSort();
        reportsFeePaidSearch = new ReportsFeePaidSearch();
        reports.setPage("1");
        if (searchData.getText().toString().isEmpty()) {
            feePaidReportsData(userId, userType, reports.getPage());

        }
     /*   searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                searchReportItem(userId, userType, reports.getPage(), newText);
                return true;
            }
        });
*/
        searchData.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if ((actionId == EditorInfo.IME_ACTION_DONE)) {

                }
                return false;
            }
        });
        searchData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newText = editable.toString().toLowerCase();
                if (newText.isEmpty()) {
                    feePaidReportsData(userId, userType, reports.getPage());

                } else {
                    searchReportItem(userId, userType, reports.getPage(), newText);

                }

            }
        });
        if (preferencesManager.getReportDetailUsername(getContext()) == null) {
            data = 5;
        } else {
            data = preferencesManager.getReportDetailUsername(getContext()).length();
            System.out.println("ReportsFeesPaidFragment.onActivityCreated" + data);
        }

        for (current_page = 0; current_page <= data; current_page++) {
            final Button btn = new Button(getActivity());
            int width = (int) getResources().getDimension(R.dimen.dim_35);
            int hieght = (int) getResources().getDimension(R.dimen.dim_35);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, hieght);
            //  lp.setMargins(5, 5, 5, 5);
            btn.setId(current_page);
            btn.setText("" + (current_page + 1));
            btn.setLayoutParams(lp);
            layout.addView(btn);

            final int index = current_page + 1;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reports = new ReportsFeePaid();
                    reports.setPage(String.valueOf(index));
                    System.out.println("ReportsFeesPaidFragment.onClick" + index);
                    feePaidReportsData(userId, userType, reports.getPage());


                }
            });
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("ReportsFeesPaidFragment.onClick===" + index);
                    if (current_page < index) {
                        current_page++;
                        reports.setPage(String.valueOf(current_page));
                    }
                    System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());

                    feePaidReportsData(userId, userType, reports.getPage());


                }
            });
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("ReportsFeesPaidFragment.onClick===" + index);
                    if (current_page >= index) {
                        current_page--;
                        reports.setPage(String.valueOf(current_page));
                    }
                    System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());

                    System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());
                    feePaidReportsData(userId, userType, reports.getPage());

                }
            });

        }


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
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int childGroupPosition, int childPosition, long id) {
                //get the group header
                FeesPaidGroupInfo headerInfo = deptList.get(childGroupPosition);
                //get the child info
                FeesPaidChildInfo detailInfo = headerInfo.getProductList().get(childPosition);

                progressBar.setVisibility(View.VISIBLE);


                if (childGroupPosition == 0 && childPosition == 0) {
                    System.out.println("ReportsFeesPaidFragment.onChildClick" + detailInfo.getName());

                    if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
                        mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidDataSort
                                (userId, userType, reports.getPage(), "ssn")
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.newThread())
                                .subscribe(this::handleResponseSort, this::handleError));


                    } else {
                        showToast("Internet Connection Is Not Available");


                    }
                    parent.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                }
                if (childGroupPosition == 0 && childPosition == 1) {
                    System.out.println("ReportsFeesPaidFragment.onChildClick" + detailInfo.getName());

                    if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
                        mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidDataSort
                                (userId, userType, reports.getPage(), "lastname")
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.newThread())
                                .subscribe(this::handleResponseSort, this::handleError));


                    } else {
                        showToast("Internet Connection Is Not Available");


                    }


                    parent.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);

                }
                if (childGroupPosition == 0 && childPosition == 2) {
                    System.out.println("ReportsFeesPaidFragment.onChildClick" + detailInfo.getName());

                    if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
                        mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidDataSort
                                (userId, userType, reports.getPage(), "product_type")
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.newThread())
                                .subscribe(this::handleResponseSort, this::handleError));


                    } else {
                        showToast("Internet Connection Is Not Available");


                    }
                    parent.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);


                }
                return false;
            }

            private void handleResponseSort(ReportsFeePaidSort response) {
                if (response.getStatus().equalsIgnoreCase("success")) {
                    progressBar.setVisibility(View.GONE);
                    //showToast(response.getMessage());
                    String totalPages = response.getTotalNoofPages();
                    System.out.println("ReportsFeesPaidFragment.handleResponse==" + totalPages);
                    preferencesManager.saveReportDetailUserName(getContext(), totalPages);
                    List<ReportsFeePaidSortNew> reportsFeePaidNewList = new ArrayList<>();
                    for (int i = 0; i < response.getFeeReport_data().size(); i++) {
                        ReportsFeePaidSortNew reportsFeePaidNew = new ReportsFeePaidSortNew();
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
                    mAdapterSort = new ReportsFeesPaidSortListAdapter(getActivity(), reportsFeePaidNewList, title);
                    recyclerView.setAdapter(mAdapterSort);
                    mAdapterSort.notifyDataSetChanged();


                    mAdapterSort.setClickListener((view, position) -> {
                        final ReportsFeePaidSortNew reports = reportsFeePaidNewList.get(position);
                        Dashboard activity = (Dashboard) view.getContext();
                        Fragment fragment = ReportsFeesPaidDetailsFragment.newInstance(title,
                                reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName()
                                , reports.getPrimarySsn(), reports.getDisbursementType(),
                                reports.getRecordcreatedate(), reports.getPreparationFeesCollected(),
                                reports.getSiteEfFeesCollected(), reports.getDocumentStorageFeesCollected()
                                , reports.getToTalSiteFeeCollected(), reports.getOtherfees());
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.main_content, fragment)
                                .addToBackStack(null)
                                .commit();
                        activity.getSupportActionBar().setTitle("REPORTS");
                    });

                }

            }

            private void handleError(Throwable error) {
                System.out.println("ReportsFeesPaidFragment.handleError==" + error.getMessage());
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

        });

    }


    private void searchReportItem(String userId, String userType, String page, String searchText) {
        System.out.println("ReportsFeesPaidFragment.feePaidReportsData==" + userId + "==" + userType);
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidDataSearch(userId, userType, page, searchText)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }


    }

    private void handleResponse(ReportsFeePaidSearch response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            System.out.println("ReportsFeesPaidFragment.handleResponse==" + totalPages);
            preferencesManager.saveReportDetailUserName(getContext(), totalPages);
            List<ReportsFeePaidSearchNew> reportsFeePaidNewList = new ArrayList<>();
            for (int i = 0; i < response.getFeeReport_data().size(); i++) {
                ReportsFeePaidSearchNew reportsFeePaidNew = new ReportsFeePaidSearchNew();
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
            mAdapterSearch = new ReportsFeesPaidSearchListAdapter(getActivity(), reportsFeePaidNewList, title);
            recyclerView.setAdapter(mAdapterSearch);
            mAdapterSearch.notifyDataSetChanged();


            mAdapterSearch.setClickListener((view, position) -> {
                final ReportsFeePaidSearchNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
                Fragment fragment = ReportsFeesPaidDetailsFragment.newInstance(title,
                        reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName()
                        , reports.getPrimarySsn(), reports.getDisbursementType(),
                        reports.getRecordcreatedate(), reports.getPreparationFeesCollected(),
                        reports.getSiteEfFeesCollected(), reports.getDocumentStorageFeesCollected()
                        , reports.getToTalSiteFeeCollected(), reports.getOtherfees());
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .addToBackStack(null)
                        .commit();
                activity.getSupportActionBar().setTitle("REPORTS");
            });

        }
    }


    private void feePaidReportsData(String userId, String userType, String page) {
        System.out.println("ReportsFeesPaidFragment.feePaidReportsData==" + userId + "==" + userType);
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidData(userId, userType, page)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }


    }

    private void handleError(Throwable error) {
        System.out.println("ReportsFeesPaidFragment.handleError==" + error.getMessage());
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

        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            System.out.println("ReportsFeesPaidFragment.handleResponse==" + totalPages);
            preferencesManager.saveReportDetailUserName(getContext(), totalPages);
            List<ReportsFeePaidNew> reportsFeePaidNewList = new ArrayList<>();
            for (int i = 0; i < response.getFeeReport_data().size(); i++) {
                ReportsFeePaidNew reportsFeePaidNew = new ReportsFeePaidNew();
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
            mAdapter = new ReportsFeesPaidListAdapter(getActivity(), reportsFeePaidNewList, title);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();


            mAdapter.setClickListener((view, position) -> {
                final ReportsFeePaidNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
                Fragment fragment = ReportsFeesPaidDetailsFragment.newInstance(title,
                        reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName()
                        , reports.getPrimarySsn(), reports.getDisbursementType(),
                        reports.getRecordcreatedate(), reports.getPreparationFeesCollected(),
                        reports.getSiteEfFeesCollected(), reports.getDocumentStorageFeesCollected()
                        , reports.getToTalSiteFeeCollected(), reports.getOtherfees());
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .addToBackStack(null)
                        .commit();
                activity.getSupportActionBar().setTitle("REPORTS");
            });


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


}
