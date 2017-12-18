package com.cattechnologies.tpg.Model;

import java.util.ArrayList;

/**
 * Created by ajay kumar on 29-Oct-17.
 */

public class CheckToPrintGroupInfo {
    private String name;
    private ArrayList<CheckToPrintChildInfo> productList = new ArrayList<CheckToPrintChildInfo>();

    public ArrayList<CheckToPrintChildInfo> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<CheckToPrintChildInfo> productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
