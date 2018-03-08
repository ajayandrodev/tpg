package com.SBTPG.TPGMobile.model.profileModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 11/15/2017.
 */

public class ProfileData implements Parcelable {

    @SerializedName("LOGIN_NAME")
    private String LOGIN_NAME;

    @SerializedName("EFIN")
    private String EFIN;

    @SerializedName("EmployeeID")
    private String EmployeeID;

    @SerializedName("FirstName")
    private String FirstName;

    @SerializedName("LastName")
    private String LastName;

    @SerializedName("UserName")
    private String UserName;

    @SerializedName("EMAIL_ADDRESS")
    private String EMAIL_ADDRESS;

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEMAIL_ADDRESS() {
        return EMAIL_ADDRESS;
    }

    public void setEMAIL_ADDRESS(String EMAIL_ADDRESS) {
        this.EMAIL_ADDRESS = EMAIL_ADDRESS;
    }

    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public void setLOGIN_NAME(String LOGIN_NAME) {
        this.LOGIN_NAME = LOGIN_NAME;
    }

    public String getEFIN() {
        return EFIN;
    }

    public void setEFIN(String EFIN) {
        this.EFIN = EFIN;
    }

    public static Creator<ProfileData> getCREATOR() {
        return CREATOR;
    }

    public ProfileData() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.LOGIN_NAME);
        dest.writeString(this.EFIN);
        dest.writeString(this.EmployeeID);
        dest.writeString(this.FirstName);
        dest.writeString(this.LastName);
        dest.writeString(this.UserName);
        dest.writeString(this.EMAIL_ADDRESS);
    }

    protected ProfileData(Parcel in) {
        this.LOGIN_NAME = in.readString();
        this.EFIN = in.readString();
        this.EmployeeID = in.readString();
        this.FirstName = in.readString();
        this.LastName = in.readString();
        this.UserName = in.readString();
        this.EMAIL_ADDRESS = in.readString();
    }

    public static final Creator<ProfileData> CREATOR = new Creator<ProfileData>() {
        @Override
        public ProfileData createFromParcel(Parcel source) {
            return new ProfileData(source);
        }

        @Override
        public ProfileData[] newArray(int size) {
            return new ProfileData[size];
        }
    };
}
