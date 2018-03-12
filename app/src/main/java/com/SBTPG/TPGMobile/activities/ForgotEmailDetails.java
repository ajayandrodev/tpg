package com.SBTPG.TPGMobile.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserEmailAddress;
import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserEmailAddressSb;
import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserEmailData;
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

public class ForgotEmailDetails extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    Toolbar toolbar;
    TextView mTitle, mTextEfin, mTextPass;
    Button mLogin;
    Button llForgotPassword;
    LinearLayout llForgotCheckBox;
    Dialog d;
    EditText loginUsername, loginUserPassword;
    private String drawerTitle;
    CompositeSubscription mSubscriptions;
    PreferencesManager preferencesManager;
    CheckBox checkBox;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_email_info);
        mLogin = (Button) findViewById(R.id.login_button_recover);
        mTextEfin = (TextView) findViewById(R.id.text_efin);
        loginUsername = (EditText) findViewById(R.id.login_username_forgot);
        loginUserPassword = (EditText) findViewById(R.id.login_password_forgot);
        mTextPass = (TextView) findViewById(R.id.text_password);
        llForgotCheckBox = (LinearLayout) findViewById(R.id.ll_forgot_checkbox);
        checkBox = (CheckBox) findViewById(R.id.checkbox_data);
        Bundle bundle = getIntent().getExtras();
        drawerTitle = bundle.getString("secleted_user_forgot");
        mLogin.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_login);
        mSubscriptions = new CompositeSubscription();
        preferencesManager = new PreferencesManager();
        checkBox.setOnCheckedChangeListener(this);
        setToolbar();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getResources().getString(R.string.forgot_user_email_new));
        drawerTitle = mTitle.getText().toString();
        selectedData(drawerTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void selectedData(String drawerTitle) {
        mTextEfin.setText("EFIN");
        loginUsername.setHint("Enter your EFIN ");
        mTextPass.setText("LAST 4 OF SSN");
        loginUserPassword.setHint("Enter last 4 digits of SSN");
        mLogin.setText("RECOVER EMAIL");
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
                String type = null;
                if (checkBox.isChecked()) {
                    type = "sb";
                    preferencesManager.saT(getApplicationContext(), type);
                } else {
                    type = "ero";
                    preferencesManager.saT(getApplicationContext(), type);
                }
                if (type.equalsIgnoreCase("sb")) {
                    if (loginUsername.getText().toString().isEmpty() &&
                            loginUserPassword.getText().toString().isEmpty()) {
                        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                    } else if (loginUsername.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Please enter your EFIN.", Toast.LENGTH_SHORT).show();

                    } else if (loginUserPassword.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Please enter your  last 4 digits of your SSN.", Toast.LENGTH_SHORT).show();

                    } else if (!(loginUsername.getText().toString().isEmpty()) &&

                            !(loginUserPassword.getText().toString().isEmpty())) {
                        forgotEmailAddressSb(forgotUname, type);

                    }
                } else if (type.equalsIgnoreCase("ero")) {
                    if (loginUsername.getText().toString().isEmpty() &&
                            loginUserPassword.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Please enter your EFIN and last 4 digits of your SSN.", Toast.LENGTH_SHORT).show();
                    } else if (loginUsername.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Please enter your EFIN.", Toast.LENGTH_SHORT).show();

                    } else if (loginUserPassword.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Please enter your  last 4 digits of your SSN.", Toast.LENGTH_SHORT).show();

                    } else if (!(loginUsername.getText().toString().isEmpty()) &&

                            !(loginUserPassword.getText().toString().isEmpty())) {
                        forgotEmailAddress(forgotUname, forgotUpass, type);

                    }

                }


        }
    }

    private void forgotEmailAddressSb(String forgotUname, String acc_type) {
        progressBar.setVisibility(View.VISIBLE);
        if (AppInternetStatus.getInstance(this).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().forgotEmailAddressSb(forgotUname, acc_type)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");

        }
    }

    private void handleResponse(ForgotUserEmailAddressSb response) {
        showToast(response.getMessage());
        progressBar.setVisibility(View.GONE);

        if (response.getStatus().equalsIgnoreCase("success")) {
            showToast(response.getMessage());
            ForgotUserEmailData forgotUserDetailsData = response.getUser_data();
            String data = forgotUserDetailsData.getEMAIL_ADDRESS();


            Intent i = new Intent(this, LoginScreen.class);
            i.putExtra(LoginScreen.ARG_SELECTION_USER, drawerTitle);
            i.putExtra("forgotUser", data);
            startActivity(i);

        }
    }

    private void forgotEmailAddress(String forgotUname, String forgotUpass, String acc_type) {
        progressBar.setVisibility(View.VISIBLE);
        if (AppInternetStatus.getInstance(this).isOnline()) {
            mSubscriptions.addAll(NetworkUtil.getRetrofit().forgotEmailAddress(forgotUname, forgotUpass, acc_type)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(this::handleResponse, this::handleError));


        } else {
            showToast("Internet Connection Is Not Available");

        }
    }

    private void handleResponse(ForgotUserEmailAddress response) {
        showToast(response.getMessage());
        progressBar.setVisibility(View.GONE);

        if (response.getStatus().equalsIgnoreCase("success")) {
            showToast(response.getMessage());
            ForgotUserEmailData forgotUserDetailsData = response.getUser_data();
            String data = forgotUserDetailsData.getEMAIL_ADDRESS();


            Intent i = new Intent(this, LoginScreen.class);
            i.putExtra(LoginScreen.ARG_SELECTION_USER, drawerTitle);
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

            } catch (IOException e) {
                Log.e("error", e.getMessage());
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
            loginUserPassword.setVisibility(View.GONE);
            mTextPass.setVisibility(View.GONE);
            preferencesManager.saT(getApplicationContext(), type);
        } else {
            type = "ero";
            preferencesManager.saT(getApplicationContext(), type);
            loginUserPassword.setVisibility(View.VISIBLE);
            mTextPass.setVisibility(View.VISIBLE);
        }
    }
}