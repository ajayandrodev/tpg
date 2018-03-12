package com.SBTPG.TPGMobile.model.feePaidModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ajay on 1/10/2018.
 */

public class ReportsPerticularFeePaidSearch implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;


    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("FeeReport_data")
    private List<ReportsPerticularFeePaidSearchNew> FeeReport_data;

    public ReportsPerticularFeePaidSearch() {

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

    public List<ReportsPerticularFeePaidSearchNew> getFeeReport_data() {
        return FeeReport_data;
    }

    public void setFeeReport_data(List<ReportsPerticularFeePaidSearchNew> feeReport_data) {
        FeeReport_data = feeReport_data;
    }

    protected ReportsPerticularFeePaidSearch(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(message);
        dest.writeString(page);
        dest.writeString(TotalNoofPages);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReportsPerticularFeePaidSearch> CREATOR = new Creator<ReportsPerticularFeePaidSearch>() {
        @Override
        public ReportsPerticularFeePaidSearch createFromParcel(Parcel in) {
            return new ReportsPerticularFeePaidSearch(in);
        }

        @Override
        public ReportsPerticularFeePaidSearch[] newArray(int size) {
            return new ReportsPerticularFeePaidSearch[size];
        }
    };
}