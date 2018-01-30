package com.cattechnologies.tpg.model.dashboardModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 11/15/2017.
 */

public class RecentTransactions implements Parcelable{

  /*   "recent_transactions": [
    {
        "LastUpdate": "2016-01-25 10:06:07.900",
            "Amount": "286.0500"
    },
    {
        "LastUpdate": "2014-12-16 07:52:01.050",
            "Amount": "1500.0000"
    }
    ]*/

/*
     "LastUpadte": "2016-01-25 10:06:07.840",
             "Amount": "180.0000"*/



    @SerializedName("LastUpdate")
    private String LastUpdate;

    @SerializedName("Amount")
    private String Amount;

    public String getLastUpadte() {
        return LastUpdate;
    }

    public void setLastUpadte(String lastUpadte) {
        LastUpdate = lastUpadte;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public static Creator<RecentTransactions> getCREATOR() {
        return CREATOR;
    }

    public RecentTransactions(){

    }

    protected RecentTransactions(Parcel in) {
        LastUpdate = in.readString();
        Amount = in.readString();
    }

    public static final Creator<RecentTransactions> CREATOR = new Creator<RecentTransactions>() {
        @Override
        public RecentTransactions createFromParcel(Parcel in) {
            return new RecentTransactions(in);
        }

        @Override
        public RecentTransactions[] newArray(int size) {
            return new RecentTransactions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(LastUpdate);
        parcel.writeString(Amount);
    }
}
