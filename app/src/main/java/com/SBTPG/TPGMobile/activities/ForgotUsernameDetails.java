package com.SBTPG.TPGMobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserNameData;
import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserNameInfo;
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
 * Created by Ajay on 12/1/2017.
 */

public class ForgotUsernameDetails extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "error";
    Toolbar toolbar;
    TextView mTitle, mTextEfin, mTextPass;
    Button mLogin;
    LinearLayout llForgotCheckBox;
    EditText loginUsername, loginUserPassword;
    private String drawerTitle;
    CompositeSubscription mSubscriptions;
    PreferencesManager preferencesManager;
    CheckBox checkBox;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_user_name_info);
        mLogin = (Button) findViewById(R.id.login_button_recover);

        mTextEfin = (TextView) findViewById(R.id.text_efin);
        loginUsername = (EditText) findViewById(R.id.login_username_forgot);
        mTextPass = (TextView) findViewById(R.id.text_password);
        loginUserPassword = (EditText) findViewById(R.id.login_password_forgot);
        progressBar = (ProgressBar) findViewById(R.id.progress_login);
        llForgotCheckBox = (LinearLayout) findViewById(R.id.ll_forgot_checkbox);
        checkBox = (CheckBox) findViewById(R.id.checkbox_data);
        Bundle bundle = getIntent().getExtras();
        drawerTitle = bundle.getString("secleted_user_forgot");
        mLogin.setOnClickListener(this);
        mSubscriptions = new CompositeSubscription();
        preferencesManager = new PreferencesManager();
        checkBox.setOnCheckedChangeListener(this);
        setToolbar();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getResources().getString(R.string.forgot_user_name_new));
        selectedData(drawerTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void selectedData(String drawerTitle) {

        mTextEfin.setText("EFIN");
        loginUsername.setHint("Enter EFIN");
        mTextPass.setText("EMAIL");
        loginUserPassword.setHint("Enter Email Address");
        mLogin.setText("RECOVER USERNAME");
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
                String forgotUname = loginUsername.getText().toString();
                String forgotUpass = loginUserPassword.getText().toString();
                if (ValidEmail(forgotUpass)) {

                }
                String type = "";
                if (checkBox.isChecked()) {
                    type = "sb";
                    preferencesManager.saT(getApplicationContext(), type);
                } else {
                    type = "ero";
                    preferencesManager.saT(getApplicationContext(), type);
                }
                forgotUserNameResponse(forgotUname, forgotUpass, preferencesManager.gaT(getApplicationContext()));

        }

    }

    private boolean ValidEmail(String string) {
        if (TextUtils.isEmpty(string) || !Patterns.EMAIL_ADDRESS.matcher(string).matches()) {

            return false;

        } else {

            return true;
        }
    }

    private void forgotUserNameResponse(String efin, String email, String type) {
        progressBar.setVisibility(View.VISIBLE);
        if (AppInternetStatus.getInstance(this).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().forgotUserName(efin, email, type)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            showToast("Internet Connection Is Not Available");

        }
    }

    private void showToast(String msg) {
        try {
            Toast.makeText(getApplicationContext(), "" + msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void handleResponse(ForgotUserNameInfo response) {
        showToast(response.getMessage());
        progressBar.setVisibility(View.GONE);
        if (response.getStatus().equalsIgnoreCase("success")) {
            showToast(response.getMessage());
            ForgotUserNameData forgotUserDetailsData = response.getUser_data();
            String data = forgotUserDetailsData.getLOGIN_NAME();
            Intent i = new Intent(this, BackToLoginScreen.class);
            i.putExtra(BackToLoginScreen.ARG_SELECTION_USER, drawerTitle);
            i.putExtra("forgotUser", data);
            startActivity(i);
        }
    }

    private void handleError(Throwable error) {
        progressBar.setVisibility(View.GONE);
        showToast(error.getMessage());
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
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginUsername.setText("");
        loginUserPassword.setText("");
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        String type = "";
        if (isChecked == true) {
            type = "sb";
            preferencesManager.saT(getApplicationContext(), type);
        } else {
            type = "ero";
            preferencesManager.saT(getApplicationContext(), type);
        }


    }
}
