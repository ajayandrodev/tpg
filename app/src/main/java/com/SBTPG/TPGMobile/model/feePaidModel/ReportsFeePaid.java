package com.SBTPG.TPGMobile.model.feePaidModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ajay on 12/22/2017.
 */

public class ReportsFeePaid implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;

    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("FeeReport_data")
    private List<ReportsFeePaidNew> FeeReport_data;

    public List<ReportsFeePaidNew> getFeeReport_data() {
        return FeeReport_data;
    }

    public void setFeeReport_data(List<ReportsFeePaidNew> feeReport_data) {
        FeeReport_data = feeReport_data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTotalNoofPages() {
        return TotalNoofPages;
    }

    public void setTotalNoofPages(String totalNoofPages) {
        TotalNoofPages = totalNoofPages;
    }

    public ReportsFeePaid() {
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.message);
        dest.writeString(this.page);
        dest.writeString(this.TotalNoofPages);
        dest.writeTypedList(this.FeeReport_data);
    }

    protected ReportsFeePaid(Parcel in) {
        this.status = in.readString();
        this.message = in.readString();
        this.page = in.readString();
        this.TotalNoofPages = in.readString();
        this.FeeReport_data = in.createTypedArrayList(ReportsFeePaidNew.CREATOR);
    }

    public static final Creator<ReportsFeePaid> CREATOR = new Creator<ReportsFeePaid>() {
        @Override
        public ReportsFeePaid createFromParcel(Parcel source) {
            return new ReportsFeePaid(source);
        }

        @Override
        public ReportsFeePaid[] newArray(int size) {
            return new ReportsFeePaid[size];
        }
    };
}