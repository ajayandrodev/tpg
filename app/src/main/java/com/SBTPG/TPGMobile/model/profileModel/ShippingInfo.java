package com.SBTPG.TPGMobile.model.profileModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 10/23/2017.
 */

public class ShippingInfo implements Parcelable {

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

    public ShippingInfo() {

    }

    protected ShippingInfo(Parcel in) {
        Street = in.readString();
        street2 = in.readString();
        City = in.readString();
        State = in.readString();
        Zipcode = in.readString();
        ShipmentHoldUntilDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Street);
        dest.writeString(street2);
        dest.writeString(City);
        dest.writeString(State);
        dest.writeString(Zipcode);
        dest.writeString(ShipmentHoldUntilDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShippingInfo> CREATOR = new Creator<ShippingInfo>() {
        @Override
        public ShippingInfo createFromParcel(Parcel in) {
            return new ShippingInfo(in);
        }

        @Override
        public ShippingInfo[] newArray(int size) {
            return new ShippingInfo[size];
        }
    };

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
