package com.cattechnologies.tpg.model.dashboardModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.cattechnologies.tpg.model.profileModel.ProfileData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 11/9/2017.
 */

public class DashboardInfo implements Parcelable{


    @SerializedName("status")
    private String status;


    @SerializedName("message")
    private String message;

    @SerializedName("dashboard_data")
    private DashboardInfoData dashboard_data;

    @SerializedName("profile_data")
    private ProfileData profile_data;

    @SerializedName("recent_transactions")
    private List<RecentTransactions> recent_transactions;

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

    public DashboardInfoData getDashboard_data() {
        return dashboard_data;
    }

    public void setDashboard_data(DashboardInfoData dashboard_data) {
        this.dashboard_data = dashboard_data;
    }

    public ProfileData getProfile_data() {
        return profile_data;
    }

    public void setProfile_data(ProfileData profile_data) {
        this.profile_data = profile_data;
    }

    public List<RecentTransactions> getRecent_transactions() {
        return recent_transactions;
    }

    public void setRecent_transactions(List<RecentTransactions> recent_transactions) {
        this.recent_transactions = recent_transactions;
    }

    public static Creator<DashboardInfo> getCREATOR() {
        return CREATOR;
    }

    protected DashboardInfo(Parcel in) {
        status = in.readString();
        message = in.readString();
        dashboard_data = in.readParcelable(DashboardInfoData.class.getClassLoader());
        profile_data = in.readParcelable(ProfileData.class.getClassLoader());
        recent_transactions = in.createTypedArrayList(RecentTransactions.CREATOR);
    }

    public static final Creator<DashboardInfo> CREATOR = new Creator<DashboardInfo>() {
        @Override
        public DashboardInfo createFromParcel(Parcel in) {
            return new DashboardInfo(in);
        }

        @Override
        public DashboardInfo[] newArray(int size) {
            return new DashboardInfo[size];
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
        parcel.writeParcelable(dashboard_data, i);
        parcel.writeParcelable(profile_data, i);
        parcel.writeTypedList(recent_transactions);
    }
}
