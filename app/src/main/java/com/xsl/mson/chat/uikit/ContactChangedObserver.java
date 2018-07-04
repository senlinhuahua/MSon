package com.xsl.mson.chat.uikit;

import java.util.List;

/**
 * Created by forest on 2018/4/11 0011.
 */

public interface ContactChangedObserver {
    /**
     * 增加或者更新好友
     *
     * @param accounts 账号列表
     */
    void onAddedOrUpdatedFriends(List<String> accounts);

    /**
     * 删除好友
     *
     * @param accounts 账号列表
     */
    void onDeletedFriends(List<String> accounts);

    /**
     * 增加到黑名单
     *
     * @param accounts 账号列表
     */
    void onAddUserToBlackList(List<String> accounts);

    /**
     * 从黑名单移除
     *
     * @param accounts 账号列表
     */
    void onRemoveUserFromBlackList(List<String> accounts);
}
