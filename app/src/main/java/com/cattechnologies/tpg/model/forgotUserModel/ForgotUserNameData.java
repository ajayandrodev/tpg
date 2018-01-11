package com.cattechnologies.tpg.model.forgotUserModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 11/30/2017.
 */

public class ForgotUserNameData {

    @SerializedName("LOGIN_NAME")
    private String LOGIN_NAME;

    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public void setLOGIN_NAME(String LOGIN_NAME) {
        this.LOGIN_NAME = LOGIN_NAME;
    }
}
