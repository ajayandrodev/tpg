package com.cattechnologies.tpg.model.eroDepositModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 1/4/2018.
 */

public class ReportsEroDepositsSortNew implements Parcelable {

    @SerializedName("Id")
    private String Id;

    @SerializedName("recordcreatedate")
    private String recordcreatedate;

    @SerializedName("System_Year")
    private String System_Year;

    @SerializedName("masterefin")
    private String masterefin;

    @SerializedName("DAN")
    private String DAN;

    @SerializedName("Reverseddate")
    private String Reverseddate;

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

    public String getReverseddate() {
        return Reverseddate;
    }

    public void setReverseddate(String reverseddate) {
        Reverseddate = reverseddate;
    }

    public String getDAN() {
        return DAN;
    }

    public void setDAN(String DAN) {
        this.DAN = DAN;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRecordcreatedate() {
        return recordcreatedate;
    }

    public void setRecordcreatedate(String recordcreatedate) {
        this.recordcreatedate = recordcreatedate;
    }

    public String getSystem_Year() {
        return System_Year;
    }

    public void setSystem_Year(String system_Year) {
        System_Year = system_Year;
    }

    public String getMasterefin() {
        return masterefin;
    }

    public void setMasterefin(String masterefin) {
        this.masterefin = masterefin;
    }

    public String getEfin() {
        return Efin;
    }

    public void setEfin(String efin) {
        Efin = efin;
    }

    public String getPrimaryFirstName() {
        return PrimaryFirstName;
    }

    public void setPrimaryFirstName(String primaryFirstName) {
        PrimaryFirstName = primaryFirstName;
    }

    public String getPrimaryLastName() {
        return PrimaryLastName;
    }

    public void setPrimaryLastName(String primaryLastName) {
        PrimaryLastName = primaryLastName;
    }

    public String getPrimarySsn() {
        return PrimarySsn;
    }

    public void setPrimarySsn(String primarySsn) {
        PrimarySsn = primarySsn;
    }

    public String getDepositType() {
        return DepositType;
    }

    public void setDepositType(String depositType) {
        DepositType = depositType;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getDepositAmount() {
        return DepositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        DepositAmount = depositAmount;
    }

    public String getDepositdate() {
        return depositdate;
    }

    public void setDepositdate(String depositdate) {
        this.depositdate = depositdate;
    }

    public String getSadjtype() {
        return sadjtype;
    }

    public void setSadjtype(String sadjtype) {
        this.sadjtype = sadjtype;
    }

    public String getDepositor() {
        return Depositor;
    }

    public void setDepositor(String depositor) {
        Depositor = depositor;
    }

    public ReportsEroDepositsSortNew() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Id);
        dest.writeString(this.recordcreatedate);
        dest.writeString(this.System_Year);
        dest.writeString(this.masterefin);
        dest.writeString(this.DAN);
        dest.writeString(this.Reverseddate);
        dest.writeString(this.Efin);
        dest.writeString(this.PrimaryFirstName);
        dest.writeString(this.PrimaryLastName);
        dest.writeString(this.PrimarySsn);
        dest.writeString(this.DepositType);
        dest.writeString(this.ProductType);
        dest.writeString(this.DepositAmount);
        dest.writeString(this.depositdate);
        dest.writeString(this.sadjtype);
        dest.writeString(this.Depositor);
    }

    protected ReportsEroDepositsSortNew(Parcel in) {
        this.Id = in.readString();
        this.recordcreatedate = in.readString();
        this.System_Year = in.readString();
        this.masterefin = in.readString();
        this.DAN = in.readString();
        this.Reverseddate = in.readString();
        this.Efin = in.readString();
        this.PrimaryFirstName = in.readString();
        this.PrimaryLastName = in.readString();
        this.PrimarySsn = in.readString();
        this.DepositType = in.readString();
        this.ProductType = in.readString();
        this.DepositAmount = in.readString();
        this.depositdate = in.readString();
        this.sadjtype = in.readString();
        this.Depositor = in.readString();
    }

    public static final Creator<ReportsEroDepositsSortNew> CREATOR = new Creator<ReportsEroDepositsSortNew>() {
        @Override
        public ReportsEroDepositsSortNew createFromParcel(Parcel source) {
            return new ReportsEroDepositsSortNew(source);
        }

        @Override
        public ReportsEroDepositsSortNew[] newArray(int size) {
            return new ReportsEroDepositsSortNew[size];
        }
    };
}