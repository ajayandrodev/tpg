package com.SBTPG.TPGMobile.model.accountDisbursementModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ajay on 1/4/2018.
 */

public class ReportsAccountDisbSearch implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;


    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("DisbursmentReport_data")
    private List<ReportAccountDisbSearchNew> DisbursmentReport_data;

    public ReportsAccountDisbSearch() {

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

    public List<ReportAccountDisbSearchNew> getDisbursmentReport_data() {
        return DisbursmentReport_data;
    }

    public void setDisbursmentReport_data(List<ReportAccountDisbSearchNew> disbursmentReport_data) {
        DisbursmentReport_data = disbursmentReport_data;
    }

    protected ReportsAccountDisbSearch(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
        DisbursmentReport_data = in.createTypedArrayList(ReportAccountDisbSearchNew.CREATOR);
    }

    public static final Creator<ReportsAccountDisbSearch> CREATOR = new Creator<ReportsAccountDisbSearch>() {
        @Override
        public ReportsAccountDisbSearch createFromParcel(Parcel in) {
            return new ReportsAccountDisbSearch(in);
        }

        @Override
        public ReportsAccountDisbSearch[] newArray(int size) {
            return new ReportsAccountDisbSearch[size];
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
        parcel.writeTypedList(DisbursmentReport_data);
    }
}