package com.xsl.mson.chat.uikit;

/**
 * Created by forest on 2018/4/11 0011.
 */

public interface OnlineStateContentProvider {

    // 用于展示最近联系人界面的在线状态
    String getSimpleDisplay(String account);

    // 用于展示聊天界面的在线状态
    String getDetailDisplay(String account);
}
