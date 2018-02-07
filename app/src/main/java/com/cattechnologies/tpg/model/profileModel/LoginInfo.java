package com.cattechnologies.tpg.model.profileModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 11/9/2017.
 */

public class LoginInfo implements Parcelable {


    @SerializedName("app_uid")
    private String app_uid;

    @SerializedName("user_efin")
    private String user_efin;

    @SerializedName("acc_type")
    private String acc_type;

    @SerializedName("app_pswd")
    private String app_pswd;
    public LoginInfo(){

    }

    protected LoginInfo(Parcel in) {
        app_uid = in.readString();
        user_efin = in.readString();
        acc_type = in.readString();
        app_pswd = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(app_uid);
        dest.writeString(user_efin);
        dest.writeString(acc_type);
        dest.writeString(app_pswd);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginInfo> CREATOR = new Creator<LoginInfo>() {
        @Override
        public LoginInfo createFromParcel(Parcel in) {
            return new LoginInfo(in);
        }

        @Override
        public LoginInfo[] newArray(int size) {
            return new LoginInfo[size];
        }
    };

    public String getApp_pswd() {
        return app_pswd;
    }

    public void setApp_pswd(String app_pswd) {
        this.app_pswd = app_pswd;
    }

    public String getUser_efin() {
        return user_efin;
    }

    public void setUser_efin(String user_efin) {
        this.user_efin = user_efin;
    }

    public String getApp_uid() {
        return app_uid;
    }

    public void setApp_uid(String app_uid) {
        this.app_uid = app_uid;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }
}
