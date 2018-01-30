package com.cattechnologies.tpg.model;

import com.cattechnologies.tpg.model.profileModel.ShippingInfo;

import java.util.ArrayList;

/**
 * Created by admin on 1/30/2018.
 */

public class MyProfileGroupShippingInfoNew {
    private String name;
    private ArrayList<ShippingInfo> list = new ArrayList<ShippingInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ShippingInfo> getList() {
        return list;
    }

    public void setList(ArrayList<ShippingInfo> list) {
        this.list = list;
    }
}
