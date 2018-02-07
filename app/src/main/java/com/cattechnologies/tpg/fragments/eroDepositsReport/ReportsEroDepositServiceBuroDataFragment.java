package com.cattechnologies.tpg.fragments.eroDepositsReport;

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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.activities.Dashboard;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportsEroDepositServiceBuroSearchListAdapter;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportsEroDepositServiceBuroSearchSortListAdapter;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportsEroDepositServiceBuroSortListAdapter;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportsEroDepositExpandableadapter;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportsEroDepositServiceBuroListAdapter;
import com.cattechnologies.tpg.model.eroDepositModel.ReportEroDepositsSearchServiceBuroSort;
import com.cattechnologies.tpg.model.eroDepositModel.ReportEroDepositsServiceBuroSearchNew;
import com.cattechnologies.tpg.model.eroDepositModel.ReportEroDepositsServiceBuroSearchSortNew;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsServiceBuroSearch;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsServiceBuroSort;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsServiceBuroSortNew;
import com.cattechnologies.tpg.model.eroDepositModel.ReportEroDepositsSearchSort;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDeposit;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositNew;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsSearch;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsServiceBuro;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsServiceBuroNew;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsSort;
import com.cattechnologies.tpg.model.Response;
import com.cattechnologies.tpg.utils.AppInternetStatus;
import com.cattechnologies.tpg.utils.NetworkUtil;
import com.cattechnologies.tpg.utils.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ajay on 2/1/2018.
 */

public class ReportsEroDepositServiceBuroDataFragment extends Fragment implements ExpandableListView.OnChildClickListener {

    public static final String ARG_SECTION_TITLE = "section_number";
    RecyclerView recyclerView;
    TextView titulo, textNoData;
    String title, newText;
    CompositeSubscription mSubscriptions;
    ProgressBar progressBar;
    String userId, userType, pagNo = "";
    PreferencesManager preferencesManager;

    Button prev, next;
    EditText searchData;
    LinearLayout layout;
    int current_page, current_page_sort = 1, current_page_search = 1, current_page_mock;
    SimpleDateFormat format, format1;

    String sort = "";
    TextWatcher textWatcher;

    ReportsEroDepositServiceBuroListAdapter mAdapter;
    ReportsEroDepositServiceBuroSearchListAdapter mAdapterSearch;
    ReportsEroDepositServiceBuroSortListAdapter mAdapterSort;
    ReportsEroDepositServiceBuroSearchSortListAdapter mSearchSortListAdapter;


    ReportsEroDepositsServiceBuro reports;
    ReportsEroDepositsServiceBuroSearch reportsFeePaidSearch;
    ReportsEroDepositsServiceBuroSort reportsFeePaidSort;
    ReportEroDepositsSearchServiceBuroSort reportFreePaidSearchSort;


    ReportsEroDepositExpandableadapter adapter;
    ExpandableListView myexpandable;
    List<String> parent;
    List<String> child;
    HashMap<String, List<String>> bind_and_display;

    HorizontalScrollView horizontalScrollView;
    ScrollView scrollView;
    Button btn;
    int wdth;


    public static Fragment newInstance(String sectionTitle, String userId, String type) {
        ReportsEroDepositServiceBuroDataFragment fragment = new ReportsEroDepositServiceBuroDataFragment();
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
        View view = inflater.inflate(R.layout.reports_ero_deposit_fragment, container, false);

        return view;
    }

    public ReportsEroDepositServiceBuroDataFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();
        ((Dashboard) getActivity()).setTitle("REPORTS");
        // sortAndSearch(sort);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if (textWatcher != null) {
            searchData.addTextChangedListener(textWatcher);
        }
        searchDataInfo(true);
    }

    private void searchDataInfo(boolean b) {
        if (layout != null) {
            layout.removeAllViews();
        }
        if (searchData.getText().toString().isEmpty()) {
            if (sort.isEmpty()) {
                if (pagNo.equalsIgnoreCase("")) {
                    eroDepositReportsData(userId, userType, reports.getPage());
                } else {
                    eroDepositReportsData(userId, userType, pagNo);
                }
            } else {
                sortAndSearch(sort);
            }
        } else {
            if (sort.isEmpty()) {
                if (pagNo.equalsIgnoreCase("")) {
                    searchReportItem(userId, userType, reportsFeePaidSearch.getPage(), newText);
                } else {
                    searchReportItem(userId, userType, pagNo, newText);
                }
            } else {
                sortAndSearch(sort);
            }
        }
        searchData.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if ((actionId == EditorInfo.IME_ACTION_DONE)) {

                }
                return false;
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title = getArguments().getString(ARG_SECTION_TITLE);
        titulo = (TextView) getActivity().findViewById(R.id.title);
        titulo.setText(title);
        prev = (Button) getActivity().findViewById(R.id.prev);
        next = (Button) getActivity().findViewById(R.id.next);
        /**Updated **/prev.setBackgroundColor(Color.parseColor("#DCDCDC"));
        /**Updated **/next.setBackgroundColor(Color.parseColor("#DCDCDC"));
        horizontalScrollView = (HorizontalScrollView) getActivity().findViewById(R.id.horizontal);
        scrollView = (ScrollView) getActivity().findViewById(R.id.scroll_data);
        textNoData = (TextView) getActivity().findViewById(R.id.search_no_data);

        searchData = (EditText) getActivity().findViewById(R.id.search_paid);
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_login);
        layout = (LinearLayout) getActivity().findViewById(R.id.button_list);
        userId = getArguments().getString("app_uid");
        userType = getArguments().getString("acc_type");


        bind_and_display = new HashMap<String, List<String>>();
        parent = new ArrayList<String>();
        child = new ArrayList<String>();
        mSubscriptions = new CompositeSubscription();
        preferencesManager = new PreferencesManager();
        myexpandable = (ExpandableListView) getActivity().findViewById(R.id.theexpandables);
        parent = Arrays.asList(getResources().getStringArray(R.array.Parent_Head_Ero_Deposits));
        bind_and_display.put(parent.get(0), Arrays.asList(getResources().getStringArray(R.array.child_report_ero_deposits)));

        adapter = new ReportsEroDepositExpandableadapter(getActivity(), parent, bind_and_display);
        myexpandable.setAdapter(adapter);
        myexpandable.setOnChildClickListener(this);


        reports = new ReportsEroDepositsServiceBuro();
        reportsFeePaidSearch = new ReportsEroDepositsServiceBuroSearch();
        reportsFeePaidSort = new ReportsEroDepositsServiceBuroSort();
        reportFreePaidSearchSort = new ReportEroDepositsSearchServiceBuroSort();

        reports.setPage("1");
        reportsFeePaidSearch.setPage("1");
        reportsFeePaidSort.setPage("1");
        reportFreePaidSearchSort.setPage("1");

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration divider =
                new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.my_custom_divider));
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        if (textWatcher == null) {
            textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    newText = editable.toString().toLowerCase();
                    if (!pagNo.isEmpty()) {
                        pagNo = "";
                    }
                    if (TextUtils.isEmpty(newText)) {
                        if (pagNo.equalsIgnoreCase("")) {
                            eroDepositReportsData(userId, userType, reports.getPage());
                        } else {
                            eroDepositReportsData(userId, userType, pagNo);
                        }
                    } else if (!TextUtils.isEmpty(newText)) {
                        //searchReportItem(userId, userType, pagNo, newText);
                        if (pagNo.equalsIgnoreCase("")) {
                            searchReportItem(userId, userType, reportsFeePaidSearch.getPage(), newText);
                        } else {
                            searchReportItem(userId, userType, reportsFeePaidSearch.getPage(), newText);
                        }
                    }
                }
            };
        }


    }

    private void searchReportItem(String userId, String userType, String page, String searchText) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getEroDepositServiceBuroDataSearch(userId, userType, page, searchText)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            showToast("Internet Connection Is Not Available");
        }
    }

    private void handleResponse(ReportsEroDepositsServiceBuroSearch response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportEroDepositsServiceBuroSearchNew> reportsFeePaidNewList = response.getService_Bureau_EroReport_data();


            ReportEroDepositsServiceBuroSearchNew reportsFeePaidNew = new ReportEroDepositsServiceBuroSearchNew();
            format = new SimpleDateFormat("yyyyMMdd");
            //format1 = new SimpleDateFormat("MM-dd-yyyy");
            format1 = new SimpleDateFormat("MM-dd-yyyy");

            String chagnedDate = null;
            for (int i = 0; i < response.getService_Bureau_EroReport_data().size(); i++) {
                try {
                    chagnedDate = format1.format(format.parse(response.getService_Bureau_EroReport_data().get(i).getDepositdate()));
                    reportsFeePaidNew.setDepositdate(chagnedDate);
                    reportsFeePaidNewList.get(i).setDepositdate(reportsFeePaidNew.getDepositdate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
            mAdapterSearch = new ReportsEroDepositServiceBuroSearchListAdapter(getActivity(), reportsFeePaidNewList, title);
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
                    btn = new Button(getActivity());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(3, 0, 3, 0);
                    btn.setBackgroundColor(Color.parseColor("#DCDCDC"));
                    btn.setId(current_page);
                    btn.setText("" + (current_page + 1));
                    btn.setLayoutParams(lp);
                    layout.addView(btn);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String currentBtnText = btn.getText().toString();
                            current_page_search = Integer.parseInt(currentBtnText);
                            final int index = current_page_search;
                            int id = view.getId();
                            id = id + 1;
                            pagNo = String.valueOf(id);
                            searchReportItem(userId, userType, pagNo, newText);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });
                    if (!pagNo.isEmpty()) {
                        if (current_page == (Integer.parseInt(pagNo) - 1)) {
                            btn.setBackgroundColor(Color.parseColor("#808080"));
                        } else {
                        }
                        if (Integer.parseInt(pagNo) > 1) {
                            prev.setEnabled(true);
                        } else {
                            prev.setEnabled(false);
                        }
                        if (Integer.parseInt(pagNo) > (totalPage - 1)) {
                            next.setEnabled(false);
                        } else {
                            next.setEnabled(true);
                        }
                    } else {
                        if (current_page == 0) {
                            btn.setBackgroundColor(Color.parseColor("#808080"));
                            prev.setEnabled(false);
                        } else {
                        }
                    }
                    prev.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (current_page_search > 1 && totalPage >= current_page_search) {
                                current_page_search = current_page_search - 1;
                                reportsFeePaidSearch.setPage(String.valueOf(current_page_search));
                            }
                            pagNo = String.valueOf(Integer.parseInt(pagNo) - 1);
                            wdth = horizontalScrollView.getScrollX() - btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                            searchReportItem(userId, userType, pagNo, newText);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (current_page_search < totalPage) {
                                current_page_search = current_page_search + 1;
                                reportsFeePaidSearch.setPage(String.valueOf(current_page_search));
                            }
                            if (pagNo.equalsIgnoreCase("")) {
                                pagNo = String.valueOf(2);
                            } else {
                                pagNo = String.valueOf(Integer.parseInt(pagNo) + 1);
                            }
                            wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                            searchReportItem(userId, userType, pagNo, newText);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });

                }
            }
            mAdapterSearch.setClickListener((view, position) -> {
                final ReportEroDepositsServiceBuroSearchNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
                Fragment fragment = ReportsEroDepositServiceBuroDataDetailsFragment.newInstance(title,
                        reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName()
                        , reports.getDAN(), reports.getDepositType(),
                        reports.getMasterefin(), reports.getDepositdate(),
                        reports.getDepositAmount(), reports.getReverseddate()
                );
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .addToBackStack(null)
                        .commit();
                activity.getSupportActionBar().setTitle("REPORTS");
            });
        } else {
            progressBar.setVisibility(View.GONE);
            showToast(response.getMessage());
            recyclerView.setVisibility(View.GONE);
            prev.setVisibility(View.GONE);
            next.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);
        }

    }

    private void eroDepositReportsData(String userId, String userType, String page) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getEroDepositServiceBuroData(userId, userType, page)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            showToast("Internet Connection Is Not Available");
        }
    }

    private void handleError(Throwable error) {

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

    private void handleResponse(ReportsEroDepositsServiceBuro response) {

        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportsEroDepositsServiceBuroNew> reportsFeePaidNewList = response.getService_Bureau_EroReport_data();

            ReportsEroDepositNew reportsFeePaidNew = new ReportsEroDepositNew();
            format = new SimpleDateFormat("yyyyMMdd");
            //format1 = new SimpleDateFormat("MM-dd-yyyy");
            format1 = new SimpleDateFormat("MM-dd-yyyy");

            String chagnedDate = null;
            for (int i = 0; i < response.getService_Bureau_EroReport_data().size(); i++) {
                try {
                    chagnedDate = format1.format(format.parse(response.getService_Bureau_EroReport_data().get(i).getDepositdate()));
                    reportsFeePaidNew.setDepositdate(chagnedDate);
                    reportsFeePaidNewList.get(i).setDepositdate(reportsFeePaidNew.getDepositdate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
            mAdapter = new ReportsEroDepositServiceBuroListAdapter(getActivity(), reportsFeePaidNewList, title);
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
                    btn = new Button(getActivity());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(3, 0, 3, 0);
                    btn.setBackgroundColor(Color.parseColor("#DCDCDC"));
                    btn.setId(current_page);
                    btn.setText("" + (current_page + 1));
                    btn.setLayoutParams(lp);
                    layout.addView(btn);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            current_page_mock = Integer.parseInt(btn.getText().toString());
                            final int index = current_page_mock;
                            int id = view.getId();
                            id = id + 1;
                            pagNo = String.valueOf(id);
                            eroDepositReportsData(userId, userType, pagNo);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });
                    if (!pagNo.isEmpty()) {
                        if (current_page == (Integer.parseInt(pagNo) - 1)) {
                            btn.setBackgroundColor(Color.parseColor("#808080"));
                        } else {
                        }
                        if (Integer.parseInt(pagNo) > 1) {
                            prev.setEnabled(true);
                        } else {
                            prev.setEnabled(false);
                        }
                        if (Integer.parseInt(pagNo) > (totalPage - 1)) {
                            next.setEnabled(false);
                        } else {
                            next.setEnabled(true);
                        }
                    } else {
                        if (current_page == 0) {
                            btn.setBackgroundColor(Color.parseColor("#808080"));
                            prev.setEnabled(false);
                        } else {
                        }
                    }

                }
                prev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (current_page_mock <= totalPage) {
                            if (current_page_mock <= 1) {
                                reports.setPage(String.valueOf(1));
                            } else {
                                current_page_mock = current_page_mock - 1;
                                reports.setPage(String.valueOf(current_page_mock));
                            }
                        }
                        if (Integer.parseInt(pagNo) <= totalPage) {
                            if (Integer.parseInt(pagNo) <= 1) {
                                pagNo = String.valueOf(1);
                            } else {
                                pagNo = String.valueOf(Integer.parseInt(pagNo) - 1);

                            }
                        }
                        wdth = horizontalScrollView.getScrollX() - btn.getWidth();
                        horizontalScrollView.smoothScrollTo(wdth, 0);
                        eroDepositReportsData(userId, userType, pagNo);
                        recyclerView.setVisibility(View.VISIBLE);
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);

                    }
                });
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (current_page_mock < totalPage) {
                            current_page_mock++;
                            reports.setPage(String.valueOf(current_page_mock));
                        }
                        if (pagNo.equalsIgnoreCase("")) {
                            pagNo = String.valueOf(2);
                        } else {
                            if (Integer.parseInt(pagNo) < (totalPage))
                                pagNo = String.valueOf(Integer.parseInt(pagNo) + 1);
                        }
                        wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                        horizontalScrollView.smoothScrollTo(wdth, 0);
                        eroDepositReportsData(userId, userType, pagNo);
                        recyclerView.setVisibility(View.VISIBLE);
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                    }
                });
            }
            mAdapter.setClickListener((view, position) -> {
                final ReportsEroDepositsServiceBuroNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
                Fragment fragment = ReportsEroDepositServiceBuroDataDetailsFragment.newInstance(title,
                        reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName()
                        , reports.getDAN(), reports.getDepositType(),
                        reports.getMasterefin(), reports.getDepositdate(),
                        reports.getDepositAmount(), reports.getReverseddate()
                );
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .addToBackStack(null)
                        .commit();
                activity.getSupportActionBar().setTitle("REPORTS");
            });
        } else if (response.getStatus().equalsIgnoreCase("fail")) {
            progressBar.setVisibility(View.GONE);
            String totalPages = response.getTotalNoofPages();

            int totalPage = Integer.parseInt(totalPages);
            if (current_page_mock < totalPage || current_page_mock > totalPage) {

            } else {
                showToast(response.getMessage());

            }
        }
    }

    private void showToast(String message) {
        try {
            Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        int gposition = groupPosition;
        int cposition = childPosition;
        Displayitemclicked(gposition, cposition, parent);
        return false;
    }

    private void Displayitemclicked(int gposition, int cposition, ExpandableListView parentList) {
        if (gposition == 0) {
            switch (cposition) {
                case 0:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "deposit_date";
                    sortAndSearch(sort);
                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;
                case 1:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "deposit_type";
                    sortAndSearch(sort);

                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;
                case 2:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "dan";
                    sortAndSearch(sort);
                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;
              /*  case 3:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "dan";

                    if (TextUtils.isEmpty(newText)) {
                        sortReportItem(userId, userType, reportsFeePaidSort.getPage(), sort);

                    } else {
                        searchSortReportData(userId, userType, newText, reportFreePaidSearchSort.getPage(), sort);

                    }
                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;*/
            }
        }
    }

    private void sortAndSearch(String sort) {
        if (TextUtils.isEmpty(newText)) {
            if (pagNo.equalsIgnoreCase("")) {
                sortReportItem(userId, userType, reportsFeePaidSort.getPage(), sort);
            } else {
                sortReportItem(userId, userType, pagNo, sort);
            }
        } else {
            if (pagNo.equalsIgnoreCase("")) {
                searchSortReportData(userId, userType, newText, reportFreePaidSearchSort.getPage(), sort);
            } else {
                searchSortReportData(userId, userType, newText, pagNo, sort);
            }
        }
    }

    private void searchSortReportData(String userId, String userType, String newText, String page, String sort) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getEroDepositsServiceBuroDataSearchSort(userId, userType, newText, page, sort)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponseSearchSort, this::handleError));
        } else {
            showToast("Internet Connection Is Not Available");
        }
    }

    private void handleResponseSearchSort(ReportEroDepositsSearchServiceBuroSort response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportEroDepositsServiceBuroSearchSortNew> reportsFeePaidNewList = response.getService_Bureau_EroReport_data();
            ReportEroDepositsServiceBuroSearchSortNew reportsFeePaidNew = new ReportEroDepositsServiceBuroSearchSortNew();
            format = new SimpleDateFormat("yyyyMMdd");
            //format1 = new SimpleDateFormat("MM-dd-yyyy");
            format1 = new SimpleDateFormat("MM-dd-yyyy");
            String chagnedDate = null;
            for (int i = 0; i < response.getService_Bureau_EroReport_data().size(); i++) {
                try {
                    chagnedDate = format1.format(format.parse(response.getService_Bureau_EroReport_data().get(i).getDepositdate()));
                    reportsFeePaidNew.setDepositdate(chagnedDate);
                    reportsFeePaidNewList.get(i).setDepositdate(reportsFeePaidNew.getDepositdate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
            mSearchSortListAdapter = new ReportsEroDepositServiceBuroSearchSortListAdapter(getActivity(), reportsFeePaidNewList, title);
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
                    btn = new Button(getActivity());

                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(3, 0, 3, 0);
                    btn.setBackgroundColor(Color.parseColor("#DCDCDC"));//  lp.setMargins(5, 5, 5, 5);
                    btn.setId(current_page);
                    btn.setText("" + (current_page + 1));
                    btn.setLayoutParams(lp);
                    layout.addView(btn);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            current_page_sort = Integer.parseInt(btn.getText().toString());
                            final int index = current_page_sort;
                            int id = view.getId();
                            id = id + 1;
                            pagNo = String.valueOf(id);
                            searchSortReportData(userId, userType, newText, pagNo, sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });
                    if (!pagNo.isEmpty()) {
                        if (current_page == (Integer.parseInt(pagNo) - 1)) {
                            btn.setBackgroundColor(Color.parseColor("#808080"));
                        } else {
                        }
                        if (Integer.parseInt(pagNo) > 1) {
                            prev.setEnabled(true);
                        } else {
                            prev.setEnabled(false);
                        }
                        if (Integer.parseInt(pagNo) > (totalPage - 1)) {
                            next.setEnabled(false);
                        } else {
                            next.setEnabled(true);
                        }
                    } else {
                        if (current_page == 0) {
                            btn.setBackgroundColor(Color.parseColor("#808080"));
                            prev.setEnabled(false);
                        } else {
                        }
                    }
                    prev.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (current_page_sort > 1 && current_page_sort <= totalPage) {
                                current_page_sort = current_page_sort - 1;
                                reportFreePaidSearchSort.setPage(String.valueOf(current_page_sort));
                            }
                            pagNo = String.valueOf(Integer.parseInt(pagNo) - 1);
                            wdth = horizontalScrollView.getScrollX() - btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
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
                            if (pagNo.equalsIgnoreCase("")) {
                                pagNo = String.valueOf(2);
                            } else {
                                pagNo = String.valueOf(Integer.parseInt(pagNo) + 1);
                            }
                            wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                            searchSortReportData(userId, userType, newText, pagNo, sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
            mSearchSortListAdapter.setClickListener((view, position) -> {
                final ReportEroDepositsServiceBuroSearchSortNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
                Fragment fragment = ReportsEroDepositServiceBuroDataDetailsFragment.newInstance(title,
                        reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName()
                        , reports.getDAN(), reports.getDepositType(),
                        reports.getMasterefin(), reports.getDepositdate(),
                        reports.getDepositAmount(), reports.getReverseddate()
                );
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .addToBackStack(null)
                        .commit();
                activity.getSupportActionBar().setTitle("REPORTS");
            });
        } else {
          /*  progressBar.setVisibility(View.GONE);
            showToast(response.getMessage());*/
        }
    }

    private void sortReportItem(String userId, String userType, String page, String type) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getEroDepositsServiceBuroDataSort(userId, userType, page, type)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponseSort, this::handleError));
        } else {
            showToast("Internet Connection Is Not Available");
        }
    }

    private void handleResponseSort(ReportsEroDepositsServiceBuroSort response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportsEroDepositsServiceBuroSortNew> reportsFeePaidNewList = response.getService_Bureau_EroReport_data();
            ReportsEroDepositsServiceBuroSortNew reportsFeePaidNew = new ReportsEroDepositsServiceBuroSortNew();
            format = new SimpleDateFormat("yyyyMMdd");
            //format1 = new SimpleDateFormat("MM-dd-yyyy");
            format1 = new SimpleDateFormat("MM-dd-yyyy");

            String chagnedDate = null;
            for (int i = 0; i < response.getService_Bureau_EroReport_data().size(); i++) {
                try {
                    chagnedDate = format1.format(format.parse(response.getService_Bureau_EroReport_data().get(i).getDepositdate()));
                    reportsFeePaidNew.setDepositdate(chagnedDate);
                    reportsFeePaidNewList.get(i).setDepositdate(reportsFeePaidNew.getDepositdate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
            mAdapterSort = new ReportsEroDepositServiceBuroSortListAdapter(getActivity(), reportsFeePaidNewList, title);
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
                    btn = new Button(getActivity());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(3, 0, 3, 0);
                    btn.setBackgroundColor(Color.parseColor("#DCDCDC"));
                    btn.setId(current_page);
                    btn.setText("" + (current_page + 1));
                    btn.setLayoutParams(lp);
                    layout.addView(btn);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            current_page_sort = Integer.parseInt(btn.getText().toString());
                            final int index = current_page_sort;
                            int id = view.getId();
                            id = id + 1;
                            pagNo = String.valueOf(id);
                            sortReportItem(userId, userType, pagNo, sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });
                    if (!pagNo.isEmpty()) {
                        if (current_page == (Integer.parseInt(pagNo) - 1)) {
                            btn.setBackgroundColor(Color.parseColor("#808080"));
                        } else {
                        }
                        if (Integer.parseInt(pagNo) > 1) {
                            prev.setEnabled(true);
                        } else {
                            prev.setEnabled(false);
                        }
                        if (Integer.parseInt(pagNo) > (totalPage - 1)) {
                            next.setEnabled(false);
                        } else {
                            next.setEnabled(true);
                        }
                    } else {
                        if (current_page == 0) {
                            btn.setBackgroundColor(Color.parseColor("#808080"));
                            prev.setEnabled(false);
                        } else {
                        }
                    }
                    prev.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (current_page_sort > 1 && current_page_sort <= totalPage) {
                                current_page_sort = current_page_sort - 1;
                                reportsFeePaidSort.setPage(String.valueOf(current_page_sort));
                            }
                            pagNo = String.valueOf(Integer.parseInt(pagNo) - 1);
                            wdth = horizontalScrollView.getScrollX() - btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                            sortReportItem(userId, userType, pagNo, sort);
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
                            if (pagNo.equalsIgnoreCase("")) {
                                pagNo = String.valueOf(2);
                            } else {
                                pagNo = String.valueOf(Integer.parseInt(pagNo) + 1);
                            }
                            wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                            sortReportItem(userId, userType, pagNo, sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
            mAdapterSort.setClickListener((view, position) -> {
                final ReportsEroDepositsServiceBuroSortNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
                Fragment fragment = ReportsEroDepositServiceBuroDataDetailsFragment.newInstance(title,
                        reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName()
                        , reports.getDAN(), reports.getDepositType(),
                        reports.getMasterefin(), reports.getDepositdate(),
                        reports.getDepositAmount(), reports.getReverseddate()
                );
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .addToBackStack(null)
                        .commit();
                activity.getSupportActionBar().setTitle("REPORTS");
            });
        } else {
        /*    progressBar.setVisibility(View.GONE);
            showToast(response.getMessage());*/
        }
    }


}
