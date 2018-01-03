package com.cattechnologies.tpg.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.cattechnologies.tpg.Activities.Dashboard;
import com.cattechnologies.tpg.Activities.EmployeeDiffCallback;
import com.cattechnologies.tpg.Activities.ItemClickListener;
import com.cattechnologies.tpg.Fragments.ReportsFeesPaidDetailsFragment;
import com.cattechnologies.tpg.Fragments.ReportsFeesPaidFragment;
import com.cattechnologies.tpg.Model.Reports;
import com.cattechnologies.tpg.Model.ReportsFeePaidNew;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.Utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajay kumar on 28-Oct-17.
 */

public class ReportsFeesPaidListAdapter extends RecyclerView.Adapter<ReportsFeesPaidListAdapter.ReportsViewHolder> {
    List<ReportsFeePaidNew> reportsList;
    String title;
    private ItemClickListener clickListener;
    String index;
    Context mContext;

    public ReportsFeesPaidListAdapter(Context mContext, List<ReportsFeePaidNew> reportsList, String title) {
        this.reportsList = reportsList;
        this.mContext = mContext;
        this.title = title;

    }

    @Override
    public ReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list_row, parent, false);
        ReportsViewHolder reportsViewHolder = new ReportsViewHolder(itemView);
        return reportsViewHolder;
    }

    @Override
    public void onBindViewHolder(ReportsViewHolder holder, int position) {
     /*   if (position % 2 == 0)
            holder.itemView.setBackgroundColor(Color.parseColor("#ebefef"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#e0e8e8"));*/

        ReportsFeePaidNew reports = reportsList.get(position);
        holder.userData.setText(reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName());
        holder.costData.setText("$" + reports.getToTalSiteFeeCollected());
        holder.accountDataSSN.setText(reports.getPrimarySsn());
        holder.detailsDataDisbush.setText(reports.getDisbursementType() + " | ");
        holder.dateData.setText(reports.getRecordcreatedate());

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void updateEmployeeListItems(List<ReportsFeePaidNew> employees) {
        final EmployeeDiffCallback diffCallback = new EmployeeDiffCallback(this.reportsList, employees);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.reportsList.clear();
        this.reportsList.addAll(employees);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {

        return reportsList == null ? 0 : reportsList.size();
        // return 0;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void setClickListener(ReportsFeesPaidFragment reportsFeesPaidFragment) {

    }

    public void setFilter(List<ReportsFeePaidNew> newList) {
        reportsList = new ArrayList<>();
        reportsList.addAll(newList);
        notifyDataSetChanged();
    }

    public void update(List<ReportsFeePaidNew> reportsList) {
        reportsList.clear();
        for (ReportsFeePaidNew model : reportsList) {
            reportsList.addAll(reportsList);
        }
        notifyDataSetChanged();
    }


    public class ReportsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView userData, costData, accountDataSSN, detailsDataDisbush, dateData;

        public ReportsViewHolder(View itemView) {
            super(itemView);
            userData = (TextView) itemView.findViewById(R.id.report_user);
            costData = (TextView) itemView.findViewById(R.id.report_rate);
            accountDataSSN = (TextView) itemView.findViewById(R.id.report_account);
            detailsDataDisbush = (TextView) itemView.findViewById(R.id.report_details);
            dateData = (TextView) itemView.findViewById(R.id.report_date);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }


}