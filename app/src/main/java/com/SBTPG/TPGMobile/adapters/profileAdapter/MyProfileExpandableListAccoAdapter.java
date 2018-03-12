package com.SBTPG.TPGMobile.adapters.profileAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.SBTPG.TPGMobile.model.profileModel.MyProfileGroupAccountInfoNew;
import com.SBTPG.TPGMobile.model.profileModel.AccountInfo;
import com.SBTPG.TPGMobile.R;

import java.util.ArrayList;

/**
 * Created by Ajay on 10/18/2017.
 */

public class MyProfileExpandableListAccoAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<MyProfileGroupAccountInfoNew> deptList;
    String type;

    public MyProfileExpandableListAccoAdapter(Context context, ArrayList<MyProfileGroupAccountInfoNew> deptList, String type) {
        this.context = context;
        this.deptList = deptList;
        this.type = type;
    }

    @Override
    public int getGroupCount() {
        return deptList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<AccountInfo> productList = deptList.get(groupPosition).getList();
        return productList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<AccountInfo> productList = deptList.get(groupPosition).getList();
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

        MyProfileGroupAccountInfoNew headerInfo = (MyProfileGroupAccountInfoNew) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.my_profile_group_items_account, null);
        }

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
        AccountInfo detailInfo = (AccountInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.my_profile_child_items_account, null);
        }
        try {

            if (type.equalsIgnoreCase("sb")) {
                TextView childItem1 = (TextView) view.findViewById(R.id.account_name_of_bank);
                childItem1.setText("Name on Bank Account: ");
                TextView childItem2 = (TextView) view.findViewById(R.id.account_name_of_bank_data);
                if (detailInfo.getNameOnAccount().trim().equalsIgnoreCase("")) {
                    childItem2.setText("-");
                } else {
                    childItem2.setText(detailInfo.getNameOnAccount().trim());

                }

                TextView childItem3 = (TextView) view.findViewById(R.id.account_name_bank_second);
                childItem3.setText("Name of Bank:");
                TextView childItem4 = (TextView) view.findViewById(R.id.account_name_bank_second_data);
                if (detailInfo.getBankName().trim().equalsIgnoreCase("")) {
                    childItem4.setText("-");
                } else {
                    childItem4.setText(detailInfo.getBankName().trim());

                }
                TextView childItem5 = (TextView) view.findViewById(R.id.account_rtn);
                childItem5.setText("Bank Routing Number:");
                TextView childItem6 = (TextView) view.findViewById(R.id.account_rtn_data);
                if (detailInfo.getRTN().trim().equalsIgnoreCase("")) {
                    childItem6.setText("-");
                } else {
                    childItem6.setText(detailInfo.getRTN().trim());

                }
                TextView childItem7 = (TextView) view.findViewById(R.id.account_number);
                childItem7.setText("Bank Account Number:");
                TextView childItem8 = (TextView) view.findViewById(R.id.account_number_data);
                if (detailInfo.getDAN().trim().equalsIgnoreCase("")) {
                    childItem8.setText("-");
                } else {
                    childItem8.setText(detailInfo.getDAN().trim());

                }
                TextView childItem9 = (TextView) view.findViewById(R.id.account_type);
                childItem9.setText("Type of Account:");
                TextView childItem10 = (TextView) view.findViewById(R.id.account_type_data);
                if (detailInfo.getAcctType().trim().equalsIgnoreCase("")) {
                    childItem10.setText("-");
                } else {
                    childItem10.setText(detailInfo.getAcctType().trim());

                }

            } else {
                TextView childItem1 = (TextView) view.findViewById(R.id.account_name_of_bank);
                childItem1.setText("Name on Bank Account: ");
                TextView childItem2 = (TextView) view.findViewById(R.id.account_name_of_bank_data);
                if (detailInfo.getNameOnAccount().trim().equalsIgnoreCase("")) {
                    childItem2.setText("-");
                } else {
                    childItem2.setText(detailInfo.getNameOnAccount().trim());

                }

                TextView childItem3 = (TextView) view.findViewById(R.id.account_name_bank_second);
                childItem3.setText("Name of Bank:");
                TextView childItem4 = (TextView) view.findViewById(R.id.account_name_bank_second_data);
                if (detailInfo.getBankName().trim().equalsIgnoreCase("")) {
                    childItem4.setText("-");
                } else {
                    childItem4.setText(detailInfo.getBankName().trim());

                }
                TextView childItem5 = (TextView) view.findViewById(R.id.account_rtn);
                childItem5.setText("Bank Routing Number:");
                TextView childItem6 = (TextView) view.findViewById(R.id.account_rtn_data);
                if (detailInfo.getRTN().trim().equalsIgnoreCase("")) {
                    childItem6.setText("-");
                } else {
                    childItem6.setText(detailInfo.getRTN().trim());

                }
                TextView childItem7 = (TextView) view.findViewById(R.id.account_number);
                childItem7.setText("Bank Account Number:");
                TextView childItem8 = (TextView) view.findViewById(R.id.account_number_data);
                if (detailInfo.getDAN().trim().equalsIgnoreCase("")) {
                    childItem8.setText("-");
                } else {
                    childItem8.setText(detailInfo.getDAN().trim());

                }
                TextView childItem9 = (TextView) view.findViewById(R.id.account_type);
                childItem9.setText("Type of Account:");
                TextView childItem10 = (TextView) view.findViewById(R.id.account_type_data);
                if (detailInfo.getAcctType().trim().equalsIgnoreCase("")) {
                    childItem10.setText("-");
                } else {
                    childItem10.setText(detailInfo.getAcctType().trim());

                }

            }
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}