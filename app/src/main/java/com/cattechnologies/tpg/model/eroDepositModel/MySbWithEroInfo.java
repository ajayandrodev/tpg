package com.cattechnologies.tpg.model.eroDepositModel;

import java.util.ArrayList;

/**
 * Created by admin on 11/24/2017.
 */

public class MySbWithEroInfo {

    private String name;
    private ArrayList<EroInfo> productList = new ArrayList<EroInfo>();

    public ArrayList<EroInfo> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<EroInfo> productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
