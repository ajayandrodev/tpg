package com.cattechnologies.tpg.model.accountDisbursementModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2/2/2018.
 */

public class ReportsAccountDisbServiceBuroSort {

    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("page")
    private String page;


    @SerializedName("Total No of Pages")
    private String TotalNoofPages;

    @SerializedName("DisbursmentReport_data")
    private List<ReportsAccountDisbServiceBuroSortNew> DisbursmentReport_data;

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

    public List<ReportsAccountDisbServiceBuroSortNew> getDisbursmentReport_data() {
        return DisbursmentReport_data;
    }

    public void setDisbursmentReport_data(List<ReportsAccountDisbServiceBuroSortNew> disbursmentReport_data) {
        DisbursmentReport_data = disbursmentReport_data;
    }
}
