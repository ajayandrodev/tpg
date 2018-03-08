package com.SBTPG.TPGMobile.adapters.accountDisbursementsReportAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.SBTPG.TPGMobile.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ajay on 1/5/2018.
 */

public class ReportsAccountDisbExpandableadapter extends BaseExpandableListAdapter {
    private Context mcontext;
    private List<String> parent;
    private HashMap<String, List<String>> bind_and_display;

    public ReportsAccountDisbExpandableadapter(Context context, List<String> listDataHeader,
                                               HashMap<String, List<String>> listChildData) {
        this.mcontext = context;
        this.parent = listDataHeader;
        this.bind_and_display = listChildData;
    }

    @Override
    public int getGroupCount() {
        return this.parent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return this.bind_and_display.get(this.parent.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return this.parent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return this.bind_and_display.get(this.parent.get(groupPosition))
                .get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String parent_text = (String) getGroup(groupPosition);

        LayoutInflater infalInflater = (LayoutInflater) this.mcontext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.report_parent_view, null);


        TextView textparent = (TextView) convertView.findViewById(R.id.parent_report_fee_paid);
        textparent.setText(parent_text);
        ImageView img = (ImageView) convertView.findViewById(R.id.imag_arrow);

        if (isLastChild) {
            img.setImageResource(R.mipmap.up_arrow_expan);
        } else {
            img.setImageResource(R.mipmap.down_arrow_expand);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String child_text = (String) getChild(groupPosition, childPosition);


        LayoutInflater infalInflater = (LayoutInflater) this.mcontext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.report_child_view, null);
        TextView textchild = (TextView) convertView.findViewById(R.id.child_report_free_paid);
        textchild.setText(child_text);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }
}