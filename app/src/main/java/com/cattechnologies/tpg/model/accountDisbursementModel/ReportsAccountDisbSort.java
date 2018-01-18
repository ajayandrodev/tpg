package com.cattechnologies.tpg.model.accountDisbursementModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 1/4/2018.
 */

public class ReportsAccountDisbSort implements Parcelable{

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;


    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("EroReport_data")
    private List<ReportsAccountDisbSortNew> EroReport_data;


    public ReportsAccountDisbSort(){

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

    public List<ReportsAccountDisbSortNew> getEroReport_data() {
        return EroReport_data;
    }

    public void setEroReport_data(List<ReportsAccountDisbSortNew> eroReport_data) {
        EroReport_data = eroReport_data;
    }

    protected ReportsAccountDisbSort(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
        EroReport_data = in.createTypedArrayList(ReportsAccountDisbSortNew.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(message);
        dest.writeString(page);
        dest.writeString(TotalNoofPages);
        dest.writeTypedList(EroReport_data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReportsAccountDisbSort> CREATOR = new Creator<ReportsAccountDisbSort>() {
        @Override
        public ReportsAccountDisbSort createFromParcel(Parcel in) {
            return new ReportsAccountDisbSort(in);
        }

        @Override
        public ReportsAccountDisbSort[] newArray(int size) {
            return new ReportsAccountDisbSort[size];
        }
    };
}
