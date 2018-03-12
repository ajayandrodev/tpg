package com.SBTPG.TPGMobile.model.eroDepositModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ajay on 1/10/2018.
 */

public class ReportsPerticularEroDepositsSearch implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;


    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("EroReport_data")
    private List<ReportsPerticularEroDepositsSearchNew> EroReport_data;

    public ReportsPerticularEroDepositsSearch() {

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

    public List<ReportsPerticularEroDepositsSearchNew> getEroReport_data() {
        return EroReport_data;
    }

    public void setEroReport_data(List<ReportsPerticularEroDepositsSearchNew> eroReport_data) {
        EroReport_data = eroReport_data;
    }

    protected ReportsPerticularEroDepositsSearch(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
        EroReport_data = in.createTypedArrayList(ReportsPerticularEroDepositsSearchNew.CREATOR);
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

    public static final Creator<ReportsPerticularEroDepositsSearch> CREATOR = new Creator<ReportsPerticularEroDepositsSearch>() {
        @Override
        public ReportsPerticularEroDepositsSearch createFromParcel(Parcel in) {
            return new ReportsPerticularEroDepositsSearch(in);
        }

        @Override
        public ReportsPerticularEroDepositsSearch[] newArray(int size) {
            return new ReportsPerticularEroDepositsSearch[size];
        }
    };
}