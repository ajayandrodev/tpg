package com.SBTPG.TPGMobile.model.profileModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 10/23/2017.
 */

public class AccountInfo implements Parcelable {

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

    public AccountInfo() {

    }

    protected AccountInfo(Parcel in) {
        BankName = in.readString();
        NameOnAccount = in.readString();
        RTN = in.readString();
        DAN = in.readString();
        AcctType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(BankName);
        dest.writeString(NameOnAccount);
        dest.writeString(RTN);
        dest.writeString(DAN);
        dest.writeString(AcctType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AccountInfo> CREATOR = new Creator<AccountInfo>() {
        @Override
        public AccountInfo createFromParcel(Parcel in) {
            return new AccountInfo(in);
        }

        @Override
        public AccountInfo[] newArray(int size) {
            return new AccountInfo[size];
        }
    };

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
