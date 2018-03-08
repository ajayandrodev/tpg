package com.SBTPG.TPGMobile.model.profileModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 12/19/2017.
 */

public class ProfileGroupData implements Parcelable{

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

    protected ProfileGroupData(Parcel in) {
        status = in.readString();
        message = in.readString();
        owner_info = in.readParcelable(EnrolInfo.class.getClassLoader());
        account_info = in.readParcelable(AccountInfo.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(message);
        dest.writeParcelable(owner_info, flags);
        dest.writeParcelable(account_info, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProfileGroupData> CREATOR = new Creator<ProfileGroupData>() {
        @Override
        public ProfileGroupData createFromParcel(Parcel in) {
            return new ProfileGroupData(in);
        }

        @Override
        public ProfileGroupData[] newArray(int size) {
            return new ProfileGroupData[size];
        }
    };

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
