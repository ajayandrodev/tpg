package com.cattechnologies.tpg.model.dashboardModel;

/**
 * Created by admin on 11/9/2017.
 */

public class DashboardResentTransactionsData {
    private String recent_transactions;
    /*
            Status;
        */
    // 2016-01-25 11:01:20.930
    private String LastUpdate;
   // private String SystemYear;
   // private String DisburseDate;
    private String Amount;
   /* private String RecordCreate;
    private String DashboardId;
    private String RecordNumber;
    private String Id;*/

    public String getRecent_transactions() {
        return recent_transactions;
    }

    public void setRecent_transactions(String recent_transactions) {
        this.recent_transactions = recent_transactions;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
