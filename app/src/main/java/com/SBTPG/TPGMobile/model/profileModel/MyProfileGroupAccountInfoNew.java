package com.SBTPG.TPGMobile.model.profileModel;

import java.util.ArrayList;

/**
 * Created by Ajay on 1/30/2018.
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
