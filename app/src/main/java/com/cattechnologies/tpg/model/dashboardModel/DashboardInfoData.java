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


    protected DashboardInfoData(Parcel in) {
        AppliedRts = in.readString();
        FundedRTs = in.readString();
        StateFundedRTs = in.readString();
        CheckRTs = in.readString();
        DirectDepositRTs = in.readString();
        PrePaidCards = in.readString();
        Direct2Cash = in.readString();
        PrepFeesPaid = in.readString();
        PastDueAccountsAmount = in.readString();
    }

    public static final Creator<DashboardInfoData> CREATOR = new Creator<DashboardInfoData>() {
        @Override
        public DashboardInfoData createFromParcel(Parcel in) {
            return new DashboardInfoData(in);
        }

        @Override
        public DashboardInfoData[] newArray(int size) {
            return new DashboardInfoData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(AppliedRts);
        parcel.writeString(FundedRTs);
        parcel.writeString(StateFundedRTs);
        parcel.writeString(CheckRTs);
        parcel.writeString(DirectDepositRTs);
        parcel.writeString(PrePaidCards);
        parcel.writeString(Direct2Cash);
        parcel.writeString(PrepFeesPaid);
        parcel.writeString(PastDueAccountsAmount);
    }

}
