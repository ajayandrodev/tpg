package com.cattechnologies.tpg.model.dashboardModel;

/**
 * Created by admin on 11/9/2017.
 */

public class DashboardProfileData {
    /*"profile_data": {
           "ACCOUNT_LOGIN_ID": "13715",
                   "EMAIL_ADDRESS": "ER525711",
                   "LOGIN_NAME": "POLLOCK62",
                   "EFIN": "525711"
       },


   */
    private String profile_data;
    private String MasterEFIN;
    private String ACCOUNT_LOGIN_ID;
    private String EFIN;
    private String EMAIL_ADDRESS;
    private String LOGIN_NAME;

    public String getProfile_data() {
        return profile_data;
    }

    public void setProfile_data(String profile_data) {
        this.profile_data = profile_data;
    }

    public String getMasterEFIN() {
        return MasterEFIN;
    }

    public void setMasterEFIN(String masterEFIN) {
        MasterEFIN = masterEFIN;
    }

    public String getACCOUNT_LOGIN_ID() {
        return ACCOUNT_LOGIN_ID;
    }

    public void setACCOUNT_LOGIN_ID(String ACCOUNT_LOGIN_ID) {
        this.ACCOUNT_LOGIN_ID = ACCOUNT_LOGIN_ID;
    }

    public String getEFIN() {
        return EFIN;
    }

    public void setEFIN(String EFIN) {
        this.EFIN = EFIN;
    }

    public String getEMAIL_ADDRESS() {
        return EMAIL_ADDRESS;
    }

    public void setEMAIL_ADDRESS(String EMAIL_ADDRESS) {
        this.EMAIL_ADDRESS = EMAIL_ADDRESS;
    }

    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public void setLOGIN_NAME(String LOGIN_NAME) {
        this.LOGIN_NAME = LOGIN_NAME;
    }
}
