package com.cattechnologies.tpg.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cattechnologies.tpg.Adapters.MyProfileExpandableListAccoAdapter;
import com.cattechnologies.tpg.Adapters.MyProfileExpandableListEnrAdapter;
import com.cattechnologies.tpg.Adapters.MyProfileExpandableListShipAdapter;
import com.cattechnologies.tpg.Model.AccountInfo;
import com.cattechnologies.tpg.Model.EnrolInfo;
import com.cattechnologies.tpg.Model.GroupInfo;
import com.cattechnologies.tpg.Model.LoginInfo;
import com.cattechnologies.tpg.Model.MyProfileGroupAccountInfo;
import com.cattechnologies.tpg.Model.MyProfileGroupEnrollInfo;
import com.cattechnologies.tpg.Model.MyProfileGroupShippingInfo;
import com.cattechnologies.tpg.Model.ProfileGroupInfo;
import com.cattechnologies.tpg.Model.Response;
import com.cattechnologies.tpg.Model.ShippingInfo;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.Utils.AppInternetStatus;
import com.cattechnologies.tpg.Utils.NetworkUtil;
import com.cattechnologies.tpg.Utils.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by admin on 10/2/2017.
 */

public class ProfileFragment extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    private static final String PREFS_NAME = "profile";
    private LinkedHashMap<String, MyProfileGroupAccountInfo> subjectsAccount = new LinkedHashMap<String, MyProfileGroupAccountInfo>();
    private LinkedHashMap<String, MyProfileGroupShippingInfo> subjectsShipping = new LinkedHashMap<String, MyProfileGroupShippingInfo>();
    private LinkedHashMap<String, MyProfileGroupEnrollInfo> subjectsEnroll = new LinkedHashMap<String, MyProfileGroupEnrollInfo>();


    private ExpandableListView simpleExpandableListViewOne;
    private ExpandableListView simpleExpandableListViewTwo;
    private ExpandableListView simpleExpandableListViewThree;

    private MyProfileExpandableListAccoAdapter accountListAdapter;
    private MyProfileExpandableListEnrAdapter enrolListAdapter;
    private MyProfileExpandableListShipAdapter shipListAdapter;


    private ArrayList<MyProfileGroupAccountInfo> deptListAccount = new ArrayList<MyProfileGroupAccountInfo>();
    private ArrayList<MyProfileGroupEnrollInfo> deptListEnroll = new ArrayList<MyProfileGroupEnrollInfo>();
    private ArrayList<MyProfileGroupShippingInfo> deptListShipping = new ArrayList<MyProfileGroupShippingInfo>();

    private CompositeSubscription mSubscriptions;
    LoginInfo loginInfo;
    PreferencesManager preferencesManager;
    String userId, userType, userIdFromForgot;
    SimpleDateFormat format, format1;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        String title = getArguments().getString(ARG_SECTION_TITLE);
        userId = getArguments().getString("app_uid");
        userType = getArguments().getString("acc_type");
        TextView titulo = (TextView) getActivity().findViewById(R.id.title);
        mSubscriptions = new CompositeSubscription();
        preferencesManager = new PreferencesManager();
        System.out.println("ProfileFragment.onCreateView==="+userId);
        loadProfileData(userId, userType);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        loadProfileData(userId, userType);
        //expand all the Groups
        // expandAll();
        // collapseAll();
        simpleExpandableListViewOne = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView_one);


        simpleExpandableListViewTwo = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView_two);


        simpleExpandableListViewThree = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView_three);


        simpleExpandableListViewThree.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {


                collapseAll(2);

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


                collapseAll(1);
                return false;
            }
        });
        simpleExpandableListViewOne.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                collapseAll(0);

                return false;
            }
        });
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

    private void collapseAll(int position) {
        int count;
        if (position == 0) {
            count = accountListAdapter.getGroupCount();
            count = shipListAdapter.getGroupCount();

            for (int i = 0; i < count; i++) {
                simpleExpandableListViewTwo.collapseGroup(i);
                simpleExpandableListViewThree.collapseGroup(i);


            }
        } else if (position == 1) {
            count = shipListAdapter.getGroupCount();
            count = accountListAdapter.getGroupCount();

            for (int i = 0; i < count; i++) {
                simpleExpandableListViewOne.collapseGroup(i);
                simpleExpandableListViewThree.collapseGroup(i);


            }
        } else if (position == 2) {
            count = enrolListAdapter.getGroupCount();
            count = shipListAdapter.getGroupCount();

            for (int i = 0; i < count; i++) {
                simpleExpandableListViewOne.collapseGroup(i);
                simpleExpandableListViewTwo.collapseGroup(i);

            }

        }
    }

    private void expandAll() {
     /*   int count = accountListAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListViewThree.expandGroup(i);
        }*/
    }


    private void loadProfileData(String userId, String type) {
        System.out.println("ProfileFragment.loadProfileData====" + userId);
        if (AppInternetStatus.getInstance(getActivity()).isOnline()) {

            try {
                System.out.println("ProfileFragment.loadProfileData=====login===" + userId);

                mSubscriptions.addAll(NetworkUtil.getRetrofit().profile(userId, type)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(this::handleResponse, this::handleError));
            } catch (Exception e) {

            }


        } else {
            showToast("Internet Connection Is Not Available");

        }

    }

    private void handleResponse(ProfileGroupInfo profileGroupInfo) {
        System.out.println("ProfileFragment.handleResponse===" + profileGroupInfo.getOwner_info());
        addProductEnroll("Owner Info", profileGroupInfo);
        addProductShipping("Shipping Info", profileGroupInfo);
        addProductAccount("Bank Account Info", profileGroupInfo);


    }

    private void handleError(Throwable error) {
        showToast(error.getMessage());
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


    private void showToast(String message) {
        Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();

    }

    private int addProductEnroll(String department, ProfileGroupInfo profileGroupInfo) {
        System.out.println("ProfileFragment.addProductEnroll===" + profileGroupInfo.getOwner_info());
        int groupPosition = 0;
        MyProfileGroupEnrollInfo headerInfo = subjectsEnroll.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new MyProfileGroupEnrollInfo();
            headerInfo.setName(department);
            subjectsEnroll.put(department, headerInfo);
            deptListEnroll.add(headerInfo);
        }

        //get the children for the group
        ArrayList<EnrolInfo> productList = headerInfo.getProductList();
        /*//size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;
*/
        //create a new child and add that to the group
        EnrolInfo detailInfo = new EnrolInfo();
        if (userType.equalsIgnoreCase("sb")) {

            detailInfo.setContactFirstName(profileGroupInfo.getOwner_info().get(0).getContactFirstName());
            detailInfo.setContactLastName(profileGroupInfo.getOwner_info().get(0).getContactLastName());
            detailInfo.setStreet(profileGroupInfo.getOwner_info().get(0).getStreet());
            detailInfo.setStreet2(profileGroupInfo.getOwner_info().get(0).getStreet2());
            detailInfo.setCity(profileGroupInfo.getOwner_info().get(0).getCity());
            detailInfo.setState(profileGroupInfo.getOwner_info().get(0).getState());
            detailInfo.setZipcode(profileGroupInfo.getOwner_info().get(0).getZipcode());
            detailInfo.setOfficePhone(profileGroupInfo.getOwner_info().get(0).getOfficePhone());
            detailInfo.setFaxPhone(profileGroupInfo.getOwner_info().get(0).getFaxPhone());
            detailInfo.setEmailAddress(profileGroupInfo.getOwner_info().get(0).getEmailAddress());
            System.out.println("ProfileFragment.addProductEnroll===" + detailInfo.getFaxPhone());


        } else if (userType.equalsIgnoreCase("ero")) {
            detailInfo.setFirstName(profileGroupInfo.getOwner_info().get(0).getFirstName());
            detailInfo.setLastName(profileGroupInfo.getOwner_info().get(0).getLastName());
            detailInfo.setStreet(profileGroupInfo.getOwner_info().get(0).getStreet());
            detailInfo.setStreet2(profileGroupInfo.getOwner_info().get(0).getStreet2());
            detailInfo.setCity(profileGroupInfo.getOwner_info().get(0).getCity());
            detailInfo.setState(profileGroupInfo.getOwner_info().get(0).getState());
            detailInfo.setZipcode(profileGroupInfo.getOwner_info().get(0).getZipcode());
            detailInfo.setWorkPhone(profileGroupInfo.getOwner_info().get(0).getWorkPhone());
            detailInfo.setMobilePhone(profileGroupInfo.getOwner_info().get(0).getMobilePhone());
            detailInfo.setHomePhone(profileGroupInfo.getOwner_info().get(0).getHomePhone());
            detailInfo.setEmailAddress(profileGroupInfo.getOwner_info().get(0).getEmailAddress());

        }

        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = deptListEnroll.indexOf(headerInfo);
        enrolListAdapter = new MyProfileExpandableListEnrAdapter(getActivity(), deptListEnroll, userType);
        simpleExpandableListViewOne.setAdapter(enrolListAdapter);

        return groupPosition;

    }

    private int addProductAccount(String department, ProfileGroupInfo product) {
        int groupPosition = 0;

        //check the hash map if the group already exists
        MyProfileGroupAccountInfo headerInfo = subjectsAccount.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new MyProfileGroupAccountInfo();
            headerInfo.setName(department);
            subjectsAccount.put(department, headerInfo);
            deptListAccount.add(headerInfo);
        }

        //get the children for the group
        ArrayList<AccountInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;
