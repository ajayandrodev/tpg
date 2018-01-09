package com.cattechnologies.tpg.Fragments;

import android.graphics.Color;
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
import android.text.TextUtils;
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
import com.cattechnologies.tpg.Adapters.MyExpandableadapter;
import com.cattechnologies.tpg.Adapters.MyExpandableadapterSb;
import com.cattechnologies.tpg.Adapters.ReportPerticulaSortListAdapter;
import com.cattechnologies.tpg.Adapters.ReportPerticularListAdapter;
import com.cattechnologies.tpg.Adapters.ReportsExpandableListFeesPaidAdapter;
import com.cattechnologies.tpg.Adapters.ReportsFeesPaidListAdapter;
import com.cattechnologies.tpg.Adapters.ReportsFeesPaidSearchListAdapter;
import com.cattechnologies.tpg.Adapters.ReportsFeesPaidSearchSortListAdapter;
import com.cattechnologies.tpg.Adapters.ReportsFeesPaidSortListAdapter;
import com.cattechnologies.tpg.Model.FeesPaidChildInfo;
import com.cattechnologies.tpg.Model.FeesPaidGroupInfo;
import com.cattechnologies.tpg.Model.ReportFreePaidSearchSort;
import com.cattechnologies.tpg.Model.ReportFreePaidSearchSortNew;
import com.cattechnologies.tpg.Model.ReportParticulrFreePaid;
import com.cattechnologies.tpg.Model.ReportParticulrFreePaidNew;
import com.cattechnologies.tpg.Model.ReportParticulrFreePaidSort;
import com.cattechnologies.tpg.Model.ReportParticulrFreePaidSortNew;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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

public class ReportsFeesPaidFragment extends Fragment implements ExpandableListView.OnChildClickListener {


    public static final String ARG_SECTION_TITLE = "section_number";
    RecyclerView recyclerView;

    ReportsFeesPaidListAdapter mAdapter;
    ReportsFeesPaidSearchListAdapter mAdapterSearch;
    ReportsFeesPaidSortListAdapter mAdapterSort;
    ReportsFeesPaidSearchSortListAdapter mSearchSortListAdapter;
    ReportPerticularListAdapter mAdapterParticularList;
    ReportPerticulaSortListAdapter mAdapterParticularSortList;

    RecyclerView.LayoutManager mLayoutManager;
    TextView titulo, expand;
    String title, dashboardTitle;
    ReportsExpandableListFeesPaidAdapter listAdapter;
    ExpandableListView simpleExpandableListView;
    CompositeSubscription mSubscriptions;
    ProgressBar progressBar;
    String userId, userType, pageEfin, efinData;
    PreferencesManager preferencesManager;

    ReportsFeePaid reports;
    ReportsFeePaidSort reportsFeePaidSort;
    ReportFreePaidSearchSort reportFreePaidSearchSort;
    ReportsFeePaidSearch reportsFeePaidSearch;
    ReportParticulrFreePaid reportParticulrFreePaid;
    ReportParticulrFreePaidSort reportParticulrFreePaidSort;
    Button prev, next;
    EditText searchData;
    LinearLayout layout;
    int current_page, current_page_sort = 1, current_page_search = 1, current_page_mock;
    SearchView searchView;
    String totalPages;
    static int currentPage;
    int i = 0;
    int totalC = 1;
    // int j = 1;
    int data;
    String newText;

    MyExpandableadapterSb adapter;
    ExpandableListView myexpandable;
    List<String> parent;
    List<String> child;
    HashMap<String, List<String>> bind_and_display;

    String sort;

    Button btn;

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

    public static Fragment newInstance(String sectionTitle, String userId, String type, String page, String effin) {
        ReportsFeesPaidFragment fragment = new ReportsFeesPaidFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        args.putString("app_uid", userId);
        args.putString("acc_type", type);
        args.putString("efin_id", effin);
        args.putString("page", page);
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

        if (efinData == null) {
            feePaidReportsData(userId, userType, reports.getPage());

        }

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
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_login);
        layout = (LinearLayout) getActivity().findViewById(R.id.button_list);
        // simpleExpandableListView = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView);
        userId = getArguments().getString("app_uid");
        userType = getArguments().getString("acc_type");

        pageEfin = getArguments().getString("page");
        efinData = getArguments().getString("efin_id");

        bind_and_display = new HashMap<String, List<String>>();
        parent = new ArrayList<String>();
        child = new ArrayList<String>();
        mSubscriptions = new CompositeSubscription();
        preferencesManager = new PreferencesManager();
        myexpandable = (ExpandableListView) getActivity().findViewById(R.id.theexpandables);
        parent = Arrays.asList(getResources().getStringArray(R.array.Parent_Head_Report_Fees_Paid));
        bind_and_display.put(parent.get(0), Arrays.asList(getResources().getStringArray(R.array.child_report_feepaid)));

