package com.cattechnologies.tpg.fragments;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cattechnologies.tpg.adapters.feePaidReportAdapter.ReportsFeePaidExpandableadapter;
import com.cattechnologies.tpg.adapters.profileAdapter.MyProfileExpandableListAccoAdapter;
import com.cattechnologies.tpg.adapters.profileAdapter.MyProfileExpandableListEnrAdapterNew;
import com.cattechnologies.tpg.adapters.profileAdapter.MyProfileExpandableListShipAdapter;
import com.cattechnologies.tpg.model.MyProfileGroupAccountInfoNew;
import com.cattechnologies.tpg.model.MyProfileGroupShippingInfoNew;
import com.cattechnologies.tpg.model.accountDisbursementModel.AccountInfo;
import com.cattechnologies.tpg.model.EnrolInfo;
import com.cattechnologies.tpg.model.profileModel.MyProfileGroupAccountInfo;
import com.cattechnologies.tpg.model.profileModel.MyProfileGroupEnrollInfoNew;
import com.cattechnologies.tpg.model.profileModel.MyProfileGroupShippingInfo;
import com.cattechnologies.tpg.model.profileModel.ProfileGroupData;
import com.cattechnologies.tpg.model.Response;
import com.cattechnologies.tpg.model.profileModel.ShippingInfo;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.utils.AppInternetStatus;
import com.cattechnologies.tpg.utils.NetworkUtil;
import com.cattechnologies.tpg.utils.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 12/19/2017.
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

    SimpleDateFormat format, format1;


    ReportsFeePaidExpandableadapter adapter;
    ExpandableListView myexpandable;
    List<String> parent;
    List<String> child;
    HashMap<String, List<String>> bind_and_display;

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
                //  collapseAll(0);

                //  parent.collapseGroup(0);
                System.out.println("ProfileFragment.onGroupClick===" + groupPosition);
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
                // parent.collapseGroup(0);
                System.out.println("ProfileFragment.onGroupClick===" + groupPosition);

                Displayitemclicked(groupPosition, parent);
                //   collapseAll(1);
                return false;
            }
        });

        simpleExpandableListViewThree.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                System.out.println("ProfileFragment.onGroupClick===" + groupPosition);

                //  parent.collapseGroup(0);
                Displayitemclicked(groupPosition, parent);
                //  collapseAll(2);

                return false;
            }
        });


    }

    private void loadProfileData(String userId, String userType) {

        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {
            try {
                mSubscriptions.addAll(NetworkUtil.getRetrofit().profileNew(userId, userType)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(this::handleResponse, this::handleError));
            } catch (Exception e) {
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
                profileGroupData.getMessage();
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
            System.out.println("ProfileFragment.addProductEnroll");

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
            System.out.println("ProfileFragment.addProductEnroll");
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
        System.out.println("ProfileFragment.addProductShipping====" + profileGroupInfo);
        //check the hash map if the group already exists
        MyProfileGroupShippingInfoNew headerInfo = subjectsShipping.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new MyProfileGroupShippingInfoNew();
            headerInfo.setName(department);
            subjectsShipping.put(department, headerInfo);
            deptListShipping.add(headerInfo);
        }
        ArrayList<ShippingInfo> productList = headerInfo.getList();

        ShippingInfo detailInfo = new ShippingInfo();

        if (userType.equalsIgnoreCase("sb")) {
            System.out.println("ProfileFragment.addProductShipping==sb===");
            detailInfo.setCity(profileGroupInfo.getCity());
            detailInfo.setState(profileGroupInfo.getState());
            detailInfo.setStreet(profileGroupInfo.getStreet());
            detailInfo.setStreet2(profileGroupInfo.getStreet2());
            detailInfo.setZipcode(profileGroupInfo.getZipcode());

        } else if (userType.equalsIgnoreCase("ero")) {
            System.out.println("ProfileFragment.addProductShipping==ero==");

            try {
                format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                format1 = new SimpleDateFormat("MM-dd-yyyy");
                String chagnedDate = null;
                detailInfo.setCity(profileGroupInfo.getCity());
                detailInfo.setState(profileGroupInfo.getState());
                detailInfo.setStreet(profileGroupInfo.getStreet());
                detailInfo.setStreet2(profileGroupInfo.getStreet2());
                detailInfo.setZipcode(profileGroupInfo.getZipcode());
                chagnedDate = format1.format(
                        format.parse(profileGroupInfo.getShipmentHoldUntilDate()));
                detailInfo.setShipmentHoldUntilDate
                        (chagnedDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        productList.add(detailInfo);
        headerInfo.setList(productList);

        //find the group position inside the list
        groupPosition = deptListShipping.indexOf(headerInfo);
        System.out.println("ProfileFragment.addProductShipping====" + groupPosition + "===" + deptListShipping + "===" + userType);
        shipListAdapter = new MyProfileExpandableListShipAdapter(getActivity(), deptListShipping, userType);
        simpleExpandableListViewTwo.setAdapter(shipListAdapter);
        return groupPosition;
    }

    private int addProductAccount(String department, AccountInfo profileGroupInfo) {
        int groupPosition = 2;
        System.out.println("ProfileFragment.addProductAccount===" + profileGroupInfo);
        //check the hash map if the group already exists
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
            System.out.println("ProfileFragment.addProductAccount==sb==");
            detailInfo.setBankName(profileGroupInfo.getBankName());
            detailInfo.setAcctType(profileGroupInfo.getAcctType());
            detailInfo.setDAN(profileGroupInfo.getDAN());
            detailInfo.setNameOnAccount(profileGroupInfo.getNameOnAccount());
            detailInfo.setRTN(profileGroupInfo.getRTN());
        } else if (userType.equalsIgnoreCase("ero")) {
            System.out.println("ProfileFragment.addProductAccount==ero==");
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
            //parent.collapseGroup(0);
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
