package com.cattechnologies.tpg.Model;

import java.util.ArrayList;

/**
 * Created by admin on 10/23/2017.
 */

public class MyProfileGroupEnrollInfo {
    private String name;
    private ArrayList<EnrolInfo> list = new ArrayList<EnrolInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<EnrolInfo> getProductList() {
        return list;
    }

    public void setProductList(ArrayList<EnrolInfo> productList) {
        this.list = productList;
    }

}
