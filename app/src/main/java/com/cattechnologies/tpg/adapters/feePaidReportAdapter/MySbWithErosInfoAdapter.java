package com.cattechnologies.tpg.adapters.feePaidReportAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cattechnologies.tpg.model.eroDepositModel.EroInfo;
import com.cattechnologies.tpg.model.eroDepositModel.MySbWithEroInfo;
import com.cattechnologies.tpg.R;

import java.util.ArrayList;

/**
 * Created by admin on 11/24/2017.
 */

public class MySbWithErosInfoAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<MySbWithEroInfo> deptList;

    public MySbWithErosInfoAdapter(Context context, ArrayList<MySbWithEroInfo> deptList) {
        this.context = context;
        this.deptList = deptList;
    }

    @Override
    public int getGroupCount() {
        return deptList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<EroInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<EroInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.get(childPosition);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {

        MySbWithEroInfo headerInfo = (MySbWithEroInfo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.my_profile_group_items_account, null);
        }
     /*   if (isLastChild) {
            view.setBackgroundColor(Color.parseColor("#ebefef"));
        } else {
            view.setBackgroundColor(Color.parseColor("#e0e8e8"));
        }*/


        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(headerInfo.getName().trim());
        ImageView img = (ImageView) view.findViewById(R.id.imag_arrow);


        if (isExpanded) {
            img.setImageResource(R.mipmap.arrow_down);
        } else {
            img.setImageResource(R.mipmap.arrow_right);
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        EroInfo detailInfo = (EroInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.my_ero_child_items_account, null);
        }



       /* if (childPosition%2==0) {
            view.setBackgroundColor(Color.parseColor("#e0e8e8"));
        } else {
            view.setBackgroundColor(Color.parseColor("#ebefef"));
        }
*/
       /* TextView sequence = (TextView) view.findViewById(R.id.sequence);
        sequence.setText(detailInfo.getSequence().trim() + ". ");*/
        TextView childItem1 = (TextView) view.findViewById(R.id.account_name_of_bank);
        childItem1.setText("All Offices");
        TextView childItem3 = (TextView) view.findViewById(R.id.account_name_bank_second);
        childItem3.setText("Particular Offices");


        return view;
    }

    @Override
    public int getChildTypeCount() {
        return 2;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        if (getChildId(groupPosition, childPosition) == 3)
            return 0;

            //Not free
        else
            return 1;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
