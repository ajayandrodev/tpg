package com.cattechnologies.tpg.utils;


import com.cattechnologies.tpg.apiCalls.RetrofitInterface;
import com.cattechnologies.tpg.viewHolderData.AnalyticsApplication;

import java.io.File;
import java.net.CookieHandler;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class NetworkUtil {
    private static RetrofitInterface retrofit = null;
    private static RxJavaCallAdapterFactory rxAdapter;

    public static RetrofitInterface getRetrofit() {
        rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(createbuild())
                    .addCallAdapterFactory(rxAdapter)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(RetrofitInterface.class);
        }
        return retrofit;
    }

    private static OkHttpClient createbuild() {
        OkHttpClient.Builder httpClient = getUnsafeOkHttpClient();
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
        return httpClient.build();
    }


    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            //  httpClient.followSslRedirects(true);
            httpClient.cache(new Cache(new File(AnalyticsApplication.getInstance().getCacheDir(), "https"), 1024 * 1024 * 10));
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            httpClient.writeTimeout(30, TimeUnit.SECONDS);

            httpClient.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return httpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
