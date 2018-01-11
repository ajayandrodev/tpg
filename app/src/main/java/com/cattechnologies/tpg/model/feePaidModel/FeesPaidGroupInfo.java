package com.cattechnologies.tpg.model.feePaidModel;

import java.util.ArrayList;

/**
 * Created by ajay kumar on 29-Oct-17.
 */

public class FeesPaidGroupInfo {
    private String name;
    private ArrayList<FeesPaidChildInfo> productList = new ArrayList<FeesPaidChildInfo>();

    public ArrayList<FeesPaidChildInfo> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<FeesPaidChildInfo> productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
