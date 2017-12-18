package com.cattechnologies.tpg.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import com.cattechnologies.tpg.Model.ForgotUserEmailAddress;
import com.cattechnologies.tpg.Model.ForgotUserEmailAddressSb;
import com.cattechnologies.tpg.Model.ForgotUserEmailData;
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

public class ForgotEmailDetails extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    Toolbar toolbar;
    TextView mTitle, mTextEfin, mTextPass;
    Button mLogin;
    Button llForgotPassword;
    LinearLayout llForgotCheckBox;
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
        setContentView(R.layout.activity_forgot_email_info);
        mLogin = (Button) findViewById(R.id.login_button_recover);
        mTextEfin = (TextView) findViewById(R.id.text_efin);
        loginUsername = (EditText) findViewById(R.id.login_username_forgot);
        loginUserPassword = (EditText) findViewById(R.id.login_password_forgot);
        mTextPass = (TextView) findViewById(R.id.text_password);
        llForgotCheckBox = (LinearLayout) findViewById(R.id.ll_forgot_checkbox);
        checkBox = (CheckBox) findViewById(R.id.checkbox_data);
        Bundle bundle = getIntent().getExtras();
        drawerTitle = bundle.getString(ARG_SELECTION_USER);
        mLogin.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_login);

        mSubscriptions = new CompositeSubscription();
        preferencesManager = new PreferencesManager();
        loginInfo = new LoginInfo();
        checkBox.setOnCheckedChangeListener(this);
        setToolbar();

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getResources().getString(R.string.forgot_user_email_new));
        drawerTitle = mTitle.getText().toString();
        System.out.println("ForgotEmailDetails.setToolbar===" + drawerTitle);
        selectedData(drawerTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void selectedData(String drawerTitle) {
        System.out.println("ForgotEmailDetails.selectedData===" + drawerTitle);
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
                    loginInfo.setAcc_type(type);
                    preferencesManager.saveAccountType(getApplicationContext(), loginInfo.getAcc_type());
                } else {
                    type = "ero";
                    loginInfo.setAcc_type(type);
                    preferencesManager.saveAccountType(getApplicationContext(), loginInfo.getAcc_type());
                }
                if (loginInfo.getAcc_type().equalsIgnoreCase("sb")) {
                    forgotEmailAddressSb(forgotUname, loginInfo.getAcc_type());

                } else {
                    forgotEmailAddress(forgotUname, forgotUpass, loginInfo.getAcc_type());

                }


        }
    }

    private void forgotEmailAddressSb(String forgotUname, String acc_type) {
        progressBar.setVisibility(View.VISIBLE);
        System.out.println("ForgotEmailDetails.forgotEmailAddressSb===issbcalling");
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
        System.out.println("ForgotEmailDetails.handleResponse==sb==" + response.getMessage());
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
        System.out.println("ForgotEmailDetails.forgotEmailAddress===");
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
        System.out.println("ForgotEmailDetails.handleResponse==" + response.getMessage());
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
        System.out.println("ForgotEmailDetails.onBackPressed====");
        loginUserPassword.setText("");
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("ForgotEmailDetails.onResume===");
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
            loginInfo.setAcc_type(type);
            loginUserPassword.setVisibility(View.GONE);
            mTextPass.setVisibility(View.GONE);
            preferencesManager.saveAccountType(getApplicationContext(), loginInfo.getAcc_type());


        } else {
            type = "ero";
            loginInfo.setAcc_type(type);
            preferencesManager.saveAccountType(getApplicationContext(), loginInfo.getAcc_type());
            loginUserPassword.setVisibility(View.VISIBLE);
            mTextPass.setVisibility(View.VISIBLE);


        }
    }
}
