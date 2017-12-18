package com.cattechnologies.tpg.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cattechnologies.tpg.Model.DashboardInfo;
import com.cattechnologies.tpg.Model.DashboardInfoData;
import com.cattechnologies.tpg.Model.EventBusModel;
import com.cattechnologies.tpg.Model.ForgotUserEmailData;
import com.cattechnologies.tpg.Model.LoginInfo;
import com.cattechnologies.tpg.Model.ProfileData;
import com.cattechnologies.tpg.Model.RecentTransactions;
import com.cattechnologies.tpg.Model.Response;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.Utils.AppInternetStatus;
import com.cattechnologies.tpg.Utils.NetworkUtil;
import com.cattechnologies.tpg.Utils.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;


import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.cattechnologies.tpg.Utils.Validation.validateEmail;
import static com.cattechnologies.tpg.Utils.Validation.validateFields;


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
    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    CheckBox checkBox;
    private CompositeSubscription mSubscriptions;
    LoginInfo loginInfo;
    ProgressBar progressBar;
    private boolean isEmployee;
    float prefFd, prefFdDue, recentAmount;
    BigDecimal bd, bd1;
    SimpleDateFormat format, format1;
    String forgotEmailData;
    Intent i;
    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_login_new);
        initViews();
        setToolbar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            drawerTitle = bundle.getString(ARG_SELECTION_USER);
            forgotEmailData = bundle.getString("forgotUser");
            System.out.println("LoginScreen.onCreate===" + drawerTitle);
            selectedForgotData(drawerTitle);
        }


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
        //    loginUser.setText(forgotEmailData);


    }

    private void selectedForgotData(String drawerTitle) {
        if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_email_new))) {
            mEmailTitle.setVisibility(View.VISIBLE);
            mLoginUsername.setText("EMAIL");
            mLoginUserPass.setText("PASSWORD");
            //  loginUser.setText(forgotEmailData);
            //  forgotEmailData = loginUser.getText().toString();
            System.out.println("LoginScreen.onClick===" + forgotEmailData);
            loginBt.setText("LOGIN");
        } else {
            mEmailTitle.setVisibility(View.GONE);
            mLoginUsername.setText("EFIN");
            mLoginUserPass.setText("PASSWORD");
            //  forgotEmailData = loginUser.getText().toString();
            System.out.println("LoginScreen.onClick===" + forgotEmailData);
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
                    showToast("EFIN  is not empty");

                } else if (loginPass.getText().toString().isEmpty()) {
                    showToast("User Password is not Empty");

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
                // selectedForgotPassword(forgotPasswordData);
                i = new Intent(this, ForgotUsernameDetails.class);
                i.putExtra(ForgotUsernameDetails.ARG_SELECTION_USER, forgotPasswordData);
                startActivity(i);
                d.dismiss();

                break;
            case R.id.forgot_user_password:
                forgotPasswordData = getResources().getString(R.string.forgot_user_password);
                //selectedForgotPassword(forgotPasswordData);
                i = new Intent(this, ForgotPasswordDetails.class);
                i.putExtra(ForgotUsernameDetails.ARG_SELECTION_USER, forgotPasswordData);
                startActivity(i);
                d.dismiss();

                break;
            case R.id.forgot_user_email:
                forgotPasswordData = getResources().getString(R.string.forgot_user_email);
                // selectedForgotPassword(forgotPasswordData);
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

    private void setError() {
        loginUser.setError(null);
        loginPass.setError(null);
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
        //  EventBus.getDefault().post(new EventBusModel("success"));
        System.out.println("LoginScreen.handleResponse===" + response.getProfile_data());
        System.out.println("LoginScreen.handleResponse====" + response.getDashboard_data());


        if (response.getStatus().equalsIgnoreCase("success") && response != null) {
            showToast(response.getMessage());
            DashboardInfoData dashboardInfo = new DashboardInfoData();
            if (response.getDashboard_data() == null) {
                showToast(response.getMessage());
               /* Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pro.sbtpg.com/"));
                startActivity(myIntent);*/
                Intent i = new Intent(this, EmployeeLoginActivity.class);
                i.putExtra(BackToLoginScreen.ARG_SELECTION_USER, drawerTitle);
                startActivity(i);
            } else {


                dashboardInfo.setAppliedRts(response.getDashboard_data().getAppliedRts());
                dashboardInfo.setFundedRTs(response.getDashboard_data().getFundedRTs());
                dashboardInfo.setStateFundedRTs(response.getDashboard_data().getStateFundedRTs());

                dashboardInfo.setCheckRTs(response.getDashboard_data().getCheckRTs());
                dashboardInfo.setDirectDepositRTs(response.getDashboard_data().getDirectDepositRTs());
                dashboardInfo.setPrePaidCards(response.getDashboard_data().getPrePaidCards());
                dashboardInfo.setDirect2Cash(response.getDashboard_data().getDirect2Cash());

              /*  prefFd = Float.parseFloat(response.getDashboard_data().getPrepFeesPaid());
                bd = new BigDecimal(Double.toString(prefFd));
                bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                bd.doubleValue();
                dashboardInfo.setPrepFeesPaid("" + bd.doubleValue());*/
                dashboardInfo.setPrepFeesPaid(response.getDashboard_data().getPrepFeesPaid());

             /*   prefFdDue = Float.parseFloat(response.getDashboard_data().getPastDueAccountsAmount());
                bd1 = new BigDecimal(Double.toString(prefFdDue));
                bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
                bd1.doubleValue();
                dashboardInfo.setPastDueAccountsAmount("" + bd1.doubleValue());*/

                dashboardInfo.setPastDueAccountsAmount(response.getDashboard_data().getPastDueAccountsAmount());

                ProfileData profileData = new ProfileData();
         /*   if (preferencesManager.getBoolean(getApplicationContext()) == true) {
                profileData.setEmployeeID(response.getProfile_data().getEmployeeID());
                profileData.setFirstName(response.getProfile_data().getFirstName());
                profileData.setUserName(response.getProfile_data().getUserName());
                profileData.setEMAIL_ADDRESS(response.getProfile_data().getEMAIL_ADDRESS());
                profileData.setLastName(response.getProfile_data().getLastName());

            } else {
*/


                profileData.setEFIN(response.getProfile_data().getEFIN());
                profileData.setLOGIN_NAME(response.getProfile_data().getLOGIN_NAME());


                List<RecentTransactions> recentTransactionsList = new ArrayList<>();
                for (int i = 0; i < response.getRecent_transactions().size(); i++) {
                    RecentTransactions recentTransactions = new RecentTransactions();
                    format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");
                    format1 = new SimpleDateFormat("MM-dd-yyyy");
                    String chagnedDate = null;
                    try {
                        chagnedDate = format1.format(format.parse(response.getRecent_transactions().get(i).getLastUpadte()));
                        recentTransactions.setLastUpadte(chagnedDate);
                      /*  recentAmount = Float.parseFloat(response.getRecent_transactions().get(i).getAmount());
                        bd = new BigDecimal(Double.toString(recentAmount));
                        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                        bd.doubleValue();
                        recentTransactions.setAmount("" + bd.doubleValue());*/

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


    private void selectedForgotPassword(String forgotPasswordData) {
        System.out.println("ForgotPassword.selectItem=====" + forgotPasswordData);
        if (getResources().getString(R.string.forgot_user_name).equalsIgnoreCase(forgotPasswordData)) {
            System.out.println("LoginScreen.selectedForgotPassword====name===" + forgotPasswordData);
            Intent i = new Intent(this, ForgotUserDetailsData.class);
            i.putExtra(ForgotUserDetailsData.ARG_SELECTION_USER, forgotPasswordData);
            startActivity(i);
        } else if (getResources().getString(R.string.forgot_user_password).equalsIgnoreCase(forgotPasswordData)) {
            System.out.println("LoginScreen.selectedForgotPassword====pwss===" + forgotPasswordData);

            Intent i = new Intent(this, ForgotUserDetailsData.class);
            i.putExtra(ForgotUserDetailsData.ARG_SELECTION_USER, forgotPasswordData);
            startActivity(i);
        } else if (getResources().getString(R.string.forgot_user_email).equalsIgnoreCase(forgotPasswordData)) {
            System.out.println("LoginScreen.selectedForgotPassword====email===" + forgotPasswordData);
            Intent i = new Intent(this, ForgotUserDetailsData.class);
            i.putExtra(ForgotUserDetailsData.ARG_SELECTION_USER, forgotPasswordData);
            startActivity(i);
           /* Bundle args = new Bundle();
            args.putString(ForgotPasswordUserDetails.ARG_SELECTION_USER, forgotPasswordData);

            Fragment fragment = ForgotPasswordUserDetails.newInstance(forgotPasswordData);
            fragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .commit();*/

        }


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
