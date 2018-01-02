package com.cattechnologies.tpg.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 12/26/2017.
 */

public class ReportsFeePaidNew implements Parcelable {

    @SerializedName("page")
    private String page;



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
    private String PrimarySsn;

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

    public String getOtherfees() {
        return otherfees;
    }

    public void setOtherfees(String otherfees) {
        this.otherfees = otherfees;
    }

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


    public ReportsFeePaidNew() {
    }

    public ReportsFeePaidNew(String PrimaryFirstName, String ToTalSiteFeeCollected, String PrimarySsn, String DisbursementType,
                             String recordcreatedate) {

        this.PrimaryFirstName = PrimaryFirstName;
        this.ToTalSiteFeeCollected = ToTalSiteFeeCollected;
        this.PrimarySsn = PrimarySsn;
        this.DisbursementType = DisbursementType;
        this.recordcreatedate = recordcreatedate;

    }
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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

    public String getPrimarySsn() {
        return PrimarySsn;
    }

    public void setPrimarySsn(String primarySsn) {
        PrimarySsn = primarySsn;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.page);
        dest.writeString(this.Id);
        dest.writeString(this.recordcreatedate);
        dest.writeString(this.IrsAcknowledgementDate);
        dest.writeString(this.IrsFundingDate);
        dest.writeString(this.StateFundingDate);
        dest.writeString(this.masterefin);
        dest.writeString(this.Efin);
        dest.writeString(this.PrimaryFirstName);
        dest.writeString(this.PrimaryLastName);
        dest.writeString(this.PrimarySsn);
        dest.writeString(this.TransmitterEfFeesCollected);
        dest.writeString(this.ServiceBureauFeeCollected);
        dest.writeString(this.SiteEfFeesCollected);
        dest.writeString(this.PreparationFeesCollected);
        dest.writeString(this.DocumentStorageFeesCollected);
        dest.writeString(this.otherfees);
        dest.writeString(this.ToTalSiteFeeCollected);
        dest.writeString(this.TransmitterName);
        dest.writeString(this.DisbursementType);
        dest.writeString(this.SBFeesDue);
        dest.writeString(this.Row);
    }

    protected ReportsFeePaidNew(Parcel in) {
        this.page = in.readString();
        this.Id = in.readString();
        this.recordcreatedate = in.readString();
        this.IrsAcknowledgementDate = in.readString();
        this.IrsFundingDate = in.readString();
        this.StateFundingDate = in.readString();
        this.masterefin = in.readString();
        this.Efin = in.readString();
        this.PrimaryFirstName = in.readString();
        this.PrimaryLastName = in.readString();
        this.PrimarySsn = in.readString();
        this.TransmitterEfFeesCollected = in.readString();
        this.ServiceBureauFeeCollected = in.readString();
        this.SiteEfFeesCollected = in.readString();
        this.PreparationFeesCollected = in.readString();
        this.DocumentStorageFeesCollected = in.readString();
        this.otherfees = in.readString();
        this.ToTalSiteFeeCollected = in.readString();
        this.TransmitterName = in.readString();
        this.DisbursementType = in.readString();
        this.SBFeesDue = in.readString();
        this.Row = in.readString();
    }

    public static final Creator<ReportsFeePaidNew> CREATOR = new Creator<ReportsFeePaidNew>() {
        @Override
        public ReportsFeePaidNew createFromParcel(Parcel source) {
            return new ReportsFeePaidNew(source);
        }

        @Override
        public ReportsFeePaidNew[] newArray(int size) {
            return new ReportsFeePaidNew[size];
        }
    };
}
