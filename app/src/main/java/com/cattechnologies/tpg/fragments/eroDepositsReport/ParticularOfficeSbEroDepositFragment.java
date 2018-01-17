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
import com.cattechnologies.tpg.adapters.MyExpandableadapterSb;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportsEroDepositParticularSearchListAdapter;
import com.cattechnologies.tpg.adapters.feePaidReportAdapter.ReportsParticularFeesPaidSearchListAdapter;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportEroDepositPerticulaSortListAdapter;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportEroDepositPerticularListAdapter;
import com.cattechnologies.tpg.adapters.eroDepositsReportAdapter.ReportsEroDepositParticularSearchSortListAdapter;
import com.cattechnologies.tpg.adapters.feePaidReportAdapter.ReportPerticulaSortListAdapter;
import com.cattechnologies.tpg.adapters.feePaidReportAdapter.ReportPerticularListAdapter;
import com.cattechnologies.tpg.adapters.feePaidReportAdapter.ReportsFeesPaidParticularSearchSortListAdapter;
import com.cattechnologies.tpg.fragments.feepaidReport.ParticularOfficeSbFeesPaidFragment;
import com.cattechnologies.tpg.fragments.feepaidReport.ReportsFeesPaidDetailsFragment;
import com.cattechnologies.tpg.model.eroDepositModel.ReportEroDepositsPerticularSearchSort;
import com.cattechnologies.tpg.model.eroDepositModel.ReportParticulrEroDeposits;
import com.cattechnologies.tpg.model.eroDepositModel.ReportParticulrEroDepositsNew;
import com.cattechnologies.tpg.model.eroDepositModel.ReportParticulrEroDepositsSearchSortNew;
import com.cattechnologies.tpg.model.eroDepositModel.ReportParticulrEroDepositsSort;
import com.cattechnologies.tpg.model.eroDepositModel.ReportParticulrEroDepositsSortNew;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDeposit;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsSearch;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsSort;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsPerticularEroDepositsSearch;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsPerticularEroDepositsSearchNew;
import com.cattechnologies.tpg.model.feePaidModel.ReportsPerticularFeePaidSearch;
import com.cattechnologies.tpg.model.feePaidModel.ReportsPerticularFeePaidSearchNew;
import com.cattechnologies.tpg.model.feePaidModel.ReportFreePaidPerticularSearchSort;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaid;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaidNew;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaidSearchSortNew;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaidSort;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaidSortNew;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaidSearch;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaidSort;
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

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by admin on 1/11/2018.
 */

public class ParticularOfficeSbEroDepositFragment extends Fragment implements ExpandableListView.OnChildClickListener {

    public static final String ARG_SECTION_TITLE = "section_number";
    RecyclerView recyclerView;

    ReportsEroDepositParticularSearchListAdapter mAdapterSearch;
    ReportEroDepositPerticularListAdapter mAdapterParticularList;
    ReportEroDepositPerticulaSortListAdapter mAdapterParticularSortList;
    ReportsEroDepositParticularSearchSortListAdapter mSearchSortListAdapter;

    TextView titulo,textNoData;
    String  pagNo = "";
    CompositeSubscription mSubscriptions;
    ProgressBar progressBar;
    String userId, userType, pageEfin, efinData;
    PreferencesManager preferencesManager;

    ReportsEroDeposit reports;
    ReportsEroDepositsSort reportsFeePaidSort;
    ReportsEroDepositsSearch reportsFeePaidSearch;
    ReportParticulrEroDeposits reportParticulrFreePaid;
    ReportParticulrEroDepositsSort reportParticulrFreePaidSort;
    ReportEroDepositsPerticularSearchSort reportFreePaidParticulrSearchSort;
    ReportsPerticularEroDepositsSearch reportsPerticularFeePaidSearch;

    Button prev, next;
    EditText searchData;
    LinearLayout layout;
    int current_page, current_page_mock, current_page_search = 1, current_page_sort = 1;
    String title, newText, sort;

    MyExpandableadapterSb adapter;
    ExpandableListView myexpandable;
    List<String> parent;
    List<String> child;
    HashMap<String, List<String>> bind_and_display;

