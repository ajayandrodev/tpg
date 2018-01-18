package com.cattechnologies.tpg.model.accountDisbursementModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 1/11/2018.
 */

public class ReportsAccountDisb implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;

    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("EroReport_data")
    private List<ReportsAccountDisbNew> EroReport_data;

    public ReportsAccountDisb() {

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setTotalNoofPages(String totalNoofPages) {
        TotalNoofPages = totalNoofPages;
    }

    public void setEroReport_data(List<ReportsAccountDisbNew> eroReport_data) {
        EroReport_data = eroReport_data;
    }

    protected ReportsAccountDisb(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPage() {
        return page;
    }

    public String getTotalNoofPages() {
        return TotalNoofPages;
    }

    public List<ReportsAccountDisbNew> getEroReport_data() {
        return EroReport_data;
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

    public static final Creator<ReportsAccountDisb> CREATOR = new Creator<ReportsAccountDisb>() {
        @Override
        public ReportsAccountDisb createFromParcel(Parcel in) {
            return new ReportsAccountDisb(in);
        }

        @Override
        public ReportsAccountDisb[] newArray(int size) {
            return new ReportsAccountDisb[size];
        }
    };
}
