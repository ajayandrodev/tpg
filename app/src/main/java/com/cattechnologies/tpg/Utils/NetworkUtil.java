package com.cattechnologies.tpg.Utils;

import android.util.Base64;


import com.cattechnologies.tpg.APICalls.RetrofitInterface;
import com.cattechnologies.tpg.Utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class NetworkUtil {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static RetrofitInterface getRetrofit() {

    /*  *//**//*  String credentials = email + ":" + password;
        String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
      *//**//**/

        //   .header("Authorization", "Basic"+"VHBnQHBwQGRtaU46KVt9I2Yza2F+ZyU2dHBnOSZqW3soJC9dfSklJA==")

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder()
                    .header("admin_username", "Tpg@pp@dmiN")
                    .header("admin_password", ")[}#f3ka~g%6tpg9&j[{($/]})%$")
                    .header("api_key", "$[}#f3ka~g%9tpg3&j[{($/]})(9tp?/!bj30xy-wi=3^9-$^R9G|J#E6AB;OP[}#")
                    .method(original.method(), original.body());
            System.out.println("NetworkUtil.getRetrofit" + original.body());
            return chain.proceed(builder.build());

        });

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitInterface.class);
    }


}
