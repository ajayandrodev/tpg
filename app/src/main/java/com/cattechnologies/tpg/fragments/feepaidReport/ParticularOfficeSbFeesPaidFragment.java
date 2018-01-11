package com.cattechnologies.tpg.fragments.feepaidReport;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cattechnologies.tpg.adapters.MyExpandableadapterSb;
import com.cattechnologies.tpg.adapters.ReportsParticularFeesPaidSearchListAdapter;
import com.cattechnologies.tpg.adapters.feePaidReportAdapter.ReportPerticulaSortListAdapter;
import com.cattechnologies.tpg.adapters.feePaidReportAdapter.ReportPerticularListAdapter;
import com.cattechnologies.tpg.adapters.feePaidReportAdapter.ReportsExpandableListFeesPaidAdapter;
import com.cattechnologies.tpg.adapters.feePaidReportAdapter.ReportsFeesPaidListAdapter;
import com.cattechnologies.tpg.adapters.feePaidReportAdapter.ReportsFeesPaidParticularSearchSortListAdapter;
import com.cattechnologies.tpg.adapters.feePaidReportAdapter.ReportsFeesPaidSortListAdapter;
import com.cattechnologies.tpg.model.ReportsPerticularFeePaidSearch;
import com.cattechnologies.tpg.model.ReportsPerticularFeePaidSearchNew;
import com.cattechnologies.tpg.model.feePaidModel.ReportFreePaidPerticularSearchSort;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaid;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaidNew;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaidSearchSortNew;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaidSort;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaidSortNew;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaid;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaidSearch;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaidSort;
import com.cattechnologies.tpg.model.Response;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.utils.AppInternetStatus;
import com.cattechnologies.tpg.utils.NetworkUtil;
import com.cattechnologies.tpg.utils.PreferencesManager;
import com.cattechnologies.tpg.activities.Dashboard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
 * Created by admin on 1/6/2018.
 */

public class ParticularOfficeSbFeesPaidFragment extends Fragment implements ExpandableListView.OnChildClickListener {

    public static final String ARG_SECTION_TITLE = "section_number";
    RecyclerView recyclerView;

    ReportsFeesPaidListAdapter mAdapter;
    ReportsParticularFeesPaidSearchListAdapter mAdapterSearch;
    ReportsFeesPaidSortListAdapter mAdapterSort;
    ReportPerticularListAdapter mAdapterParticularList;
    ReportPerticulaSortListAdapter mAdapterParticularSortList;
    ReportsFeesPaidParticularSearchSortListAdapter mSearchSortListAdapter;

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
    ReportsFeePaidSearch reportsFeePaidSearch;
    ReportParticulrFreePaid reportParticulrFreePaid;
    ReportParticulrFreePaidSort reportParticulrFreePaidSort;
    ReportFreePaidPerticularSearchSort reportFreePaidParticulrSearchSort;
    ReportsPerticularFeePaidSearch reportsPerticularFeePaidSearch;

    Button prev, next;
    EditText searchData;
    LinearLayout layout;
    int current_page, current_page_mock, current_page_search = 1, current_page_sort = 1;
    SearchView searchView;
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

    public static Fragment newInstance(String sectionTitle, String userId, String type, String page, String effin) {
        ParticularOfficeSbFeesPaidFragment fragment = new ParticularOfficeSbFeesPaidFragment();
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
        View view = inflater.inflate(R.layout.reports_fragment_particular, container, false);

        return view;
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

        adapter = new MyExpandableadapterSb(getActivity(), parent, bind_and_display);
        myexpandable.setAdapter(adapter);
        myexpandable.setOnChildClickListener(this);
        reports = new ReportsFeePaid();
        reportsFeePaidSort = new ReportsFeePaidSort();
        reportsFeePaidSearch = new ReportsFeePaidSearch();
        reportParticulrFreePaid = new ReportParticulrFreePaid();
        reportParticulrFreePaidSort = new ReportParticulrFreePaidSort();
        reportFreePaidParticulrSearchSort = new ReportFreePaidPerticularSearchSort();
        reportsPerticularFeePaidSearch=new ReportsPerticularFeePaidSearch();
        reports.setPage("1");
        reportsFeePaidSearch.setPage("1");
        reportsFeePaidSort.setPage("1");
        reportParticulrFreePaid.setPage(pageEfin);
        reportParticulrFreePaidSort.setPage(pageEfin);
        reportFreePaidParticulrSearchSort.setPage(pageEfin);
        reportsPerticularFeePaidSearch.setPage(pageEfin);
        // particularReportData(userId, userType, reportParticulrFreePaid.getPage(), efinData);
        if (layout != null) {
            layout.removeAllViews();
        }
        if (searchData.getText().toString().isEmpty()) {
            particularReportData(userId, userType, reportParticulrFreePaid.getPage(), efinData);


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
                if (TextUtils.isEmpty(newText)) {
                    particularReportData(userId, userType, reportParticulrFreePaid.getPage(), efinData);
                    recyclerView.setVisibility(View.VISIBLE);
                    prev.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                } else if (!TextUtils.isEmpty(newText)) {
                    particularOfficeSearch(userId, userType, reportsPerticularFeePaidSearch.getPage(), newText,efinData);

                    //  searchReportItem(userId, userType, reportsFeePaidSearch.getPage(), newText);
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

    private void particularOfficeSearch(String userId, String userType, String page, String newText, String efinData) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getPerticularFeePaidSearch(userId, userType, page, newText,efinData)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }

    }




    private void handleResponse(ReportsPerticularFeePaidSearch response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportsPerticularFeePaidSearchNew> reportsFeePaidNewList = response.getFeeReport_data();

            mAdapterSearch = new ReportsParticularFeesPaidSearchListAdapter(getActivity(), reportsFeePaidNewList, title);
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
                            String currentBtnText = btn.getText().toString();
                            current_page_search = Integer.parseInt(currentBtnText);
                            final int index = current_page_search;
                            btn.setBackgroundColor(Color.parseColor("#808080"));

                            reportsPerticularFeePaidSearch.setPage(String.valueOf(index));
                            particularOfficeSearch(userId, userType, reportsPerticularFeePaidSearch.getPage(), newText,efinData);
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
                                reportsPerticularFeePaidSearch.setPage(String.valueOf(current_page_search));
                            }
                            particularOfficeSearch(userId, userType, reportsPerticularFeePaidSearch.getPage(), newText,efinData);
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
                                reportsPerticularFeePaidSearch.setPage(String.valueOf(current_page_search));
                            }

