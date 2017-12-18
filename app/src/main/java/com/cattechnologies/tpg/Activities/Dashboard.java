package com.cattechnologies.tpg.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.TextView;

import com.cattechnologies.tpg.Fragments.ProfileFragment;
import com.cattechnologies.tpg.Fragments.DashboardFragment;
import com.cattechnologies.tpg.Model.DashboardInfoData;
import com.cattechnologies.tpg.Model.LoginInfo;
import com.cattechnologies.tpg.Model.ProfileData;
import com.cattechnologies.tpg.Model.RecentTransactions;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.Utils.PreferencesManager;

import java.util.List;

/**
 * Created by admin on 9/29/2017.
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
    LoginInfo loginInfo;

    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        dashboardInfoData = getIntent().getParcelableExtra("DashboardInfoData");
        profileData = getIntent().getParcelableExtra("ProfileData");
        recentTransactions = getIntent().getParcelableArrayListExtra("RecentTransactions");
        loginInfo = new LoginInfo();
        preferencesManager = new PreferencesManager();
        setToolbar();
        System.out.println("Dashboard.onCreate=="
                + profileData.getEFIN() + "==" + profileData.getEMAIL_ADDRESS() + "==" + profileData.getLOGIN_NAME());
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        headerUserName = (TextView) header.findViewById(R.id.username);
        headerEfinNum = (TextView) header.findViewById(R.id.efin_data);
        headerType = (TextView) header.findViewById(R.id.type_data);
        headerUserName.setText(profileData.getLOGIN_NAME());
        headerEfinNum.setText(profileData.getEFIN());
        headerType.setText(preferencesManager.getAccountType(getApplicationContext()));
        System.out.println("Dashboard.onCreate==" + profileData.getEFIN());
        String data = profileData.getEFIN();
        preferencesManager.saveUserId(getApplicationContext(), data);

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
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectItem(String title) {

        Fragment fragment = null;

        if (getResources().getString(R.string.home_item).equalsIgnoreCase(title)) {


            fragment = DashboardFragment.newInstance(title, dashboardInfoData, recentTransactions);

        } else if (getResources().getString(R.string.compras_item).equalsIgnoreCase(title)) {

            fragment = ProfileFragment.newInstance(title, preferencesManager.getUserId(getApplicationContext())
                    , preferencesManager.getAccountType(getApplicationContext()));


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

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // TODO: 29-Oct-17
        int backStackEntryCount = getFragmentManager().getBackStackEntryCount();

        if (backStackEntryCount == 0) {
            toolBarText.setText(getResources().getString(R.string.home_item));

          /*  Toast.makeText(this, "get", Toast.LENGTH_SHORT).show();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburger);
      */
        } else {
           /* Toast.makeText(this, "other", Toast.LENGTH_SHORT).show();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
*/
            toolBarText.setText(getResources().getString(R.string.productos_item));
        }


    }

    public void setTitle(String title) {
        toolBarText.setText(title);
    }


}
