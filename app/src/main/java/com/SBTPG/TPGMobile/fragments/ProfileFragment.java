package com.SBTPG.TPGMobile.fragments;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.SBTPG.TPGMobile.activities.Dashboard;
import com.SBTPG.TPGMobile.adapters.profileAdapter.MyProfileExpandableListAccoAdapter;
import com.SBTPG.TPGMobile.adapters.profileAdapter.MyProfileExpandableListEnrAdapterNew;
import com.SBTPG.TPGMobile.adapters.profileAdapter.MyProfileExpandableListShipAdapter;
import com.SBTPG.TPGMobile.model.profileModel.MyProfileGroupAccountInfoNew;
import com.SBTPG.TPGMobile.model.profileModel.MyProfileGroupShippingInfoNew;
import com.SBTPG.TPGMobile.model.profileModel.AccountInfo;
import com.SBTPG.TPGMobile.model.profileModel.EnrolInfo;
import com.SBTPG.TPGMobile.model.profileModel.MyProfileGroupEnrollInfoNew;
import com.SBTPG.TPGMobile.model.profileModel.ProfileGroupData;
import com.SBTPG.TPGMobile.model.Response;
import com.SBTPG.TPGMobile.model.profileModel.ShippingInfo;
import com.SBTPG.TPGMobile.R;
import com.SBTPG.TPGMobile.utils.AppInternetStatus;
import com.SBTPG.TPGMobile.utils.DateUtils;
import com.SBTPG.TPGMobile.utils.NetworkUtil;
import com.SBTPG.TPGMobile.utils.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ajay on 12/19/2017.
 */

public class ProfileFragment extends Fragment {

    ExpandableListView simpleExpandableListViewOne;
    ExpandableListView simpleExpandableListViewTwo;
    ExpandableListView simpleExpandableListViewThree;
    public static final String ARG_SECTION_TITLE = "section_number";
    private static final String PREFS_NAME = "profile";
    String userId, userType;
    ProgressBar progressBar;
    CompositeSubscription mSubscriptions;
    PreferencesManager preferencesManager;

    private LinkedHashMap<String, MyProfileGroupAccountInfoNew> subjectsAccount = new LinkedHashMap<String, MyProfileGroupAccountInfoNew>();
    private LinkedHashMap<String, MyProfileGroupShippingInfoNew> subjectsShipping = new LinkedHashMap<String, MyProfileGroupShippingInfoNew>();
    private LinkedHashMap<String, MyProfileGroupEnrollInfoNew> subjectsEnroll = new LinkedHashMap<String, MyProfileGroupEnrollInfoNew>();

    ArrayList<MyProfileGroupEnrollInfoNew> deptListEnroll = new ArrayList<MyProfileGroupEnrollInfoNew>();
    ArrayList<MyProfileGroupShippingInfoNew> deptListShipping = new ArrayList<MyProfileGroupShippingInfoNew>();
    ArrayList<MyProfileGroupAccountInfoNew> deptListAccount = new ArrayList<MyProfileGroupAccountInfoNew>();


    MyProfileExpandableListEnrAdapterNew enrolListAdapter;
    MyProfileExpandableListAccoAdapter accountListAdapter;
    MyProfileExpandableListShipAdapter shipListAdapter;


    Dashboard dashboard;

