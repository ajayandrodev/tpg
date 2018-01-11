package com.cattechnologies.tpg.apiCalls;


import com.cattechnologies.tpg.model.ReportsEfinValidCheck;
import com.cattechnologies.tpg.model.ReportsEroDeposit;
import com.cattechnologies.tpg.model.ReportsPerticularFeePaidSearch;
import com.cattechnologies.tpg.model.dashboardModel.DashboardInfo;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserEmailAddress;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserEmailAddressSb;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserNameInfo;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserPasswordInfo;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserPasswordInfoEmp;
import com.cattechnologies.tpg.model.profileModel.ProfileGroupData;
import com.cattechnologies.tpg.model.profileModel.ProfileGroupInfo;
import com.cattechnologies.tpg.model.feePaidModel.ReportFreePaidPerticularSearchSort;
import com.cattechnologies.tpg.model.feePaidModel.ReportFreePaidSearchSort;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaid;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaidSort;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaid;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaidSearch;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaidSort;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Completable;
import rx.Observable;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST("sign-in")
    Observable<DashboardInfo> sign(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                   @Field("app_pswd") String app_pswd);


    /*forgot apis*/
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
    Observable<ForgotUserEmailAddressSb> forgotEmailAddressSb
            (@Field("user_efin") String forgotUname, @Field("acc_type") String acc_type);

    @FormUrlEncoded
    @POST("get-forgot-password")
    Observable<ForgotUserPasswordInfoEmp> forgotPasswordEmp(@Field("user_email") String forgotUpass, @Field("acc_type") String acc_type);

    /* profile info api*/
    @FormUrlEncoded
    @POST("get-profile-info")
    Observable<ProfileGroupData> profileNew(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type);


    /*feePaidEroAndSB*/

    @FormUrlEncoded
    @POST("fee-paid-offset")
    Observable<ReportsFeePaid> getFeePaidData(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                              @Field("page") String page);

    @FormUrlEncoded
    @POST("search2")
    Observable<ReportsFeePaidSearch> getFeePaidDataSearch(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                          @Field("page") String page, @Field("search") String search);


    @FormUrlEncoded
    @POST("sortFeepaid")
    Observable<ReportsFeePaidSort> getFeePaidDataSort(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                      @Field("page") String page, @Field("sort") String search);

    @FormUrlEncoded
    @POST("search-sort")
    Observable<ReportFreePaidSearchSort> getFeePaidDataSearchSort(@Field("app_uid") String userId,
                                                                  @Field("acc_type") String userType,
                                                                  @Field("search") String newText,
                                                                  @Field("page") String page,
                                                                  @Field("sort") String sort);

   /* particular feepaid sb*/

    @FormUrlEncoded
    @POST("particular-office")
    Observable<ReportParticulrFreePaid> getFeePaidParticularData(@Field("app_uid") String userId,
                                                                 @Field("acc_type") String userType,
                                                                 @Field("page") String page, @Field("efin_id") String particularPerson);


    @FormUrlEncoded
    @POST("particular-office-search")
    Observable<ReportsPerticularFeePaidSearch> getPerticularFeePaidSearch(
            @Field("app_uid") String userId,
            @Field("acc_type") String userType,
            @Field("page") String page,
            @Field("search") String newText,
            @Field("efin_id") String efinData);

    @FormUrlEncoded
    @POST("EfinValid-SB")
    Observable<ReportsEfinValidCheck> getEfinValidCheck(@Field("app_uid")String userId,
                                                        @Field("acc_type")String userType,
                                                        @Field("efin_check")String title);

    @FormUrlEncoded
    @POST("sort_particular_SB")
    Observable<ReportParticulrFreePaidSort> getFeePaidParticularDataSort(@Field("app_uid") String userId,
                                                                         @Field("acc_type") String userType,
                                                                         @Field("page") String pageEfin,
                                                                         @Field("efin_id") String efinData,
                                                                         @Field("sort") String sort);

    @FormUrlEncoded
    @POST("particular-search-sort-feepaid")
    Observable<ReportFreePaidPerticularSearchSort> getFeePaidParticularDataSearchSort(
            @Field("app_uid") String userId,
            @Field("acc_type") String userType,
            @Field("search") String newText,
            @Field("page") String page,
            @Field("efin_id") String efinData,
            @Field("sort") String sort);

   /* eroDepositData*/

    @FormUrlEncoded
    @POST("EroDeposits")
    Observable<ReportsEroDeposit> getEroDepositData(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                    @Field("page") String page);


}
