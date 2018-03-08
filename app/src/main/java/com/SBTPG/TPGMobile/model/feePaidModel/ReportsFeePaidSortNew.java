package com.SBTPG.TPGMobile.model.feePaidModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 1/4/2018.
 */

public class ReportsFeePaidSortNew implements Parcelable {

    @SerializedName("Id")
    private String Id;

    @SerializedName("recordcreatedate")
    private String recordcreatedate;

    @SerializedName("IrsAcknowledgementDate")
    private String IrsAcknowledgementDate;

    @SerializedName("IrsFundingDate")
    private String IrsFundingDate;

    @SerializedName("StateFundingDate")
    private String StateFundingDate;

    @SerializedName("masterefin")
    private String masterefin;

    @SerializedName("Efin")
    private String Efin;

    @SerializedName("PrimaryFirstName")
    private String PrimaryFirstName;

    @SerializedName("PrimaryLastName")
    private String PrimaryLastName;

    @SerializedName("PrimarySsn")
    private String PrimarySinfo;

    @SerializedName("TransmitterEfFeesCollected")
    private String TransmitterEfFeesCollected;

    @SerializedName("ServiceBureauFeeCollected")
    private String ServiceBureauFeeCollected;

    @SerializedName("SiteEfFeesCollected")
    private String SiteEfFeesCollected;

    @SerializedName("PreparationFeesCollected")
    private String PreparationFeesCollected;

    @SerializedName("DocumentStorageFeesCollected")
    private String DocumentStorageFeesCollected;

    @SerializedName("otherfees")
    private String otherfees;

    @SerializedName("ToTalSiteFeeCollected")
    private String ToTalSiteFeeCollected;
    @SerializedName("TransmitterName")
    private String TransmitterName;

    @SerializedName("DisbursementType")
    private String DisbursementType;

    @SerializedName("SBFeesDue")
    private String SBFeesDue;

    @SerializedName("Row")
    private String Row;

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

    public String getIrsAcknowledgementDate() {
        return IrsAcknowledgementDate;
    }

    public void setIrsAcknowledgementDate(String irsAcknowledgementDate) {
        IrsAcknowledgementDate = irsAcknowledgementDate;
    }

    public String getIrsFundingDate() {
        return IrsFundingDate;
    }

    public void setIrsFundingDate(String irsFundingDate) {
        IrsFundingDate = irsFundingDate;
    }

    public String getStateFundingDate() {
        return StateFundingDate;
    }

    public void setStateFundingDate(String stateFundingDate) {
        StateFundingDate = stateFundingDate;
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

    public String getPrimarySinfo() {
        return PrimarySinfo;
    }

    public void setPrimarySinfo(String PrimarySinfo) {
        PrimarySinfo = PrimarySinfo;
    }

    public String getTransmitterEfFeesCollected() {
        return TransmitterEfFeesCollected;
    }

    public void setTransmitterEfFeesCollected(String transmitterEfFeesCollected) {
        TransmitterEfFeesCollected = transmitterEfFeesCollected;
    }

    public String getServiceBureauFeeCollected() {
        return ServiceBureauFeeCollected;
    }

    public void setServiceBureauFeeCollected(String serviceBureauFeeCollected) {
        ServiceBureauFeeCollected = serviceBureauFeeCollected;
    }

    public String getSiteEfFeesCollected() {
        return SiteEfFeesCollected;
    }

    public void setSiteEfFeesCollected(String siteEfFeesCollected) {
        SiteEfFeesCollected = siteEfFeesCollected;
    }

    public String getPreparationFeesCollected() {
        return PreparationFeesCollected;
    }

    public void setPreparationFeesCollected(String preparationFeesCollected) {
        PreparationFeesCollected = preparationFeesCollected;
    }

    public String getDocumentStorageFeesCollected() {
        return DocumentStorageFeesCollected;
    }

    public void setDocumentStorageFeesCollected(String documentStorageFeesCollected) {
        DocumentStorageFeesCollected = documentStorageFeesCollected;
    }

    public String getOtherfees() {
        return otherfees;
    }

    public void setOtherfees(String otherfees) {
        this.otherfees = otherfees;
    }

    public String getToTalSiteFeeCollected() {
        return ToTalSiteFeeCollected;
    }

    public void setToTalSiteFeeCollected(String toTalSiteFeeCollected) {
        ToTalSiteFeeCollected = toTalSiteFeeCollected;
    }

    public String getTransmitterName() {
        return TransmitterName;
    }

    public void setTransmitterName(String transmitterName) {
        TransmitterName = transmitterName;
    }

    public String getDisbursementType() {
        return DisbursementType;
    }

    public void setDisbursementType(String disbursementType) {
        DisbursementType = disbursementType;
    }

    public String getSBFeesDue() {
        return SBFeesDue;
    }

    public void setSBFeesDue(String SBFeesDue) {
        this.SBFeesDue = SBFeesDue;
    }

    public String getRow() {
        return Row;
    }

    public void setRow(String row) {
        Row = row;
    }

    public ReportsFeePaidSortNew() {

    }

    protected ReportsFeePaidSortNew(Parcel in) {
        Id = in.readString();
        recordcreatedate = in.readString();
        IrsAcknowledgementDate = in.readString();
        IrsFundingDate = in.readString();
        StateFundingDate = in.readString();
        masterefin = in.readString();
        Efin = in.readString();
        PrimaryFirstName = in.readString();
        PrimaryLastName = in.readString();
        PrimarySinfo = in.readString();
        TransmitterEfFeesCollected = in.readString();
        ServiceBureauFeeCollected = in.readString();
        SiteEfFeesCollected = in.readString();
        PreparationFeesCollected = in.readString();
        DocumentStorageFeesCollected = in.readString();
        otherfees = in.readString();
        ToTalSiteFeeCollected = in.readString();
        TransmitterName = in.readString();
        DisbursementType = in.readString();
        SBFeesDue = in.readString();
        Row = in.readString();
    }

    public static final Creator<ReportsFeePaidSortNew> CREATOR = new Creator<ReportsFeePaidSortNew>() {
        @Override
        public ReportsFeePaidSortNew createFromParcel(Parcel in) {
            return new ReportsFeePaidSortNew(in);
        }

        @Override
        public ReportsFeePaidSortNew[] newArray(int size) {
            return new ReportsFeePaidSortNew[size];
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
        parcel.writeString(IrsAcknowledgementDate);
        parcel.writeString(IrsFundingDate);
        parcel.writeString(StateFundingDate);
        parcel.writeString(masterefin);
        parcel.writeString(Efin);
        parcel.writeString(PrimaryFirstName);
        parcel.writeString(PrimaryLastName);
        parcel.writeString(PrimarySinfo);
        parcel.writeString(TransmitterEfFeesCollected);
        parcel.writeString(ServiceBureauFeeCollected);
        parcel.writeString(SiteEfFeesCollected);
        parcel.writeString(PreparationFeesCollected);
        parcel.writeString(DocumentStorageFeesCollected);
        parcel.writeString(otherfees);
        parcel.writeString(ToTalSiteFeeCollected);
        parcel.writeString(TransmitterName);
        parcel.writeString(DisbursementType);
        parcel.writeString(SBFeesDue);
        parcel.writeString(Row);
    }
}
