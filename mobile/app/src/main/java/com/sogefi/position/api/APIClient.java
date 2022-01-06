package com.sogefi.position.api;

import static com.sogefi.position.utils.Constants.BASEURL;
import static com.sogefi.position.utils.Constants.NOMINATIMURL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit1 = null;
    private static Retrofit retrofit2 = null;
    private static Retrofit retrofit3 = null;
    private static Retrofit retrofit4 = null;




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

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static Retrofit getNewClient() {
        if (retrofit1 == null) {
            retrofit1 = new Retrofit.Builder()
                    .baseUrl(NOMINATIMURL)
                    .client(new OkHttpClient.Builder()
                            .readTimeout(30000, TimeUnit.SECONDS)
                            .connectTimeout(30000, TimeUnit.SECONDS)
                            .build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit1;
    }


    public static Retrofit getNewClient2() {
        if (retrofit3 == null) {
            retrofit3 = new Retrofit.Builder()
                    .baseUrl(NOMINATIMURL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(new OkHttpClient.Builder()
                            .readTimeout(30000, TimeUnit.SECONDS)
                            .connectTimeout(30000, TimeUnit.SECONDS)
                            .build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit3;
    }

    public static Retrofit getNewClient3() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit2 == null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(new OkHttpClient.Builder()
                            .readTimeout(30000, TimeUnit.SECONDS)
                            .connectTimeout(30000, TimeUnit.SECONDS)
                            .build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit2;
    }

}
