package com.cattechnologies.tpg.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cattechnologies.tpg.Activities.Dashboard;
import com.cattechnologies.tpg.Fragments.ReportsFeesPaidDetailsFragment;
import com.cattechnologies.tpg.Model.DashboardData;
import com.cattechnologies.tpg.Model.DashboardResentTransactionsData;
import com.cattechnologies.tpg.Model.RecentTransactions;
import com.cattechnologies.tpg.Model.Reports;
import com.cattechnologies.tpg.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by admin on 10/31/2017.
 */

public class DashboardListAdapter extends RecyclerView.Adapter<DashboardListAdapter.ReportsViewHolder> {
    //  List<DashboardData> reportsList;
    List<RecentTransactions> recentTransactionsList;
    Context mContext;

    public DashboardListAdapter(Context mContext, List<RecentTransactions> recentTransactionsList) {
        this.recentTransactionsList = recentTransactionsList;
        this.mContext = mContext;
    }

    @Override
    public ReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_report_list_row, parent, false);
        ReportsViewHolder reportsViewHolder = new ReportsViewHolder(itemView);
        return reportsViewHolder;
    }

    @Override
    public void onBindViewHolder(DashboardListAdapter.ReportsViewHolder holder, int position) {
     /*   if (position % 2 == 0)
            holder.itemView.setBackgroundColor(Color.parseColor("#ebefef"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#e0e8e8"));*/
        RecentTransactions reports = recentTransactionsList.get(position);

        holder.userData.setText(reports.getLastUpadte());
        holder.costData.setText(reports.getAmount());

    }

    @Override
    public int getItemCount() {

        return recentTransactionsList == null ? 0 : recentTransactionsList.size();
        // return 0;
    }

    public class ReportsViewHolder extends RecyclerView.ViewHolder {
        TextView userData, costData;

        public ReportsViewHolder(View itemView) {
            super(itemView);
            userData = (TextView) itemView.findViewById(R.id.report_user);
            costData = (TextView) itemView.findViewById(R.id.report_rate);


        }

    }
}
