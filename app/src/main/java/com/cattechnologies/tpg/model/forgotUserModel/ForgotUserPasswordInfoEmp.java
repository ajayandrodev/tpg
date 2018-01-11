package com.cattechnologies.tpg.model.forgotUserModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 12/6/2017.
 */

public class ForgotUserPasswordInfoEmp {


    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("user_data")
    private ForgotUserPasswordData user_data;

    public ForgotUserPasswordData getUser_data() {
        return user_data;
    }

    public void setUser_data(ForgotUserPasswordData user_data) {
        this.user_data = user_data;
    }

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
}