        adapter = new MyExpandableadapterSb(getContext(), parent, bind_and_display);
        myexpandable.setAdapter(adapter);
        myexpandable.setOnChildClickListener(this);


        reports = new ReportsFeePaid();
        reportsFeePaidSort = new ReportsFeePaidSort();
        reportsFeePaidSearch = new ReportsFeePaidSearch();
        reportParticulrFreePaid = new ReportParticulrFreePaid();
        reportParticulrFreePaidSort = new ReportParticulrFreePaidSort();
        reportFreePaidSearchSort = new ReportFreePaidSearchSort();
        reports.setPage("1");
        reportsFeePaidSearch.setPage("1");
        reportsFeePaidSort.setPage("1");
        reportFreePaidSearchSort.setPage("1");
        reportParticulrFreePaid.setPage(pageEfin);
        reportParticulrFreePaidSort.setPage(pageEfin);
        if (layout != null) {
            layout.removeAllViews();
        }
        if (searchData.getText().toString().isEmpty()) {
            feePaidReportsData(userId, userType, reports.getPage());


        }


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

                newText = editable.toString().toLowerCase();
                if (newText.isEmpty()) {
                    feePaidReportsData(userId, userType, reports.getPage());
                    recyclerView.setVisibility(View.VISIBLE);
                    prev.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                } else if (!TextUtils.isEmpty(newText)) {

                    searchReportItem(userId, userType, reportsFeePaidSearch.getPage(), newText);
                    recyclerView.setVisibility(View.VISIBLE);
                    prev.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                }

            }
        });

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration divider =
                new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.my_custom_divider));
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

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
            List<ReportsFeePaidSearchNew> reportsFeePaidNewList = response.getFeeReport_data();

            mAdapterSearch = new ReportsFeesPaidSearchListAdapter(getActivity(), reportsFeePaidNewList, title);
            recyclerView.setAdapter(mAdapterSearch);
            mAdapterSearch.notifyDataSetChanged();

            if (layout != null) {
                layout.removeAllViews();
            }
            int totalPage = Integer.parseInt(totalPages);
            if (totalPage == 1) {

                prev.setVisibility(View.GONE);
                next.setVisibility(View.GONE);
            } else {
                for (current_page = 0; current_page < totalPage; current_page++) {
                    final Button btn = new Button(getActivity());
                    int width = (int) getResources().getDimension(R.dimen.dim_40);
                    int hieght = (int) getResources().getDimension(R.dimen.dim_40);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, hieght);
                    //  lp.setMargins(5, 5, 5, 5);
                    btn.setId(current_page);
                    btn.setText("" + (current_page + 1));
                    btn.setLayoutParams(lp);
                    layout.addView(btn);


                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //   reports = new ReportsFeePaid();
                            ///  final int index = current_page_search + 1;
                            String currentBtnText = btn.getText().toString();
                            current_page_search = Integer.parseInt(currentBtnText);
                            final int index = current_page_search;
                            reportsFeePaidSearch.setPage(String.valueOf(index));
                            System.out.println("ReportsFeesPaidFragment.onClick" + index);
                            searchReportItem(userId, userType, reportsFeePaidSearch.getPage(), newText);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);

                        }
                    });
                    prev.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("ReportsFeesPaidFragment.onClick===" + current_page_search);

                            if (current_page_search > 1 && totalPage >= current_page_search) {
                                current_page_search = current_page_search - 1;
                                reportsFeePaidSearch.setPage(String.valueOf(current_page_search));
                            }


                            System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());

                            searchReportItem(userId, userType, reportsFeePaidSearch.getPage(), newText);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);

                        }
                    });
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("ReportsFeesPaidFragment.onClick===" + current_page_search);
                            if (current_page_search < totalPage) {
                                current_page_search = current_page_search + 1;
                                reportsFeePaidSearch.setPage(String.valueOf(current_page_search));
                            }
                            searchReportItem(userId, userType, reportsFeePaidSearch.getPage(), newText);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });

                }


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
        } else if (response.getStatus().equalsIgnoreCase("fail")) {
            showToast(response.getMessage());
            recyclerView.setVisibility(View.GONE);
            prev.setVisibility(View.GONE);
            next.setVisibility(View.GONE);
            layout.removeAllViews();
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
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
            List<ReportsFeePaidNew> reportsFeePaidNewList = response.getFeeReport_data();

            mAdapter = new ReportsFeesPaidListAdapter(getActivity(), reportsFeePaidNewList, title);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            if (layout != null) {
                layout.removeAllViews();
            }
            int totalPage = Integer.parseInt(totalPages);
            if (totalPage == 1) {
                prev.setVisibility(View.GONE);
                next.setVisibility(View.GONE);
            } else {
                for (current_page = 0; current_page < totalPage; current_page++) {
                    final Button btn = new Button(getActivity());
                    int width = (int) getResources().getDimension(R.dimen.dim_40);
                    int hieght = (int) getResources().getDimension(R.dimen.dim_40);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, hieght);
                    //  lp.setMargins(5, 5, 5, 5);
                    btn.setId(current_page);
                    btn.setText("" + (current_page + 1));
                    btn.setLayoutParams(lp);
                    layout.addView(btn);


                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //   reports = new ReportsFeePaid()
                           // preferencesManager.setParticularPerson(getActivity(),btn.getText().toString());
                            btn.setBackgroundColor(getResources().getColor(R.color.selector));
                            current_page_mock = Integer.parseInt(btn.getText().toString());
                            final int index = current_page_mock;
                            reports.setPage(String.valueOf(index));
                            feePaidReportsData(userId, userType, reports.getPage());
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);

                        }
                    });

                }
                //  currentPage=totalPage;
                prev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //  System.out.println("ReportsFeesPaidFragment.onClick===" + index);
                        if (current_page_mock <= totalPage) {


                            if (current_page_mock <= 1) {

                                reports.setPage(String.valueOf(1));
                            } else {
                                current_page_mock = current_page_mock - 1;
                                // current_page_mock= currentPage-1;
                                reports.setPage(String.valueOf(current_page_mock));
                            }

                        }
                        feePaidReportsData(userId, userType, reports.getPage());
                        recyclerView.setVisibility(View.VISIBLE);
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);

                    }
                });
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //   System.out.println("ReportsFeesPaidFragment.onClick===" + index);
                        if (current_page_mock < totalPage) {
                            current_page_mock++;
                            reports.setPage(String.valueOf(current_page_mock));

                        }
                        feePaidReportsData(userId, userType, reports.getPage());
                        recyclerView.setVisibility(View.VISIBLE);
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);

                    }
                });


            }


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


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        int gposition = groupPosition;
        int cposition = childPosition;

        Displayitemclicked(gposition, cposition, parent);
        //passing the integer value of grouposition and childposition to the above method when an item is clicked
        return false;
    }

    private void Displayitemclicked(int gposition, int cposition, ExpandableListView parentList) {

        if (gposition == 0) {

            switch (cposition) {

                case 0:
                    progressBar.setVisibility(View.VISIBLE);

                    sort = "disbursment_date";
                    if (newText == null) {
                        sortReportItem(userId, userType, reportsFeePaidSort.getPage(), sort);

                    } else {
                        searchSortReportData(userId, userType, newText, reportFreePaidSearchSort.getPage(), sort);

                    }


                    parentList.collapseGroup(0);

                    progressBar.setVisibility(View.GONE);

                    break;
                case 1:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "ssn";

                    if (newText == null) {
                        sortReportItem(userId, userType, reportsFeePaidSort.getPage(), sort);

                    } else {
                        searchSortReportData(userId, userType, newText, reportFreePaidSearchSort.getPage(), sort);

                    }


                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;
                case 2:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "lastname";


                    if (newText == null) {
                        sortReportItem(userId, userType, reportsFeePaidSort.getPage(), sort);

                    } else {
                        searchSortReportData(userId, userType, newText, reportFreePaidSearchSort.getPage(), sort);

                    }

                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;
                case 3:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "product_type";

                    if (newText == null) {
                        sortReportItem(userId, userType, reportsFeePaidSort.getPage(), sort);

                    } else {
                        searchSortReportData(userId, userType, newText, reportFreePaidSearchSort.getPage(), sort);

                    }

                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;
            }
        }
    }

    private void searchSortReportData(String userId, String userType, String newText, String page, String sort) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidDataSearchSort
                    (userId, userType, newText, page, sort)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponseSearchSort, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }
    }

    private void handleResponseSearchSort(ReportFreePaidSearchSort response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportFreePaidSearchSortNew> reportsFeePaidNewList = response.getFeeReport_data();

            mSearchSortListAdapter = new ReportsFeesPaidSearchSortListAdapter(getActivity(), reportsFeePaidNewList, title);
            recyclerView.setAdapter(mSearchSortListAdapter);
            mSearchSortListAdapter.notifyDataSetChanged();
            if (layout != null) {
                layout.removeAllViews();
            }
            int totalPage = Integer.parseInt(totalPages);
            if (totalPage == 1) {
                prev.setVisibility(View.GONE);
                next.setVisibility(View.GONE);
            } else {
                for (current_page = 0; current_page < totalPage; current_page++) {
                    final Button btn = new Button(getActivity());
                    int width = (int) getResources().getDimension(R.dimen.dim_40);
                    int hieght = (int) getResources().getDimension(R.dimen.dim_40);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, hieght);
                    //  lp.setMargins(5, 5, 5, 5);
                    btn.setId(current_page);
                    btn.setText("" + (current_page + 1));
                    btn.setLayoutParams(lp);
                    layout.addView(btn);


                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //   reports = new ReportsFeePaid();
                            current_page_sort = Integer.parseInt(btn.getText().toString());
                            final int index = current_page_sort;

                            reportFreePaidSearchSort.setPage(String.valueOf(index));
                            System.out.println("ReportsFeesPaidFragment.onClick" + index);
                            searchSortReportData(userId, userType, newText, reportFreePaidSearchSort.getPage(), sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);

                        }
                    });
                    prev.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("ReportsFeesPaidFragment.onClick===" + current_page_sort);
                            if (current_page_sort > 1 && current_page_sort <= totalPage) {
                                current_page_sort = current_page_sort - 1;
                                reportFreePaidSearchSort.setPage(String.valueOf(current_page_sort));
                            }
                            System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());

                            searchSortReportData(userId, userType, newText, reportFreePaidSearchSort.getPage(), sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);

                        }
                    });
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("ReportsFeesPaidFragment.onClick===" + current_page_sort);
                            if (current_page_sort < totalPage) {
                                current_page_sort = current_page_sort + 1;
                                reportFreePaidSearchSort.setPage(String.valueOf(current_page_sort));
                            }
                            System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());

                            System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());
                            searchSortReportData(userId, userType, newText, reportFreePaidSearchSort.getPage(), sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });

                }

            }


            mSearchSortListAdapter.setClickListener((view, position) -> {
                final ReportFreePaidSearchSortNew reports = reportsFeePaidNewList.get(position);
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


    private void sortReportItem(String userId, String userType, String page, String type) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidDataSort
                    (userId, userType, page, type)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponseSort, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }

    }

    private void handleResponseSort(ReportsFeePaidSort response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportsFeePaidSortNew> reportsFeePaidNewList = response.getFeeReport_data();

            mAdapterSort = new ReportsFeesPaidSortListAdapter(getActivity(), reportsFeePaidNewList, title);
            recyclerView.setAdapter(mAdapterSort);
            mAdapterSort.notifyDataSetChanged();
            if (layout != null) {
                layout.removeAllViews();
            }
            int totalPage = Integer.parseInt(totalPages);
            if (totalPage == 1) {
                prev.setVisibility(View.GONE);
                next.setVisibility(View.GONE);
            } else {
                for (current_page = 0; current_page < totalPage; current_page++) {
                    final Button btn = new Button(getActivity());
                    int width = (int) getResources().getDimension(R.dimen.dim_40);
                    int hieght = (int) getResources().getDimension(R.dimen.dim_40);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, hieght);
                    //  lp.setMargins(5, 5, 5, 5);
                    btn.setId(current_page);
                    btn.setText("" + (current_page + 1));
                    btn.setLayoutParams(lp);
                    layout.addView(btn);


                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //   reports = new ReportsFeePaid();
                            current_page_sort = Integer.parseInt(btn.getText().toString());
                            final int index = current_page_sort;

                            reportsFeePaidSort.setPage(String.valueOf(index));
                            System.out.println("ReportsFeesPaidFragment.onClick" + index);
                            sortReportItem(userId, userType, reportsFeePaidSort.getPage(), sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);

                        }
                    });
                    prev.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("ReportsFeesPaidFragment.onClick===" + current_page_sort);
                            if (current_page_sort > 1 && current_page_sort <= totalPage) {
                                current_page_sort = current_page_sort - 1;
                                reportsFeePaidSort.setPage(String.valueOf(current_page_sort));
                            }
                            System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());

                            sortReportItem(userId, userType, reportsFeePaidSort.getPage(), sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);

                        }
                    });
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("ReportsFeesPaidFragment.onClick===" + current_page_sort);
                            if (current_page_sort < totalPage) {
                                current_page_sort = current_page_sort + 1;
                                reportsFeePaidSort.setPage(String.valueOf(current_page_sort));
                            }
                            System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());

                            System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());
                            sortReportItem(userId, userType, reportsFeePaidSort.getPage(), sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });
                }


            }


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


}
