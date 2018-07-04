package com.xsl.mson.chat;

/**
 * Created by forest on 2018/4/11 0011.
 */


import android.content.Context;

import com.xsl.mson.chat.uikit.OnlineStateChangeObservable;
import com.xsl.mson.chat.uikit.OnlineStateContentProvider;
import com.xsl.mson.chat.uikit.UIKitOptions;
import com.xsl.mson.chat.uikit.UserInfoObservable;

/**
 * 能力实现类。
 */
public final class NimUIKitImpl {

    // context
    private static Context context;

    // 自己的用户帐号
    private static String account;

    private static UIKitOptions options;

    // userInfo 变更监听
    private static UserInfoObservable userInfoObservable;

    // 在线状态变化监听
    private static OnlineStateChangeObservable onlineStateChangeObservable;

    // 在线状态展示内容
    private static OnlineStateContentProvider onlineStateContentProvider;

    public static UserInfoObservable getUserInfoObservable() {
        if (userInfoObservable == null) {
            userInfoObservable = new UserInfoObservable(context);
        }
        return userInfoObservable;
    }

    /**
     * 获取上下文
     *
     * @return 必须初始化后才有值
     */
    public static Context getContext() {
        //return NimUIKitImpl.getContext();
        return context;
    }

    public static void setContext(Context context) {
        NimUIKitImpl.context = context;
    }

    public static void setOptions(Context context) {
        //NimUIKitImpl.context = context;
    }

    public static OnlineStateChangeObservable getOnlineStateChangeObservable() {
        if (onlineStateChangeObservable == null) {
            onlineStateChangeObservable = new OnlineStateChangeObservable(context);
        }
        return onlineStateChangeObservable;
    }

    public static boolean enableOnlineState() {
        return onlineStateContentProvider != null;
    }

    public static OnlineStateContentProvider getOnlineStateContentProvider() {
        return onlineStateContentProvider;
    }

    public static UIKitOptions getOptions() {
        return options;
    }


    public static String getAccount() {
        return account;
    }

}
