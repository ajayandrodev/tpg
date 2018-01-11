package com.cattechnologies.tpg.adapters.accountDisbursementsReportAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cattechnologies.tpg.model.accountDisbursementModel.AccountDisbursementChildInfo;
import com.cattechnologies.tpg.model.accountDisbursementModel.AccountDisbursementGroupInfo;
import com.cattechnologies.tpg.R;

import java.util.ArrayList;

/**
 * Created by ajay kumar on 28-Oct-17.
 */

public class ReportsExpandableListAccountDisbursementAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<AccountDisbursementGroupInfo> deptList;

    public ReportsExpandableListAccountDisbursementAdapter(Context context, ArrayList<AccountDisbursementGroupInfo> deptList) {
        this.context = context;
        this.deptList = deptList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<AccountDisbursementChildInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        AccountDisbursementChildInfo detailInfo = (AccountDisbursementChildInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.account_disbursement_child_items, null);
        }

       /* if (childPosition%2==0) {
            view.setBackgroundColor(Color.parseColor("#e0e8e8"));
        } else {
            view.setBackgroundColor(Color.parseColor("#ebefef"));
        }
*/
       /* TextView sequence = (TextView) view.findViewById(R.id.sequence);
        sequence.setText(detailInfo.getSequence().trim() + ". ");*/
        TextView childItem = (TextView) view.findViewById(R.id.childItem);
        childItem.setText(detailInfo.getName().trim());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<AccountDisbursementChildInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return deptList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        AccountDisbursementGroupInfo headerInfo = (AccountDisbursementGroupInfo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.account_disbursement_group_items, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(headerInfo.getName().trim());
        ImageView img = (ImageView) view.findViewById(R.id.imag_arrow);


        if (isLastChild) {
            img.setImageResource(R.drawable.up_arrow_icon);
        } else {
            img.setImageResource(R.drawable.down_arrow_icon);
        }
     /*   if (isLastChild) {
            view.setBackgroundColor(Color.parseColor("#ebefef"));
        } else {
            view.setBackgroundColor(Color.parseColor("#e0e8e8"));
        }*/


        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
