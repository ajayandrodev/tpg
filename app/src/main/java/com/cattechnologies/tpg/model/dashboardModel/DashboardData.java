package com.cattechnologies.tpg.model.dashboardModel;

/**
 * Created by admin on 10/31/2017.
 */

public class DashboardData {

    String userData;
    String costData;

    public DashboardData(String userData, String costData) {
        this.userData = userData;
        this.costData = costData;
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
}
