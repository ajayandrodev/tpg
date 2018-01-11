package com.cattechnologies.tpg.model.accountDisbursementModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 10/23/2017.
 */

public class AccountInfo {

 /*
     "account_info": [
    {
        "BankName": "WELLS FARGO",
            "NameOnAccount": "MISTER MANAGER",
            "RTN": "051400345",
            "DAN": "203066472",
            "AcctType": "C"
    }
    ]*/

    @SerializedName("BankName")
    private String BankName;

    @SerializedName("NameOnAccount")
    private String NameOnAccount;

    @SerializedName("RTN")
    private String RTN;

    @SerializedName("DAN")
    private String DAN;

    @SerializedName("AcctType")
    private String AcctType;

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getNameOnAccount() {
        return NameOnAccount;
    }

    public void setNameOnAccount(String nameOnAccount) {
        NameOnAccount = nameOnAccount;
    }

    public String getRTN() {
        return RTN;
    }

    public void setRTN(String RTN) {
        this.RTN = RTN;
    }

    public String getDAN() {
        return DAN;
    }

    public void setDAN(String DAN) {
        this.DAN = DAN;
    }

    public String getAcctType() {
        return AcctType;
    }

    public void setAcctType(String acctType) {
        AcctType = acctType;
    }
}
