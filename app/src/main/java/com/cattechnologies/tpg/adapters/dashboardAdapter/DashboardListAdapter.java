package com.cattechnologies.tpg.adapters.dashboardAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cattechnologies.tpg.model.dashboardModel.RecentTransactions;
import com.cattechnologies.tpg.R;

import java.util.List;

/**
 * Created by admin on 10/31/2017.
 */

public class DashboardListAdapter extends RecyclerView.Adapter<DashboardListAdapter.ReportsViewHolder> {
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
       RecentTransactions reports = recentTransactionsList.get(position);
        holder.userData.setText(reports.getLastUpadte());
        holder.costData.setText("$" + reports.getAmount());

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
