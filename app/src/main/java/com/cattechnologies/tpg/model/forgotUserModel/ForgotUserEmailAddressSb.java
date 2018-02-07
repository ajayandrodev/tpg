package com.cattechnologies.tpg.model.forgotUserModel;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 12/6/2017.
 */

public class ForgotUserEmailAddressSb implements Parcelable{

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("user_data")
    private ForgotUserEmailData user_data;

    protected ForgotUserEmailAddressSb(Parcel in) {
        status = in.readString();
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ForgotUserEmailAddressSb> CREATOR = new Creator<ForgotUserEmailAddressSb>() {
        @Override
        public ForgotUserEmailAddressSb createFromParcel(Parcel in) {
            return new ForgotUserEmailAddressSb(in);
        }

        @Override
        public ForgotUserEmailAddressSb[] newArray(int size) {
            return new ForgotUserEmailAddressSb[size];
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

    public ForgotUserEmailData getUser_data() {
        return user_data;
    }

    public void setUser_data(ForgotUserEmailData user_data) {
        this.user_data = user_data;
    }
}
