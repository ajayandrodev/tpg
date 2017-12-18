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
import com.cattechnologies.tpg.Model.MyProfileGroupShippingInfo;
import com.cattechnologies.tpg.Model.ShippingInfo;
import com.cattechnologies.tpg.R;

import java.util.ArrayList;

/**
 * Created by admin on 10/18/2017.
 */

public class MyProfileExpandableListShipAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<MyProfileGroupShippingInfo> deptList;
    String type;

    public MyProfileExpandableListShipAdapter(Context context, ArrayList<MyProfileGroupShippingInfo> deptList, String type) {
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
        ArrayList<ShippingInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ShippingInfo> productList = deptList.get(groupPosition).getProductList();
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

        MyProfileGroupShippingInfo headerInfo = (MyProfileGroupShippingInfo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.my_profile_group_items_shipping, null);
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
        ShippingInfo detailInfo = (ShippingInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.my_profile_child_items_shipping, null);
        }
      /*  LinearLayout.LayoutParams vi_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                (int)(MainActivity.screen*0.33));
        view.setLayoutParams(vi_params);*/

       /* if (childPosition%2==0) {
            view.setBackgroundColor(Color.parseColor("#e0e8e8"));
        } else {
            view.setBackgroundColor(Color.parseColor("#ebefef"));
        }
*/
       /* TextView sequence = (TextView) view.findViewById(R.id.sequence);
        sequence.setText(detailInfo.getSequence().trim() + ". ");*/

        //create a new child and add that to the group
     /*   ShippingInfo detailInfo = new ShippingInfo();
        detailInfo.setAddress("Address:");
        detailInfo.setAddressData("123 South Main Street\n" +
                "Suite 200");
        detailInfo.setCity("City,State,Zip:");
        detailInfo.setCityData("Bellflower, CA\n" +
                "        90706");
        detailInfo.setPhone("Shipping Wait Date:");
        detailInfo.setPhoneData("12-10-2017");*/
        try {
            if (type.equalsIgnoreCase("sb")) {
                TextView childItem1 = (TextView) view.findViewById(R.id.shipping_address);
                childItem1.setText("Address:");
                TextView childItem2 = (TextView) view.findViewById(R.id.shipping_address_data);
                if (detailInfo.getStreet2() != null) {
                    childItem2.setText(detailInfo.getStreet().trim() + "," + detailInfo.getStreet2().trim());
                } else {
                    childItem2.setText(detailInfo.getStreet().trim() + "," + "");

                }
                TextView childItem3 = (TextView) view.findViewById(R.id.shipping_address_city);
                childItem3.setText("City,State,Zip:");
                TextView childItem4 = (TextView) view.findViewById(R.id.shipping_address_city_data);
                childItem4.setText(detailInfo.getCity().trim() + "," + detailInfo.getState().trim() + "," + detailInfo.getZipcode().trim());
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_shipping_three);
                linearLayout.setVisibility(View.GONE);

            /*  TextView childItem5 = (TextView) view.findViewById(R.id.shipping_address_phone);
            childItem5.setText("Shipping Wait Date:");
            TextView childItem6 = (TextView) view.findViewById(R.id.shipping_address_phone_data);
            childItem6.setText(detailInfo.getShipmentHoldUntilDate().trim());*/
            } else {
                TextView childItem1 = (TextView) view.findViewById(R.id.shipping_address);
                childItem1.setText("Address:");
                TextView childItem2 = (TextView) view.findViewById(R.id.shipping_address_data);
                childItem2.setText(detailInfo.getStreet().trim() + "," + detailInfo.getStreet2().trim());
                TextView childItem3 = (TextView) view.findViewById(R.id.shipping_address_city);
                childItem3.setText("City,State,Zip:");
                TextView childItem4 = (TextView) view.findViewById(R.id.shipping_address_city_data);
                childItem4.setText(detailInfo.getCity().trim() + "," + detailInfo.getState().trim() + "," + detailInfo.getZipcode().trim());
                TextView childItem5 = (TextView) view.findViewById(R.id.shipping_address_phone);
                childItem5.setText("Shipping Wait Date:");
                TextView childItem6 = (TextView) view.findViewById(R.id.shipping_address_phone_data);
                childItem6.setText(detailInfo.getShipmentHoldUntilDate().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