    HorizontalScrollView horizontalScrollView;
    ScrollView scrollView;
    Button btn;
    int wdth;

    public static Fragment newInstance(String sectionTitle, String userId, String type, String page, String effin) {
        ParticularOfficeSbEroDepositFragment fragment = new ParticularOfficeSbEroDepositFragment();
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
        titulo.setText(ARG_SECTION_TITLE);
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
        parent = Arrays.asList(getResources().getStringArray(R.array.Parent_Head_Ero_Deposits));
        bind_and_display.put(parent.get(0), Arrays.asList(getResources().getStringArray(R.array.child_report_ero_deposits)));

        adapter = new MyExpandableadapterSb(getActivity(), parent, bind_and_display);
        myexpandable.setAdapter(adapter);
        myexpandable.setOnChildClickListener(this);
        reports = new ReportsEroDeposit();
        reportsFeePaidSort = new ReportsEroDepositsSort();
        reportsFeePaidSearch = new ReportsEroDepositsSearch();
        reportParticulrFreePaid = new ReportParticulrEroDeposits();
        reportParticulrFreePaidSort = new ReportParticulrEroDepositsSort();
        reportFreePaidParticulrSearchSort = new ReportEroDepositsPerticularSearchSort();
        reportsPerticularFeePaidSearch = new ReportsPerticularEroDepositsSearch();
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
                    layout.setVisibility(View.VISIBLE);
                } else if (!TextUtils.isEmpty(newText)) {
                    particularOfficeSearch(userId, userType, reportsPerticularFeePaidSearch.getPage(), newText, efinData);

                    recyclerView.setVisibility(View.VISIBLE);
                    prev.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    layout.setVisibility(View.VISIBLE);
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
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getPerticularEroDepositsSearch(userId, userType, page, newText, efinData)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }

    }


    private void handleResponse(ReportsPerticularEroDepositsSearch response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportsPerticularEroDepositsSearchNew> reportsFeePaidNewList = response.getEroReport_data();

            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);

            mAdapterSearch = new ReportsEroDepositParticularSearchListAdapter(getActivity(), reportsFeePaidNewList, title);
            recyclerView.setAdapter(mAdapterSearch);
            mAdapterSearch.notifyDataSetChanged();
            // layout.setVisibility(View.VISIBLE);

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
                            LinearLayout.LayoutParams.WRAP_CONTENT);                    //  lp.setMargins(5, 5, 5, 5);
                    btn.setId(current_page);
                    btn.setText("" + (current_page + 1));
                    if (!pagNo.isEmpty()) {
                        if (current_page == (Integer.parseInt(pagNo) - 1)) {
                            btn.setBackgroundColor(Color.parseColor("#808080"));
                        }
                    } else {
                        if (current_page == 0) {
                            btn.setBackgroundColor(Color.parseColor("#808080"));

                        }
                    }

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
                          //  reportsPerticularFeePaidSearch.setPage(String.valueOf(index));
                          //  pagNo = reportsPerticularFeePaidSearch.getPage();
                            particularOfficeSearch(userId, userType,pagNo, newText, efinData);
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
                                reportsPerticularFeePaidSearch.setPage(String.valueOf(current_page_search));
                            }
                            pagNo = String.valueOf(Integer.parseInt(pagNo) - 1);
                            wdth = horizontalScrollView.getScrollX() - btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                          //  pagNo = reportsPerticularFeePaidSearch.getPage();
                            particularOfficeSearch(userId, userType,pagNo, newText, efinData);
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

                            if (pagNo.equalsIgnoreCase("")) {
                                pagNo = String.valueOf(2);
                            } else {
                                pagNo = String.valueOf(Integer.parseInt(pagNo) + 1);
                            }
                            wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                       //     pagNo = reportsPerticularFeePaidSearch.getPage();
                            particularOfficeSearch(userId, userType, pagNo, newText, efinData);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);

                        }
                    });

                }


                mAdapterSearch.setClickListener((view, position) -> {
                    final ReportsPerticularEroDepositsSearchNew reports = reportsFeePaidNewList.get(position);
                    Dashboard activity = (Dashboard) view.getContext();
                    Fragment fragment = ReportsEroDepositsDetailsFragment.newInstance(title,
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

            }
        } else if (response.getStatus().equalsIgnoreCase("fail")) {
            showToast(response.getMessage());
            recyclerView.setVisibility(View.GONE);
            prev.setVisibility(View.GONE);
            next.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);
        }
    }


    private void particularReportData(String userId, String userType, String page, String efinData) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getEroDepositsParticularData(userId, userType, page, efinData)
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

        if (error instanceof retrofit2.adapter.rxjava.HttpException) {

            Gson gson = new GsonBuilder().create();

            try {
                String errorBody = ((retrofit2.adapter.rxjava.HttpException) error).response().errorBody().string();
                com.cattechnologies.tpg.model.Response response = gson.fromJson(errorBody, com.cattechnologies.tpg.model.Response.class);
                showToast(response.getMessage());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showToast("Network Error !");
        }

    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
    }


    private void handleResponse(ReportParticulrEroDeposits response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportParticulrEroDepositsNew> reportsFeePaidNewList = response.getEroReport_data();
            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
            mAdapterParticularList = new ReportEroDepositPerticularListAdapter(getActivity(), reportsFeePaidNewList, title);
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
                            current_page_mock = Integer.parseInt(btn.getText().toString());
                            int id = view.getId();
                            id = id + 1;
                            pagNo = String.valueOf(id);
                           /* wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);*/
                         //   reportParticulrFreePaid.setPage(String.valueOf(current_page_mock));
                          //  pagNo = reportParticulrFreePaid.getPage();
                            //System.out.println("ReportsFeesPaidFragment.onClick" + current_page_mock);
                            particularReportData(userId, userType, pagNo, efinData);
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
                            pagNo = String.valueOf(Integer.parseInt(pagNo) - 1);
                            wdth = horizontalScrollView.getScrollX() - btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                          //  pagNo = reportParticulrFreePaid.getPage();
                            particularReportData(userId, userType,pagNo , efinData);
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
                            if (pagNo.equalsIgnoreCase("")) {
                                pagNo = String.valueOf(2);
                            } else {
                                pagNo = String.valueOf(Integer.parseInt(pagNo) + 1);
                            }
                            wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                          //  pagNo = reportParticulrFreePaid.getPage();
                            particularReportData(userId, userType, pagNo, efinData);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                            horizontalScrollView.smoothScrollTo((int) horizontalScrollView.getScrollX() + 50, 0);

                        }
                    });

                }

            }

            mAdapterParticularList.setClickListener((view, position) -> {
                final ReportParticulrEroDepositsNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
                Fragment fragment = ReportsEroDepositsDetailsFragment.newInstance(title,
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

                    sort = "deposit_date";
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
                    sort = "deposit_type";

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
                    sort = "dan";
                    if (TextUtils.isEmpty(newText)) {
                        particularOfficeSort(userId, userType, reportParticulrFreePaidSort.getPage(), efinData, sort);

                    } else {
                        particularOfficeSearchSort(userId, userType, newText, reportFreePaidParticulrSearchSort.getPage(), sort);

                    }

                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;
             /*   case 3:
                    progressBar.setVisibility(View.VISIBLE);
                    sort = "product_type";

                    if (TextUtils.isEmpty(newText)) {
                        particularOfficeSort(userId, userType, reportParticulrFreePaidSort.getPage(), efinData, sort);

                    } else {
                        particularOfficeSearchSort(userId, userType, newText, reportFreePaidParticulrSearchSort.getPage(), sort);

                    }

                    parentList.collapseGroup(0);
                    progressBar.setVisibility(View.GONE);
                    break;*/
            }
        }
    }

    private void particularOfficeSearchSort(String userId, String userType, String newText, String page, String sort) {

        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getEroDepositsParticularDataSearchSort(userId, userType, newText, page, efinData, sort)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }

    }

    private void handleResponse(ReportEroDepositsPerticularSearchSort response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportParticulrEroDepositsSearchSortNew> reportsFeePaidNewList = response.getEroReport_data();

            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);

            mSearchSortListAdapter = new ReportsEroDepositParticularSearchSortListAdapter(getActivity(), reportsFeePaidNewList, title);
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
                          /*  wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);*/
                          //  btn.setBackgroundColor(Color.parseColor("#808080"));

                           // reportFreePaidParticulrSearchSort.setPage(String.valueOf(index));
                           // pagNo = reportFreePaidParticulrSearchSort.getPage();
                           // System.out.println("ReportsFeesPaidFragment.onClick" + index);
                            particularOfficeSearchSort(userId, userType, newText, pagNo, sort);
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
                                reportFreePaidParticulrSearchSort.setPage(String.valueOf(current_page_sort));
                            }

                            pagNo = String.valueOf(Integer.parseInt(pagNo) - 1);
                            wdth = horizontalScrollView.getScrollX() - btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                        //    System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());
                         //   pagNo = reportFreePaidParticulrSearchSort.getPage();
                            particularOfficeSearchSort(userId, userType, newText,pagNo, sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                            horizontalScrollView.smoothScrollTo((int) horizontalScrollView.getScrollX() - 50, (int) horizontalScrollView.getScrollY());


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


                            if (pagNo.equalsIgnoreCase("")) {
                                pagNo = String.valueOf(2);
                            } else {
                                pagNo = String.valueOf(Integer.parseInt(pagNo) + 1);
                            }
                            wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                          //  pagNo = reportFreePaidParticulrSearchSort.getPage();
                           // System.out.println("ReportsFeesPaidFragment.onClick==" + reports.getPage());
                            particularOfficeSearchSort(userId, userType, newText,pagNo, sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);

                        }
                    });
                }


            }


            mSearchSortListAdapter.setClickListener((view, position) -> {
                final ReportParticulrEroDepositsSearchSortNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
                Fragment fragment = ReportsEroDepositsDetailsFragment.newInstance(title,
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

        }


    }

    private void particularOfficeSort(String userId, String userType, String page, String efinData, String sort) {
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getEroDepositsParticularDataSort(userId, userType, page, efinData, sort)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");


        }
    }

    private void handleResponse(ReportParticulrEroDepositsSort response) {
        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            List<ReportParticulrEroDepositsSortNew> reportsFeePaidNewList = response.getEroReport_data();

            recyclerView.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);

            mAdapterParticularSortList = new ReportEroDepositPerticulaSortListAdapter(getActivity(), reportsFeePaidNewList, title);
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
                          /*  wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);*/
                            particularOfficeSort(userId, userType, pagNo, efinData, sort);
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
                                reportParticulrFreePaidSort.setPage(String.valueOf(current_page_sort));
                            }
                            pagNo = String.valueOf(Integer.parseInt(pagNo) - 1);
                            wdth = horizontalScrollView.getScrollX() - btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                          //  pagNo = reportParticulrFreePaidSort.getPage();
                            particularOfficeSort(userId, userType,pagNo, efinData, sort);
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

                            if (pagNo.equalsIgnoreCase("")) {
                                pagNo = String.valueOf(2);
                            } else {
                                pagNo = String.valueOf(Integer.parseInt(pagNo) + 1);
                            }
                            wdth = horizontalScrollView.getScrollX() + btn.getWidth();
                            horizontalScrollView.smoothScrollTo(wdth, 0);
                          //  pagNo = reportParticulrFreePaidSort.getPage();
                            particularOfficeSort(userId, userType,pagNo, efinData, sort);
                            recyclerView.setVisibility(View.VISIBLE);
                            prev.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);

                        }
                    });

                }


            }

            mAdapterParticularSortList.setClickListener((view, position) -> {
                final ReportParticulrEroDepositsSortNew reports = reportsFeePaidNewList.get(position);
                Dashboard activity = (Dashboard) view.getContext();
                Fragment fragment = ReportsEroDepositsDetailsFragment.newInstance(title,
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


        }

    }
}
