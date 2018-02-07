package com.cattechnologies.tpg.adapters.feePaidReportAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.fragments.feepaidReport.ReportsFeesPaidFragment;
import com.cattechnologies.tpg.interfaces.ItemClickListener;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaidSearchServiceBuroNew;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by admin on 2/1/2018.
 */

public class ReportsFeesPaidServiceBuroSearchListAdapter extends RecyclerView.Adapter<ReportsFeesPaidServiceBuroSearchListAdapter.ReportsViewHolder> {
    List<ReportsFeePaidSearchServiceBuroNew> reportsList;
    String title;
    private ItemClickListener clickListener;
    String index;
    Context mContext;
    //  private ItemFilter mFilter = new ItemFilter();
    SimpleDateFormat format, format1;


    public ReportsFeesPaidServiceBuroSearchListAdapter(Context mContext, List<ReportsFeePaidSearchServiceBuroNew> reportsList, String title) {
        this.reportsList = reportsList;
        this.mContext = mContext;
        this.title = title;

    }

    @Override
    public ReportsFeesPaidServiceBuroSearchListAdapter.ReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list_row, parent, false);
        ReportsFeesPaidServiceBuroSearchListAdapter.ReportsViewHolder reportsViewHolder
                = new ReportsFeesPaidServiceBuroSearchListAdapter.ReportsViewHolder(itemView);
        return reportsViewHolder;
    }

    @Override
    public void onBindViewHolder(ReportsFeesPaidServiceBuroSearchListAdapter.ReportsViewHolder holder, int position) {
         ReportsFeePaidSearchServiceBuroNew reports = reportsList.get(position);
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


    @Override
    public int getItemCount() {

        return reportsList == null ? 0 : reportsList.size();
        // return 0;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
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