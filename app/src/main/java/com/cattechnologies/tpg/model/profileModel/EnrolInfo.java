package com.cattechnologies.tpg.model.profileModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 10/23/2017.
 */

public class EnrolInfo implements Parcelable {

    @SerializedName("FirstName")
    private String FirstName;

    @SerializedName("FaxPhone")
    private String FaxPhone;

    @SerializedName("OfficePhone")
    private String OfficePhone;

    public EnrolInfo() {
    }


    protected EnrolInfo(Parcel in) {
        FirstName = in.readString();
        FaxPhone = in.readString();
        OfficePhone = in.readString();
        ContactFirstName = in.readString();
        ContactLastName = in.readString();
        LastName = in.readString();
        Street = in.readString();
        street2 = in.readString();
        City = in.readString();
        State = in.readString();
        Zipcode = in.readString();
        WorkPhone = in.readString();
        MobilePhone = in.readString();
        HomePhone = in.readString();
        EmailAddress = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(FirstName);
        dest.writeString(FaxPhone);
        dest.writeString(OfficePhone);
        dest.writeString(ContactFirstName);
        dest.writeString(ContactLastName);
        dest.writeString(LastName);
        dest.writeString(Street);
        dest.writeString(street2);
        dest.writeString(City);
        dest.writeString(State);
        dest.writeString(Zipcode);
        dest.writeString(WorkPhone);
        dest.writeString(MobilePhone);
        dest.writeString(HomePhone);
        dest.writeString(EmailAddress);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EnrolInfo> CREATOR = new Creator<EnrolInfo>() {
        @Override
        public EnrolInfo createFromParcel(Parcel in) {
            return new EnrolInfo(in);
        }

        @Override
        public EnrolInfo[] newArray(int size) {
            return new EnrolInfo[size];
        }
    };

    public String getFaxPhone() {
        return FaxPhone;
    }

    public void setFaxPhone(String faxPhone) {
        FaxPhone = faxPhone;
    }

    public String getOfficePhone() {
        return OfficePhone;
    }

    public void setOfficePhone(String officePhone) {
        OfficePhone = officePhone;
    }

    @SerializedName("ContactFirstName")
    private String ContactFirstName;

    @SerializedName("ContactLastName")
    private String ContactLastName;


    public String getContactFirstName() {
        return ContactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        ContactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return ContactLastName;
    }

    public void setContactLastName(String contactLastName) {
        ContactLastName = contactLastName;
    }

    @SerializedName("LastName")

    private String LastName;

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

    @SerializedName("WorkPhone")
    private String WorkPhone;

    @SerializedName("MobilePhone")
    private String MobilePhone;

    @SerializedName("HomePhone")
    private String HomePhone;

    @SerializedName("EmailAddress")
    private String EmailAddress;

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
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

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
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

    public String getWorkPhone() {
        return WorkPhone;
    }

    public void setWorkPhone(String workPhone) {
        WorkPhone = workPhone;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return HomePhone;
    }

    public void setHomePhone(String homePhone) {
        HomePhone = homePhone;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }
}
