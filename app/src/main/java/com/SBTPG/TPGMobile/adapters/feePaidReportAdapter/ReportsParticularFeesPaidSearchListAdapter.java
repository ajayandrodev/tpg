package com.SBTPG.TPGMobile.adapters.feePaidReportAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.SBTPG.TPGMobile.R;
import com.SBTPG.TPGMobile.interfaces.ItemClickListener;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportsPerticularFeePaidSearchNew;

import java.util.List;

/**
 * Created by Ajay on 1/10/2018.
 */

public class ReportsParticularFeesPaidSearchListAdapter extends RecyclerView.Adapter<ReportsParticularFeesPaidSearchListAdapter.ReportsViewHolder> {
    List<ReportsPerticularFeePaidSearchNew> reportsList;
    String title;
    private ItemClickListener clickListener;
    String index;
    Context mContext;

    public ReportsParticularFeesPaidSearchListAdapter(Context mContext, List<ReportsPerticularFeePaidSearchNew> reportsList, String title) {
        this.reportsList = reportsList;
        this.mContext = mContext;
        this.title = title;

    }

    @Override
    public ReportsParticularFeesPaidSearchListAdapter.ReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list_row, parent, false);
        ReportsParticularFeesPaidSearchListAdapter.ReportsViewHolder reportsViewHolder
                = new ReportsParticularFeesPaidSearchListAdapter.ReportsViewHolder(itemView);
        return reportsViewHolder;
    }

    @Override
    public void onBindViewHolder(ReportsParticularFeesPaidSearchListAdapter.ReportsViewHolder holder, int position) {
        ReportsPerticularFeePaidSearchNew reports = reportsList.get(position);
        holder.userData.setText(reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName());
        holder.costData.setText("$" + reports.getToTalSiteFeeCollected());
        holder.accountDataSid.setText(reports.getPrimarySinfo());
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
        TextView userData, costData, accountDataSid, detailsDataDisbush, dateData;

        public ReportsViewHolder(View itemView) {
            super(itemView);
            userData = (TextView) itemView.findViewById(R.id.report_user);
            costData = (TextView) itemView.findViewById(R.id.report_rate);
            accountDataSid = (TextView) itemView.findViewById(R.id.report_account);
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