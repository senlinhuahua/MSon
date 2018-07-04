package com.forest.meshe.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.forest.meshe.daoshe.ImageCache;
import com.forest.meshe.utils.CloseUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by forest on 2018/5/8 0008.
 */

public class DiskCache implements ImageCache {

    public static String cacheDir = "sdcard/mson/";

    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir+url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(cacheDir+url);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            CloseUtils.closeQuietly(fileOutputStream);
        }
         }
}
