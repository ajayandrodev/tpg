package com.SBTPG.TPGMobile.model.eroDepositModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 1/11/2018.
 */

public class ReportsEroDepositNew implements Parcelable {


    @SerializedName("Id")
    private String Id;

    @SerializedName("recordcreatedate")
    private String recordcreatedate;

    @SerializedName("System_Year")
    private String System_Year;

    @SerializedName("masterefin")
    private String masterefin;

    @SerializedName("Reverseddate")
    private String Reverseddate;

    @SerializedName("DAN")
    private String DAN;


    @SerializedName("Efin")
    private String Efin;

    @SerializedName("PrimaryFirstName")
    private String PrimaryFirstName;

    @SerializedName("PrimaryLastName")
    private String PrimaryLastName;

    @SerializedName("PrimarySsn")
    private String PrimarySinfo;

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

    public String getDAN() {
        return DAN;
    }

    public void setDAN(String DAN) {
        this.DAN = DAN;
    }

    public String getReverseddate() {
        return Reverseddate;
    }

    public void setReverseddate(String reverseddate) {
        Reverseddate = reverseddate;
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

    public void setPrimarySinfo(String PrimarySinfo) {
        PrimarySinfo = PrimarySinfo;
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

    public String getPrimarySinfo() {
        return PrimarySinfo;
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
        dest.writeString(this.Reverseddate);
        dest.writeString(this.DAN);
        dest.writeString(this.Efin);
        dest.writeString(this.PrimaryFirstName);
        dest.writeString(this.PrimaryLastName);
        dest.writeString(this.PrimarySinfo);
        dest.writeString(this.DepositType);
        dest.writeString(this.ProductType);
        dest.writeString(this.DepositAmount);
        dest.writeString(this.depositdate);
        dest.writeString(this.sadjtype);
        dest.writeString(this.Depositor);
    }

    protected ReportsEroDepositNew(Parcel in) {
        this.Id = in.readString();
        this.recordcreatedate = in.readString();
        this.System_Year = in.readString();
        this.masterefin = in.readString();
        this.Reverseddate = in.readString();
        this.DAN = in.readString();
        this.Efin = in.readString();
        this.PrimaryFirstName = in.readString();
        this.PrimaryLastName = in.readString();
        this.PrimarySinfo = in.readString();
        this.DepositType = in.readString();
        this.ProductType = in.readString();
        this.DepositAmount = in.readString();
        this.depositdate = in.readString();
        this.sadjtype = in.readString();
        this.Depositor = in.readString();
    }

    public static final Creator<ReportsEroDepositNew> CREATOR = new Creator<ReportsEroDepositNew>() {
        @Override
        public ReportsEroDepositNew createFromParcel(Parcel source) {
            return new ReportsEroDepositNew(source);
        }

        @Override
        public ReportsEroDepositNew[] newArray(int size) {
            return new ReportsEroDepositNew[size];
        }
    };
}