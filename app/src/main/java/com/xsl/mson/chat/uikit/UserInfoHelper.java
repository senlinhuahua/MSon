package com.xsl.mson.chat.uikit;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;

/**
 * Created by forest on 2018/4/11 0011.
 */

public class UserInfoHelper {

    // 获取用户显示在标题栏和最近联系人中的名字
    public static String getUserTitleName(String id, SessionTypeEnum sessionType) {
//        if (sessionType == SessionTypeEnum.P2P) {
//            if (NimUIKit.getAccount().equals(id)) {
//                return "我的电脑";
//            } else {
//                return getUserDisplayName(id);
//            }
//        } else if (sessionType == SessionTypeEnum.Team) {
//            return TeamHelper.getTeamName(id);
//        }
        return id;
    }

//    /**
//     * @param account 用户帐号
//     * @return
//     */
//    public static String getUserDisplayName(String account) {
//        String alias = NimUIKit.getContactProvider().getAlias(account);
//        if (!TextUtils.isEmpty(alias)) {
//            return alias;
//        } else {
//            UserInfo userInfo = NimUIKit.getUserInfoProvider().getUserInfo(account);
//            if (userInfo != null && !TextUtils.isEmpty(userInfo.getName())) {
//                return userInfo.getName();
//            } else {
//                return account;
//            }
//        }
//    }



}
