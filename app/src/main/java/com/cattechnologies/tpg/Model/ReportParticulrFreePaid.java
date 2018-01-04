package com.cattechnologies.tpg.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 1/4/2018.
 */

public class ReportParticulrFreePaid implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;

    @SerializedName("Total No of Pages")
    private String TotalNoofPages;
    @SerializedName("FeeReport_data")
    private List<ReportParticulrFreePaidNew> FeeReport_data;

    public List<ReportParticulrFreePaidNew> getFeeReport_data() {
        return FeeReport_data;
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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotalNoofPages() {
        return TotalNoofPages;
    }

    public void setTotalNoofPages(String totalNoofPages) {
        TotalNoofPages = totalNoofPages;
    }

    public void setFeeReport_data(List<ReportParticulrFreePaidNew> feeReport_data) {
        FeeReport_data = feeReport_data;
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

    public ReportParticulrFreePaid() {
    }

    protected ReportParticulrFreePaid(Parcel in) {
        this.status = in.readString();
        this.message = in.readString();
        this.page = in.readString();
        this.TotalNoofPages = in.readString();
    }

    public static final Creator<ReportParticulrFreePaid> CREATOR = new Creator<ReportParticulrFreePaid>() {
        @Override
        public ReportParticulrFreePaid createFromParcel(Parcel source) {
            return new ReportParticulrFreePaid(source);
        }

        @Override
        public ReportParticulrFreePaid[] newArray(int size) {
            return new ReportParticulrFreePaid[size];
        }
    };
}