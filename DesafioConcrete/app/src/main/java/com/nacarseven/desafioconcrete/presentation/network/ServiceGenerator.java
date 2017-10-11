package com.nacarseven.desafioconcrete.presentation.network;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by nacarseven on 10/10/17.
 */

public class ServiceGenerator {

    private static final long DEFAULT_TIMEOUT = 30;
    private static String authorization;

    public ServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass, final String baseUrl) {
        return createService(serviceClass, baseUrl, null);
    }


    public static <S> S createService(Class<S> serviceClass, final String baseUrl, String auth) {
        authorization = auth;

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(getDefaultInterceptor())
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(httpClient)
                .build();

        return retrofit.create(serviceClass);
    }

    //region PRIVATE METHODS
    private static Interceptor getDefaultInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                Request request = builder.build();

                HttpUrl.Builder urlBuilder = request.url().newBuilder();

                if (!TextUtils.isEmpty(authorization))
                    urlBuilder
                            .addEncodedQueryParameter("access_token", authorization)
                            .build();

                request = request.newBuilder()
                        .url(urlBuilder.build())
                        .addHeader("Connection", "close")
                        .build();

                return chain.proceed(request);
            }
        };
    }

}
