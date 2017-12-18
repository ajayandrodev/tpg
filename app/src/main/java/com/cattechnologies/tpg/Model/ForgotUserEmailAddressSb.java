package com.cattechnologies.tpg.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 12/6/2017.
 */

public class ForgotUserEmailAddressSb {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("user_data")
    private ForgotUserEmailData user_data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ForgotUserEmailData getUser_data() {
        return user_data;
    }

    public void setUser_data(ForgotUserEmailData user_data) {
        this.user_data = user_data;
    }
}
