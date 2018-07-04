package com.xsl.mson.dao;

import com.xsl.mson.entity.GankResEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public interface GankApi {

    /**
     * 获取指定类型数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/2
     */
    @GET("data/{type}/{count}/{page}")
    Call<GankResEntity> getSpecifyGanHuo(@Path("type") String type, @Path("count") int count, @Path("page") int page);


}
