package com.cattechnologies.tpg.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cattechnologies.tpg.Activities.Dashboard;
import com.cattechnologies.tpg.Activities.ItemClickListener;
import com.cattechnologies.tpg.Fragments.ReportsFeesPaidDetailsFragment;
import com.cattechnologies.tpg.Fragments.ReportsFeesPaidFragment;
import com.cattechnologies.tpg.Model.Reports;
import com.cattechnologies.tpg.Model.ReportsFeePaidNew;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.Utils.PreferencesManager;

import java.util.List;

/**
 * Created by ajay kumar on 28-Oct-17.
 */

public class ReportsFeesPaidListAdapter extends RecyclerView.Adapter<ReportsFeesPaidListAdapter.ReportsViewHolder> {
    List<ReportsFeePaidNew> reportsList;
    Context mContext;
    String title;
    PreferencesManager preferencesManager;
    String name;
    private ItemClickListener clickListener;

    public ReportsFeesPaidListAdapter(Context mContext, List<ReportsFeePaidNew> reportsList, String title, String name) {
        this.reportsList = reportsList;
        this.mContext = mContext;
        this.title = title;
        this.name = name;
    }

    @Override
    public ReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list_row, parent, false);
        ReportsViewHolder reportsViewHolder = new ReportsViewHolder(itemView);
        preferencesManager = new PreferencesManager();
        return reportsViewHolder;
    }

    @Override
    public void onBindViewHolder(ReportsViewHolder holder, int position) {
     /*   if (position % 2 == 0)
            holder.itemView.setBackgroundColor(Color.parseColor("#ebefef"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#e0e8e8"));*/
        final ReportsFeePaidNew reports = reportsList.get(position);
        holder.userData.setText(reports.getPrimaryFirstName() + " " + reports.getPrimaryLastName());
        holder.costData.setText("$" + reports.getToTalSiteFeeCollected());
        holder.accountDataSSN.setText(reports.getPrimarySsn());
        holder.detailsDataDisbush.setText(reports.getDisbursementType() + " | ");
        holder.dateData.setText(reports.getRecordcreatedate());

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
/*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Dashboard activity = (Dashboard) v.getContext();
                    Fragment fragment = ReportsFeesPaidDetailsFragment.newInstance(title,
                            name);
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.main_content, fragment)
                            .addToBackStack(null)
                            .commit();
                    activity.getSupportActionBar().setTitle("REPORTS");

                }
            });
*/


        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }


}