package com.xsl.mson.controller;

import android.content.Context;

import com.xsl.mson.dao.RetrofitListener;
import com.xsl.mson.entity.Guniang;
import com.xsl.mson.ui.activity.GuNiangActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class MeiziController {
    private static final String TAG = MeiziController.class.getSimpleName();

    private Context mContext;
    private GuNiangActivity mMeiziActivity;

    public MeiziController(Context context) {
        if (!(context instanceof GuNiangActivity)) {
            //LogUtils.e(TAG, TAG + "绑定错误的Activity");
            throw new UnsupportedOperationException(TAG + "绑定错误的Activity");
        }
        mContext = context;
        mMeiziActivity = (GuNiangActivity) context;
    }

    private final String MEIZI_TYPE = GankController.TYPE_MEIZI;

    public void loadBaseData() {
        mMeiziPage = 1;
        GankController.getSpecifyGanHuo(MEIZI_TYPE, 1, new RetrofitListener<List<Guniang>>() {
            @Override
            public void onSuccess(List<Guniang> data) {
                mMeiziActivity.updateMeizi(data);
            }

            @Override
            public void onError(String description) {

            }
        });
    }

    private static final int STATE_REFRESH_END = 1;
    private static final int STATE_REFRESHING = 2;

    private int mRefreshState = STATE_REFRESH_END;
    private int mMeiziPage = 1;

    public void startPullUpRefresh() {
        //防止重复发起请求
        if (mRefreshState == STATE_REFRESHING) {
            return;
        }
        mRefreshState = STATE_REFRESHING;
        GankController.getSpecifyGanHuo(MEIZI_TYPE, ++mMeiziPage, new RetrofitListener<List<Guniang>>() {
            @Override
            public void onSuccess(List<Guniang> data) {
                mRefreshState = STATE_REFRESH_END;
                if (data != null && data.size() > 0) {
                    mMeiziActivity.refreshMeizi(data);
                } else {
                    //todo 加载失败，activity是否应该开个接口显示

                }
            }

            @Override
            public void onError(String description) {
                mRefreshState = STATE_REFRESH_END;
            }
        });
    }
}
