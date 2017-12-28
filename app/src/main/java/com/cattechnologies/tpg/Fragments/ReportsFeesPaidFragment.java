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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.cattechnologies.tpg.Utils.Constants;
import com.cattechnologies.tpg.Utils.NetworkUtil;
import com.cattechnologies.tpg.Utils.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import android.support.v7.widget.SearchView;

/**
 * Created by ajay kumar on 28-Oct-17.
 */

public class ReportsFeesPaidFragment extends Fragment {


    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;
    ReportsFeePaid dashboardInfo;

    String BASE_URL = Constants.BASE_URL_D;
    String jsonResponse;

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
    String noOfPages;
    PreferencesManager preferencesManager;
    String changeDate = null;
    String nameFirst, nameLast, amount, ssn, disbustype, date, prepFee,
            electFee,
            docPrepFee,
            otherFee,
            totalFee;
    ReportsFeePaidNew reports;
    Button prev, next;
    Date dateData = null;
    String inputPattern = "yyyyddMM";
    String outputPattern = "MM-dd-yyyy";

    SimpleDateFormat format, format1;

    private static int current_page = 1;
    int count = 0;
    SearchView searchView;

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
        LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.button_list);
        prev = (Button) getActivity().findViewById(R.id.prev);
        next = (Button) getActivity().findViewById(R.id.next);
        searchView = (SearchView) getActivity().findViewById(R.id.search_paid);
        searchView.setQueryHint("Search Customer");
        userId = getArguments().getString("app_uid");
        userType = getArguments().getString("acc_type");
        mSubscriptions = new CompositeSubscription();
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_login);
        preferencesManager = new PreferencesManager();
        reports = new ReportsFeePaidNew();
        loadData();
        simpleExpandableListView = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView);
        listAdapter = new ReportsExpandableListFeesPaidAdapter(getActivity(), deptList);
        simpleExpandableListView.setAdapter(listAdapter);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);

        reportsList = new ArrayList<>();

        usingAsynchTa(userId, userType, "1");
        System.out.println("ReportsFeesPaidFragment.onActivityCreated" +
                preferencesManager.getReportDetailUsername(getContext()));
        int data;
        if (preferencesManager.getReportDetailUsername(getContext()) == null) {
            data = 5;
        } else {
            data = preferencesManager.getReportDetailUsername(getContext()).length();
            System.out.println("ReportsFeesPaidFragment.onActivityCreated" + data);
        }
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = data;
                count++;
                System.out.println("ReportsFeesPaidFragment.onClick" + count);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = data;
                count--;
                System.out.println("ReportsFeesPaidFragment.onClick" + count);

            }
        });
        for (int i = 0; i <= data; i++) {
            Button btn = new Button(getActivity());
            btn.setId(i);
            btn.setText("" + (i + 1));
            // btn.setLayoutParams(lprams);
            final int index = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("ReportsFeesPaidFragment.onClick" + index);
                    if (index == 0) {
                        usingAsynchTa(userId, userType, "1");
                    } else if (index == 1) {
                        usingAsynchTa(userId, userType, "2");

                    } else if (index == 2) {
                        usingAsynchTa(userId, userType, "3");

                    }

                }
            });

            layout.addView(btn);
        }

        // feePaidReportsData(userId, userType);

        //expand all the Groups
        // expandAll();

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            /*    //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo = headerInfo.getProductList().get(childPosition);
              *//*  //display it or do something with it
                Toast.makeText(getActivity(), " Clicked on :: " + headerInfo.getName()
                        + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();*//*
        */
                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
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

    private void usingAsynchTa(String userId, String userType, String index) {

        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("admin_username", "Tpg@pp@dmiN");
        asyncHttpClient.addHeader("admin_password", ")[}#f3ka~g%6tpg9&j[{($/]})%$");
        asyncHttpClient.addHeader("api_key", "$[}#f3ka~g%9tpg3&j[{($/]})(9tp?/!bj30xy-wi=3^9-$^R9G|J#E6AB;OP[}#");
        requestParams = new RequestParams();
        requestParams.put("app_uid", userId);
        requestParams.put("acc_type", userType);
        asyncHttpClient.post(BASE_URL, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                jsonResponse = response.toString();
                progressBar.setVisibility(View.GONE);

                Log.i("dd", "onSuccess: " + jsonResponse);
                try {
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    //  dashboardInfo = new ReportsFeePaid();
                    noOfPages = jsonObject.getString("Total No of Pages");
                    preferencesManager.saveReportDetailUserName(getContext(), noOfPages);

                    JSONArray jsonArray = null;
                    JSONObject jsonObject1 = null;
                    for (int i = 0; i < noOfPages.length(); i++) {
                        jsonArray = jsonObject.getJSONArray("FeeReport_data");
                        for (int j = 0; j < jsonArray.length(); j++) {
                            jsonObject1 = jsonArray.getJSONObject(j);


                        }
                        try {
                            JSONArray array = jsonObject1.getJSONArray(index);
                            for (int k = 0; k < array.length(); k++) {
                                JSONObject jsonObject2 = array.getJSONObject(k);
                                nameFirst = jsonObject2.getString("PrimaryFirstName");
                                nameLast = jsonObject2.getString("PrimaryLastName");
                                amount = jsonObject2.getString("ToTalSiteFeeCollected");
                                ssn = jsonObject2.getString("PrimarySsn");
                                disbustype = jsonObject2.getString("DisbursementType");
                                date = jsonObject2.getString("recordcreatedate");
                                otherFee = jsonObject2.getString("otherfees");


                                format = new SimpleDateFormat("yyyyddMM");
                                format1 = new SimpleDateFormat("MM-dd-yyyy");
                                try {
                                    changeDate = format1.format(format.parse(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                prepFee = jsonObject2.getString("PreparationFeesCollected");
                                electFee = jsonObject2.getString("SiteEfFeesCollected");
                                docPrepFee = jsonObject2.getString("DocumentStorageFeesCollected");
                                totalFee = jsonObject2.getString("ToTalSiteFeeCollected");
                                reports = new ReportsFeePaidNew();
                                if (nameFirst != null) {
                                    reports.setPrimaryFirstName(nameFirst);

                                } else {
                                    reports.setPrimaryFirstName("");

                                }
                                if (nameLast != null) {
                                    reports.setPrimaryLastName(nameLast);

                                } else {
                                    reports.setPrimaryLastName("");
                                }
                                reports.setToTalSiteFeeCollected(amount);
                                reports.setPrimarySsn(ssn);
                                reports.setDisbursementType(disbustype);
                                reports.setRecordcreatedate(changeDate);
                                reports.setPreparationFeesCollected(prepFee);
                                reports.setSiteEfFeesCollected(electFee);
                                reports.setOtherfees(otherFee);
                                reports.setDocumentStorageFeesCollected(docPrepFee);
                                reports.setToTalSiteFeeCollected(totalFee);
                                reportsList.add(reports);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // selectedPage(1, jsonObject1);


                    }
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    mAdapter = new ReportsFeesPaidListAdapter(getActivity(), reportsList, title, nameLast);
                    DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.my_custom_divider));
                    recyclerView.addItemDecoration(divider);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
                    recyclerView.setAdapter(mAdapter);
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            newText = newText.toLowerCase();
                            ArrayList<ReportsFeePaidNew> newList = new ArrayList<>();
                            for (ReportsFeePaidNew channel : reportsList) {
                                String channelName = channel.getPrimaryFirstName().toLowerCase();
                                String lastName = channel.getPrimaryLastName().toLowerCase();
                                String ssn = channel.getPrimarySsn();
                                if (channelName.contains(newText) || ssn.contains(newText) || lastName.contains(newText)) {
                                    newList.add(channel);
                                
                                }else {
                                   // Toast.makeText(getContext(), "We couldnot find any thing related to your search, Please try with different keywords", Toast.LENGTH_SHORT).show();
                                }

                            }
                            mAdapter.setFilter(newList);
                            return true;
                        }
                    });
                    mAdapter.setClickListener((view, position) -> {
                        final ReportsFeePaidNew reports = reportsList.get(position);
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


                    //  int dd = Integer.parseInt("noOfPages");


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progressBar.setVisibility(View.GONE);
                Log.e("ddd", "onFailure: " + errorResponse);
            }
        });
    }

    private void selectedPage(int i, JSONObject jsonObject1) {
        System.out.println("ReportsFeesPaidFragment.selectedPage===first==" + jsonObject1);
        System.out.println("ReportsFeesPaidFragment.selectedPage===vi==" + i);
        System.out.println("ReportsFeesPaidFragment.selectedPage===second==" + jsonObject1);
        JSONArray array = null;
        if (i == 1)
            try {
                array = jsonObject1.getJSONArray(String.valueOf(i));
                for (int k = 0; k < array.length(); k++) {
                    JSONObject jsonObject2 = null;
                    try {
                        jsonObject2 = array.getJSONObject(k);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        nameFirst = jsonObject2.getString("PrimaryFirstName");
                        System.out.println("ReportsFeesPaidFragment.selectedPage===" + nameFirst);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        nameLast = jsonObject2.getString("PrimaryLastName");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        amount = jsonObject2.getString("ToTalSiteFeeCollected");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        ssn = jsonObject2.getString("PrimarySsn");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        disbustype = jsonObject2.getString("DisbursementType");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        date = jsonObject2.getString("recordcreatedate");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    try {
                   /* inputFormat = new SimpleDateFormat(inputPattern);
                    outputFormat = new SimpleDateFormat(outputPattern);
                    dateData = inputFormat.parse(date);
                    changeDate = outputFormat.format(dateData);*/

                        format = new SimpleDateFormat("yyyyddMM");
                        format1 = new SimpleDateFormat("MM-dd-yyyy");
                        try {
                            changeDate = format1.format(format.parse(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        prepFee = jsonObject2.getString("PreparationFeesCollected");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        electFee = jsonObject2.getString("SiteEfFeesCollected");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        docPrepFee = jsonObject2.getString("DocumentStorageFeesCollected");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        totalFee = jsonObject2.getString("ToTalSiteFeeCollected");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    reports = new ReportsFeePaidNew();
                    if (nameFirst != null) {
                        reports.setPrimaryFirstName(nameFirst);

                    } else {
                        reports.setPrimaryFirstName("");

                    }
                    if (nameLast != null) {
                        reports.setPrimaryLastName(nameLast);

                    } else {
                        reports.setPrimaryLastName("");
                    }
                    reports.setToTalSiteFeeCollected(amount);
                    reports.setPrimarySsn(ssn);
                    reports.setDisbursementType(disbustype);
                    reports.setRecordcreatedate(changeDate);
                    reports.setPreparationFeesCollected(prepFee);
                    reports.setSiteEfFeesCollected(electFee);
                    reports.setDocumentStorageFeesCollected(docPrepFee);
                    reports.setToTalSiteFeeCollected(totalFee);
                    reportsList.add(reports);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


    }


    private void feePaidReportsData(String userId, String userType) {
        System.out.println("ReportsFeesPaidFragment.feePaidReportsData==" + userId + "==" + userType);
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().getFeePaidData(userId, userType)
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
            showToast(response.getMessage());
            String totalPages = response.getTotalNoofPages();
            System.out.println("ReportsFeesPaidFragment.handleResponse==" + totalPages);


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
