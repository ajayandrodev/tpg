package com.cattechnologies.tpg.APICalls;


import com.cattechnologies.tpg.Model.DashboardInfo;
import com.cattechnologies.tpg.Model.EnrolInfo;
import com.cattechnologies.tpg.Model.ForgotUserEmailAddress;
import com.cattechnologies.tpg.Model.ForgotUserEmailAddressSb;
import com.cattechnologies.tpg.Model.ForgotUserNameInfo;
import com.cattechnologies.tpg.Model.ForgotUserPasswordInfo;
import com.cattechnologies.tpg.Model.ForgotUserPasswordInfoEmp;
import com.cattechnologies.tpg.Model.LoginInfo;
import com.cattechnologies.tpg.Model.ProfileGroupData;
import com.cattechnologies.tpg.Model.ProfileGroupInfo;
import com.cattechnologies.tpg.Model.ReportsFeePaid;
import com.cattechnologies.tpg.Model.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Completable;
import rx.Observable;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST("sign-in")
    Observable<DashboardInfo> sign(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                   @Field("app_pswd") String app_pswd);

    @FormUrlEncoded
    @POST("get-profile-info")
    Observable<ProfileGroupInfo> profile(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type);

    @FormUrlEncoded
    @POST("get-forgot-username")
    Observable<ForgotUserNameInfo> forgotUserName
            (@Field("user_efin") String efin, @Field("user_email") String email, @Field("acc_type") String type);


    @FormUrlEncoded
    @POST("get-forgot-password")
    Observable<ForgotUserPasswordInfo> forgotPassword(@Field("username_efin") String forgotUname,
                                                      @Field("user_email") String forgotUpass,
                                                      @Field("acc_type") String acc_type);

    @FormUrlEncoded
    @POST("get-forgot-email")
    Observable<ForgotUserEmailAddress> forgotEmailAddress(@Field("user_efin") String forgotUname,
                                                          @Field("ssn_no") String forgotUpass,
                                                          @Field("acc_type") String acc_type);

    @FormUrlEncoded
    @POST("get-forgot-email")
    Observable<ForgotUserEmailAddressSb> forgotEmailAddressSb(@Field("user_efin") String forgotUname, @Field("acc_type") String acc_type);

    @FormUrlEncoded
    @POST("get-forgot-password")
    Observable<ForgotUserPasswordInfoEmp> forgotPasswordEmp(@Field("user_email") String forgotUpass, @Field("acc_type") String acc_type);

    @FormUrlEncoded
    @POST("get-profile-info")
    Observable<ProfileGroupData> profileNew(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type);

    @FormUrlEncoded
    @POST("fee-paid")
    Observable<ReportsFeePaid> getFeePaidReport(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type);
}
