package com.cattechnologies.tpg.adapters.feePaidReportAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.cattechnologies.tpg.fragments.feepaidReport.ReportsFeesPaidFragment;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaidSearchNew;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 1/4/2018.
 */

public class ReportsFeesPaidSearchListAdapter extends RecyclerView.Adapter<ReportsFeesPaidSearchListAdapter.ReportsViewHolder> {
    List<ReportsFeePaidSearchNew> reportsList;
    String title;
    private ItemClickListener clickListener;
    String index;
    Context mContext;
    private ItemFilter mFilter = new ItemFilter();


    public ReportsFeesPaidSearchListAdapter(Context mContext, List<ReportsFeePaidSearchNew> reportsList, String title) {
        this.reportsList = reportsList;
        this.mContext = mContext;
        this.title = title;

    }

    @Override
    public ReportsFeesPaidSearchListAdapter.ReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list_row, parent, false);
        ReportsFeesPaidSearchListAdapter.ReportsViewHolder reportsViewHolder
                = new ReportsFeesPaidSearchListAdapter.ReportsViewHolder(itemView);
        return reportsViewHolder;
    }

    @Override
    public void onBindViewHolder(ReportsFeesPaidSearchListAdapter.ReportsViewHolder holder, int position) {
     /*   if (position % 2 == 0)
            holder.itemView.setBackgroundColor(Color.parseColor("#ebefef"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#e0e8e8"));*/

        ReportsFeePaidSearchNew reports = reportsList.get(position);
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

    public Filter getFilter() {
        return mFilter;
    }

   /* public void setClickListener(ReportsFeesPaidFragment reportsFeesPaidFragment) {

    }*/

  /*  public void setFilter(List<ReportsFeePaidNew> newList) {
        reportsList = new ArrayList<>();
        reportsList.addAll(newList);
        notifyDataSetChanged();
    }
*/

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


    private class ItemFilter extends Filter {


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            int count = reportsList.size();

            final ArrayList<ReportsFeePaidSearchNew> tempFilterList = new ArrayList<ReportsFeePaidSearchNew>(count);

            String filterableString;
            for (int i = 0; i < count; i++) {
                filterableString = reportsList.get(i).getPrimaryLastName();
                if (filterableString.toLowerCase().contains(filterString)) {
                    tempFilterList.add(reportsList.get(i));
                }
            }

            results.values = tempFilterList;
            results.count = tempFilterList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            reportsList.clear();
            reportsList = (ArrayList<ReportsFeePaidSearchNew>) results.values;
            notifyDataSetChanged();
        }
    }
}
