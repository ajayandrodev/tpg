package com.cattechnologies.tpg.model.profileModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 10/23/2017.
 */

public class ShippingInfo {

   /*   "shipping_info": [
    {
        "Street": "300 CENTER POINT DRIVE 300",
            "street2": "",
            "City": "VIRGINIA BEACH",
            "State": "CA",
            "Zipcode": "23462",
            "ShipmentHoldUntilDate": "2017-11-30 00:00:00.000"
    }
    ],*/


  /*   "shipping_info": [
    {
        "Street": "11085 N TORREY PINES",
            "street2": null,
            "City": "LA JOLLA",
            "State": "CA",
            "Zipcode": "92037"
    }
    ],*/

    @SerializedName("Street")
    private String Street;

    @SerializedName("street2")
    private String street2;

    @SerializedName("City")
    private String City;

    @SerializedName("State")
    private String State;

    @SerializedName("Zipcode")
    private String Zipcode;

    @SerializedName("ShipmentHoldUntilDate")
    private String ShipmentHoldUntilDate;

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

    public String getShipmentHoldUntilDate() {
        return ShipmentHoldUntilDate;
    }

    public void setShipmentHoldUntilDate(String shipmentHoldUntilDate) {
        ShipmentHoldUntilDate = shipmentHoldUntilDate;
    }
}
