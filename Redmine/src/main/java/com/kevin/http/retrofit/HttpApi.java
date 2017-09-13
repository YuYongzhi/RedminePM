package com.kevin.http.retrofit;

import android.support.v7.app.AppCompatActivity;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.kevin.realm.Properties;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>
 * - Created by: maitao.
 * <br>
 * -       Date: 17-9-13.
 */

public class HttpApi {

    public static <T> T create(Class<T> service) {
        OkHttpClient.Builder clientBuilder = getClientBuilder();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(getApiHost());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.client(clientBuilder.build());
        Retrofit retrofit = builder.build();
        return retrofit.create(service);
    }

    public static <T> void enqueue(AppCompatActivity activity,
                                   Observable<T> observable,
                                   final ApiObserver<T> observer) {
        if (observable == null) {
            return;
        }
        if (activity instanceof RxAppCompatActivity) {
            RxAppCompatActivity rxAppCompatActivity = (RxAppCompatActivity) activity;
            observable = observable.compose(rxAppCompatActivity.<T>bindUntilEvent(ActivityEvent.DESTROY));
        }
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        if (observer != null) {
                            observer.onSubscribe(disposable);
                        }
                    }

                    @Override
                    public void onNext(@NonNull T t) {
                        if (observer != null) {
                            observer.onNext(t);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        if (observer != null) {
                            observer.onError(throwable);
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (observer != null) {
                            observer.onComplete();
                        }
                    }
                });
    }


    private static OkHttpClient.Builder getClientBuilder() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        clientBuilder.readTimeout(30, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS);
//        if (interceptor != null) {
//            clientBuilder.addInterceptor(interceptor);
//        }
        clientBuilder.addNetworkInterceptor(new StethoInterceptor());
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        clientBuilder.addNetworkInterceptor(loggingInterceptor);
//        clientBuilder.addInterceptor(new ChuckInterceptor(App.getContext()));
        try {
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
            HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            clientBuilder.sslSocketFactory(sslContext.getSocketFactory(),
                    Platform.get().trustManager(sslContext.getSocketFactory()));
            clientBuilder.hostnameVerifier(DO_NOT_VERIFY);
        }
        catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return clientBuilder;
    }


    public static String getApiHost() {
        return Properties.get("redmine_api_host", "");
    }
}
