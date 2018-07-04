package com.forest.meshe.cache;

import android.graphics.Bitmap;

import com.forest.meshe.daoshe.ImageCache;

/**
 * Created by forest on 2018/5/8 0008.
 */

public class DoubleCache implements ImageCache {

    ImageCache mMemoryCache = new MemoryCache();
    ImageCache mDiskCache = new DiskCache();

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null){
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }
}
