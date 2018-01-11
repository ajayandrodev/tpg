package com.cattechnologies.tpg.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 1/11/2018.
 */

public class ReportsEroDepositNew implements Parcelable {

 /* "Id": 132,
          "System_Year": 2018,
          "recordcreatedate": "20181508",
          "masterefin": "612354",
          "Efin": "060020",
          "PrimaryFirstName": "MOHSIN",
          "PrimaryLastName": "majage",
          "PrimarySsn": "XXX-XX-1031",
          "DepositType": "Unknown",
          "ProductType": "RT",
          "DepositAmount": 12,
          "depositdate": "20170202",
          "sadjtype": "I",
          "Depositor": "IRS"*/

    @SerializedName("Id")
    private String Id;

    @SerializedName("recordcreatedate")
    private String recordcreatedate;

    @SerializedName("System_Year")
    private String System_Year;

    @SerializedName("masterefin")
    private String masterefin;

    @SerializedName("Efin")
    private String Efin;

    @SerializedName("PrimaryFirstName")
    private String PrimaryFirstName;

    @SerializedName("PrimaryLastName")
    private String PrimaryLastName;

    @SerializedName("PrimarySsn")
    private String PrimarySsn;

    @SerializedName("DepositType")
    private String DepositType;

    @SerializedName("ProductType")
    private String ProductType;


    @SerializedName("DepositAmount")
    private String DepositAmount;

    @SerializedName("depositdate")
    private String depositdate;

    @SerializedName("sadjtype")
    private String sadjtype;

    @SerializedName("Depositor")
    private String Depositor;

    public void setId(String id) {
        Id = id;
    }

    public void setRecordcreatedate(String recordcreatedate) {
        this.recordcreatedate = recordcreatedate;
    }

    public void setSystem_Year(String system_Year) {
        System_Year = system_Year;
    }

    public void setMasterefin(String masterefin) {
        this.masterefin = masterefin;
    }

    public void setEfin(String efin) {
        Efin = efin;
    }

    public void setPrimaryFirstName(String primaryFirstName) {
        PrimaryFirstName = primaryFirstName;
    }

    public void setPrimaryLastName(String primaryLastName) {
        PrimaryLastName = primaryLastName;
    }

    public void setPrimarySsn(String primarySsn) {
        PrimarySsn = primarySsn;
    }

    public void setDepositType(String depositType) {
        DepositType = depositType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public void setDepositAmount(String depositAmount) {
        DepositAmount = depositAmount;
    }

    public void setDepositdate(String depositdate) {
        this.depositdate = depositdate;
    }

    public void setSadjtype(String sadjtype) {
        this.sadjtype = sadjtype;
    }

    public void setDepositor(String depositor) {
        Depositor = depositor;
    }

    public ReportsEroDepositNew() {

    }

    public String getId() {
        return Id;
    }

    public String getRecordcreatedate() {
        return recordcreatedate;
    }

    public String getSystem_Year() {
        return System_Year;
    }

    public String getMasterefin() {
        return masterefin;
    }

    public String getEfin() {
        return Efin;
    }

    public String getPrimaryFirstName() {
        return PrimaryFirstName;
    }

    public String getPrimaryLastName() {
        return PrimaryLastName;
    }

    public String getPrimarySsn() {
        return PrimarySsn;
    }

    public String getDepositType() {
        return DepositType;
    }

    public String getProductType() {
        return ProductType;
    }

    public String getDepositAmount() {
        return DepositAmount;
    }

    public String getDepositdate() {
        return depositdate;
    }

    public String getSadjtype() {
        return sadjtype;
    }

    public String getDepositor() {
        return Depositor;
    }

    protected ReportsEroDepositNew(Parcel in) {
        Id = in.readString();
        recordcreatedate = in.readString();
        System_Year = in.readString();
        masterefin = in.readString();
        Efin = in.readString();
        PrimaryFirstName = in.readString();
        PrimaryLastName = in.readString();
        PrimarySsn = in.readString();
        DepositType = in.readString();
        ProductType = in.readString();
        DepositAmount = in.readString();
        depositdate = in.readString();
        sadjtype = in.readString();
        Depositor = in.readString();
    }

    public static final Creator<ReportsEroDepositNew> CREATOR = new Creator<ReportsEroDepositNew>() {
        @Override
        public ReportsEroDepositNew createFromParcel(Parcel in) {
            return new ReportsEroDepositNew(in);
        }

        @Override
        public ReportsEroDepositNew[] newArray(int size) {
            return new ReportsEroDepositNew[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Id);
        parcel.writeString(recordcreatedate);
        parcel.writeString(System_Year);
        parcel.writeString(masterefin);
        parcel.writeString(Efin);
        parcel.writeString(PrimaryFirstName);
        parcel.writeString(PrimaryLastName);
        parcel.writeString(PrimarySsn);
        parcel.writeString(DepositType);
        parcel.writeString(ProductType);
        parcel.writeString(DepositAmount);
        parcel.writeString(depositdate);
        parcel.writeString(sadjtype);
        parcel.writeString(Depositor);
    }
}