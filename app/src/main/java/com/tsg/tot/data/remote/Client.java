package com.tsg.tot.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class Client {

    private static Retrofit retrofit = null;
    public static class UnsafeOkHttpClient {

        OkHttpClient getUnsafeOkHttpClient() {
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

                OkHttpClient okHttpClient = builder.build();
                return okHttpClient;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static Retrofit getClientTaskRegister(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new UnsafeOkHttpClient().getUnsafeOkHttpClient()
                .newBuilder()
                .readTimeout(80, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                // .addInterceptor(new  NetworkConnectionInterceptor())
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    // .baseUrl(ApiUtils.BASE_URL+":"+ApiUtils.PORT_URL+"/")
                    .baseUrl(ApiUtils.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
        }
        return retrofit;

    }

    static Retrofit getClientGetTasks(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new UnsafeOkHttpClient().getUnsafeOkHttpClient()
                .newBuilder()
                .readTimeout(180, TimeUnit.SECONDS)
                .connectTimeout(80, TimeUnit.SECONDS)
                .addInterceptor(logging)
                // .addInterceptor(new  NetworkConnectionInterceptor())
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    // .baseUrl(ApiUtils.BASE_URL+":"+ApiUtils.PORT_URL+"/")
                    .baseUrl(ApiUtils.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
        }
        return retrofit;

    }

    static Retrofit getClientIsConnected()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new UnsafeOkHttpClient().getUnsafeOkHttpClient()
                .newBuilder()
                .readTimeout(25, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(logging)
                // .addInterceptor(new  NetworkConnectionInterceptor())
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    // .baseUrl(ApiUtils.BASE_URL+":"+ApiUtils.PORT_URL+"/")
                    .baseUrl(ApiUtils.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
        }
        return retrofit;
/*        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUtils.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;*/
    }
    static Retrofit getClientLogin()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new UnsafeOkHttpClient().getUnsafeOkHttpClient()
                .newBuilder()
                .readTimeout(25, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(logging)
                // .addInterceptor(new  NetworkConnectionInterceptor())
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    // .baseUrl(ApiUtils.BASE_URL+":"+ApiUtils.PORT_URL+"/")
                    .baseUrl(ApiUtils.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
        }
        return retrofit;
/*        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUtils.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;*/
    }

    static Retrofit getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new UnsafeOkHttpClient().getUnsafeOkHttpClient()
                .newBuilder()
                .readTimeout(180, TimeUnit.SECONDS)
                .connectTimeout(80, TimeUnit.SECONDS)
                .addInterceptor(logging)
               // .addInterceptor(new  NetworkConnectionInterceptor())
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                   // .baseUrl(ApiUtils.BASE_URL+":"+ApiUtils.PORT_URL+"/")
                    .baseUrl(ApiUtils.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
        }
        return retrofit;
/*        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUtils.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;*/
    }

    public boolean isClientOnline() {
        boolean isOnline = false;
        try {
           // Socket s = new Socket(ApiUtils.BASE_URL, Integer.parseInt(ApiUtils.PORT_URL));
            Socket s = new Socket(ApiUtils.BASE_URL, Integer.parseInt("6104"));
            if (s.isConnected()) {
                isOnline = true;
                System.out.println("Conexión establecida con la dirección: " + ApiUtils.BASE_URL);
            }
        } catch (Exception e) {
            System.err.println("No se pudo establecer conexión con: " +ApiUtils.BASE_URL);
        }
        return isOnline;
    }

}
