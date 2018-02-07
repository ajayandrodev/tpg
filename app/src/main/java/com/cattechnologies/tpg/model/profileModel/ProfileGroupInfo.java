package com.cattechnologies.tpg.model.profileModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ajay on 11/21/2017.
 */

public class ProfileGroupInfo {

    @SerializedName("status")
    private String status;



    @SerializedName("message")
    private String message;

    @SerializedName("owner_info")
    private ArrayList<EnrolInfo> owner_info;

    @SerializedName("shipping_info")
    private ArrayList<ShippingInfo> shipping_info;

    @SerializedName("account_info")
    private ArrayList<AccountInfo> account_info;

    public ArrayList<ShippingInfo> getShipping_info() {
        return shipping_info;
    }

    public void setShipping_info(ArrayList<ShippingInfo> shipping_info) {
        this.shipping_info = shipping_info;
    }

    public ArrayList<AccountInfo> getAccount_info() {
        return account_info;
    }

    public void setAccount_info(ArrayList<AccountInfo> account_info) {
        this.account_info = account_info;
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

    public ArrayList<EnrolInfo> getOwner_info() {
        return owner_info;
    }

    public void setOwner_info(ArrayList<EnrolInfo> owner_info) {
        this.owner_info = owner_info;
    }
}
