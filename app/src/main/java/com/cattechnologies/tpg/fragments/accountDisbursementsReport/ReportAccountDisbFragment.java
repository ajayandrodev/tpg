package com.cattechnologies.tpg.fragments.accountDisbursementsReport;

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
import com.cattechnologies.tpg.adapters.accountDisbursementsReportAdapter.ReportsAccountDisbExpandableadapter;
import com.cattechnologies.tpg.adapters.accountDisbursementsReportAdapter.ReportsAccountDisbListAdapter;
import com.cattechnologies.tpg.adapters.accountDisbursementsReportAdapter.ReportsAccountDisbSearchListAdapter;
import com.cattechnologies.tpg.adapters.accountDisbursementsReportAdapter.ReportsAccountDisbSearchSortListAdapter;
import com.cattechnologies.tpg.adapters.accountDisbursementsReportAdapter.ReportsAccountDisbSortListAdapter;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportsEroDepositSearchListAdapter;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportsEroDepositSearchSortListAdapter;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportsEroDepositSortListAdapter;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportsEroDepostListAdapter;
import com.cattechnologies.tpg.model.Response;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportAccountDisbSearchNew;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportAccountDisbSearchSort;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportAccountDisbSearchSortNew;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportsAccountDisb;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportsAccountDisbNew;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportsAccountDisbSearch;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportsAccountDisbSort;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportsAccountDisbSortNew;
import com.cattechnologies.tpg.model.eroDepositModel.ReportEroDepositsSearchNew;
import com.cattechnologies.tpg.model.eroDepositModel.ReportEroDepositsSearchSort;
import com.cattechnologies.tpg.model.eroDepositModel.ReportEroDepositsSearchSortNew;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDeposit;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositNew;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsSearch;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsSort;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsSortNew;
import com.cattechnologies.tpg.utils.AppInternetStatus;
import com.cattechnologies.tpg.utils.NetworkUtil;
import com.cattechnologies.tpg.utils.PreferencesManager;
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
 * Created by admin on 1/11/2018.
 */

public class ReportAccountDisbFragment extends Fragment implements ExpandableListView.OnChildClickListener {

    public static final String ARG_SECTION_TITLE = "section_number";
    RecyclerView recyclerView;
    TextView titulo, textNoData;
    String title, newText, sort;
    CompositeSubscription mSubscriptions;
    ProgressBar progressBar;
    String userId, userType, pagNo = "";
    PreferencesManager preferencesManager;

    Button prev, next;
    EditText searchData;
    LinearLayout layout;
    int current_page, current_page_sort = 1, current_page_search = 1, current_page_mock;


    ReportsAccountDisbListAdapter mAdapter;
    ReportsAccountDisbSearchListAdapter mAdapterSearch;
    ReportsAccountDisbSortListAdapter mAdapterSort;
    ReportsAccountDisbSearchSortListAdapter mSearchSortListAdapter;


    ReportsAccountDisb reports;
    ReportsAccountDisbSort reportsFeePaidSort;
    ReportAccountDisbSearchSort reportFreePaidSearchSort;
    ReportsAccountDisbSearch reportsFeePaidSearch;


    ReportsAccountDisbExpandableadapter adapter;
    ExpandableListView myexpandable;
    List<String> parent;
    List<String> child;
    HashMap<String, List<String>> bind_and_display;

    HorizontalScrollView horizontalScrollView;
    ScrollView scrollView;
    Button btn;
    int wdth;


    public static Fragment newInstance(String sectionTitle, String userId, String type) {
        ReportAccountDisbFragment fragment = new ReportAccountDisbFragment();
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

    public ReportAccountDisbFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();
        ((Dashboard) getActivity()).setTitle("REPORTS");
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
        titulo.setText(title);
        prev = (Button) getActivity().findViewById(R.id.prev);
        next = (Button) getActivity().findViewById(R.id.next);
        horizontalScrollView = (HorizontalScrollView) getActivity().findViewById(R.id.horizontal);
        scrollView = (ScrollView) getActivity().findViewById(R.id.scroll_data);
        textNoData = (TextView) getActivity().findViewById(R.id.search_no_data);
        /**Updated **/ prev.setBackgroundColor(Color.parseColor("#DCDCDC"));
        /**Updated **/ next.setBackgroundColor(Color.parseColor("#DCDCDC"));

        searchData = (EditText) getActivity().findViewById(R.id.search_paid);
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_login);
        layout = (LinearLayout) getActivity().findViewById(R.id.button_list);
        userId = getArguments().getString("app_uid");
        userType = getArguments().getString("acc_type");
        layout.setVisibility(View.VISIBLE);