                            particularOfficeSearch(userId, userType, reportsPerticularFeePaidSearch.getPage(), newText,efinData);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });

                }


                mAdapterSearch.setClickListener((view, position) -> {
                    final ReportsPerticularFeePaidSearchNew reports = reportsFeePaidNewList.get(position);
                    Dashboard activity = (Dashboard) view.getContext();
                    android.support.v4.app.Fragment fragment = ReportsFeesPaidDetailsFragment.newInstance(title,
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





    private void particularReportData(String userId, String userType, String page, String efinData) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidParticularData
                    (userId, userType, page, efinData)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
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

    private void handleResponse(ReportParticulrFreePaid response) {
        System.out.println("ServiceBruoNewFragment.handleResponse===" + response.getFeeReport_data());
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportParticulrFreePaidNew> reportsFeePaidNewList = response.getFeeReport_data();

            mAdapterParticularList = new ReportPerticularListAdapter(getActivity(), reportsFeePaidNewList, title);
            recyclerView.setAdapter(mAdapterParticularList);
            mAdapterParticularList.notifyDataSetChanged();
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
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);                    //  lp.setMargins(5, 5, 5, 5);
                    btn.setId(current_page);
                    btn.setText("" + (current_page + 1));

//                btn.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
                    btn.setLayoutParams(lp);
                    layout.addView(btn);


                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //   reports = new ReportsFeePaid();
                            current_page_mock = Integer.parseInt(btn.getText().toString());
                            btn.setBackgroundColor(Color.parseColor("#808080"));

//                            ((Button) layout.getChildAt(btn.getId())).setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
                            reportParticulrFreePaid.setPage(String.valueOf(current_page_mock));
                            System.out.println("ReportsFeesPaidFragment.onClick" + current_page_mock);
                            particularReportData(userId, userType, reportParticulrFreePaid.getPage(), efinData);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);


                        }
                    });
                    prev.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                         /*   System.out.println("ReportsFeesPaidFragment.onClick===" + current_page_mock);
                            if (current_page_mock > 1 && current_page_mock < totalPage) {
                                current_page_mock--;
                                reportParticulrFreePaid.setPage(String.valueOf(current_page_mock));
                            }*/
                            //  System.out.println("ReportsFeesPaidFragment.onClick===" + index);
                            if (current_page_mock <= totalPage) {
                                if (current_page_mock <= 1) {
                                    reportParticulrFreePaid.setPage(String.valueOf(1));
                                } else {
                                    current_page_mock = current_page_mock - 1;
                                    // current_page_mock= currentPage-1;
                                    reportParticulrFreePaid.setPage(String.valueOf(current_page_mock));
                                }

                            }
                            particularReportData(userId, userType, reportParticulrFreePaid.getPage(), efinData);
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
                                reportParticulrFreePaid.setPage(String.valueOf(current_page_mock));

                            }
                         /*   if (current_page > current_page_mock) {
                                current_page_mock++;
                                reportParticulrFreePaid.setPage(String.valueOf(current_page_mock));
                            }*/
                            particularReportData(userId, userType, reportParticulrFreePaid.getPage(), efinData);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });

                }

            }

            mAdapterParticularList.setClickListener((view, position) -> {
                final ReportParticulrFreePaidNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
                android.support.v4.app.Fragment fragment = ReportsFeesPaidDetailsFragment.newInstance(title,
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

    @Override
    public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
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
                    System.out.println("ReportsFeesPaidFragment.Displayitemclicked" + efinData);
                    if (TextUtils.isEmpty(newText)) {
                        particularOfficeSort(userId, userType, reportParticulrFreePaidSort.getPage(), efinData, sort);

                    } else {
                        particularOfficeSearchSort(userId, userType, newText, reportFreePaidParticulrSearchSort.getPage(), sort);

                    }


                    parentList.collapseGroup(0);

                    progressBar.setVisibility(View.GONE);

                    break;
                case 1:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "ssn";

                    if (TextUtils.isEmpty(newText)) {
                        particularOfficeSort(userId, userType, reportParticulrFreePaidSort.getPage(), efinData, sort);

                    } else {
                        particularOfficeSearchSort(userId, userType, newText, reportFreePaidParticulrSearchSort.getPage(), sort);

                    }


                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;
                case 2:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "lastname";

                    if (TextUtils.isEmpty(newText)) {
                        particularOfficeSort(userId, userType, reportParticulrFreePaidSort.getPage(), efinData, sort);

                    } else {
                        particularOfficeSearchSort(userId, userType, newText, reportFreePaidParticulrSearchSort.getPage(), sort);

                    }

                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;
                case 3:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "product_type";

                    if (TextUtils.isEmpty(newText)) {
                        particularOfficeSort(userId, userType, reportParticulrFreePaidSort.getPage(), efinData, sort);

                    } else {
                        particularOfficeSearchSort(userId, userType, newText, reportFreePaidParticulrSearchSort.getPage(), sort);

                    }

                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;
            }
        }
    }

    private void particularOfficeSearchSort(String userId, String userType, String newText, String page, String sort) {

        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidParticularDataSearchSort
                    (userId, userType, newText, page, efinData, sort)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }

    }

    private void handleResponse(ReportFreePaidPerticularSearchSort response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportParticulrFreePaidSearchSortNew> reportsFeePaidNewList = response.getFeeReport_data();

            mSearchSortListAdapter = new ReportsFeesPaidParticularSearchSortListAdapter(getActivity(), reportsFeePaidNewList, title);
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
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);                    //  lp.setMargins(5, 5, 5, 5);
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
                            btn.setBackgroundColor(Color.parseColor("#808080"));

                            reportFreePaidParticulrSearchSort.setPage(String.valueOf(index));
                            System.out.println("ReportsFeesPaidFragment.onClick" + index);
                            particularOfficeSearchSort(userId, userType, newText, reportFreePaidParticulrSearchSort.getPage(), sort);
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
                                reportFreePaidParticulrSearchSort.setPage(String.valueOf(current_page_sort));
                            }
                            System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());

                            particularOfficeSearchSort(userId, userType, newText, reportFreePaidParticulrSearchSort.getPage(), sort);
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
                                reportFreePaidParticulrSearchSort.setPage(String.valueOf(current_page_sort));
                            }
                            System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());

                            System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());
                            particularOfficeSearchSort(userId, userType, newText, reportFreePaidParticulrSearchSort.getPage(), sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });
                }


            }


            mSearchSortListAdapter.setClickListener((view, position) -> {
                final ReportParticulrFreePaidSearchSortNew reports = reportsFeePaidNewList.get(position);
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

    private void particularOfficeSort(String userId, String userType, String page, String efinData, String sort) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidParticularDataSort
                    (userId, userType, page, efinData, sort)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }
    }

    private void handleResponse(ReportParticulrFreePaidSort response) {
        System.out.println("ServiceBruoNewFragment.handleResponse===" + response.getFeeReport_data());
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportParticulrFreePaidSortNew> reportsFeePaidNewList = response.getFeeReport_data();

            mAdapterParticularSortList = new ReportPerticulaSortListAdapter(getActivity(), reportsFeePaidNewList, title);
            recyclerView.setAdapter(mAdapterParticularSortList);
            mAdapterParticularSortList.notifyDataSetChanged();
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
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);                    //  lp.setMargins(5, 5, 5, 5);
                    btn.setId(current_page);
                    btn.setText("" + (current_page + 1));
                    btn.setLayoutParams(lp);
                    layout.addView(btn);


                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            current_page_sort = Integer.parseInt(btn.getText().toString());
                            final int index = current_page_sort;
                            btn.setBackgroundColor(Color.parseColor("#808080"));
                            reportParticulrFreePaidSort.setPage(String.valueOf(index));
                            particularOfficeSort(userId, userType, reportParticulrFreePaidSort.getPage(), efinData, sort);
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
                                reportParticulrFreePaidSort.setPage(String.valueOf(current_page_sort));
                            }

                            particularOfficeSort(userId, userType, reportParticulrFreePaidSort.getPage(), efinData, sort);
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
                                reportParticulrFreePaidSort.setPage(String.valueOf(current_page_sort));
                            }
                            particularOfficeSort(userId, userType, reportParticulrFreePaidSort.getPage(), efinData, sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });

                }


            }

            mAdapterParticularSortList.setClickListener((view, position) -> {
                final ReportParticulrFreePaidSortNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
                android.support.v4.app.Fragment fragment = ReportsFeesPaidDetailsFragment.newInstance(title,
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