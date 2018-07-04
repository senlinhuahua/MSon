package com.xsl.mson.controller;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xsl.mson.dao.GankApi;
import com.xsl.mson.dao.RetrofitListener;
import com.xsl.mson.entity.GankResEntity;
import com.xsl.mson.entity.Guniang;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class GankController {

    public static final String TYPE_MEIZI = "福利";

    //默认每次只加载10个数据
    private static final int DEFAULT_LOAD_COUNTS = 10;

    private static Gson sGson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:SS")
            .serializeNulls()
            .create();

    private static GankApi getGankApi() {
        return GankApiSingleton.mInstance;
    }


    /**
     * 获取指定类型的干货，默认一次加载10条信息
     *
     * @param type
     * @param page     分页加载
     * @param callback
     */
    public static void getSpecifyGanHuo(final String type, int page, final RetrofitListener<List<Guniang>> callback) {
        //LogUtils.d(TAG, "正在下载干货，类型： " + type + " ，下载数量： " + DEFAULT_LOAD_COUNTS + " ，下载第 " + page + " 页数据");
        getGankApi().getSpecifyGanHuo(type, DEFAULT_LOAD_COUNTS, page).enqueue(new Callback<GankResEntity>() {
            @Override
            public void onResponse(Call<GankResEntity> call, Response<GankResEntity> response) {
                if (response.isSuccessful()) {
                    Log.d("forest", type + "干货下载成功： " + response.body().toString());
                    Object results = response.body().getResults();
                    Type t = new TypeToken<List<Guniang>>() {
                    }.getType();
                    List<Guniang> data = sGson.fromJson(sGson.toJson(results), t);
                    callback.onSuccess(data);
                } else {
                    //获取数据失败，可能是 404 之类的原因
                 //   LogUtils.e(TAG, type + "干货下载失败，code： " + response.code());
                    callback.onError("请求失败，code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<GankResEntity> call, Throwable t) {
                //可能是网络问题，请求发送失败
                //LogUtils.e(TAG,type + "干货下载失败", t);
                callback.onError(t.getMessage());
            }
        });
    }
    private static class GankApiSingleton {
        private static GankApi mInstance = RetrofitHelper.newRetrofit(BuildConfig.GANK_SERVICE).create(GankApi.class);
    }
}
