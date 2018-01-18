package com.cattechnologies.tpg.apiCalls;


import com.cattechnologies.tpg.model.ReportsEfinValidCheck;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportAccountDisbPerticularSearchSort;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportAccountDisbSearchSort;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportParticulrAccountDisb;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportParticulrAccountDisbSort;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportsAccountDisb;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportsAccountDisbSearch;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportsAccountDisbSort;
import com.cattechnologies.tpg.model.accountDisbursementModel.ReportsPerticularAccountDisbSearch;
import com.cattechnologies.tpg.model.eroDepositModel.ReportEroDepositsPerticularSearchSort;
import com.cattechnologies.tpg.model.eroDepositModel.ReportEroDepositsSearchSort;
import com.cattechnologies.tpg.model.eroDepositModel.ReportParticulrEroDeposits;
import com.cattechnologies.tpg.model.eroDepositModel.ReportParticulrEroDepositsSort;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDeposit;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsSearch;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsEroDepositsSort;
import com.cattechnologies.tpg.model.eroDepositModel.ReportsPerticularEroDepositsSearch;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaid;
import com.cattechnologies.tpg.model.feePaidModel.ReportsPerticularFeePaidSearch;
import com.cattechnologies.tpg.model.dashboardModel.DashboardInfo;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserEmailAddress;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserEmailAddressSb;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserNameInfo;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserPasswordInfo;
import com.cattechnologies.tpg.model.forgotUserModel.ForgotUserPasswordInfoEmp;
import com.cattechnologies.tpg.model.profileModel.ProfileGroupData;
import com.cattechnologies.tpg.model.feePaidModel.ReportFreePaidPerticularSearchSort;
import com.cattechnologies.tpg.model.feePaidModel.ReportFreePaidSearchSort;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaid;
import com.cattechnologies.tpg.model.feePaidModel.ReportParticulrFreePaidSort;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaidSearch;
import com.cattechnologies.tpg.model.feePaidModel.ReportsFeePaidSort;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
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


    @FormUrlEncoded
    @POST("ero-deposit-search")
    Observable<ReportsEroDepositsSearch> getEroDepositDataSearch
            (@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
             @Field("page") String page, @Field("search") String search);


    @FormUrlEncoded
    @POST("SortEroDeposit")
    Observable<ReportsEroDepositsSort> getEroDepositsDataSort(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                              @Field("page") String page, @Field("sort") String search);

    @FormUrlEncoded
    @POST("SearchSortEroDeposit")
    Observable<ReportEroDepositsSearchSort> getEroDepositsDataSearchSort(@Field("app_uid") String userId,
                                                                         @Field("acc_type") String userType,
                                                                         @Field("search") String newText,
                                                                         @Field("page") String page,
                                                                         @Field("sort") String sort);

   /* particular erodeposit sb*/

    @FormUrlEncoded
    @POST("EroDeposit-particular-office")
    Observable<ReportParticulrEroDeposits> getEroDepositsParticularData(@Field("app_uid") String userId,
                                                                        @Field("acc_type") String userType,
                                                                        @Field("page") String page,
                                                                        @Field("efin_id") String particularPerson);


    @FormUrlEncoded
    @POST("EroDeposit-particular-office-search")
    Observable<ReportsPerticularEroDepositsSearch> getPerticularEroDepositsSearch(
            @Field("app_uid") String userId,
            @Field("acc_type") String userType,
            @Field("page") String page,
            @Field("search") String newText,
            @Field("efin_id") String efinData);

    @FormUrlEncoded
    @POST("EfinValid-SB")
    Observable<ReportsEfinValidCheck> getEroDepositsEfinValidCheck(@Field("app_uid")String userId,
                                                        @Field("acc_type")String userType,
                                                        @Field("efin_check")String title);

    @FormUrlEncoded
    @POST("EroDeposit-particular-office-sort")
    Observable<ReportParticulrEroDepositsSort> getEroDepositsParticularDataSort(@Field("app_uid") String userId,
                                                                                @Field("acc_type") String userType,
                                                                                @Field("page") String pageEfin,
                                                                                @Field("efin_id") String efinData,
                                                                                @Field("sort") String sort);

    @FormUrlEncoded
    @POST("EroDeposit-particular-office-search-sort")
    Observable<ReportEroDepositsPerticularSearchSort> getEroDepositsParticularDataSearchSort(
            @Field("app_uid") String userId,
            @Field("acc_type") String userType,
            @Field("search") String newText,
            @Field("page") String page,
            @Field("efin_id") String efinData,
            @Field("sort") String sort);




      /* AccountDisbData*/

    @FormUrlEncoded
    @POST("DisbursmentReport")
    Observable<ReportsAccountDisb> getAccountDisbData(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                      @Field("page") String page);


    @FormUrlEncoded
    @POST("DisbursmentReportSearch")
    Observable<ReportsAccountDisbSearch> getAccountDisbDataSearch
            (@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
             @Field("page") String page, @Field("search") String search);


    @FormUrlEncoded
    @POST("DisbursmentReportSort")
    Observable<ReportsAccountDisbSort> getAccountDisbDataSort(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                              @Field("page") String page, @Field("sort") String search);

    @FormUrlEncoded
    @POST("DisbursmentReportSearchSort")
    Observable<ReportAccountDisbSearchSort> getAccountDisbDataSearchSort(@Field("app_uid") String userId,
                                                                         @Field("acc_type") String userType,
                                                                         @Field("search") String newText,
                                                                         @Field("page") String page,
                                                                         @Field("sort") String sort);

   /* particularAccountDisb sb*/

    @FormUrlEncoded
    @POST("EroDeposit-particular-office")
    Observable<ReportParticulrAccountDisb> getAccountDisbParticularData(@Field("app_uid") String userId,
                                                                        @Field("acc_type") String userType,
                                                                        @Field("page") String page,
                                                                        @Field("efin_id") String particularPerson);


    @FormUrlEncoded
    @POST("EroDeposit-particular-office-search")
    Observable<ReportsPerticularAccountDisbSearch> getPerticularAccountDisbSearch(
            @Field("app_uid") String userId,
            @Field("acc_type") String userType,
            @Field("page") String page,
            @Field("search") String newText,
            @Field("efin_id") String efinData);

    @FormUrlEncoded
    @POST("EfinValid-SB")
    Observable<ReportsEfinValidCheck> getAccountDisbEfinValidCheck(@Field("app_uid")String userId,
                                                                   @Field("acc_type")String userType,
                                                                   @Field("efin_check")String title);

    @FormUrlEncoded
    @POST("EroDeposit-particular-office-sort")
    Observable<ReportParticulrAccountDisbSort> getAccountDisbParticularDataSort(@Field("app_uid") String userId,
                                                                                @Field("acc_type") String userType,
                                                                                @Field("page") String pageEfin,
                                                                                @Field("efin_id") String efinData,
                                                                                @Field("sort") String sort);

    @FormUrlEncoded
    @POST("EroDeposit-particular-office-search-sort")
    Observable<ReportAccountDisbPerticularSearchSort> getAccountDisbParticularDataSearchSort(
            @Field("app_uid") String userId,
            @Field("acc_type") String userType,
            @Field("search") String newText,
            @Field("page") String page,
            @Field("efin_id") String efinData,
            @Field("sort") String sort);
}
