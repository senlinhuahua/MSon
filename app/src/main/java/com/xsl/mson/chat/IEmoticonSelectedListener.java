package com.xsl.mson.chat;

/**
 * Created by forest on 2018/4/11 0011.
 */

interface IEmoticonSelectedListener {
    void onEmojiSelected(String key);

    void onStickerSelected(String categoryName, String stickerName);
}
