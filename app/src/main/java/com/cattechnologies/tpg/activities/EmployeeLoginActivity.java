package com.cattechnologies.tpg.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cattechnologies.tpg.R;

/**
 * Created by admin on 11/3/2017.
 */

public class EmployeeLoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ARG_SELECTION_USER = "back_to_screen";
    Toolbar toolbar;
    private String drawerTitle;
    TextView mTitle, textData;
    Button mBackLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_to_login_employee);
        mBackLogin = (Button) findViewById(R.id.main_get_login);
        textData = (TextView) findViewById(R.id.text_data);
        setToolbar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            drawerTitle = bundle.getString(ARG_SELECTION_USER);
            System.out.println("LoginScreen.onCreate===" + drawerTitle);
            selectedData(drawerTitle);

        }
        mBackLogin.setText("Click Here");
        mBackLogin.setOnClickListener(this);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        drawerTitle = getIntent().getExtras().getString(ARG_SELECTION_USER);
        setSupportActionBar(toolbar);
        mTitle.setText(drawerTitle);

        if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_name_new))) {
            mTitle.setText(getResources().getString(R.string.forgot_user_name_new));

        } else if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_password_new))) {
            mTitle.setText("EMPLOYEE LOGIN");

        } else if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_email_new))) {
            mTitle.setText(getResources().getString(R.string.forgot_user_email_new));

        }
        selectedData(drawerTitle);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void selectedData(String drawerTitle) {
        if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_name))) {
            textData.setText(getResources().getString(R.string.back_to_username_employee));

        } else if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_password))) {

            textData.setText(getResources().getString(R.string.back_to_username_employee));

        } else if (drawerTitle.equalsIgnoreCase(getResources().getString(R.string.forgot_user_email))) {
            textData.setText("");
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_get_login:
                mBackLogin.setBackgroundColor(getResources().getColor(R.color.back_button_click_color));
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pro.sbtpg.com/"));
                startActivity(myIntent);
                break;

        }
    }
}
