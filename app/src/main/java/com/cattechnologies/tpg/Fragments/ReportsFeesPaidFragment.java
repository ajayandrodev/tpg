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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.cattechnologies.tpg.Model.FeesPaidChildInfo;
import com.cattechnologies.tpg.Model.FeesPaidGroupInfo;
import com.cattechnologies.tpg.Model.ReportsFeePaid;
import com.cattechnologies.tpg.Model.ReportsFeePaidNew;
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


    List<ReportsFeePaidNew> reportsList;
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
    String userId, userType;
    PreferencesManager preferencesManager;

    ReportsFeePaidNew reports;
    Button prev, next;
    EditText searchData;


    private static int current_page = 1;
    SearchView searchView;
    int i = 0;
    int totalC = 1;
    int j = 0;
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
//        searchData = (EditText) getActivity().findViewById(R.id.search_paid);
        searchView = (SearchView) getActivity().findViewById(R.id.search_paid);

        searchView.setQueryHint("Search Customer");
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
        reports = new ReportsFeePaidNew();
        reports.setPage("1");

        reportsList = new ArrayList<>();

        feePaidReportsData(userId, userType, reports.getPage());
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                searchReportItem(userId, userType, reports.getPage(), newText);

                  /*  ArrayList<ReportsFeePaidNew> newList = new ArrayList<>();
                    for (ReportsFeePaidNew channel : reportsFeePaidNewList) {
                        String channelName = channel.getPrimaryFirstName().toLowerCase();
                        String lastName = channel.getPrimaryLastName().toLowerCase();
                        String ssn = channel.getPrimarySsn();
                        if (channelName.contains(newText) || ssn.contains(newText) || lastName.contains(newText)) {

                            newList.add(channel);
                            mAdapter.notifyDataSetChanged();


                        } else {
                            // Toast.makeText(getContext(), "We couldnot find any thing related to your search, Please try with different keywords", Toast.LENGTH_SHORT).show();
                        }

                    }
                    mAdapter.setFilter(newList);*/
                return true;
            }
        });
      /*  searchData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //mAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searhText = searchData.getText().toString().toLowerCase(Locale.getDefault());
                // adapter.filter(text);
                searchReportItem(userId, userType, reports.getPage(), searhText);

            }
        });*/
        if (preferencesManager.getReportDetailUsername(getContext()) == null) {
            data = 5;
        } else {
            data = preferencesManager.getReportDetailUsername(getContext()).length();
            System.out.println("ReportsFeesPaidFragment.onActivityCreated" + data);
        }

        for (int i = 0; i <= data; i++) {
            final Button btn = new Button(getActivity());
            int width = (int) getResources().getDimension(R.dimen.dim_35);
            int hieght = (int) getResources().getDimension(R.dimen.dim_35);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, hieght);
            //  lp.setMargins(5, 5, 5, 5);
            btn.setId(i);
            btn.setText("" + (i + 1));
            btn.setLayoutParams(lp);
            layout.addView(btn);

            final int index = i + 1;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reports = new ReportsFeePaidNew();
                    reports.setPage(String.valueOf(index));
                    System.out.println("ReportsFeesPaidFragment.onClick" + index);
                    feePaidReportsData(userId, userType, reports.getPage());
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

                }
            });
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("ReportsFeesPaidFragment.onClick===" + index);

                    feePaidReportsData(userId, userType, reports.getPage());
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

                }
            });
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("ReportsFeesPaidFragment.onClick===" + index);
                    feePaidReportsData(userId, userType, reports.getPage());
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
    }

    private void searchReportItem(String userId, String userType, String page, String searchText) {
        System.out.println("ReportsFeesPaidFragment.feePaidReportsData==" + userId + "==" + userType);
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidDataSearch(userId, userType, page, searchText)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponseSearch, this::handleErrorSearch));


        } else {
            showToast("Internet Connection Is Not Available");


        }


    }

    private void handleErrorSearch(Throwable error) {
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

    private void handleResponseSearch(ReportsFeePaid response) {

        if (response.getStatus().equalsIgnoreCase("success")) {
            progressBar.setVisibility(View.GONE);
            //showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            System.out.println("ReportsFeesPaidFragment.handleResponse==" + totalPages);

          //  preferencesManager.saveReportDetailUserName(getContext(), totalPages);
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


            simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int childGroupPosition, int childPosition, long id) {
                    //get the group header
                    FeesPaidGroupInfo headerInfo = deptList.get(childGroupPosition);
                    //get the child info
                    FeesPaidChildInfo detailInfo = headerInfo.getProductList().get(childPosition);
                    //display it or do something with it
                    System.out.println("ReportsFeesPaidFragment.onChildClick===" + detailInfo.getName());
                    progressBar.setVisibility(View.VISIBLE);

                    List<ReportsFeePaidNew> newList = reportsFeePaidNewList;
                    mAdapter.notifyDataSetChanged();

                    if (childGroupPosition == 0 && childPosition == 0) {
                        System.out.println("ReportsFeesPaidFragment.onChildClick" + detailInfo.getName());
                        Collections.sort(newList, new Comparator<ReportsFeePaidNew>() {
                            @Override
                            public int compare(ReportsFeePaidNew lhs, ReportsFeePaidNew rhs) {
                                return lhs.getPrimarySsn().compareTo(rhs.getPrimarySsn());

                            }
                        });
                        Collections.reverse(reportsFeePaidNewList);
                        parent.collapseGroup(0);
                        progressBar.setVisibility(View.GONE);
                    }
                    if (childGroupPosition == 0 && childPosition == 1) {
                        System.out.println("ReportsFeesPaidFragment.onChildClick" + detailInfo.getName());
                        Collections.sort(newList, new Comparator<ReportsFeePaidNew>() {
                            @Override
                            public int compare(ReportsFeePaidNew lhs, ReportsFeePaidNew rhs) {
                                return lhs.getPrimaryLastName().compareTo(rhs.getPrimaryLastName());
                            }
                        });
                        Collections.reverse(reportsFeePaidNewList);
                        parent.collapseGroup(0);
                        progressBar.setVisibility(View.GONE);

                    }
                    if (childGroupPosition == 0 && childPosition == 2) {
                        System.out.println("ReportsFeesPaidFragment.onChildClick" + detailInfo.getName());
                        Collections.sort(newList, new Comparator<ReportsFeePaidNew>() {
                            @Override
                            public int compare(ReportsFeePaidNew lhs, ReportsFeePaidNew rhs) {
                                return lhs.getDisbursementType().compareTo(rhs.getDisbursementType());
                            }
                        });
                        Collections.reverse(reportsFeePaidNewList);
                        parent.collapseGroup(0);
                        progressBar.setVisibility(View.GONE);


                    }
                    return false;
                }
            });


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


    private void prepareReportData(String PrimaryFirstName, String ToTalSiteFeeCollected,
                                   String PrimarySsn, String DisbursementType,
                                   String recordcreatedate) {

        ReportsFeePaidNew reports = new ReportsFeePaidNew(PrimaryFirstName, ToTalSiteFeeCollected
                , PrimarySsn, DisbursementType, recordcreatedate);
        reportsList.add(reports);
      /*  Reports reports = new Reports("smith-alonza", "$125", "XXX-XX-0123", "RT w/ FCA | ", "05-03-2017");
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
        reportsList.add(reports);*/
        LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.button_list);

        Button tv[] = new Button[10];
        for (int i = 1; i < 10; i++) {
            tv[i] = new Button(getActivity());

            tv[i].setText("" + i);

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
