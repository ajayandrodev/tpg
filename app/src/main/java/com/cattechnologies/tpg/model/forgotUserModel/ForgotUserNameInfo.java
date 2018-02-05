package com.cattechnologies.tpg.model.forgotUserModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 11/30/2017.
 */

public class ForgotUserNameInfo implements Parcelable{

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("user_data")
    private ForgotUserNameData user_data;

    protected ForgotUserNameInfo(Parcel in) {
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

    public static final Creator<ForgotUserNameInfo> CREATOR = new Creator<ForgotUserNameInfo>() {
        @Override
        public ForgotUserNameInfo createFromParcel(Parcel in) {
            return new ForgotUserNameInfo(in);
        }

        @Override
        public ForgotUserNameInfo[] newArray(int size) {
            return new ForgotUserNameInfo[size];
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

    public ForgotUserNameData getUser_data() {
        return user_data;
    }

    public void setUser_data(ForgotUserNameData user_data) {
        this.user_data = user_data;
    }
}
