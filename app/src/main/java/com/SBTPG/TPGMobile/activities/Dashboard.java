package com.SBTPG.TPGMobile.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.TextView;

import com.SBTPG.TPGMobile.fragments.DashboardFragment;
import com.SBTPG.TPGMobile.fragments.ProfileFragment;
import com.SBTPG.TPGMobile.model.dashboardModel.DashboardInfoData;
import com.SBTPG.TPGMobile.model.profileModel.ProfileData;
import com.SBTPG.TPGMobile.model.dashboardModel.RecentTransactions;
import com.SBTPG.TPGMobile.R;
import com.SBTPG.TPGMobile.utils.PreferencesManager;

import java.util.List;

/**
 * Created by Ajay on 9/29/2017.
 */

public class Dashboard extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    String drawerTitle;
    TextView toolBarText, headerUserName, headerEfinNum, headerType;
    String tiTitle;
    ActionBar ab;
    DashboardInfoData dashboardInfoData;
    ProfileData profileData;
    List<RecentTransactions> recentTransactions;
    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        dashboardInfoData = getIntent().getParcelableExtra("DashboardInfoData");
        profileData = getIntent().getParcelableExtra("ProfileData");
        recentTransactions = getIntent().getParcelableArrayListExtra("RecentTransactions");
        preferencesManager = new PreferencesManager();
        setToolbar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        headerUserName = (TextView) header.findViewById(R.id.username);
        headerEfinNum = (TextView) header.findViewById(R.id.efin_data);
        headerType = (TextView) header.findViewById(R.id.type_data);
        if (profileData.getLOGIN_NAME() != null) {
            headerUserName.setText(profileData.getLOGIN_NAME());

        } else {
            headerUserName.setText("");

        }
        headerEfinNum.setText(profileData.getEFIN());
        headerType.setText(preferencesManager.gaT(getApplicationContext()));
        String data = profileData.getEFIN();
        preferencesManager.suD(getApplicationContext(), data);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        drawerTitle = getResources().getString(R.string.home_item);
        if (savedInstanceState == null) {
            selectItem(drawerTitle);
        }
    }

    public void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolBarText = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.hamburger);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        String title = menuItem.getTitle().toString();
                        selectItem(title);
                        return true;
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.main_content);
                if ((f instanceof DashboardFragment) || (f instanceof ProfileFragment)) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    onBackPressed();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectItem(String title) {

        Fragment fragment = null;

        if (getResources().getString(R.string.home_item).equalsIgnoreCase(title)) {
            fragment = DashboardFragment.newInstance(title, dashboardInfoData, recentTransactions);

        } else if (getResources().getString(R.string.compras_item).equalsIgnoreCase(title)) {
            fragment = ProfileFragment.newInstance(title, preferencesManager.getUserId(getApplicationContext()),
                    preferencesManager.gaT(getApplicationContext()));


        } else if (getResources().getString(R.string.ordenes_item).equalsIgnoreCase(title)) {
            preferencesManager.clearSharedPreference(getApplicationContext());
            finish();


        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .commit();
            toolBarText.setText(title);
            tiTitle = toolBarText.getText().toString();
            setTitle(tiTitle);
            drawerLayout.closeDrawers();
        }

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                showOrHideTitleBar();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        showOrHideTitleBar();
    }

    public void showOrHideTitleBar() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.main_content);
        if (f instanceof DashboardFragment) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburger);
        } else {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.main_content);
        if (f instanceof DashboardFragment || f instanceof ProfileFragment) {
            alertDialog();
        } else {
            super.onBackPressed();
            showOrHideTitleBar();
        }
    }

    private void alertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit");
        alertDialogBuilder
                .setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void setTitle(String title) {
        toolBarText.setText(title);
    }

}
