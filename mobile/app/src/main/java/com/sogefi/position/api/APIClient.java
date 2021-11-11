package com.sogefi.position.api;

import java.security.cert.CertificateException;

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

    public static final String BASE_URL1 = "https://nominatim.openstreetmap.org/";
    public static final String BASE_URL2 = "https://services.position.cm/";
    public static final String BASE_URL3 = "https://api.position.cm/";
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
                    .baseUrl(BASE_URL1)
                    .client(getUnsafeOkHttpClient().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit1;
    }


    public static Retrofit getNewClient2() {
        if (retrofit3 == null) {
            retrofit3 = new Retrofit.Builder()
                    .baseUrl(BASE_URL1)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getUnsafeOkHttpClient().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit3;
    }

    public static Retrofit getNewClient3() {
        if (retrofit2 == null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .client(getUnsafeOkHttpClient().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }

    public static Retrofit getNewClient4() {
        if (retrofit4 == null) {
            retrofit4 = new Retrofit.Builder()
                    .baseUrl(BASE_URL3)
                    .client(getUnsafeOkHttpClient().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit4;
    }
}
