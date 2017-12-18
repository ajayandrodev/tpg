package com.cattechnologies.tpg.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 11/9/2017.
 */

public class LoginInfo {

    /*  app_uid:525711
      acc_type:sb_emp*/
    @SerializedName("app_uid")
    private String app_uid;

    @SerializedName("user_efin")
    private String user_efin;

    @SerializedName("acc_type")
    private String acc_type;

    @SerializedName("app_pswd")
    private String app_pswd;

    public String getApp_pswd() {
        return app_pswd;
    }

    public void setApp_pswd(String app_pswd) {
        this.app_pswd = app_pswd;
    }

    public String getUser_efin() {
        return user_efin;
    }

    public void setUser_efin(String user_efin) {
        this.user_efin = user_efin;
    }

    public String getApp_uid() {
        return app_uid;
    }

    public void setApp_uid(String app_uid) {
        this.app_uid = app_uid;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }
}
