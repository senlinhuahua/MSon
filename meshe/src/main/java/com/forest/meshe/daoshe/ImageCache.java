package com.forest.meshe.daoshe;

import android.graphics.Bitmap;

/**
 * Created by forest on 2018/5/8 0008.
 */

public interface ImageCache {

    public Bitmap get(String url);
    public void put(String url,Bitmap bitmap);
}
