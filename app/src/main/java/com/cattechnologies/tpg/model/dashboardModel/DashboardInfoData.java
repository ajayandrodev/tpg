package com.cattechnologies.tpg.model.dashboardModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 11/15/2017.
 */

public class DashboardInfoData implements Parcelable {

 /* "AppliedRts": "8",
          "FundedRTs": "7",
          "StateFundedRTs": "6",
          "CheckRTs": "1",
          "DirectDepositRTs": "7",
          "PrePaidCards": "0",
          "Direct2Cash": "0",
          "PrepFeesPaid": "1095.0000",
          "PastDueAccountsAmount": "1205.0000"
*/
  /* "AppliedRts": "263",
           "FundedRTs": "65",
           "StateFundedRTs": "79",
           "CheckRTs": "151",
           "DirectDepositRTs": "308",
           "PrePaidCards": "147",
           "Direct2Cash": "53",
           "PrepFeesPaid": "6540.00",
           "PastDueAccountsAmount": "1470.00",
           "CountTaxpayer": "2"*/

    @SerializedName("AppliedRts")
    private String AppliedRts;

    @SerializedName("FundedRTs")
    private String FundedRTs;

    @SerializedName("StateFundedRTs")
    private String StateFundedRTs;

    @SerializedName("CheckRTs")
    private String CheckRTs;

    @SerializedName("DirectDepositRTs")
    private String DirectDepositRTs;

    @SerializedName("PrePaidCards")
    private String PrePaidCards;

    @SerializedName("Direct2Cash")
    private String Direct2Cash;

    @SerializedName("PrepFeesPaid")
    private String PrepFeesPaid;

    @SerializedName("PastDueAccountsAmount")
    private String PastDueAccountsAmount;

    @SerializedName("CountTaxpayer")
    private String CountTaxpayer;

    public String getCountTaxpayer() {
        return CountTaxpayer;
    }

    public void setCountTaxpayer(String countTaxpayer) {
        CountTaxpayer = countTaxpayer;
    }

    public String getAppliedRts() {
        return AppliedRts;
    }

    public void setAppliedRts(String appliedRts) {
        AppliedRts = appliedRts;
    }

    public String getFundedRTs() {
        return FundedRTs;
    }

    public void setFundedRTs(String fundedRTs) {
        FundedRTs = fundedRTs;
    }

    public String getStateFundedRTs() {
        return StateFundedRTs;
    }

    public void setStateFundedRTs(String stateFundedRTs) {
        StateFundedRTs = stateFundedRTs;
    }

    public String getCheckRTs() {
        return CheckRTs;
    }

    public void setCheckRTs(String checkRTs) {
        CheckRTs = checkRTs;
    }

    public String getDirectDepositRTs() {
        return DirectDepositRTs;
    }

    public void setDirectDepositRTs(String directDepositRTs) {
        DirectDepositRTs = directDepositRTs;
    }

    public String getPrePaidCards() {
        return PrePaidCards;
    }

    public void setPrePaidCards(String prePaidCards) {
        PrePaidCards = prePaidCards;
    }

    public String getDirect2Cash() {
        return Direct2Cash;
    }

    public void setDirect2Cash(String direct2Cash) {
        Direct2Cash = direct2Cash;
    }

    public String getPrepFeesPaid() {
        return PrepFeesPaid;
    }

    public void setPrepFeesPaid(String prepFeesPaid) {
        PrepFeesPaid = prepFeesPaid;
    }

    public String getPastDueAccountsAmount() {
        return PastDueAccountsAmount;
    }

    public void setPastDueAccountsAmount(String pastDueAccountsAmount) {
        PastDueAccountsAmount = pastDueAccountsAmount;
    }

    public static Creator<DashboardInfoData> getCREATOR() {
        return CREATOR;
    }

    public DashboardInfoData() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.AppliedRts);
        dest.writeString(this.FundedRTs);
        dest.writeString(this.StateFundedRTs);
        dest.writeString(this.CheckRTs);
        dest.writeString(this.DirectDepositRTs);
        dest.writeString(this.PrePaidCards);
        dest.writeString(this.Direct2Cash);
        dest.writeString(this.PrepFeesPaid);
        dest.writeString(this.PastDueAccountsAmount);
        dest.writeString(this.CountTaxpayer);
    }

    protected DashboardInfoData(Parcel in) {
        this.AppliedRts = in.readString();
        this.FundedRTs = in.readString();
        this.StateFundedRTs = in.readString();
        this.CheckRTs = in.readString();
        this.DirectDepositRTs = in.readString();
        this.PrePaidCards = in.readString();
        this.Direct2Cash = in.readString();
        this.PrepFeesPaid = in.readString();
        this.PastDueAccountsAmount = in.readString();
        this.CountTaxpayer = in.readString();
    }

    public static final Creator<DashboardInfoData> CREATOR = new Creator<DashboardInfoData>() {
        @Override
        public DashboardInfoData createFromParcel(Parcel source) {
            return new DashboardInfoData(source);
        }

        @Override
        public DashboardInfoData[] newArray(int size) {
            return new DashboardInfoData[size];
        }
    };
}
