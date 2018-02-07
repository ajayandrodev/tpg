package com.cattechnologies.tpg.model.forgotUserModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 12/1/2017.
 */

public class ForgotUserEmailData {


    @SerializedName("EMAIL_ADDRESS")
    private String EMAIL_ADDRESS;

    public String getEMAIL_ADDRESS() {
        return EMAIL_ADDRESS;
    }

    public void setEMAIL_ADDRESS(String EMAIL_ADDRESS) {
        this.EMAIL_ADDRESS = EMAIL_ADDRESS;
    }
}
