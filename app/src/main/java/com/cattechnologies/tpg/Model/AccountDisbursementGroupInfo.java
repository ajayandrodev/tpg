package com.cattechnologies.tpg.Model;

import java.util.ArrayList;

/**
 * Created by ajay kumar on 29-Oct-17.
 */

public class AccountDisbursementGroupInfo {
    private String name;
    private ArrayList<AccountDisbursementChildInfo> productList = new ArrayList<AccountDisbursementChildInfo>();

    public ArrayList<AccountDisbursementChildInfo> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<AccountDisbursementChildInfo> productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
