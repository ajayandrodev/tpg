package com.cattechnologies.tpg.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cattechnologies.tpg.Model.ForgotUserEmailAddress;
import com.cattechnologies.tpg.Model.ForgotUserEmailData;
import com.cattechnologies.tpg.Model.ForgotUserNameData;
import com.cattechnologies.tpg.Model.ForgotUserNameInfo;
import com.cattechnologies.tpg.Model.ForgotUserPasswordData;
import com.cattechnologies.tpg.Model.ForgotUserPasswordInfo;
import com.cattechnologies.tpg.Model.ForgotUserPasswordInfoEmp;
import com.cattechnologies.tpg.Model.LoginInfo;
import com.cattechnologies.tpg.Model.Response;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.Utils.AppInternetStatus;
import com.cattechnologies.tpg.Utils.NetworkUtil;
import com.cattechnologies.tpg.Utils.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by admin on 12/6/2017.
 */

public class ForgotPasswordDetails extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView mTitle, mTextEfin, mTextPass;
    Button mLogin;
    Button llForgotPassword;
    LinearLayout llForgotCheckBox;
    Button forgot_user_name, forgot_user_password, forgot_user_email, forgot_user_cancel;
    Dialog d;
    EditText loginUsername, loginUserPassword;
    private String drawerTitle;
    public static final String ARG_SELECTION_USER = "secleted_user_forgot";
    CompositeSubscription mSubscriptions;
    PreferencesManager preferencesManager;
    CheckBox checkBox;
    LoginInfo loginInfo;
    ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_info);
        mLogin = (Button) findViewById(R.id.login_button_recover);
        mTextEfin = (TextView) findViewById(R.id.text_efin);
        loginUsername = (EditText) findViewById(R.id.login_username_forgot);
        loginUserPassword = (EditText) findViewById(R.id.login_password_forgot);
        mTextPass = (TextView) findViewById(R.id.text_password);

        llForgotPassword = (Button) findViewById(R.id.login_business_patner);

        llForgotCheckBox = (LinearLayout) findViewById(R.id.ll_forgot_checkbox);
        checkBox = (CheckBox) findViewById(R.id.checkbox_data);
        Bundle bundle = getIntent().getExtras();
        drawerTitle = bundle.getString(ARG_SELECTION_USER);
        mLogin.setOnClickListener(this);
        llForgotPassword.setOnClickListener(this);
        mSubscriptions = new CompositeSubscription();
        preferencesManager = new PreferencesManager();
        loginInfo = new LoginInfo();
        progressBar = (ProgressBar) findViewById(R.id.progress_login);


        setToolbar();

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getResources().getString(R.string.forgot_user_password_new));
        selectedData(drawerTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void selectedData(String drawerTitle) {

        mTextEfin.setText("EFIN OR USERNAME");
        loginUsername.setHint("Enter your EFIN or Username");
        mTextPass.setText("EMAIL");
        loginUserPassword.setHint("Enter your email address");
        mLogin.setText("REQUEST PIN");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_button_recover:
                mLogin.setBackgroundColor(getResources().getColor(R.color.back_button_click_color));
                if (llForgotPassword.getText().equals(getResources().getString(R.string.office_emp))) {
                    loginUsername.setVisibility(View.GONE);
                    String forgotUpass = loginUserPassword.getText().toString();
                    String type = "emp";
                    loginInfo.setAcc_type(type);
                    preferencesManager.saveAccountType(getApplicationContext(), loginInfo.getAcc_type());
                    forgotPasswordEmp(forgotUpass, loginInfo.getAcc_type());


                } else if (llForgotPassword.getText().equals(getResources().getString(R.string.service_buro))) {
                    String forgotUname = loginUsername.getText().toString();
                    String forgotUpass = loginUserPassword.getText().toString();
                    String type = "sb";
                    loginInfo.setAcc_type(type);
                    preferencesManager.saveAccountType(getApplicationContext(), loginInfo.getAcc_type());

                    forgotPassword(forgotUname, forgotUpass, loginInfo.getAcc_type());


                } else if (llForgotPassword.getText().equals(getResources().getString(R.string.ero_info))) {
                    String forgotUname = loginUsername.getText().toString();
                    String forgotUpass = loginUserPassword.getText().toString();
                    String type = "ero";
                    loginInfo.setAcc_type(type);
                    preferencesManager.saveAccountType(getApplicationContext(), loginInfo.getAcc_type());

                    forgotPassword(forgotUname, forgotUpass, loginInfo.getAcc_type());
                } else {
                    if (TextUtils.isEmpty(loginUsername.getText().toString())) {
                        Toast.makeText(this, "User EFIN cannot be empty", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(loginUserPassword.getText().toString())) {
                        Toast.makeText(this, "Email ID cannot be empty", Toast.LENGTH_SHORT).show();

                    } else if (TextUtils.isEmpty(llForgotPassword.getText().toString())) {
                        Toast.makeText(this, "Please select your login type", Toast.LENGTH_SHORT).show();

                    }
                }

                break;
            case R.id.login_business_patner:
                d = new BottomSheetDialog(this, R.style.AppTheme);
                d.setContentView(R.layout.dialog_for_forgot);
                forgot_user_name = (Button) d.findViewById(R.id.forgot_user_name_ss);
                forgot_user_password = (Button) d.findViewById(R.id.forgot_user_password_ss);
                forgot_user_email = (Button) d.findViewById(R.id.forgot_user_email_ss);
                forgot_user_cancel = (Button) d.findViewById(R.id.cancel_forgot_ss);
                d.setCancelable(true);
                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d.show();
                forgot_user_name.setOnClickListener(this);
                forgot_user_password.setOnClickListener(this);
                forgot_user_email.setOnClickListener(this);
                forgot_user_cancel.setOnClickListener(this);
                break;
            case R.id.forgot_user_name_ss:
                llForgotPassword.setText(getResources().getString(R.string.ero_info));
                mTextEfin.setVisibility(View.VISIBLE);
                loginUsername.setVisibility(View.VISIBLE);
                d.dismiss();

                break;
            case R.id.forgot_user_password_ss:
                llForgotPassword.setText(getResources().getString(R.string.office_emp));
                mTextEfin.setVisibility(View.GONE);
                loginUsername.setVisibility(View.GONE);

                d.dismiss();

                break;
            case R.id.forgot_user_email_ss:
                llForgotPassword.setText(getResources().getString(R.string.service_buro));
                mTextEfin.setVisibility(View.VISIBLE);
                loginUsername.setVisibility(View.VISIBLE);
                d.dismiss();

                break;
            case R.id.cancel_forgot_ss:
                d.dismiss();
                break;
        }
    }

    private void forgotPasswordEmp(String forgotUpass, String acc_type) {
        System.out.println("ForgotPasswordDetails.forgotPassword====" + forgotUpass + "===" + acc_type);
        progressBar.setVisibility(View.VISIBLE);

        if (AppInternetStatus.getInstance(this).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().forgotPasswordEmp(forgotUpass, acc_type)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");

        }
    }

    private void handleResponse(ForgotUserPasswordInfoEmp response) {
        showToast(response.getMessage());
        progressBar.setVisibility(View.GONE);

        System.out.println("ForgotPasswordDetails.handleResponse====" + response.getMessage());
        if (response.getStatus().equalsIgnoreCase("success")) {
            showToast(response.getMessage());
            ForgotUserPasswordData forgotUserDetailsData = response.getUser_data();
            String data = forgotUserDetailsData.getTEMPORARY_PIN();

            Intent i = new Intent(this, BackToLoginScreen.class);
            i.putExtra(BackToLoginScreen.ARG_SELECTION_USER, drawerTitle);
            i.putExtra("forgotUser", data);
            startActivity(i);

        }

    }


    private void forgotPassword(String forgotUname, String forgotUpass, String acc_type) {
        System.out.println("ForgotPasswordDetails.forgotPassword====" + forgotUname + "===" + forgotUpass + "===" + acc_type);
        progressBar.setVisibility(View.VISIBLE);


        if (AppInternetStatus.getInstance(this).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().forgotPassword(forgotUname, forgotUpass, acc_type)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");

        }
    }

    private void handleResponse(ForgotUserPasswordInfo response) {
        showToast(response.getMessage());
        progressBar.setVisibility(View.GONE);

        System.out.println("ForgotPasswordDetails.handleResponse====" + response.getMessage());
        if (response.getStatus().equalsIgnoreCase("success")) {
            showToast(response.getMessage());
            ForgotUserPasswordData forgotUserDetailsData = response.getUser_data();
            String data = forgotUserDetailsData.getTEMPORARY_PIN();
            System.out.println("ForgotPasswordDetails.handleResponse===" + data);

            Intent i = new Intent(this, BackToLoginScreen.class);
            i.putExtra(BackToLoginScreen.ARG_SELECTION_USER, drawerTitle);
            i.putExtra("forgotUser", data);
            startActivity(i);

        }

    }


    private void showToast(String msg) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
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
                System.out.println("ForgotPasswordDetails.handleError===" + errorBody);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showToast("Network Error !");
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        loginUsername.setText("");
        loginUserPassword.setText("");
        llForgotPassword.setText("");

    }

    @Override
    protected void onResume() {
        super.onResume();
        loginUsername.setText("");
        loginUserPassword.setText("");
        llForgotPassword.setText("");


    }
}
