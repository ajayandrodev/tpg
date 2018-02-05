package com.cattechnologies.tpg.model.feePaidModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2/1/2018.
 */

public class ReportsFeePaidSearchServiceBuro implements Parcelable {
    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;

    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("ServiceBureau_FeePaid_Report_data")
    private List<ReportsFeePaidSearchServiceBuroNew> ServiceBureau_FeePaid_Report_data;

    public ReportsFeePaidSearchServiceBuro() {

    }

    protected ReportsFeePaidSearchServiceBuro(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
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

    public static final Creator<ReportsFeePaidSearchServiceBuro> CREATOR = new Creator<ReportsFeePaidSearchServiceBuro>() {
        @Override
        public ReportsFeePaidSearchServiceBuro createFromParcel(Parcel in) {
            return new ReportsFeePaidSearchServiceBuro(in);
        }

        @Override
        public ReportsFeePaidSearchServiceBuro[] newArray(int size) {
            return new ReportsFeePaidSearchServiceBuro[size];
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

    public List<ReportsFeePaidSearchServiceBuroNew> getServiceBureau_FeePaid_Report_data() {
        return ServiceBureau_FeePaid_Report_data;
    }

    public void setServiceBureau_FeePaid_Report_data(List<ReportsFeePaidSearchServiceBuroNew> serviceBureau_FeePaid_Report_data) {
        ServiceBureau_FeePaid_Report_data = serviceBureau_FeePaid_Report_data;
    }
}
