package com.cattechnologies.tpg.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 10/23/2017.
 */

public class EnrolInfo {
    /*
      "owner_info": {
            "Street": "123 ADDRESS",
                    "street2": null,
                    "City": "SAN DIEGO",
                    "State": "SA",
                    "Zipcode": "92117",
                    "FirstName": "JOHN",
                    "LastName": "PUTNAM",
                    "EmailAddress": "0084849955485",
                    "WorkPhone": null,
                    "HomePhone": "8586038067",
                    "MobilePhone": null
        },*/

    /* "owner_info": [
    {
        "ContactFirstName": "MIKE",
            "ContactLastName": "NEWMAN",
            "EmailAddress": "QA@SBTPG.COM",
            "FaxPhone": "8582222222",
            "OfficePhone": "8581111111",
            "Street": "11085 N TORREY PINES",
            "street2": null,
            "City": "LA JOLLA",
            "State": "CA",
            "Zipcode": "92037"
    }*/
    @SerializedName("FirstName")
    private String FirstName;

    @SerializedName("FaxPhone")
    private String FaxPhone;

    @SerializedName("OfficePhone")
    private String OfficePhone;

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
