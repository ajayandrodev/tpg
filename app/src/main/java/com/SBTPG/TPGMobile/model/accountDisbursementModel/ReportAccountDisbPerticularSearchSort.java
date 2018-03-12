package com.SBTPG.TPGMobile.model.accountDisbursementModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ajay on 1/9/2018.
 */

public class ReportAccountDisbPerticularSearchSort implements Parcelable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;

    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("DisbursmentReport_data")
    private List<ReportParticulrAccountDisbSearchSortNew> DisbursmentReport_data;

    public ReportAccountDisbPerticularSearchSort() {

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

    public List<ReportParticulrAccountDisbSearchSortNew> getDisbursmentReport_data() {
        return DisbursmentReport_data;
    }

    public void setDisbursmentReport_data(List<ReportParticulrAccountDisbSearchSortNew> disbursmentReport_data) {
        DisbursmentReport_data = disbursmentReport_data;
    }

    protected ReportAccountDisbPerticularSearchSort(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
        DisbursmentReport_data = in.createTypedArrayList(ReportParticulrAccountDisbSearchSortNew.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(message);
        dest.writeString(page);
        dest.writeString(TotalNoofPages);
        dest.writeTypedList(DisbursmentReport_data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReportAccountDisbPerticularSearchSort> CREATOR = new Creator<ReportAccountDisbPerticularSearchSort>() {
        @Override
        public ReportAccountDisbPerticularSearchSort createFromParcel(Parcel in) {
            return new ReportAccountDisbPerticularSearchSort(in);
        }

        @Override
        public ReportAccountDisbPerticularSearchSort[] newArray(int size) {
            return new ReportAccountDisbPerticularSearchSort[size];
        }
    };
}