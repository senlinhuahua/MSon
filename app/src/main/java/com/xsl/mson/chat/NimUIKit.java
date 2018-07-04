package com.xsl.mson.chat;

import android.content.Context;

import com.xsl.mson.chat.uikit.UserInfoObservable;

/**
 * Created by forest on 2018/4/11 0011.
 */

public class NimUIKit {



    /**
     * 获取上下文
     *
     * @return 必须初始化后才有值
     */
    public static Context getContext() {
        return NimUIKitImpl.getContext();
    }

    /**
     * 获取 “用户资料” 变更监听管理者
     * UIKit 与 app 之间 userInfo 数据更新通知接口
     *
     * @return UserInfoObservable
     */
    public static UserInfoObservable getUserInfoObservable() {
        return NimUIKitImpl.getUserInfoObservable();
    }


    /**
     * 获取当前登录的账号
     *
     * @return 必须登录成功后才有值
     */
    public static String getAccount() {
        return NimUIKitImpl.getAccount();
    }


}