        bind_and_display = new HashMap<String, List<String>>();
        parent = new ArrayList<String>();
        child = new ArrayList<String>();
        mSubscriptions = new CompositeSubscription();
        preferencesManager = new PreferencesManager();
        myexpandable = (ExpandableListView) getActivity().findViewById(R.id.theexpandables);
        parent = Arrays.asList(getResources().getStringArray(R.array.Parent_Head_Account_Disbursement));
        bind_and_display.put(parent.get(0), Arrays.asList(getResources().getStringArray(R.array.child_report_account_disbursement)));

        adapter = new ReportsAccountDisbExpandableadapter(getActivity(), parent, bind_and_display);
        myexpandable.setAdapter(adapter);
        myexpandable.setOnChildClickListener(this);


        reports = new ReportsAccountDisb();
        reportsFeePaidSearch = new ReportsAccountDisbSearch();
        reportsFeePaidSort = new ReportsAccountDisbSort();
        reportFreePaidSearchSort = new ReportAccountDisbSearchSort();

        reports.setPage("1");
        reportsFeePaidSearch.setPage("1");
        reportsFeePaidSort.setPage("1");
        reportFreePaidSearchSort.setPage("1");

        if (layout != null) {
            layout.removeAllViews();
        }
        if (searchData.getText().toString().isEmpty()) {

            eroDepositReportsData(userId, userType, reports.getPage());


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
                    eroDepositReportsData(userId, userType, reports.getPage());
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
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getAccountDisbDataSearch(userId, userType, page, searchText)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }


    }

    private void eroDepositReportsData(String userId, String userType, String page) {
        System.out.println("ReportsFeesPaidFragment.eroDepositReportsData==" + userId + "==" + userType);
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getAccountDisbData(userId, userType, page)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }

    }

    private void handleResponse(ReportsAccountDisbSearch response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportAccountDisbSearchNew> reportsFeePaidNewList = response.getEroReport_data();
            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);

            mAdapterSearch = new ReportsAccountDisbSearchListAdapter(getActivity(), reportsFeePaidNewList, title);
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
                    //  lp.setMargins(5, 5, 5, 5);
                    lp.setMargins(5,0,5,0);
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
                          /*  wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);*/
                          //  reportsFeePaidSearch.setPage(String.valueOf(index));
                           // pagNo = reportsFeePaidSearch.getPage();
                           // System.out.println("ReportsFeesPaidFragment.onClick" + index);
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
                           // pagNo = reportsFeePaidSearch.getPage();
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
                          //  pagNo = reportsFeePaidSearch.getPage();
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
                mAdapterSearch.setClickListener((view, position) -> {
                    final ReportAccountDisbSearchNew reports = reportsFeePaidNewList.get(position);
                    Dashboard activity = (Dashboard) view.getContext();
                  /*  Fragment fragment = ReportsAccountDisbDetailsFragment.newInstance(title,
                            reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName()
                            , reports.getDAN(), reports.getDepositType(),
                            reports.getMasterefin(), reports.getDepositdate(),
                            reports.getDepositAmount(), reports.getReverseddate()
                    );*/
                    Fragment fragment = ReportsAccountDisbDetailsFragment.newInstance(title);
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
            layout.setVisibility(View.GONE);

        }
    }







    private void showToast(String msg) {
        Toast.makeText(getContext(), "" + msg, Toast.LENGTH_SHORT).show();
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

    private void handleResponse(ReportsAccountDisb response) {

        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            System.out.println("ReportsFeesPaidFragment.handleResponse==" + totalPages);
            List<ReportsAccountDisbNew> reportsFeePaidNewList = response.getEroReport_data();
            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);

            mAdapter = new ReportsAccountDisbListAdapter(getActivity(), reportsFeePaidNewList, title);
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
                    //  lp.setMargins(5, 5, 5, 5);
                    lp.setMargins(5,0,5,0);
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
                          /*  wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);*/
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
                       // pagNo = reports.getPage();
                        pagNo = String.valueOf(Integer.parseInt(pagNo) - 1);
                        wdth = horizontalScrollView.getScrollX() - btn.getWidth();
                        horizontalScrollView.smoothScrollTo(wdth, 0);
                        eroDepositReportsData(userId, userType,pagNo);
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
                        if (pagNo.equalsIgnoreCase("")) {
                            pagNo = String.valueOf(2);
                        } else {
                            pagNo = String.valueOf(Integer.parseInt(pagNo) + 1);
                        }
                        wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                        horizontalScrollView.smoothScrollTo(wdth, 0);
                       // pagNo = reports.getPage();
                        eroDepositReportsData(userId, userType, pagNo);
                        recyclerView.setVisibility(View.VISIBLE);
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.VISIBLE);

                    }
                });


            }


            mAdapter.setClickListener((view, position) -> {
                final ReportsAccountDisbNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
             /*   Fragment fragment = ReportsAccountDisbDetailsFragment.newInstance(title,
                        reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName()
                        , reports.getDAN(), reports.getDepositType(),
                        reports.getMasterefin(), reports.getDepositdate(),
                        reports.getDepositAmount(), reports.getReverseddate()
                );*/
                Fragment fragment = ReportsAccountDisbDetailsFragment.newInstance(title);

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

                    sort = "deposit_date";
                    if (TextUtils.isEmpty(newText)) {
                        sortReportItem(userId, userType, reportsFeePaidSort.getPage(), sort);

                    } else {
                        searchSortReportData(userId, userType, newText, reportFreePaidSearchSort.getPage(), sort);

                    }


                    parentList.collapseGroup(0);

                    progressBar.setVisibility(View.GONE);

                    break;
                case 1:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "deposit_type";

                    if (TextUtils.isEmpty(newText)) {
                        sortReportItem(userId, userType, reportsFeePaidSort.getPage(), sort);

                    } else {
                        searchSortReportData(userId, userType, newText, reportFreePaidSearchSort.getPage(), sort);

                    }


                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;
                case 2:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "dan";


                    if (TextUtils.isEmpty(newText)) {
                        sortReportItem(userId, userType, reportsFeePaidSort.getPage(), sort);

                    } else {
                        searchSortReportData(userId, userType, newText, reportFreePaidSearchSort.getPage(), sort);

                    }

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

    private void searchSortReportData(String userId, String userType, String newText, String page, String sort) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getAccountDisbDataSearchSort(userId, userType, newText, page, sort)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponseSearchSort, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }
    }

    private void handleResponseSearchSort(ReportAccountDisbSearchSort response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportAccountDisbSearchSortNew> reportsFeePaidNewList = response.getEroReport_data();
            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);

            mSearchSortListAdapter = new ReportsAccountDisbSearchSortListAdapter(getActivity(), reportsFeePaidNewList, title);
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

                    lp.setMargins(5,0,5,0);
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
                           /* wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);*/
                          //  reportFreePaidSearchSort.setPage(String.valueOf(index));
                           // pagNo = reportFreePaidSearchSort.getPage();
                          //  System.out.println("ReportsFeesPaidFragment.onClick" + index);
                            searchSortReportData(userId, userType, newText,pagNo, sort);
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
                            System.out.println("ReportsFeesPaidFragment.onClick===" + current_page_sort);
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
                       //     System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());
                          //  pagNo = reportFreePaidSearchSort.getPage();
                          //  System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());
                            searchSortReportData(userId, userType, newText,pagNo, sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);

                        }
                    });

                }

            }


            mSearchSortListAdapter.setClickListener((view, position) -> {
                final ReportAccountDisbSearchSortNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
              /*  Fragment fragment = ReportsAccountDisbDetailsFragment.newInstance(title,
                        reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName()
                        , reports.getDAN(), reports.getDepositType(),
                        reports.getMasterefin(), reports.getDepositdate(),
                        reports.getDepositAmount(), reports.getReverseddate()
                );*/
                Fragment fragment = ReportsAccountDisbDetailsFragment.newInstance(title);

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
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getAccountDisbDataSort(userId, userType, page, type)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponseSort, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }

    }

    private void handleResponseSort(ReportsAccountDisbSort response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportsAccountDisbSortNew> reportsFeePaidNewList = response.getEroReport_data();
            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);

            mAdapterSort = new ReportsAccountDisbSortListAdapter(getActivity(), reportsFeePaidNewList, title);
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
                    lp.setMargins(5,0,5,0);
                    btn.setBackgroundColor(Color.parseColor("#DCDCDC"));
                    //  lp.setMargins(5, 5, 5, 5);
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
                          /*  wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);*/
                          //  reportsFeePaidSort.setPage(String.valueOf(index));
                           // pagNo = reportsFeePaidSort.getPage();
                          //  System.out.println("ReportsFeesPaidFragment.onClick" + index);
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
                            System.out.println("ReportsFeesPaidFragment.onClick===" + current_page_sort);
                            if (current_page_sort > 1 && current_page_sort <= totalPage) {
                                current_page_sort = current_page_sort - 1;
                                reportsFeePaidSort.setPage(String.valueOf(current_page_sort));
                            }
                            pagNo = String.valueOf(Integer.parseInt(pagNo) - 1);
                            wdth = horizontalScrollView.getScrollX() - btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                        //    System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());
                         //   pagNo = reportsFeePaidSort.getPage();
                            sortReportItem(userId, userType,pagNo, sort);
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
                           // pagNo = reportsFeePaidSort.getPage();
                            sortReportItem(userId, userType, pagNo, sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);

                        }
                    });
                }


            }


            mAdapterSort.setClickListener((view, position) -> {
                final ReportsAccountDisbSortNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
              /*  Fragment fragment = ReportsAccountDisbDetailsFragment.newInstance(title,
                        reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName()
                        , reports.getDAN(), reports.getDepositType(),
                        reports.getMasterefin(), reports.getDepositdate(),
                        reports.getDepositAmount(), reports.getReverseddate()
                );*/
                Fragment fragment = ReportsAccountDisbDetailsFragment.newInstance(title);

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