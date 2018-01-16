package com.cattechnologies.tpg.adapters.eroDepositsReportAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.interfaces.ItemClickListener;
import com.cattechnologies.tpg.model.eroDepositModel.ReportEroDepositsSearchSortNew;
import com.cattechnologies.tpg.model.feePaidModel.ReportFreePaidSearchSortNew;

import java.util.List;

/**
 * Created by admin on 1/9/2018.
 */

public class ReportsEroDepositSearchSortListAdapter extends RecyclerView.Adapter<ReportsEroDepositSearchSortListAdapter.ReportsViewHolder> {
    List<ReportEroDepositsSearchSortNew> reportsList;
    String title;
    private ItemClickListener clickListener;
    String index;
    Context mContext;

    public ReportsEroDepositSearchSortListAdapter(Context mContext, List<ReportEroDepositsSearchSortNew> reportsList, String title) {
        this.reportsList = reportsList;
        this.mContext = mContext;
        this.title = title;

    }

    @Override
    public ReportsEroDepositSearchSortListAdapter.ReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list_row, parent, false);
        ReportsEroDepositSearchSortListAdapter.ReportsViewHolder reportsViewHolder
                = new ReportsEroDepositSearchSortListAdapter.ReportsViewHolder(itemView);
        return reportsViewHolder;
    }

    @Override
    public void onBindViewHolder(ReportsEroDepositSearchSortListAdapter.ReportsViewHolder holder, int position) {
     /*   if (position % 2 == 0)
            holder.itemView.setBackgroundColor(Color.parseColor("#ebefef"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#e0e8e8"));*/

        ReportEroDepositsSearchSortNew reports = reportsList.get(position);
        holder.userData.setText(reports.getDAN());
        holder.costData.setText("$" + reports.getDepositAmount());
        holder.accountDataSSN.setText(reports.getDepositType());
        holder.detailsDataDisbush.setText("");
        holder.dateData.setText(reports.getDepositdate());
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

   /* public void setClickListener(ReportsFeesPaidFragment reportsFeesPaidFragment) {

    }*/



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
