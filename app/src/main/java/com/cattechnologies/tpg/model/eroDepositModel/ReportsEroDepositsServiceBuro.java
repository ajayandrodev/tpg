package com.cattechnologies.tpg.model.eroDepositModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ajay on 2/1/2018.
 */

public class ReportsEroDepositsServiceBuro implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;

    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("Service_Bureau_EroReport_data")
    private List<ReportsEroDepositsServiceBuroNew> Service_Bureau_EroReport_data;


    public ReportsEroDepositsServiceBuro() {
    }

    protected ReportsEroDepositsServiceBuro(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
        Service_Bureau_EroReport_data = in.createTypedArrayList(ReportsEroDepositsServiceBuroNew.CREATOR);
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

    public static final Creator<ReportsEroDepositsServiceBuro> CREATOR = new Creator<ReportsEroDepositsServiceBuro>() {
        @Override
        public ReportsEroDepositsServiceBuro createFromParcel(Parcel in) {
            return new ReportsEroDepositsServiceBuro(in);
        }

        @Override
        public ReportsEroDepositsServiceBuro[] newArray(int size) {
            return new ReportsEroDepositsServiceBuro[size];
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

    public List<ReportsEroDepositsServiceBuroNew> getService_Bureau_EroReport_data() {
        return Service_Bureau_EroReport_data;
    }

    public void setService_Bureau_EroReport_data(List<ReportsEroDepositsServiceBuroNew> service_Bureau_EroReport_data) {
        Service_Bureau_EroReport_data = service_Bureau_EroReport_data;
    }
}
