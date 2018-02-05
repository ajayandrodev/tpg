package com.cattechnologies.tpg.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cattechnologies.tpg.fragments.accountDisbursementsReport.ReportAccountDisbFragment;
import com.cattechnologies.tpg.fragments.accountDisbursementsReport.ReportsAccountDisbServiceBuroFragment;
import com.cattechnologies.tpg.fragments.eroDepositsReport.ReportEroDepositFragment;
import com.cattechnologies.tpg.fragments.eroDepositsReport.ReportsEroDepositServiceBuroFragment;
import com.cattechnologies.tpg.fragments.feepaidReport.ReportsFeesPaidFragment;
import com.cattechnologies.tpg.fragments.feepaidReport.ReportsFeesPaidServiceBuroFragment;
import com.cattechnologies.tpg.model.dashboardModel.DashboardInfoData;
import com.cattechnologies.tpg.model.dashboardModel.RecentTransactions;
import com.cattechnologies.tpg.adapters.dashboardAdapter.DashboardListAdapter;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.CompositeSubscription;


public class DashboardFragment extends Fragment implements View.OnClickListener {

    public static final String ARG_SECTION_TITLE = "section_number";
    Button checkToPrint, feesPaid, accountDis, eroDepo;
    String title;
    Bundle args;
    FragmentManager fragmentManager;
    RecyclerView recyclerView;
    DashboardListAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private CompositeSubscription mSubscriptions;
    TextView appliedFdrlRT,
            fundedFdrlRT,
            fundedStateRT, check, ach, card, directCash, prepFeesPaid, taxPayersSmall,
            unfundedPrepFees, appliedFdrlRTValue,
            fundedFdrlRTValue,
            fundedStateRTValue,
            checkValue, achValue,
            cardValue, directCashValue,
            prepFeesPaidValue, taxPayersSmallValue;
    DashboardInfoData dashData;
    List<RecentTransactions> recentTransactionsList;
    PreferencesManager preferencesManager;


    private static final String TAG = "MOVIE_TRIVIA";


