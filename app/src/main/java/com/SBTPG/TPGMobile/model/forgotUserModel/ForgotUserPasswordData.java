package com.SBTPG.TPGMobile.model.forgotUserModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 12/1/2017.
 */

public class ForgotUserPasswordData {

    @SerializedName("TEMPORARY_PIN")
    private String TEMPORARY_PIN;

    public String getTEMPORARY_PIN() {
        return TEMPORARY_PIN;
    }

    public void setTEMPORARY_PIN(String TEMPORARY_PIN) {
        this.TEMPORARY_PIN = TEMPORARY_PIN;
    }
}
