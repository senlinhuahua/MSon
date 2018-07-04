package com.xsl.mson.controller;

import android.content.Context;

import com.xsl.mson.utils.SPUtils;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class GankSp {

    public static final String GANK_DATE_COUNTS = "gank_date_counts";
    private static String spName = "gank";
    private static SPUtils gankSpUtils;

    private static SPUtils getSpUtils(Context context) {
        if (gankSpUtils == null) {
            gankSpUtils = new SPUtils(context, spName);
        }
        return gankSpUtils;
    }

    public static int getGankDateCounts(Context context) {
        return getSpUtils(context).getInt(GANK_DATE_COUNTS, 100);
    }
}
