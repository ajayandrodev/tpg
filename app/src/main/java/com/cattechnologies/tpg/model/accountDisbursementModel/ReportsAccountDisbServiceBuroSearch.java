package com.cattechnologies.tpg.model.accountDisbursementModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.cattechnologies.tpg.model.accountDisbursementModel.ReportAccountDisbServiceBuroSearchNew;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ajay on 2/2/2018.
 */

public class ReportsAccountDisbServiceBuroSearch implements Parcelable {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;


    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("DisbursmentReport_data")
    private List<ReportAccountDisbServiceBuroSearchNew> DisbursmentReport_data;
    public ReportsAccountDisbServiceBuroSearch(){

    }

    protected ReportsAccountDisbServiceBuroSearch(Parcel in) {
        status = in.readString();
        message = in.readString();
        page = in.readString();
        TotalNoofPages = in.readString();
        DisbursmentReport_data = in.createTypedArrayList(ReportAccountDisbServiceBuroSearchNew.CREATOR);
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

    public static final Creator<ReportsAccountDisbServiceBuroSearch> CREATOR = new Creator<ReportsAccountDisbServiceBuroSearch>() {
        @Override
        public ReportsAccountDisbServiceBuroSearch createFromParcel(Parcel in) {
            return new ReportsAccountDisbServiceBuroSearch(in);
        }

        @Override
        public ReportsAccountDisbServiceBuroSearch[] newArray(int size) {
            return new ReportsAccountDisbServiceBuroSearch[size];
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

    public List<ReportAccountDisbServiceBuroSearchNew> getDisbursmentReport_data() {
        return DisbursmentReport_data;
    }

    public void setDisbursmentReport_data(List<ReportAccountDisbServiceBuroSearchNew> disbursmentReport_data) {
        DisbursmentReport_data = disbursmentReport_data;
    }
}
