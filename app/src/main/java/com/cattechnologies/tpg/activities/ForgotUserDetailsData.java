package com.cattechnologies.tpg.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserEmailAddress;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserEmailData;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserNameData;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserNameInfo;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserPasswordData;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserPasswordInfo;
import com.cattechnologies.tpg.model.Response;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.utils.AppInternetStatus;
import com.cattechnologies.tpg.utils.NetworkUtil;
import com.cattechnologies.tpg.utils.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by Ajay on 10/10/2017.
 */

public class ForgotUserDetailsData extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_info);
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
        checkBox.setOnCheckedChangeListener(this);
        setToolbar();

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_name))) {
            mTitle.setText(getResources().getString(R.string.forgot_user_name_new));
        } else if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_password))) {
            mTitle.setText(getResources().getString(R.string.forgot_user_password_new));
        } else if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_email))) {
            mTitle.setText(getResources().getString(R.string.forgot_user_email_new));
        }
        selectedData(drawerTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void selectedData(String drawerTitle) {
        if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_name))) {
            mTextEfin.setText("EFIN");
            loginUsername.setHint("Enter EFIN");
            mTextPass.setText("EMAIL");
            loginUserPassword.setHint("Enter Email Address");
            mLogin.setText("RECOVER USERNAME");
        } else if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_password))) {
            mTextEfin.setText("EFIN OR USERNAME");
            loginUsername.setHint("Enter your EFIN or Username");
            mTextPass.setText("EMAIL");
            loginUserPassword.setHint("Enter your email address");
            mLogin.setText("REQUEST PIN");
            llForgotPassword.setVisibility(View.VISIBLE);
            llForgotCheckBox.setVisibility(View.GONE);
        } else if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_email))) {
            mTextEfin.setText("EFIN");
            loginUsername.setHint("Enter your EFIN ");
            mTextPass.setText("LAST 4 OF SSN");
            loginUserPassword.setHint("Enter last 4 digits of SSN");
            mLogin.setText("RECOVER EMAIL");
        }

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
                if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_name))) {
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
                    forgotUserNameResponse(forgotUname, forgotUpass, preferencesManager.gaT(getApplicationContext()));
                } else if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_password))) {
                    if (llForgotPassword.getText().toString().equalsIgnoreCase(getResources().getString(R.string.office_emp))) {
                        Intent i = new Intent(this, BackToLoginScreen.class);
                        i.putExtra(BackToLoginScreen.ARG_SELECTION_USER, drawerTitle);
                        startActivity(i);
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
                    } else {
                        Intent i = new Intent(this, BackToLoginScreen.class);
                        i.putExtra(BackToLoginScreen.ARG_SELECTION_USER, drawerTitle);
                        startActivity(i);
                    }
                } else if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_email))) {
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
                    forgotEmailAddress(forgotUname, forgotUpass, type);
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
                mTextEfin.setVisibility(View.INVISIBLE);
                loginUsername.setVisibility(View.INVISIBLE);
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

    private void forgotEmailAddress(String forgotUname, String forgotUpass, String acc_type) {
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
        if (response.getStatus().equalsIgnoreCase("success") && response != null) {
            showToast(response.getMessage());
            ForgotUserEmailData forgotUserDetailsData = response.getUser_data();
            String data = forgotUserDetailsData.getEMAIL_ADDRESS();
            Intent i = new Intent(this, LoginScreen.class);
            i.putExtra(LoginScreen.ARG_SELECTION_USER, drawerTitle);
            i.putExtra("forgotUser", data);
            startActivity(i);

        }
    }

    private void forgotPassword(String forgotUname, String forgotUpass, String acc_type) {
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
        if (response.getStatus().equalsIgnoreCase("success") && response != null) {
            showToast(response.getMessage());
            ForgotUserPasswordData forgotUserDetailsData = response.getUser_data();
            String data = forgotUserDetailsData.getTEMPORARY_PIN();
            Intent i = new Intent(this, BackToLoginScreen.class);
            i.putExtra(BackToLoginScreen.ARG_SELECTION_USER, drawerTitle);
            i.putExtra("forgotUser", data);
            startActivity(i);
        }

    }

    private void forgotUserNameResponse(String efin, String email, String type) {
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
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
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
                throw new RuntimeException(e);            }
        } else {
            showToast("Network Error !");
        }
    }

    private void handleResponse(ForgotUserNameInfo response) {
        if (response.getStatus().equalsIgnoreCase("success") && response != null) {
            showToast(response.getMessage());
            ForgotUserNameData forgotUserDetailsData = response.getUser_data();
            String data = forgotUserDetailsData.getLOGIN_NAME();
            Intent i = new Intent(this, BackToLoginScreen.class);
            i.putExtra(BackToLoginScreen.ARG_SELECTION_USER, drawerTitle);
            i.putExtra("forgotUser", data);
            startActivity(i);

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
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }
}
