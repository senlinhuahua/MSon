package com.forest.meshe.cache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.forest.meshe.daoshe.ImageCache;

/**
 * Created by forest on 2018/5/8 0008.
 */

public class MemoryCache implements ImageCache {
    public static final String TGA = "forest";
    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCache() {
        initImageCache();

    }

    private void initImageCache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() /1024);
        Log.d(TGA,"maxMemory"+maxMemory);
        //取可用的最大内存的1/4
        final int cache = maxMemory/4;
        mMemoryCache = new LruCache<String, Bitmap>(cache){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() /1024;
            }
        };
    }

    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);

    }
}
