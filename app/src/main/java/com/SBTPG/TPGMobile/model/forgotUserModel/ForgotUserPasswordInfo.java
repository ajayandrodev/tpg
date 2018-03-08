package com.SBTPG.TPGMobile.model.forgotUserModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 12/1/2017.
 */

public class ForgotUserPasswordInfo implements Parcelable {


    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("user_data")
    private ForgotUserPasswordData user_data;

    protected ForgotUserPasswordInfo(Parcel in) {
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

    public static final Creator<ForgotUserPasswordInfo> CREATOR = new Creator<ForgotUserPasswordInfo>() {
        @Override
        public ForgotUserPasswordInfo createFromParcel(Parcel in) {
            return new ForgotUserPasswordInfo(in);
        }

        @Override
        public ForgotUserPasswordInfo[] newArray(int size) {
            return new ForgotUserPasswordInfo[size];
        }
    };

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
