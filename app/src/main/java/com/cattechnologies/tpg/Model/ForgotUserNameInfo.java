package com.cattechnologies.tpg.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 11/30/2017.
 */

public class ForgotUserNameInfo {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("user_data")
    private ForgotUserNameData user_data;

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

    public ForgotUserNameData getUser_data() {
        return user_data;
    }

    public void setUser_data(ForgotUserNameData user_data) {
        this.user_data = user_data;
    }
}
