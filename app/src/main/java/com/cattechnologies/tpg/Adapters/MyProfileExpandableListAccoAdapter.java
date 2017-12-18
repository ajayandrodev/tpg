package com.cattechnologies.tpg.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cattechnologies.tpg.Activities.MainActivity;
import com.cattechnologies.tpg.Model.AccountInfo;
import com.cattechnologies.tpg.Model.MyProfileGroupAccountInfo;
import com.cattechnologies.tpg.R;

import java.util.ArrayList;

/**
 * Created by admin on 10/18/2017.
 */

public class MyProfileExpandableListAccoAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<MyProfileGroupAccountInfo> deptList;

    public MyProfileExpandableListAccoAdapter(Context context, ArrayList<MyProfileGroupAccountInfo> deptList) {
        this.context = context;
        this.deptList = deptList;
    }

    @Override
    public int getGroupCount() {
        return deptList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<AccountInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<AccountInfo> productList = deptList.get(groupPosition).getProductList();
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

        MyProfileGroupAccountInfo headerInfo = (MyProfileGroupAccountInfo) getGroup(groupPosition);
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
        AccountInfo detailInfo = (AccountInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.my_profile_child_items_account, null);
        }
      /*  AccountInfo detailInfo = new AccountInfo();
        detailInfo.setAccountNameOnBankAccount("Name on Bank Account: ");
        detailInfo.setAccountNameOnBankAccountData(product.getAccount_info().get(0).);
        detailInfo.setAccountNameBankAccount("Name of Bank:");
        detailInfo.setAccountNameBankAccountData("Bank of the west");
        detailInfo.setAccountRoutingNum("Bank Routing Number:");
        detailInfo.setAccountRoutingNumData("123123123");
        detailInfo.setAccountNum("Bank Account Number:");
        detailInfo.setAccountNumData("XX3XX3");
        detailInfo.setAccountType("Type of Account:");
        detailInfo.setAccountTypeData("checking");*/


       /* if (childPosition%2==0) {
            view.setBackgroundColor(Color.parseColor("#e0e8e8"));
        } else {
            view.setBackgroundColor(Color.parseColor("#ebefef"));
        }
*/
       /* TextView sequence = (TextView) view.findViewById(R.id.sequence);
        sequence.setText(detailInfo.getSequence().trim() + ". ");*/
        TextView childItem1 = (TextView) view.findViewById(R.id.account_name_of_bank);
        childItem1.setText("Name on Bank Account: ");
        TextView childItem2 = (TextView) view.findViewById(R.id.account_name_of_bank_data);
        childItem2.setText(detailInfo.getNameOnAccount().trim());
        TextView childItem3 = (TextView) view.findViewById(R.id.account_name_bank_second);
        childItem3.setText("Name of Bank:");
        TextView childItem4 = (TextView) view.findViewById(R.id.account_name_bank_second_data);
        childItem4.setText(detailInfo.getBankName().trim());
        TextView childItem5 = (TextView) view.findViewById(R.id.account_rtn);
        childItem5.setText("Bank Routing Number:");
        TextView childItem6 = (TextView) view.findViewById(R.id.account_rtn_data);
        childItem6.setText(detailInfo.getRTN().trim());
        TextView childItem7 = (TextView) view.findViewById(R.id.account_number);
        childItem7.setText("Bank Account Number:");
        TextView childItem8 = (TextView) view.findViewById(R.id.account_number_data);
        childItem8.setText(detailInfo.getDAN().trim());
        TextView childItem9 = (TextView) view.findViewById(R.id.account_type);
        childItem9.setText("Type of Account:");
        TextView childItem10 = (TextView) view.findViewById(R.id.account_type_data);
        childItem10.setText(detailInfo.getAcctType().trim());


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
