package com.cattechnologies.tpg.model.profileModel;

import com.cattechnologies.tpg.model.profileModel.AccountInfo;

import java.util.ArrayList;

/**
 * Created by admin on 1/30/2018.
 */

public class MyProfileGroupAccountInfoNew {
    private String name;
    private ArrayList<AccountInfo> list = new ArrayList<AccountInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<AccountInfo> getList() {
        return list;
    }

    public void setList(ArrayList<AccountInfo> list) {
        this.list = list;
    }
}
