package com.cattechnologies.tpg.adapters.accountDisbursementsReportAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.fragments.feepaidReport.ReportsFeesPaidFragment;
import com.cattechnologies.tpg.interfaces.ItemClickListener;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportsAccountDisbServiceBuroSortNew;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by admin on 2/2/2018.
 */

public class ReportsAccountDisbServiceBuroSortListAdapter extends RecyclerView.Adapter<ReportsAccountDisbServiceBuroSortListAdapter.ReportsViewHolder> {
    List<ReportsAccountDisbServiceBuroSortNew> reportsList;
    String title;
    private ItemClickListener clickListener;
    String index;
    Context mContext;
    SimpleDateFormat format, format1;

    public ReportsAccountDisbServiceBuroSortListAdapter(Context mContext, List<ReportsAccountDisbServiceBuroSortNew> reportsList, String title) {
        this.reportsList = reportsList;
        this.mContext = mContext;
        this.title = title;

    }

    @Override
    public ReportsAccountDisbServiceBuroSortListAdapter.ReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list_row, parent, false);
        ReportsAccountDisbServiceBuroSortListAdapter.ReportsViewHolder reportsViewHolder
                = new ReportsAccountDisbServiceBuroSortListAdapter.ReportsViewHolder(itemView);
        return reportsViewHolder;
    }

    @Override
    public void onBindViewHolder(ReportsAccountDisbServiceBuroSortListAdapter.ReportsViewHolder holder, int position) {
        ReportsAccountDisbServiceBuroSortNew reports = reportsList.get(position);
        holder.userData.setText(reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName());
        holder.costData.setText("$" + reports.getDisbursmentamount());
        holder.accountDataSSN.setText(reports.getPrimarySsn());
        holder.detailsDataDisbush.setText(reports.getDisbType() + " | ");
        format = new SimpleDateFormat("yyyyMMdd");
        format1 = new SimpleDateFormat("MM-dd-yyyy");

        String chagnedDate = null;
        try {
            chagnedDate = format1.format(format.parse(reports.getDisbursementDate()));
            reports.setDisbursementDate(chagnedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.dateData.setText(reports.getDisbursementDate());

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