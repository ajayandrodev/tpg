package com.cattechnologies.tpg.model.accountDisbursementModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 2/2/2018.
 */

public class ReportAccountDisbServiceBuroSearchNew implements Parcelable {
    @SerializedName("Id")
    private String Id;

    @SerializedName("SYSTEM_YEAR")
    private String SYSTEM_YEAR;

    @SerializedName("masterefin")
    private String masterefin;


    @SerializedName("Efin")
    private String Efin;


    @SerializedName("PrimaryFirstName")
    private String PrimaryFirstName;

    @SerializedName("PrimaryLastName")
    private String PrimaryLastName;

    @SerializedName("PrimarySsn")
    private String PrimarySid;

    @SerializedName("ProductType")
    private String ProductType;

    @SerializedName("expecteddepdate")
    private String expecteddepdate;

    @SerializedName("ExpectedRefund")
    private String ExpectedRefund;

    @SerializedName("DisbursementDate")
    private String DisbursementDate;

    @SerializedName("disbursmentamount")
    private String disbursmentamount;

    @SerializedName("DisbType")
    private String DisbType;

    protected ReportAccountDisbServiceBuroSearchNew(Parcel in) {
        Id = in.readString();
        SYSTEM_YEAR = in.readString();
        masterefin = in.readString();
        Efin = in.readString();
        PrimaryFirstName = in.readString();
        PrimaryLastName = in.readString();
        PrimarySid = in.readString();
        ProductType = in.readString();
        expecteddepdate = in.readString();
        ExpectedRefund = in.readString();
        DisbursementDate = in.readString();
        disbursmentamount = in.readString();
        DisbType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(SYSTEM_YEAR);
        dest.writeString(masterefin);
        dest.writeString(Efin);
        dest.writeString(PrimaryFirstName);
        dest.writeString(PrimaryLastName);
        dest.writeString(PrimarySid);
        dest.writeString(ProductType);
        dest.writeString(expecteddepdate);
        dest.writeString(ExpectedRefund);
        dest.writeString(DisbursementDate);
        dest.writeString(disbursmentamount);
        dest.writeString(DisbType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReportAccountDisbServiceBuroSearchNew> CREATOR = new Creator<ReportAccountDisbServiceBuroSearchNew>() {
        @Override
        public ReportAccountDisbServiceBuroSearchNew createFromParcel(Parcel in) {
            return new ReportAccountDisbServiceBuroSearchNew(in);
        }

        @Override
        public ReportAccountDisbServiceBuroSearchNew[] newArray(int size) {
            return new ReportAccountDisbServiceBuroSearchNew[size];
        }
    };

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSYSTEM_YEAR() {
        return SYSTEM_YEAR;
    }

    public void setSYSTEM_YEAR(String SYSTEM_YEAR) {
        this.SYSTEM_YEAR = SYSTEM_YEAR;
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

    public String getPrimarySid() {
        return PrimarySid;
    }

    public void setPrimarySid(String PrimarySid) {
        PrimarySid = PrimarySid;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getExpecteddepdate() {
        return expecteddepdate;
    }

    public void setExpecteddepdate(String expecteddepdate) {
        this.expecteddepdate = expecteddepdate;
    }

    public String getExpectedRefund() {
        return ExpectedRefund;
    }

    public void setExpectedRefund(String expectedRefund) {
        ExpectedRefund = expectedRefund;
    }

    public String getDisbursementDate() {
        return DisbursementDate;
    }

    public void setDisbursementDate(String disbursementDate) {
        DisbursementDate = disbursementDate;
    }

    public String getDisbursmentamount() {
        return disbursmentamount;
    }

    public void setDisbursmentamount(String disbursmentamount) {
        this.disbursmentamount = disbursmentamount;
    }

    public String getDisbType() {
        return DisbType;
    }

    public void setDisbType(String disbType) {
        DisbType = disbType;
    }

    public ReportAccountDisbServiceBuroSearchNew() {

    }
}
