package com.cattechnologies.tpg.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by admin on 12/19/2017.
 */

public class ProfileGroupData {
   /* {
        "status": "success",
            "message": "Data found successfully",
            "owner_info": {
        "Street": "2 LONG STREET",
                "street2": null,
                "City": "SARASOTA",
                "State": "FL",
                "Zipcode": "34235",
                "FirstName": "BOB",
                "LastName": "BOB",
                "EmailAddress": "JH.JAH@JHNET.COM",
                "WorkPhone": null,
                "HomePhone": "6759876789",
                "MobilePhone": null
    },
        "shipping_info": {
        "Street": "300 CENTER POINT DRIVE 300",
                "street2": "",
                "City": "VIRGINIA BEACH",
                "State": "CA",
                "Zipcode": "23462",
                "ShipmentHoldUntilDate": "2017-11-30 00:00:00.000"
    },
        "account_info": {
        "BankName": "WELLS FARGO",
                "NameOnAccount": "MISTER MANAGER",
                "RTN": "051400345",
                "DAN": "203066472",
                "AcctType": "C"
    }
    }
}*/

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("owner_info")
    private EnrolInfo owner_info;

    @SerializedName("shipping_info")
    private ShippingInfo shipping_info;

    @SerializedName("account_info")
    private AccountInfo account_info;

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

    public EnrolInfo getOwner_info() {
        return owner_info;
    }

    public void setOwner_info(EnrolInfo owner_info) {
        this.owner_info = owner_info;
    }

    public ShippingInfo getShipping_info() {
        return shipping_info;
    }

    public void setShipping_info(ShippingInfo shipping_info) {
        this.shipping_info = shipping_info;
    }

    public AccountInfo getAccount_info() {
        return account_info;
    }

    public void setAccount_info(AccountInfo account_info) {
        this.account_info = account_info;
    }
}
