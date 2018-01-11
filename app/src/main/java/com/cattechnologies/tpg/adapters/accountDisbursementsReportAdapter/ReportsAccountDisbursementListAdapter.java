package com.cattechnologies.tpg.adapters.accountDisbursementsReportAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cattechnologies.tpg.fragments.accountDisbursementsReport.ReportsAccountDisbursementDetailsFragment;
import com.cattechnologies.tpg.model.Reports;
import com.cattechnologies.tpg.R;
import com.cattechnologies.tpg.activities.Dashboard;

import java.util.List;

/**
 * Created by ajay kumar on 28-Oct-17.
 */

public class ReportsAccountDisbursementListAdapter extends RecyclerView.Adapter<ReportsAccountDisbursementListAdapter.ReportsViewHolder> {
    List<Reports> reportsList;
    Context mContext;
    String title;

    public ReportsAccountDisbursementListAdapter(Context mContext, List<Reports> reportsList, String title) {
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
        Reports reports = reportsList.get(position);
        holder.userData.setText(reports.getUserData());
        holder.costData.setText(reports.getCostData());
        holder.accountData.setText(reports.getAccountData());
        holder.detailsData.setText(reports.getDetailsData());
        holder.dateData.setText(reports.getDateData());
    }

    @Override
    public int getItemCount() {

        return reportsList == null ? 0 : reportsList.size();
        // return 0;
    }

    public class ReportsViewHolder extends RecyclerView.ViewHolder {
        TextView userData, costData, accountData, detailsData, dateData;

        public ReportsViewHolder(View itemView) {
            super(itemView);
            userData = (TextView) itemView.findViewById(R.id.report_user);
            costData = (TextView) itemView.findViewById(R.id.report_rate);
            accountData = (TextView) itemView.findViewById(R.id.report_account);
            detailsData = (TextView) itemView.findViewById(R.id.report_details);
            dateData = (TextView) itemView.findViewById(R.id.report_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Dashboard activity = (Dashboard) v.getContext();
                    Fragment fragment = ReportsAccountDisbursementDetailsFragment.newInstance(title);
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.main_content, fragment)
                            .addToBackStack(null)
                            .commit();

                }
            });


        }

    }
}
