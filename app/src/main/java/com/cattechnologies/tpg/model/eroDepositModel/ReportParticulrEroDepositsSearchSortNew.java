package com.cattechnologies.tpg.model.eroDepositModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 1/9/2018.
 */

public class ReportParticulrEroDepositsSearchSortNew implements Parcelable {

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

    protected ReportParticulrEroDepositsSearchSortNew(Parcel in) {
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(recordcreatedate);
        dest.writeString(System_Year);
        dest.writeString(masterefin);
        dest.writeString(Efin);
        dest.writeString(PrimaryFirstName);
        dest.writeString(PrimaryLastName);
        dest.writeString(PrimarySsn);
        dest.writeString(DepositType);
        dest.writeString(ProductType);
        dest.writeString(DepositAmount);
        dest.writeString(depositdate);
        dest.writeString(sadjtype);
        dest.writeString(Depositor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReportParticulrEroDepositsSearchSortNew> CREATOR = new Creator<ReportParticulrEroDepositsSearchSortNew>() {
        @Override
        public ReportParticulrEroDepositsSearchSortNew createFromParcel(Parcel in) {
            return new ReportParticulrEroDepositsSearchSortNew(in);
        }

        @Override
        public ReportParticulrEroDepositsSearchSortNew[] newArray(int size) {
            return new ReportParticulrEroDepositsSearchSortNew[size];
        }
    };
}

