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

    @SerializedName("DisbursmentReport_data")
    private List<ReportsAccountDisbSortNew> DisbursmentReport_data;


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

    public List<ReportsAccountDisbSortNew> getDisbursmentReport_data() {
        return DisbursmentReport_data;
    }

    public void setDisbursmentReport_data(List<ReportsAccountDisbSortNew> disbursmentReport_data) {
        DisbursmentReport_data = disbursmentReport_data;
    }

    protected ReportsAccountDisbSort(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
        DisbursmentReport_data = in.createTypedArrayList(ReportsAccountDisbSortNew.CREATOR);
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
