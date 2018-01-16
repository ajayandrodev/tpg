package com.cattechnologies.tpg.model.eroDepositModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 1/11/2018.
 */

public class ReportsEroDeposit implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;

    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("EroReport_data")
    private List<ReportsEroDepositNew> EroReport_data;

    public ReportsEroDeposit() {

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

    public void setEroReport_data(List<ReportsEroDepositNew> eroReport_data) {
        EroReport_data = eroReport_data;
    }

    protected ReportsEroDeposit(Parcel in) {
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

    public List<ReportsEroDepositNew> getEroReport_data() {
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

    public static final Creator<ReportsEroDeposit> CREATOR = new Creator<ReportsEroDeposit>() {
        @Override
        public ReportsEroDeposit createFromParcel(Parcel in) {
            return new ReportsEroDeposit(in);
        }

        @Override
        public ReportsEroDeposit[] newArray(int size) {
            return new ReportsEroDeposit[size];
        }
    };
}
