package com.cattechnologies.tpg.model.eroDepositModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ajay on 2/1/2018.
 */

public class ReportsEroDepositsServiceBuroSort implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;

    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("Service_Bureau_EroReport_data")
    private List<ReportsEroDepositsServiceBuroSortNew> Service_Bureau_EroReport_data;

    public ReportsEroDepositsServiceBuroSort() {
    }


    protected ReportsEroDepositsServiceBuroSort(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
        Service_Bureau_EroReport_data = in.createTypedArrayList(ReportsEroDepositsServiceBuroSortNew.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(message);
        dest.writeString(page);
        dest.writeString(TotalNoofPages);
        dest.writeTypedList(Service_Bureau_EroReport_data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReportsEroDepositsServiceBuroSort> CREATOR = new Creator<ReportsEroDepositsServiceBuroSort>() {
        @Override
        public ReportsEroDepositsServiceBuroSort createFromParcel(Parcel in) {
            return new ReportsEroDepositsServiceBuroSort(in);
        }

        @Override
        public ReportsEroDepositsServiceBuroSort[] newArray(int size) {
            return new ReportsEroDepositsServiceBuroSort[size];
        }
    };

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

    public List<ReportsEroDepositsServiceBuroSortNew> getService_Bureau_EroReport_data() {
        return Service_Bureau_EroReport_data;
    }

    public void setService_Bureau_EroReport_data(List<ReportsEroDepositsServiceBuroSortNew> service_Bureau_EroReport_data) {
        Service_Bureau_EroReport_data = service_Bureau_EroReport_data;
    }
}
