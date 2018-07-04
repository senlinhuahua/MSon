package com.xsl.mson.chat.uikit;

import java.util.List;

/**
 * Created by forest on 2018/4/11 0011.
 */

public interface UserInfoObserver {

    /**
     * 用户信息变更
     *
     * @param accounts 账号列表
     */
    void onUserInfoChanged(List<String> accounts);
}
