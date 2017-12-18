package com.cattechnologies.tpg.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 12/1/2017.
 */

public class ForgotUserPasswordInfo {

    /*{
        "status": "success",
            "message": "Your temporary pin has been sent to your registered email id",
            "user_data": {
        "TEMPORARY_PIN": "30153"
    }
    }*/


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
