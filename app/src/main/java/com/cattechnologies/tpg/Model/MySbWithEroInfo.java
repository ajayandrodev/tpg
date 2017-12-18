package com.cattechnologies.tpg.Model;

import java.util.ArrayList;

/**
 * Created by admin on 11/24/2017.
 */

public class MySbWithEroInfo {

    private String name;
    private ArrayList<EroInfo> list = new ArrayList<EroInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<EroInfo> getProductList() {
        return list;
    }

    public void setProductList(ArrayList<EroInfo> productList) {
        this.list = productList;
    }
}