    public static ProfileFragment newInstance(String sectionTitle, String userId, String type) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        args.putString("app_uid", userId);
        args.putString("acc_type", type);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment_new, container, false);
        userId = getArguments().getString("app_uid");
        userType = getArguments().getString("acc_type");
        mSubscriptions = new CompositeSubscription();
        preferencesManager = new PreferencesManager();
        dashboard = new Dashboard();
        loadProfileData(userId, userType);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        simpleExpandableListViewOne = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView_one);
        simpleExpandableListViewTwo = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView_two);
        simpleExpandableListViewThree = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView_three);
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_profile);

        simpleExpandableListViewOne.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Displayitemclicked(groupPosition, parent);
                return false;
            }
        });
        simpleExpandableListViewTwo.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                if (settings.getBoolean("isFirstRun", true)) {
                    showDialog();
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("isFirstRun", false);
                    editor.commit();
                }

                Displayitemclicked(groupPosition, parent);
                //   collapseAll(1);
                return false;
            }
        });

        simpleExpandableListViewThree.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Displayitemclicked(groupPosition, parent);

                return false;
            }
        });


    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public Dashboard getDashboard() {
        dashboard.onBackPressed();
        return dashboard;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSubscriptions.unsubscribe();

    }

    private void loadProfileData(String userId, String userType) {

        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            try {
                mSubscriptions.addAll(NetworkUtil.getRetrofit().profileNew(userId, userType)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(this::handleResponse, this::handleError));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showToast("Internet Connection Is Not Available");
        }
    }

    private void handleResponse(ProfileGroupData profileGroupData) {
        try {
            progressBar.setVisibility(View.GONE);

            if (profileGroupData.getStatus().equalsIgnoreCase("success")) {
                addProductEnroll("Owner Info", profileGroupData.getOwner_info());
                addProductShipping("Shipping Info", profileGroupData.getShipping_info());
                addProductAccount("Bank Account Info", profileGroupData.getAccount_info());
            } else {
                showToast(profileGroupData.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int addProductEnroll(String department, EnrolInfo profileGroupInfo) {

        int groupPosition = 0;
        MyProfileGroupEnrollInfoNew headerInfo = subjectsEnroll.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new MyProfileGroupEnrollInfoNew();
            headerInfo.setName(department);
            subjectsEnroll.put(department, headerInfo);
            deptListEnroll.add(headerInfo);

        }

        //get the children for the group
        ArrayList<EnrolInfo> productList = headerInfo.getProductList();

        EnrolInfo detailInfo = new EnrolInfo();
        if (userType.equalsIgnoreCase("sb")) {
            detailInfo.setContactFirstName(profileGroupInfo.getContactFirstName());
            detailInfo.setContactLastName(profileGroupInfo.getContactLastName());
            detailInfo.setStreet(profileGroupInfo.getStreet());
            detailInfo.setStreet2(profileGroupInfo.getStreet2());
            detailInfo.setCity(profileGroupInfo.getCity());
            detailInfo.setState(profileGroupInfo.getState());
            detailInfo.setZipcode(profileGroupInfo.getZipcode());
            detailInfo.setOfficePhone(profileGroupInfo.getOfficePhone());
            detailInfo.setFaxPhone(profileGroupInfo.getFaxPhone());
            detailInfo.setEmailAddress(profileGroupInfo.getEmailAddress());
        } else if (userType.equalsIgnoreCase("ero")) {
            detailInfo.setFirstName(profileGroupInfo.getFirstName());
            detailInfo.setLastName(profileGroupInfo.getLastName());
            detailInfo.setStreet(profileGroupInfo.getStreet());
            detailInfo.setStreet2(profileGroupInfo.getStreet2());
            detailInfo.setCity(profileGroupInfo.getCity());
            detailInfo.setState(profileGroupInfo.getState());
            detailInfo.setZipcode(profileGroupInfo.getZipcode());
            detailInfo.setWorkPhone(profileGroupInfo.getWorkPhone());
            detailInfo.setMobilePhone(profileGroupInfo.getMobilePhone());
            detailInfo.setHomePhone(profileGroupInfo.getHomePhone());
            detailInfo.setEmailAddress(profileGroupInfo.getEmailAddress());

        }
        // productList.add(detailInfo);

        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = deptListEnroll.indexOf(headerInfo);
        enrolListAdapter = new MyProfileExpandableListEnrAdapterNew(getActivity(), deptListEnroll, userType);
        simpleExpandableListViewOne.setAdapter(enrolListAdapter);

        return groupPosition;

    }

    private int addProductShipping(String department, ShippingInfo profileGroupInfo) {
        int groupPosition = 1;
        MyProfileGroupShippingInfoNew headerInfo = subjectsShipping.get(department);
        if (headerInfo == null) {
            headerInfo = new MyProfileGroupShippingInfoNew();
            headerInfo.setName(department);
            subjectsShipping.put(department, headerInfo);
            deptListShipping.add(headerInfo);
        }
        ArrayList<ShippingInfo> productList = headerInfo.getList();

        ShippingInfo detailInfo = new ShippingInfo();

        if (userType.equalsIgnoreCase("sb")) {
            detailInfo.setCity(profileGroupInfo.getCity());
            detailInfo.setState(profileGroupInfo.getState());
            detailInfo.setStreet(profileGroupInfo.getStreet());
            detailInfo.setStreet2(profileGroupInfo.getStreet2());
            detailInfo.setZipcode(profileGroupInfo.getZipcode());
        } else if (userType.equalsIgnoreCase("ero")) {
            String changeDateNew = DateUtils.reportDate(profileGroupInfo.getShipmentHoldUntilDate());
            detailInfo.setCity(profileGroupInfo.getCity());
            detailInfo.setState(profileGroupInfo.getState());
            detailInfo.setStreet(profileGroupInfo.getStreet());
            detailInfo.setStreet2(profileGroupInfo.getStreet2());
            detailInfo.setZipcode(profileGroupInfo.getZipcode());
            detailInfo.setShipmentHoldUntilDate(changeDateNew);
        }
        productList.add(detailInfo);
        headerInfo.setList(productList);
        groupPosition = deptListShipping.indexOf(headerInfo);
        shipListAdapter = new MyProfileExpandableListShipAdapter(getActivity(), deptListShipping, userType);
        simpleExpandableListViewTwo.setAdapter(shipListAdapter);
        return groupPosition;
    }

    private int addProductAccount(String department, AccountInfo profileGroupInfo) {
        int groupPosition = 2;
        MyProfileGroupAccountInfoNew headerInfo = subjectsAccount.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new MyProfileGroupAccountInfoNew();
            headerInfo.setName(department);
            subjectsAccount.put(department, headerInfo);
            deptListAccount.add(headerInfo);
        }

        //get the children for the group
        ArrayList<AccountInfo> productList = headerInfo.getList();

        AccountInfo detailInfo = new AccountInfo();

        if (userType.equalsIgnoreCase("sb")) {
            detailInfo.setBankName(profileGroupInfo.getBankName());
            detailInfo.setAcctType(profileGroupInfo.getAcctType());
            detailInfo.setDAN(profileGroupInfo.getDAN());
            detailInfo.setNameOnAccount(profileGroupInfo.getNameOnAccount());
            detailInfo.setRTN(profileGroupInfo.getRTN());
        } else if (userType.equalsIgnoreCase("ero")) {
            detailInfo.setBankName(profileGroupInfo.getBankName());
            detailInfo.setAcctType(profileGroupInfo.getAcctType());
            detailInfo.setDAN(profileGroupInfo.getDAN());
            detailInfo.setNameOnAccount(profileGroupInfo.getNameOnAccount());
            detailInfo.setRTN(profileGroupInfo.getRTN());
        }


        productList.add(detailInfo);
        headerInfo.setList(productList);
        //find the group position inside the list
        groupPosition = deptListAccount.indexOf(headerInfo);

        accountListAdapter = new MyProfileExpandableListAccoAdapter(getActivity(), deptListAccount, userType);
        simpleExpandableListViewThree.setAdapter(accountListAdapter);
        return groupPosition;
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
    }

    private void handleError(Throwable error) {
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


    private void Displayitemclicked(int groupPosition, ExpandableListView parent) {
        if (groupPosition == 0) {
            try {
                simpleExpandableListViewTwo.collapseGroup(0);
                simpleExpandableListViewOne.collapseGroup(0);
                simpleExpandableListViewThree.collapseGroup(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private void showDialog() {

        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setMessage(getActivity().getResources().getString(R.string.text_dialog));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
