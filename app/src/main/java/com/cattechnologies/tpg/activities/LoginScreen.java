package com.cattechnologies.tpg.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cattechnologies.tpg.model.dashboardModel.DashboardInfo;
import com.cattechnologies.tpg.model.dashboardModel.DashboardInfoData;
import com.cattechnologies.tpg.model.LoginInfo;
import com.cattechnologies.tpg.model.profileModel.ProfileData;
import com.cattechnologies.tpg.model.dashboardModel.RecentTransactions;
import com.cattechnologies.tpg.model.Response;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.utils.AppInternetStatus;
import com.cattechnologies.tpg.utils.NetworkUtil;
import com.cattechnologies.tpg.utils.PreferencesManager;
import com.cattechnologies.tpg.viewHolderData.AnalyticsApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by admin on 9/29/2017.
 */

public class LoginScreen extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final String ARG_SELECTION_USER = "forgot_user_data";
    Button loginBt;
    TextView footer;
    Toolbar toolbar;
    private String drawerTitle;
    String forgotPasswordData;
    TextView mTitle, mEmailTitle, mLoginUsername, mLoginUserPass;
    Button forgot_user_name, forgot_user_password, forgot_user_email, forgot_user_cancel;
    EditText loginUser, loginPass;
    Dialog d;

    CheckBox checkBox;
    private CompositeSubscription mSubscriptions;
    LoginInfo loginInfo;
    ProgressBar progressBar;
    SimpleDateFormat format, format1;
    String forgotEmailData;
    Intent i;
    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initViews();
        setToolbar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            drawerTitle = bundle.getString(ARG_SELECTION_USER);
            forgotEmailData = bundle.getString("forgotUser");
            System.out.println("LoginScreen.onCreate===" + drawerTitle);
            selectedForgotData(drawerTitle);
        }
     //   AnalyticsApplication.getInstance().clearApplicationData();

    }

    private void initViews() {
        mSubscriptions = new CompositeSubscription();

        mEmailTitle = (TextView) findViewById(R.id.text_email_title);
        mLoginUsername = (TextView) findViewById(R.id.login_username_text);
        mLoginUserPass = (TextView) findViewById(R.id.login_pass_text);
        loginBt = (Button) findViewById(R.id.login_button);
        loginUser = (EditText) findViewById(R.id.login_username);
        loginPass = (EditText) findViewById(R.id.login_password);
        footer = (TextView) findViewById(R.id.footer);
        checkBox = (CheckBox) findViewById(R.id.checkbox_data_login);
        progressBar = (ProgressBar) findViewById(R.id.progress_login);
        loginInfo = new LoginInfo();
        preferencesManager = new PreferencesManager();
        loginBt.setOnClickListener(this);
        footer.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(this);


    }

    private void selectedForgotData(String drawerTitle) {
        if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_email_new))) {
            mEmailTitle.setVisibility(View.VISIBLE);
            mLoginUsername.setText("EMAIL");
            mLoginUserPass.setText("PASSWORD");
            loginBt.setText("LOGIN");
        } else {
            mEmailTitle.setVisibility(View.GONE);
            mLoginUsername.setText("EFIN");
            mLoginUserPass.setText("PASSWORD");
            loginBt.setText("LOGIN");
        }
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        drawerTitle = getResources().getString(R.string.login_item);
        setSupportActionBar(toolbar);
        mTitle.setText(drawerTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_button:
                loginBt.setBackgroundColor(getResources().getColor(R.color.back_button_click_color));


                if (loginUser.getText().toString().isEmpty()) {
                    showToast("Please enter your User ID.");

                } else if (loginPass.getText().toString().isEmpty()) {
                    showToast("Please enter your password.");

                } else if (!(loginUser.getText().toString().isEmpty()) && !(loginPass.getText().toString().isEmpty())) {
                    String type = null;
                    if (checkBox.isChecked()) {
                        type = "sb";
                        loginInfo.setAcc_type(type);
                    } else {
                        type = "ero";
                        loginInfo.setAcc_type(type);
                    }


                    loadLoginResponse(loginUser.getText().toString(), loginInfo.getAcc_type(), loginPass.getText().toString());
                    progressBar.setVisibility(View.VISIBLE);

                }

                break;
            case R.id.footer:
                footer.setBackgroundColor(getResources().getColor(R.color.footer_back_ground_clicked));
                footer.setTextColor(getResources().getColor(R.color.footer_back_ground_clicked_text));
                d = new BottomSheetDialog(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                d.setContentView(R.layout.dialog);
                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                forgot_user_name = (Button) d.findViewById(R.id.forgot_user_name);
                forgot_user_password = (Button) d.findViewById(R.id.forgot_user_password);
                forgot_user_email = (Button) d.findViewById(R.id.forgot_user_email);
                forgot_user_cancel = (Button) d.findViewById(R.id.cancel_forgot);
                d.setCancelable(true);
                d.show();
                forgot_user_name.setOnClickListener(this);
                forgot_user_password.setOnClickListener(this);
                forgot_user_email.setOnClickListener(this);
                forgot_user_cancel.setOnClickListener(this);
                break;
            case R.id.forgot_user_name:
                forgotPasswordData = getResources().getString(R.string.forgot_user_name);
                i = new Intent(this, ForgotUsernameDetails.class);
                i.putExtra(ForgotUsernameDetails.ARG_SELECTION_USER, forgotPasswordData);
                startActivity(i);
                d.dismiss();

                break;
            case R.id.forgot_user_password:
                forgotPasswordData = getResources().getString(R.string.forgot_user_password);
                i = new Intent(this, ForgotPasswordDetails.class);
                i.putExtra(ForgotUsernameDetails.ARG_SELECTION_USER, forgotPasswordData);
                startActivity(i);
                d.dismiss();

                break;
            case R.id.forgot_user_email:
                forgotPasswordData = getResources().getString(R.string.forgot_user_email);
                i = new Intent(this, ForgotEmailDetails.class);
                i.putExtra(ForgotUsernameDetails.ARG_SELECTION_USER, forgotPasswordData);
                startActivity(i);
                d.dismiss();

                break;
            case R.id.cancel_forgot:
                d.dismiss();
                break;

        }

    }


    private void loadLoginResponse(String userId, String type, String userPassword) {


        loginInfo.setApp_uid(userId);
        loginInfo.setApp_pswd(userPassword);
        loginInfo.setAcc_type(type);
        loginInfo.setUser_efin(userId);

        preferencesManager.saveAccountType(getApplicationContext(), loginInfo.getAcc_type());
        preferencesManager.saveUserId(getApplicationContext(), loginInfo.getApp_uid());
        if (AppInternetStatus.getInstance(this).isOnline()) {
            progressBar.setVisibility(View.VISIBLE);

            mSubscriptions.addAll(NetworkUtil.getRetrofit().sign(userId,
                    type, userPassword)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));

        } else {
            showToast("Internet Connection Is Not Available");

        }


    }

    private void handleError(Throwable error) {
        progressBar.setVisibility(View.GONE);
        System.out.println("LoginScreen.handleResponse==="+error.getMessage());

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


    private void handleResponse(DashboardInfo response) {
        progressBar.setVisibility(View.GONE);
        System.out.println("LoginScreen.handleResponse==="+response.getMessage());
        if (response.getStatus().equalsIgnoreCase("success") && response != null) {
          //  showToast(response.getMessage());
            DashboardInfoData dashboardInfo = response.getDashboard_data();
            if (response.getDashboard_data() == null) {
                showToast(response.getMessage());
                Intent i = new Intent(this, EmployeeLoginActivity.class);
                i.putExtra(BackToLoginScreen.ARG_SELECTION_USER, drawerTitle);
                startActivity(i);
            } else {
                ProfileData profileData =response.getProfile_data();
                List<RecentTransactions> recentTransactionsList = new ArrayList<>();
                for (int i = 0; i < response.getRecent_transactions().size(); i++) {
                    RecentTransactions recentTransactions = new RecentTransactions();
                    format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    //format1 = new SimpleDateFormat("MM-dd-yyyy");
                    format1 = new SimpleDateFormat("MM-dd-yyyy");

                    String chagnedDate = null;
                    try {
                        chagnedDate = format1.format(format.parse(response.getRecent_transactions().get(i).getLastUpadte()));
                        recentTransactions.setLastUpadte(chagnedDate);
                        recentTransactions.setAmount(response.getRecent_transactions().get(i).getAmount());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    recentTransactionsList.add(recentTransactions);
                }

                Intent intent = new Intent(LoginScreen.this, Dashboard.class);
                intent.putExtra("DashboardInfoData", dashboardInfo);
                intent.putExtra("ProfileData", profileData);
                intent.putParcelableArrayListExtra("RecentTransactions", (ArrayList<? extends Parcelable>) recentTransactionsList);
                startActivity(intent);
            }

        } else {
            showToast(response.getMessage());
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("LoginScreen.onResume");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        if (forgotEmailData != null) {
            if (loginUser.getText().toString().isEmpty()) {
                loginUser.setText(forgotEmailData);
            } else {
                loginUser.setText("");
                mEmailTitle.setVisibility(View.GONE);
            }
            loginPass.setText("");
            if (checkBox.isChecked()) {
                checkBox.setChecked(false);
            }
        } else {
            loginUser.setText("");
            loginPass.setText("");
            if (checkBox.isChecked()) {
                checkBox.setChecked(false);
            }
        }


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        loginUser.setText("");
        loginPass.setText("");
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
        }

    }

    private void showToast(String login) {
        Toast.makeText(this, "" + login, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("LoginScreen.onDestroy==" + loginUser.getText().toString());
        mSubscriptions.unsubscribe();
        loginUser.setText("");

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        if (isChecked) {
            loginInfo.setAcc_type("sb");
        } else {
            loginInfo.setAcc_type("ero");
        }


    }
}
