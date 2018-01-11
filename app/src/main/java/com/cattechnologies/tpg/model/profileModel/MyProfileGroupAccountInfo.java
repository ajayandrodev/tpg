package com.cattechnologies.tpg.model.profileModel;

import com.cattechnologies.tpg.model.accountDisbursementModel.AccountInfo;

import java.util.ArrayList;

/**
 * Created by admin on 10/23/2017.
 */

public class MyProfileGroupAccountInfo {

    private String name;
    private ArrayList<AccountInfo> list = new ArrayList<AccountInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<AccountInfo> getProductList() {
        return list;
    }

    public void setProductList(ArrayList<AccountInfo> productList) {
        this.list = productList;
    }

}