    public static DashboardFragment newInstance(String sectionTitle, DashboardInfoData dashboardInfoData, List<RecentTransactions> recentTransactions) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        args.putParcelable("DashboardInfoData", dashboardInfoData);
        args.putParcelableArrayList("RecentTransactions", (ArrayList<? extends Parcelable>) recentTransactions);
        fragment.setArguments(args);
        return fragment;
    }

    public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        title = getArguments().getString(ARG_SECTION_TITLE);
        dashData = getActivity().getIntent().getParcelableExtra("DashboardInfoData");
        recentTransactionsList = getActivity().getIntent().getParcelableArrayListExtra("RecentTransactions");
        mSubscriptions = new CompositeSubscription();
        preferencesManager = new PreferencesManager();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSubscriptions.unsubscribe();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {


        appliedFdrlRT = (TextView) getActivity().findViewById(R.id.applied_fd);
        fundedFdrlRT = getActivity().findViewById(R.id.funded_fd);
        fundedStateRT = getActivity().findViewById(R.id.funded_st);

        appliedFdrlRTValue = (TextView) getActivity().findViewById(R.id.applied_fd_value);
        fundedFdrlRTValue = getActivity().findViewById(R.id.funded_fd_value);
        fundedStateRTValue = getActivity().findViewById(R.id.funded_st_value);


        check = getActivity().findViewById(R.id.check_rt);
        ach = getActivity().findViewById(R.id.ach);
        card = getActivity().findViewById(R.id.card);
        directCash = getActivity().findViewById(R.id.direch_cash);

        checkValue = getActivity().findViewById(R.id.check_rt_value);
        achValue = getActivity().findViewById(R.id.ach_value);
        cardValue = getActivity().findViewById(R.id.card_value);
        directCashValue = getActivity().findViewById(R.id.direch_cash_value);


        prepFeesPaid = getActivity().findViewById(R.id.prep_fees_paid);
        prepFeesPaidValue = getActivity().findViewById(R.id.prep_fees_paid_value);


        taxPayersSmallValue = getActivity().findViewById(R.id.tax_payers_value);
        taxPayersSmall = getActivity().findViewById(R.id.tax_payers);
        unfundedPrepFees = getActivity().findViewById(R.id.tax_payers_unfunded);


        feesPaid = (Button) getActivity().findViewById(R.id.bt_fees_paid);
        checkToPrint = (Button) getActivity().findViewById(R.id.bt_check_print);
        accountDis = (Button) getActivity().findViewById(R.id.bt_account_dis);
        eroDepo = (Button) getActivity().findViewById(R.id.bt_ero_depo);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_dashboard);
        recyclerView.setHasFixedSize(true);


        feesPaid.setOnClickListener(this);
        checkToPrint.setOnClickListener(this);
        accountDis.setOnClickListener(this);
        eroDepo.setOnClickListener(this);
        feesPaid.setOnClickListener(this);

        appliedFdrlRTValue.setText(dashData.getAppliedRts());
        fundedFdrlRTValue.setText(dashData.getFundedRTs());
        fundedStateRTValue.setText(dashData.getStateFundedRTs());


        checkValue.setText(dashData.getCheckRTs());
        achValue.setText(dashData.getDirectDepositRTs());
        cardValue.setText(dashData.getPrePaidCards());
        directCashValue.setText(dashData.getDirect2Cash());

        prepFeesPaidValue.setText("$" + dashData.getPrepFeesPaid());
        taxPayersSmallValue.setText("$" + dashData.getPastDueAccountsAmount());

        taxPayersSmall.setText(dashData.getCountTaxpayer() + "\t" + "tax payers");


        mAdapter = new DashboardListAdapter(getActivity(), recentTransactionsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.bt_fees_paid:
                if (preferencesManager.getAccountType(getContext()).equalsIgnoreCase("sb")) {
                    feesPaid.setBackgroundColor(getResources().getColor(R.color.back_button_click_color));
                    title = getResources().getString(R.string.dashboard_fee_paid);
                    fragment = ReportsFeesPaidServiceBuroFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                            preferencesManager.getAccountType(getActivity()));
                } else {
                    feesPaid.setBackgroundColor(getResources().getColor(R.color.back_button_click_color));
                    title = getResources().getString(R.string.dashboard_fee_paid);

                    fragment = ReportsFeesPaidFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                            preferencesManager.getAccountType(getActivity()));
                }
                break;
            case R.id.bt_check_print:
                checkToPrint.setEnabled(false);
               /* if (preferencesManager.getAccountType(getContext()).equalsIgnoreCase("sb")) {
                    checkToPrint.setBackgroundColor(getResources().getColor(R.color.back_button_click_color));
                    title = getResources().getString(R.string.dashboard_check_to_print);
                    fragment = EmptyCheckToPrintFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                            preferencesManager.getAccountType(getActivity()));
                } else {
                    checkToPrint.setBackgroundColor(getResources().getColor(R.color.back_button_click_color));
                    title = getResources().getString(R.string.dashboard_check_to_print);
                    fragment = EmptyCheckToPrintFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                            preferencesManager.getAccountType(getActivity()));
                }*/
                break;
            case R.id.bt_account_dis:

                if (preferencesManager.getAccountType(getContext()).equalsIgnoreCase("sb")) {
                    accountDis.setBackgroundColor(getResources().getColor(R.color.back_button_click_color));
                    title = getResources().getString(R.string.dashboard_account_dis_title);
                    fragment = ReportsAccountDisbServiceBuroFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                            preferencesManager.getAccountType(getActivity()));
                } else {
                    accountDis.setBackgroundColor(getResources().getColor(R.color.back_button_click_color));
                    title = getResources().getString(R.string.dashboard_account_dis_title);
                    fragment = ReportAccountDisbFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                            preferencesManager.getAccountType(getActivity()));
                }
                break;
            case R.id.bt_ero_depo:
                if (preferencesManager.getAccountType(getContext()).equalsIgnoreCase("sb")) {
                    eroDepo.setBackgroundColor(getResources().getColor(R.color.back_button_click_color));
                    title = getResources().getString(R.string.dashboard_ero_deposits);
                    fragment = ReportsEroDepositServiceBuroFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                            preferencesManager.getAccountType(getActivity()));
                } else {
                    eroDepo.setBackgroundColor(getResources().getColor(R.color.back_button_click_color));
                    title = getResources().getString(R.string.dashboard_ero_deposits);
                    fragment = ReportEroDepositFragment.newInstance(title, preferencesManager.getUserId(getActivity()),
                            preferencesManager.getAccountType(getActivity()));
                }
                break;
        }
        if (fragment != null) {
            fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .addToBackStack(null)
                    .commit();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
