package com.cattechnologies.tpg.model.accountDisbursementModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.cattechnologies.tpg.model.accountDisbursementModel.ReportAccountDisbServiceBuroNew;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2/2/2018.
 */

public class ReportsAccountDisbServiceBuro implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;


    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("DisbursmentReport_data")
    private List<ReportAccountDisbServiceBuroNew> DisbursmentReport_data;

    public ReportsAccountDisbServiceBuro() {
    }


    protected ReportsAccountDisbServiceBuro(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
        DisbursmentReport_data = in.createTypedArrayList(ReportAccountDisbServiceBuroNew.CREATOR);
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

    public static final Creator<ReportsAccountDisbServiceBuro> CREATOR = new Creator<ReportsAccountDisbServiceBuro>() {
        @Override
        public ReportsAccountDisbServiceBuro createFromParcel(Parcel in) {
            return new ReportsAccountDisbServiceBuro(in);
        }

        @Override
        public ReportsAccountDisbServiceBuro[] newArray(int size) {
            return new ReportsAccountDisbServiceBuro[size];
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

    public List<ReportAccountDisbServiceBuroNew> getDisbursmentReport_data() {
        return DisbursmentReport_data;
    }

    public void setDisbursmentReport_data(List<ReportAccountDisbServiceBuroNew> disbursmentReport_data) {
        DisbursmentReport_data = disbursmentReport_data;
    }
}