/*

        //create a new child and add that to the group
        AccountInfo detailInfo = new AccountInfo();
        detailInfo.setAccountNameOnBankAccount("Name on Bank Account: ");
        detailInfo.setAccountNameOnBankAccountData(product.getAccount_info().get(0).);
        detailInfo.setAccountNameBankAccount("Name of Bank:");
        detailInfo.setAccountNameBankAccountData("Bank of the west");
        detailInfo.setAccountRoutingNum("Bank Routing Number:");
        detailInfo.setAccountRoutingNumData("123123123");
        detailInfo.setAccountNum("Bank Account Number:");
        detailInfo.setAccountNumData("XX3XX3");
        detailInfo.setAccountType("Type of Account:");
        detailInfo.setAccountTypeData("checking");*/
        AccountInfo detailInfo = new AccountInfo();
        detailInfo.setBankName(product.getAccount_info().get(0).getBankName());
        detailInfo.setAcctType(product.getAccount_info().get(0).getAcctType());
        detailInfo.setDAN(product.getAccount_info().get(0).getDAN());
        detailInfo.setNameOnAccount(product.getAccount_info().get(0).getNameOnAccount());
        detailInfo.setRTN(product.getAccount_info().get(0).getRTN());


        productList.add(detailInfo);
        headerInfo.setProductList(productList);
        //find the group position inside the list
        groupPosition = deptListAccount.indexOf(headerInfo);

        accountListAdapter = new MyProfileExpandableListAccoAdapter(getActivity(), deptListAccount);
        simpleExpandableListViewThree.setAdapter(accountListAdapter);
        return groupPosition;
    }

    private int addProductShipping(String department, ProfileGroupInfo profileGroupInfo) {
        int groupPosition = 0;

        //check the hash map if the group already exists
        MyProfileGroupShippingInfo headerInfo = subjectsShipping.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new MyProfileGroupShippingInfo();
            headerInfo.setName(department);
            subjectsShipping.put(department, headerInfo);
            deptListShipping.add(headerInfo);
        }

        //get the children for the group
        ArrayList<ShippingInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;
        ShippingInfo detailInfo = new ShippingInfo();

        if (userType.equalsIgnoreCase("sb")) {
            detailInfo.setCity(profileGroupInfo.getShipping_info().get(0).getCity());
            detailInfo.setState(profileGroupInfo.getShipping_info().get(0).getState());
            detailInfo.setStreet(profileGroupInfo.getShipping_info().get(0).getStreet());
            detailInfo.setStreet2(profileGroupInfo.getShipping_info().get(0).getStreet2());
            detailInfo.setZipcode(profileGroupInfo.getShipping_info().get(0).getZipcode());
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");
            format1 = new SimpleDateFormat("MM-dd-yyyy");
            String chagnedDate = null;
            try {

                detailInfo.setCity(profileGroupInfo.getShipping_info().get(0).getCity());
                detailInfo.setState(profileGroupInfo.getShipping_info().get(0).getState());
                detailInfo.setStreet(profileGroupInfo.getShipping_info().get(0).getStreet());
                detailInfo.setStreet2(profileGroupInfo.getShipping_info().get(0).getStreet2());
                detailInfo.setZipcode(profileGroupInfo.getShipping_info().get(0).getZipcode());
                chagnedDate = format1.format(
                        format.parse(profileGroupInfo.getShipping_info().get(0).getShipmentHoldUntilDate()));
                detailInfo.setShipmentHoldUntilDate
                        (chagnedDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
     /*   //create a new child and add that to the group
        ShippingInfo detailInfo = new ShippingInfo();
        detailInfo.setAddress("Address:");
        detailInfo.setAddressData("123 South Main Street\n" +
                "Suite 200");
        detailInfo.setCity("City,State,Zip:");
        detailInfo.setCityData("Bellflower, CA\n" +
                "        90706");
        detailInfo.setPhone("Shipping Wait Date:");
        detailInfo.setPhoneData("12-10-2017");*/
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = deptListShipping.indexOf(headerInfo);
        shipListAdapter = new MyProfileExpandableListShipAdapter(getActivity(), deptListShipping, userType);
        simpleExpandableListViewTwo.setAdapter(shipListAdapter);
        return groupPosition;
    }


}
