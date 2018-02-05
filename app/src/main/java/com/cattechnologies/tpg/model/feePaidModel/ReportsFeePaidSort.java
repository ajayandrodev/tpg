package com.cattechnologies.tpg.model.feePaidModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 1/4/2018.
 */

public class ReportsFeePaidSort implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;


    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("FeeReport_data")
    private List<ReportsFeePaidSortNew> FeeReport_data;

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

    public List<ReportsFeePaidSortNew> getFeeReport_data() {
        return FeeReport_data;
    }

    public void setFeeReport_data(List<ReportsFeePaidSortNew> feeReport_data) {
        FeeReport_data = feeReport_data;
    }

    public ReportsFeePaidSort() {

    }

    protected ReportsFeePaidSort(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
    }

    public static final Creator<ReportsFeePaidSort> CREATOR = new Creator<ReportsFeePaidSort>() {
        @Override
        public ReportsFeePaidSort createFromParcel(Parcel in) {
            return new ReportsFeePaidSort(in);
        }

        @Override
        public ReportsFeePaidSort[] newArray(int size) {
            return new ReportsFeePaidSort[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(status);
        parcel.writeString(message);
        parcel.writeString(page);
        parcel.writeString(TotalNoofPages);
    }
}
