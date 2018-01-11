package com.cattechnologies.tpg.model.profileModel;

import java.util.ArrayList;

/**
 * Created by admin on 10/23/2017.
 */

public class MyProfileGroupShippingInfo {
    private String name;
    private ArrayList<ShippingInfo> list = new ArrayList<ShippingInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ShippingInfo> getProductList() {
        return list;
    }

    public void setProductList(ArrayList<ShippingInfo> productList) {
        this.list = productList;
    }

}
