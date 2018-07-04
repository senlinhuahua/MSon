package com.forest.meshe.ashe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.forest.meshe.daoshe.ImageCache;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by forest on 2018/5/7 0007.
 */

public class ImageLoader {

    public static ImageLoader getInstance() {
        return new ImageLoader();
    }

    ImageCache mImageCache;

    public void setImageCache(ImageCache cache){
        mImageCache = cache;
    }

    //cpu数量
    ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    Handler mUiHandler = new Handler(Looper.getMainLooper());

    private void updateImageView(final ImageView imageView, final Bitmap bitmap){
        mUiHandler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }
    //加载图片
    public void displayImage(final String url, final ImageView imageView){
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        submitLoadDown(url,imageView);

    }

    private void submitLoadDown(final String url, final ImageView imageView) {

        imageView.setTag(url);
        //Log.d("forest","executorService:"+executorService);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downLoadImage(url);
                if (bitmap == null){
                    return;
                }
                if (imageView.getTag().equals(url)){
                    updateImageView(imageView,bitmap);
                }
                mImageCache.put(url,bitmap);
            }
        });
    }

    private Bitmap downLoadImage(String url) {
        Bitmap bitmap = null;
        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }
}
