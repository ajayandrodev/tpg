package com.cattechnologies.tpg.Activities;

import android.util.Log;

import com.cattechnologies.tpg.Model.DashboardInfo;
import com.cattechnologies.tpg.Utils.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * Created by admin on 11/10/2017.
 */

public class MyLoopjTask {

   /* private static final String TAG = "MOVIE_TRIVIA";

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    String BASE_URL = Constants.BASE_URL;
    String jsonResponse;
    DashboardInfo dashboardInfo;

    public MyLoopjTask() {
        asyncHttpClient = new AsyncHttpClient();
        requestParams = new RequestParams();
        asyncHttpClient.get(BASE_URL, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                jsonResponse = response.toString();
                Log.i(TAG, "onSuccess: " + jsonResponse);
                try {
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    dashboardInfo = new DashboardInfo();
                    JSONObject data = jsonObject.getJSONObject("dashboard_data");
                    String prepFaid = data.getString("PrepFeesPaid");
                    String pastDueAct = data.getString("PastDueAccountsAmount");
                    String stateFundRTs = data.getString("StateFundedRTs");
                    String fundedRTS = data.getString("FundedRTs");
                    String directDeposits = data.getString("DirectDepositRTs");
                    String checkRTS = data.getString("CheckRTs");
                    String directCash = data.getString("Direct2Cash");
                    String appliedRT = data.getString("AppliedRts");
                    String prePaidCard = data.getString("PrePaidCards");
                    dashboardInfo.setPrePaidCards(prePaidCard);
                    dashboardInfo.setAppliedRts(appliedRT);
                    dashboardInfo.setCheckRTs(checkRTS);
                    dashboardInfo.setDirect2Cash(directCash);
                    dashboardInfo.setFundedRTs(fundedRTS);
                    dashboardInfo.setPrepFeesPaid(prepFaid);
                    dashboardInfo.setPastDueAccountsAmount(pastDueAct);
                    dashboardInfo.setStateFundedRTs(stateFundRTs);
                    dashboardInfo.setDirectDepositRTs(directDeposits);

                    Log.i(TAG, "onSuccess: " + dashboardInfo.getPrepFeesPaid());

                    Log.i(TAG, "onSuccess: " + dashboardInfo.getCheckRTs());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e(TAG, "onFailure: " + errorResponse);
            }
        });
    }*/
}
