package com.cattechnologies.tpg.adapters.profileAdapter;

import android.content.Context;
import android.os.Build;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cattechnologies.tpg.model.EnrolInfo;
import com.cattechnologies.tpg.model.profileModel.MyProfileGroupEnrollInfoNew;
import com.cattechnologies.tpg.R;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by admin on 12/19/2017.
 */

public class MyProfileExpandableListEnrAdapterNew extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<MyProfileGroupEnrollInfoNew> deptList;
    String type;

    public MyProfileExpandableListEnrAdapterNew(Context context, ArrayList<MyProfileGroupEnrollInfoNew> deptList, String type) {
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
        ArrayList<EnrolInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<EnrolInfo> productList = deptList.get(groupPosition).getProductList();
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
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        MyProfileGroupEnrollInfoNew headerInfo = (MyProfileGroupEnrollInfoNew) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.my_profile_group_items_enroll, null);
        }
     /*   if (isLastChild) {
            view.setBackgroundColor(Color.parseColor("#ebefef"));
        } else {
            view.setBackgroundColor(Color.parseColor("#e0e8e8"));
        }*/

     /*   LinearLayout.LayoutParams vi_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                (int)(MainActivity.screen*0.33));
        view.setLayoutParams(vi_params);*/

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(headerInfo.getName().toString());
        ImageView img = (ImageView) view.findViewById(R.id.imag_arrow);


        if (isExpanded) {
            img.setImageResource(R.mipmap.arrow_down);
        } else {
            img.setImageResource(R.mipmap.arrow_right);
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        EnrolInfo detailInfo = (EnrolInfo) getChild(groupPosition, childPosition);

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.my_profile_child_items_enroll, null);
        }
        try {
            TextView childItem1 = (TextView) view.findViewById(R.id.enroll_first_name);
            childItem1.setText("Owner First Name:");
            View view1 = (View) view.findViewById(R.id.view_enroll_six);
            // childItem1.setText(detailInfo.getEnrollFirstName().trim());
            TextView childItem2 = (TextView) view.findViewById(R.id.enroll_first_name_data);
            TextView childItem3 = (TextView) view.findViewById(R.id.enroll_last_name);
            childItem3.setText("Owner Last Name:");
            TextView childItem4 = (TextView) view.findViewById(R.id.enroll_last_name_data);
            TextView childItem5 = (TextView) view.findViewById(R.id.enroll_address);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_enroll_five);
            childItem5.setText("Address:");
            TextView childItem6 = (TextView) view.findViewById(R.id.enroll_address_data);
            TextView childItem9 = (TextView) view.findViewById(R.id.enroll_office_phone);
            childItem9.setText("Office Phone:");
            TextView childItem10 = (TextView) view.findViewById(R.id.enroll_office_phone_data);
            TextView childItem11 = (TextView) view.findViewById(R.id.enroll_mobile_phone);
            childItem11.setText("Mobile Phone:");
            TextView childItem12 = (TextView) view.findViewById(R.id.enroll_mobile_phone_data);
            TextView childItem15 = (TextView) view.findViewById(R.id.enroll_home_phone);
            childItem15.setText("Home Phone:");
            TextView childItem13 = (TextView) view.findViewById(R.id.enroll_email);
            childItem13.setText("Email:");
            TextView childItem14 = (TextView) view.findViewById(R.id.enroll_email_data);
            TextView childItem16 = (TextView) view.findViewById(R.id.enroll_home_phone_data);
            if (type.equalsIgnoreCase("sb")) {
                childItem2.setText(detailInfo.getContactFirstName().trim());
                childItem4.setText(detailInfo.getContactLastName().trim());
                childItem6.setText(detailInfo.getStreet() + "," + detailInfo.getCity() + "," + detailInfo.getZipcode());
                if (detailInfo.getOfficePhone() != null) {

                    try {
                        // phone must begin with '+'
                        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                        Phonenumber.PhoneNumber numberProto = phoneUtil.parse(detailInfo.getOfficePhone().trim(), "US");
                      /*  int countryCode = numberProto.getCountryCode();
                        long nationalNumber = numberProto.getNationalNumber();*/
                        String pnE164 = phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
                        childItem16.setText(pnE164.replace("+1", ""));
                    } catch (NumberParseException e) {
                        System.err.println("NumberParseException was thrown: " + e.toString());
                    }


                } else {
                    childItem10.setText("-");

                }
                if (detailInfo.getMobilePhone() != null) {

                    try {
                        // phone must begin with '+'
                        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                        Phonenumber.PhoneNumber numberProto = phoneUtil.parse(detailInfo.getMobilePhone().trim(), "US");
                      /*  int countryCode = numberProto.getCountryCode();
                        long nationalNumber = numberProto.getNationalNumber();*/
                        String pnE164 = phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
                        childItem16.setText(pnE164.replace("+1", ""));
                    } catch (NumberParseException e) {
                        System.err.println("NumberParseException was thrown: " + e.toString());
                    }

                } else {
                    childItem12.setText("-");

                }
                if (detailInfo.getFaxPhone() != null) {


                    try {
                        // phone must begin with '+'
                        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                        Phonenumber.PhoneNumber numberProto = phoneUtil.parse(detailInfo.getFaxPhone().trim(), "US");
                      /*  int countryCode = numberProto.getCountryCode();
                        long nationalNumber = numberProto.getNationalNumber();*/
                        String pnE164 = phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
                        childItem16.setText(pnE164.replace("+1", ""));
                    } catch (NumberParseException e) {
                        System.err.println("NumberParseException was thrown: " + e.toString());
                    }


                } else {
                    childItem16.setText("-");

                }
                childItem14.setText(detailInfo.getEmailAddress().trim());


            } else if (type.equalsIgnoreCase("ero")) {
                childItem2.setText(detailInfo.getFirstName().trim());
                childItem4.setText(detailInfo.getLastName().trim());
                childItem6.setText(detailInfo.getStreet() + "," + detailInfo.getCity() + "," + detailInfo.getZipcode());
                if (detailInfo.getWorkPhone() != null) {

                    try {
                        // phone must begin with '+'
                        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                        Phonenumber.PhoneNumber numberProto = phoneUtil.parse(detailInfo.getWorkPhone().trim(), "US");
                      /*  int countryCode = numberProto.getCountryCode();
                        long nationalNumber = numberProto.getNationalNumber();*/
                        String pnE164 = phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
                        childItem16.setText(pnE164.replace("+1", ""));
                    } catch (NumberParseException e) {
                        System.err.println("NumberParseException was thrown: " + e.toString());
                    }


                } else {
                    childItem10.setText("-");

                }
                if (detailInfo.getMobilePhone() != null) {
                    try {
                        // phone must begin with '+'
                        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                        Phonenumber.PhoneNumber numberProto = phoneUtil.parse(detailInfo.getMobilePhone().trim(), "US");
                      /*  int countryCode = numberProto.getCountryCode();
                        long nationalNumber = numberProto.getNationalNumber();*/
                        String pnE164 = phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
                        childItem16.setText(pnE164.replace("+1", ""));
                    } catch (NumberParseException e) {
                        System.err.println("NumberParseException was thrown: " + e.toString());
                    }


                } else {
                    childItem12.setText("-");

                }
                if (detailInfo.getHomePhone() != null) {
                    try {
                        // phone must begin with '+'
                        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                        Phonenumber.PhoneNumber numberProto = phoneUtil.parse(detailInfo.getHomePhone().trim(), "US");
                      /*  int countryCode = numberProto.getCountryCode();
                        long nationalNumber = numberProto.getNationalNumber();*/
                        String pnE164 = phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
                        childItem16.setText(pnE164.replace("+1", ""));
                    } catch (NumberParseException e) {
                        System.err.println("NumberParseException was thrown: " + e.toString());
                    }


                } else {
                    childItem16.setText("-");

                }
                childItem14.setText(detailInfo.getEmailAddress().trim());


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
