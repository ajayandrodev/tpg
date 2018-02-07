package com.cattechnologies.tpg.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajay on 1/11/2018.
 */

public class ReportsEfinValidCheck implements Parcelable {
    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReportsEfinValidCheck() {
    }


    protected ReportsEfinValidCheck(Parcel in) {
        status = in.readString();
        message = in.readString();
    }

    public static final Creator<ReportsEfinValidCheck> CREATOR = new Creator<ReportsEfinValidCheck>() {
        @Override
        public ReportsEfinValidCheck createFromParcel(Parcel in) {
            return new ReportsEfinValidCheck(in);
        }

        @Override
        public ReportsEfinValidCheck[] newArray(int size) {
            return new ReportsEfinValidCheck[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(status);
        parcel.writeString(message);
    }
}
