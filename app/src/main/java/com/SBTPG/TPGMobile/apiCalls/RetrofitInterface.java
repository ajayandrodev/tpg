package com.SBTPG.TPGMobile.apiCalls;


import com.SBTPG.TPGMobile.model.accountDisbursementModel.ReportAccountDisbServiceBuroSearchSort;
import com.SBTPG.TPGMobile.model.eroDepositModel.ReportEroDepositsSearchServiceBuroSort;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportFreePaidSearchSortServiceBuro;
import com.SBTPG.TPGMobile.model.accountDisbursementModel.ReportsAccountDisbServiceBuro;
import com.SBTPG.TPGMobile.model.accountDisbursementModel.ReportsAccountDisbServiceBuroSearch;
import com.SBTPG.TPGMobile.model.accountDisbursementModel.ReportsAccountDisbServiceBuroSort;
import com.SBTPG.TPGMobile.model.ReportsEfinValidCheck;
import com.SBTPG.TPGMobile.model.eroDepositModel.ReportsEroDepositsServiceBuroSearch;
import com.SBTPG.TPGMobile.model.eroDepositModel.ReportsEroDepositsServiceBuroSort;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportsFeePaidSearchServiceBuro;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportsFeePaidSortServiceBuro;
import com.SBTPG.TPGMobile.model.eroDepositModel.ReportsEroDepositsServiceBuro;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportsFeePaidServiceBuro;
import com.SBTPG.TPGMobile.model.accountDisbursementModel.ReportAccountDisbPerticularSearchSort;
import com.SBTPG.TPGMobile.model.accountDisbursementModel.ReportAccountDisbSearchSort;
import com.SBTPG.TPGMobile.model.accountDisbursementModel.ReportParticulrAccountDisb;
import com.SBTPG.TPGMobile.model.accountDisbursementModel.ReportParticulrAccountDisbSort;
import com.SBTPG.TPGMobile.model.accountDisbursementModel.ReportsAccountDisb;
import com.SBTPG.TPGMobile.model.accountDisbursementModel.ReportsAccountDisbSearch;
import com.SBTPG.TPGMobile.model.accountDisbursementModel.ReportsAccountDisbSort;
import com.SBTPG.TPGMobile.model.accountDisbursementModel.ReportsPerticularAccountDisbSearch;
import com.SBTPG.TPGMobile.model.eroDepositModel.ReportEroDepositsPerticularSearchSort;
import com.SBTPG.TPGMobile.model.eroDepositModel.ReportEroDepositsSearchSort;
import com.SBTPG.TPGMobile.model.eroDepositModel.ReportParticulrEroDeposits;
import com.SBTPG.TPGMobile.model.eroDepositModel.ReportParticulrEroDepositsSort;
import com.SBTPG.TPGMobile.model.eroDepositModel.ReportsEroDeposit;
import com.SBTPG.TPGMobile.model.eroDepositModel.ReportsEroDepositsSearch;
import com.SBTPG.TPGMobile.model.eroDepositModel.ReportsEroDepositsSort;
import com.SBTPG.TPGMobile.model.eroDepositModel.ReportsPerticularEroDepositsSearch;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportsFeePaid;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportsPerticularFeePaidSearch;
import com.SBTPG.TPGMobile.model.dashboardModel.DashboardInfo;
import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserEmailAddress;
import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserEmailAddressSb;
import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserNameInfo;
import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserPasswordInfo;
import com.SBTPG.TPGMobile.model.forgotUserModel.ForgotUserPasswordInfoEmp;
import com.SBTPG.TPGMobile.model.profileModel.ProfileGroupData;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportFreePaidPerticularSearchSort;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportFreePaidSearchSort;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportParticulrFreePaid;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportParticulrFreePaidSort;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportsFeePaidSearch;
import com.SBTPG.TPGMobile.model.feePaidModel.ReportsFeePaidSort;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface RetrofitInterface {

   @FormUrlEncoded
    @POST("sign-in.php")
    Observable<DashboardInfo> sign(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                   @Field("app_pswd") String app_pswd);




    /*forgot apis*/
    @FormUrlEncoded
    @POST("get-forgot-username.php")
    Observable<ForgotUserNameInfo> forgotUserName
    (@Field("user_efin") String efin, @Field("user_email") String email, @Field("acc_type") String type);


    @FormUrlEncoded
    @POST("get-forgot-password.php")
    Observable<ForgotUserPasswordInfo> forgotPassword(@Field("username_efin") String forgotUname,
                                                      @Field("user_email") String forgotUpass,
                                                      @Field("acc_type") String acc_type);

    @FormUrlEncoded
    @POST("get-forgot-email.php")
    Observable<ForgotUserEmailAddress> forgotEmailAddress(@Field("user_efin") String forgotUname,
                                                          @Field("ssn_no") String forgotUpass,
                                                          @Field("acc_type") String acc_type);

    @FormUrlEncoded
    @POST("get-forgot-email.php")
    Observable<ForgotUserEmailAddressSb> forgotEmailAddressSb
            (@Field("user_efin") String forgotUname, @Field("acc_type") String acc_type);

    @FormUrlEncoded
    @POST("get-forgot-password.php")
    Observable<ForgotUserPasswordInfoEmp> forgotPasswordEmp(@Field("user_email") String forgotUpass, @Field("acc_type") String acc_type);

    /* profile info api*/
    @FormUrlEncoded
    @POST("get-profile-info.php")
    Observable<ProfileGroupData> profileNew(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type);


    /*feePaidEroAndSB*/

   @FormUrlEncoded
    @POST("fee-paid-offset.php")
    Observable<ReportsFeePaid> getFeePaidData(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                              @Field("page") String page);

    @FormUrlEncoded
    @POST("search2.php")
    Observable<ReportsFeePaidSearch> getFeePaidDataSearch(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                          @Field("page") String page, @Field("search") String search);


    @FormUrlEncoded
    @POST("sortFeepaid.php")
    Observable<ReportsFeePaidSort> getFeePaidDataSort(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                      @Field("page") String page, @Field("sort") String search);

    @FormUrlEncoded
    @POST("search-sort.php")
    Observable<ReportFreePaidSearchSort> getFeePaidDataSearchSort(@Field("app_uid") String userId,
                                                                  @Field("acc_type") String userType,
                                                                  @Field("search") String newText,
                                                                  @Field("page") String page,
                                                                  @Field("sort") String sort);



/*
    FeesPaidServiceBuroInfo Data*/

    @FormUrlEncoded
    @POST("ServiceBureauFeePaid.php")
    Observable<ReportsFeePaidServiceBuro> getFeePaidServiceBuroData(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                                    @Field("page") String page);


    @FormUrlEncoded
    @POST("ServiceBureauFeePaidSearch.php")
    Observable<ReportsFeePaidSearchServiceBuro> getFeePaidServiceBuroDataSearch(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                                                @Field("page") String page, @Field("search") String search);


    @FormUrlEncoded
    @POST("ServiceBureauFeePaidSort.php")
    Observable<ReportsFeePaidSortServiceBuro> getFeePaidServiceBuroDataSort(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                                            @Field("page") String page, @Field("sort") String search);

    @FormUrlEncoded
    @POST("ServiceBureauFeePaidSearchSort.php")
    Observable<ReportFreePaidSearchSortServiceBuro> getFeePaidServiceBuroDataSearchSort(@Field("app_uid") String userId,
                                                                                        @Field("acc_type") String userType,
                                                                                        @Field("search") String newText,
                                                                                        @Field("page") String page,
                                                                                        @Field("sort") String sort);




   /* particular feepaid sb*/

    @FormUrlEncoded
    @POST("particular-office.php")
    Observable<ReportParticulrFreePaid> getFeePaidParticularData(@Field("app_uid") String userId,
                                                                 @Field("acc_type") String userType,
                                                                 @Field("page") String page, @Field("efin_id") String particularPerson);


    @FormUrlEncoded
    @POST("particular-office-search.php")
    Observable<ReportsPerticularFeePaidSearch> getPerticularFeePaidSearch(
            @Field("app_uid") String userId,
            @Field("acc_type") String userType,
            @Field("page") String page,
            @Field("search") String newText,
            @Field("efin_id") String efinData);

    @FormUrlEncoded
    @POST("EfinValid-SB.php")
    Observable<ReportsEfinValidCheck> getEfinValidCheck(@Field("app_uid") String userId,
                                                        @Field("acc_type") String userType,
                                                        @Field("efin_check") String title);

    @FormUrlEncoded
    @POST("sort_particular_SB.php")
    Observable<ReportParticulrFreePaidSort> getFeePaidParticularDataSort(@Field("app_uid") String userId,
                                                                         @Field("acc_type") String userType,
                                                                         @Field("page") String pageEfin,
                                                                         @Field("efin_id") String efinData,
                                                                         @Field("sort") String sort);

    @FormUrlEncoded
    @POST("particular-search-sort-feepaid.php")
    Observable<ReportFreePaidPerticularSearchSort> getFeePaidParticularDataSearchSort(
            @Field("app_uid") String userId,
            @Field("acc_type") String userType,
            @Field("search") String newText,
            @Field("page") String page,
            @Field("efin_id") String efinData,
            @Field("sort") String sort);

   /* eroDepositData*/

    @FormUrlEncoded
    @POST("EroDeposits.php")
    Observable<ReportsEroDeposit> getEroDepositData(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                    @Field("page") String page);

    @FormUrlEncoded
    @POST("ero-deposit-search.php")
    Observable<ReportsEroDepositsSearch> getEroDepositDataSearch
            (@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
             @Field("page") String page, @Field("search") String search);


    @FormUrlEncoded
    @POST("SortEroDeposit.php")
    Observable<ReportsEroDepositsSort> getEroDepositsDataSort(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                              @Field("page") String page, @Field("sort") String search);

    @FormUrlEncoded
    @POST("SearchSortEroDeposit.php")
    Observable<ReportEroDepositsSearchSort> getEroDepositsDataSearchSort(@Field("app_uid") String userId,
                                                                         @Field("acc_type") String userType,
                                                                         @Field("search") String newText,
                                                                         @Field("page") String page,
                                                                         @Field("sort") String sort);

    /*
    eroDepositServiveBuro*/
    @FormUrlEncoded
    @POST("ServiceBureauEroDeposits.php")
    Observable<ReportsEroDepositsServiceBuro> getEroDepositServiceBuroData(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                                           @Field("page") String page);


    @FormUrlEncoded
    @POST("ServiceBureauEroDepositsSearch.php")
    Observable<ReportsEroDepositsServiceBuroSearch> getEroDepositServiceBuroDataSearch
            (@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
             @Field("page") String page, @Field("search") String search);


    @FormUrlEncoded
    @POST("ServiceBureauEroDepositsSort.php")
    Observable<ReportsEroDepositsServiceBuroSort> getEroDepositsServiceBuroDataSort(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                                                    @Field("page") String page, @Field("sort") String search);

    @FormUrlEncoded
    @POST("ServiceBureauEroDepositsSearchSort.php")
    Observable<ReportEroDepositsSearchServiceBuroSort> getEroDepositsServiceBuroDataSearchSort(@Field("app_uid") String userId,
                                                                                               @Field("acc_type") String userType,
                                                                                               @Field("search") String newText,
                                                                                               @Field("page") String page,
                                                                                               @Field("sort") String sort);



   /* particular erodeposit sb*/

    @FormUrlEncoded
    @POST("EroDeposit-particular-office.php")
    Observable<ReportParticulrEroDeposits> getEroDepositsParticularData(@Field("app_uid") String userId,
                                                                        @Field("acc_type") String userType,
                                                                        @Field("page") String page,
                                                                        @Field("efin_id") String particularPerson);


    @FormUrlEncoded
    @POST("EroDeposit-particular-office-search.php")
    Observable<ReportsPerticularEroDepositsSearch> getPerticularEroDepositsSearch(
            @Field("app_uid") String userId,
            @Field("acc_type") String userType,
            @Field("page") String page,
            @Field("search") String newText,
            @Field("efin_id") String efinData);

    @FormUrlEncoded
    @POST("EfinValid-SB.php")
    Observable<ReportsEfinValidCheck> getEroDepositsEfinValidCheck(@Field("app_uid") String userId,
                                                                   @Field("acc_type") String userType,
                                                                   @Field("efin_check") String title);

    @FormUrlEncoded
    @POST("EroDeposit-particular-office-sort.php")
    Observable<ReportParticulrEroDepositsSort> getEroDepositsParticularDataSort(@Field("app_uid") String userId,
                                                                                @Field("acc_type") String userType,
                                                                                @Field("page") String pageEfin,
                                                                                @Field("efin_id") String efinData,
                                                                                @Field("sort") String sort);

    @FormUrlEncoded
    @POST("EroDeposit-particular-office-search-sort.php")
    Observable<ReportEroDepositsPerticularSearchSort> getEroDepositsParticularDataSearchSort(
            @Field("app_uid") String userId,
            @Field("acc_type") String userType,
            @Field("search") String newText,
            @Field("page") String page,
            @Field("efin_id") String efinData,
            @Field("sort") String sort);




      /* AccountDisbData*/

    @FormUrlEncoded
    @POST("DisbursmentReport.php")
    Observable<ReportsAccountDisb> getAccountDisbData(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                      @Field("page") String page);


    @FormUrlEncoded
    @POST("DisbursmentReportSearch.php")
    Observable<ReportsAccountDisbSearch> getAccountDisbDataSearch
            (@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
             @Field("page") String page, @Field("search") String search);


    @FormUrlEncoded
    @POST("DisbursmentReportSort.php")
    Observable<ReportsAccountDisbSort> getAccountDisbDataSort(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                              @Field("page") String page, @Field("sort") String search);

    @FormUrlEncoded
    @POST("DisbursmentReportSearchSort.php")
    Observable<ReportAccountDisbSearchSort> getAccountDisbDataSearchSort(@Field("app_uid") String userId,
                                                                         @Field("acc_type") String userType,
                                                                         @Field("search") String newText,
                                                                         @Field("page") String page,
                                                                         @Field("sort") String sort);

   /* AccountServiceburo*/

    @FormUrlEncoded
    @POST("ServiceBureauDisbursmentReport.php")
    Observable<ReportsAccountDisbServiceBuro> getAccountDisbServiceBuroData(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                                            @Field("page") String page);


    @FormUrlEncoded
    @POST("ServiceBureauDisbursmentReportSearch.php")
    Observable<ReportsAccountDisbServiceBuroSearch> getAccountDisbServiceBuroDataSearch
            (@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
             @Field("page") String page, @Field("search") String search);


    @FormUrlEncoded
    @POST("ServiceBureauDisbursmentReportSort.php")
    Observable<ReportsAccountDisbServiceBuroSort> getAccountDisbServiceBuroDataSort(@Field("app_uid") String app_uid, @Field("acc_type") String acc_type,
                                                                                    @Field("page") String page, @Field("sort") String search);

    @FormUrlEncoded
    @POST("ServiceBureauDisbursmentSearchSort.php")
    Observable<ReportAccountDisbServiceBuroSearchSort> getAccountDisbServiceBuroDataSearchSort(@Field("app_uid") String userId,
                                                                                               @Field("acc_type") String userType,
                                                                                               @Field("search") String newText,
                                                                                               @Field("page") String page,
                                                                                               @Field("sort") String sort);

   /* particularAccountDisb sb*/

    @FormUrlEncoded
    @POST("DisbursmentReportParticularOffice.php")
    Observable<ReportParticulrAccountDisb> getAccountDisbParticularData(@Field("app_uid") String userId,
                                                                        @Field("acc_type") String userType,
                                                                        @Field("page") String page,
                                                                        @Field("efin_id") String particularPerson);


    @FormUrlEncoded
    @POST("DisbursmentReportParticularOfficeSearch.php")
    Observable<ReportsPerticularAccountDisbSearch> getPerticularAccountDisbSearch(
            @Field("app_uid") String userId,
            @Field("acc_type") String userType,
            @Field("page") String page,
            @Field("search") String newText,
            @Field("efin_id") String efinData);

    @FormUrlEncoded
    @POST("EfinValid-SB.php")
    Observable<ReportsEfinValidCheck> getAccountDisbEfinValidCheck(@Field("app_uid") String userId,
                                                                   @Field("acc_type") String userType,
                                                                   @Field("efin_check") String title);

    @FormUrlEncoded
    @POST("DisbursmentReportParticularOfficeSort.php")
    Observable<ReportParticulrAccountDisbSort> getAccountDisbParticularDataSort(@Field("app_uid") String userId,
                                                                                @Field("acc_type") String userType,
                                                                                @Field("page") String pageEfin,
                                                                                @Field("efin_id") String efinData,
                                                                                @Field("sort") String sort);

    @FormUrlEncoded
    @POST("DisbursmentReportParticularOfficeSearchSort.php")
    Observable<ReportAccountDisbPerticularSearchSort> getAccountDisbParticularDataSearchSort(
            @Field("app_uid") String userId,
            @Field("acc_type") String userType,
            @Field("search") String newText,
            @Field("page") String page,
            @Field("efin_id") String efinData,
            @Field("sort") String sort);

}
