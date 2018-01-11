package com.cattechnologies.tpg.model;

/**
 * Created by admin on 10/16/2017.
 */

public class Reports {


    String userData;
    String costData;
    String accountData;
    String detailsData;
    String dateData;

    public Reports() {

    }

    public Reports(String userData, String costData, String accountData,String dateData) {
        this.userData = userData;
        this.costData = costData;
        this.accountData = accountData;
        this.dateData = dateData;

    }
    public Reports(String userData, String costData, String accountData, String detailsData, String dateData) {
        this.userData = userData;
        this.costData = costData;
        this.accountData = accountData;
        this.detailsData = detailsData;
        this.dateData = dateData;

    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getCostData() {
        return costData;
    }

    public void setCostData(String costData) {
        this.costData = costData;
    }

    public String getAccountData() {
        return accountData;
    }

    public void setAccountData(String accountData) {
        this.accountData = accountData;
    }

    public String getDetailsData() {
        return detailsData;
    }

    public void setDetailsData(String detailsData) {
        this.detailsData = detailsData;
    }

    public String getDateData() {
        return dateData;
    }

    public void setDateData(String dateData) {
        this.dateData = dateData;
    }


}
