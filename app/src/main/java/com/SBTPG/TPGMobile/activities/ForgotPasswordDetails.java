package com.SBTPG.TPGMobile.activities;

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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserPasswordData;
import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserPasswordInfo;
import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserPasswordInfoEmp;
import com.SBTPG.TPGMobile.model.Response;
import com.SBTPG.TPGMobile.R;
import com.SBTPG.TPGMobile.utils.AppInternetStatus;
import com.SBTPG.TPGMobile.utils.NetworkUtil;
import com.SBTPG.TPGMobile.utils.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ajay on 12/6/2017.
 */

public class ForgotPasswordDetails extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "error";
    Toolbar toolbar;
    TextView mTitle, mTextEfin, mTextPass;
    Button mLogin;
    Button llForgotPassword;
    Button forgot_user_name, forgot_user_password, forgot_user_email, forgot_user_cancel;
    Dialog d;
    EditText loginUsername, loginUserPassword;
    private String drawerTitle;
    CompositeSubscription mSubscriptions;
    PreferencesManager preferencesManager;
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

        Bundle bundle = getIntent().getExtras();
        drawerTitle = bundle.getString("secleted_user_forgot");
        mLogin.setOnClickListener(this);
        llForgotPassword.setOnClickListener(this);
        mSubscriptions = new CompositeSubscription();
        preferencesManager = new PreferencesManager();
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
                if (llForgotPassword.getText().toString().equalsIgnoreCase(getResources().getString(R.string.office_emp))) {
                    loginUsername.setVisibility(View.GONE);
                    String forgotUpass = loginUserPassword.getText().toString();
                    String type = "emp";
                    preferencesManager.saT(getApplicationContext(), type);
                    forgotPasswordEmp(forgotUpass, type);
                } else if (llForgotPassword.getText().toString().equalsIgnoreCase(getResources().getString(R.string.service_buro))) {
                    String forgotUname = loginUsername.getText().toString();
                    String forgotUpass = loginUserPassword.getText().toString();
                    String type = "sb";
                    preferencesManager.saT(getApplicationContext(), type);
                    forgotPassword(forgotUname, forgotUpass, type);
                } else if (llForgotPassword.getText().toString().equalsIgnoreCase(getResources().getString(R.string.ero_info))) {
                    String forgotUname = loginUsername.getText().toString();
                    String forgotUpass = loginUserPassword.getText().toString();
                    String type = "ero";
                    preferencesManager.saT(getApplicationContext(), type);
                    forgotPassword(forgotUname, forgotUpass, type);
                } else if (TextUtils.isEmpty(loginUsername.getText().toString()) &&
                        !(llForgotPassword.getText().toString().equalsIgnoreCase(getResources().getString(R.string.office_emp)))) {
                    Toast.makeText(this, "Please enter your EFIN.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(loginUserPassword.getText().toString())) {
                    Toast.makeText(this, "Please enter your email address.", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(llForgotPassword.getText().toString())) {
                    Toast.makeText(this, "Please select your login type", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(loginUserPassword.getText().toString()) &&
                        llForgotPassword.getText().toString().equalsIgnoreCase(getResources().getString(R.string.office_emp))) {
                    Toast.makeText(this, "Please enter your email address.", Toast.LENGTH_SHORT).show();
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


    private void showToast(String msg) {
        try {
            Toast.makeText(getApplicationContext(), "" + msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
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
                throw new RuntimeException(e);
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
