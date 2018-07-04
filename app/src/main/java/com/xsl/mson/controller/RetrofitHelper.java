package com.xsl.mson.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class RetrofitHelper {

    private static final String TAG = RetrofitHelper.class.getSimpleName();

    private static OkHttpClient mOkHttpClient;
    private static Gson mGson;

    private static void init() {
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .serializeNulls()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = OkHttpProvider.getCacheOkHttpClient();
    }

    static Retrofit newRetrofit(String baseUrl) {
        if (mOkHttpClient == null || mGson == null) {
            init();
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();
    }
}
