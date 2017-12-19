package com.cattechnologies.tpg.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.cattechnologies.tpg.Adapters.MyProfileExpandableListEnrAdapter;
import com.cattechnologies.tpg.Adapters.MyProfileExpandableListEnrAdapterNew;
import com.cattechnologies.tpg.Model.EnrolInfo;
import com.cattechnologies.tpg.Model.MyProfileGroupEnrollInfoNew;
import com.cattechnologies.tpg.Model.ProfileGroupData;
import com.cattechnologies.tpg.Model.Response;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.Utils.AppInternetStatus;
import com.cattechnologies.tpg.Utils.NetworkUtil;
import com.cattechnologies.tpg.Utils.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by admin on 12/19/2017.
 */

public class ProfileFragmentNew extends Fragment {

    ExpandableListView simpleExpandableListViewOne;
    ExpandableListView simpleExpandableListViewTwo;
    ExpandableListView simpleExpandableListViewThree;
    public static final String ARG_SECTION_TITLE = "section_number";
    private static final String PREFS_NAME = "profile";
    String userId, userType;
    private CompositeSubscription mSubscriptions;
    PreferencesManager preferencesManager;

    LinkedHashMap<String, MyProfileGroupEnrollInfoNew> subjectsEnroll = new LinkedHashMap<String, MyProfileGroupEnrollInfoNew>();
    ArrayList<MyProfileGroupEnrollInfoNew> deptListEnroll = new ArrayList<MyProfileGroupEnrollInfoNew>();
    MyProfileExpandableListEnrAdapterNew enrolListAdapter;

    public static ProfileFragmentNew newInstance(String sectionTitle, String userId, String type) {
        ProfileFragmentNew fragment = new ProfileFragmentNew();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        args.putString("app_uid", userId);
        args.putString("acc_type", type);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragmentNew() {
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

        addProductEnroll("Owner Info", profileGroupData);

    }

    private int addProductEnroll(String department, ProfileGroupData profileGroupInfo) {

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
        /*//size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;
*/
        //create a new child and add that to the group
        EnrolInfo detailInfo = new EnrolInfo();
        if (userType.equalsIgnoreCase("sb")) {

            detailInfo.setContactFirstName(profileGroupInfo.getOwner_info().getContactFirstName());
            detailInfo.setContactLastName(profileGroupInfo.getOwner_info().getContactLastName());
            detailInfo.setStreet(profileGroupInfo.getOwner_info().getStreet());
            detailInfo.setStreet2(profileGroupInfo.getOwner_info().getStreet2());
            detailInfo.setCity(profileGroupInfo.getOwner_info().getCity());
            detailInfo.setState(profileGroupInfo.getOwner_info().getState());
            detailInfo.setZipcode(profileGroupInfo.getOwner_info().getZipcode());
            detailInfo.setOfficePhone(profileGroupInfo.getOwner_info().getOfficePhone());
            detailInfo.setFaxPhone(profileGroupInfo.getOwner_info().getFaxPhone());
            detailInfo.setEmailAddress(profileGroupInfo.getOwner_info().getEmailAddress());
            System.out.println("ProfileFragment.addProductEnroll===" + detailInfo.getFaxPhone());


        } else if (userType.equalsIgnoreCase("ero")) {
            detailInfo.setFirstName(profileGroupInfo.getOwner_info().getFirstName());
            detailInfo.setLastName(profileGroupInfo.getOwner_info().getLastName());
            detailInfo.setStreet(profileGroupInfo.getOwner_info().getStreet());
            detailInfo.setStreet2(profileGroupInfo.getOwner_info().getStreet2());
            detailInfo.setCity(profileGroupInfo.getOwner_info().getCity());
            detailInfo.setState(profileGroupInfo.getOwner_info().getState());
            detailInfo.setZipcode(profileGroupInfo.getOwner_info().getZipcode());
            detailInfo.setWorkPhone(profileGroupInfo.getOwner_info().getWorkPhone());
            detailInfo.setMobilePhone(profileGroupInfo.getOwner_info().getMobilePhone());
            detailInfo.setHomePhone(profileGroupInfo.getOwner_info().getHomePhone());
            detailInfo.setEmailAddress(profileGroupInfo.getOwner_info().getEmailAddress());

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

    private void showToast(String msg) {
        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        simpleExpandableListViewOne = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView_one);
        simpleExpandableListViewTwo = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView_two);
        simpleExpandableListViewThree = (ExpandableListView) getActivity().findViewById(R.id.simpleExpandableListView_three);


    }
}
