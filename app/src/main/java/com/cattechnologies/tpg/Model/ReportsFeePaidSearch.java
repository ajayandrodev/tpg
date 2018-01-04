package com.cattechnologies.tpg.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 1/4/2018.
 */

public class ReportsFeePaidSearch implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;


    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("FeeReport_data")
    private List<ReportsFeePaidSearchNew> FeeReport_data;

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

    public List<ReportsFeePaidSearchNew> getFeeReport_data() {
        return FeeReport_data;
    }

    public void setFeeReport_data(List<ReportsFeePaidSearchNew> feeReport_data) {
        FeeReport_data = feeReport_data;
    }

    public ReportsFeePaidSearch() {

    }

    protected ReportsFeePaidSearch(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
        FeeReport_data = in.createTypedArrayList(ReportsFeePaidSearchNew.CREATOR);
    }

    public static final Creator<ReportsFeePaidSearch> CREATOR = new Creator<ReportsFeePaidSearch>() {
        @Override
        public ReportsFeePaidSearch createFromParcel(Parcel in) {
            return new ReportsFeePaidSearch(in);
        }

        @Override
        public ReportsFeePaidSearch[] newArray(int size) {
            return new ReportsFeePaidSearch[size];
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
        parcel.writeTypedList(FeeReport_data);
    }
}
