package com.xsl.mson.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.forest.meshe.ashe.ImageLoader;
import com.forest.meshe.cache.DoubleCache;
import com.xsl.mson.R;
import com.xsl.mson.base.BaseActivity;
import com.xsl.mson.ui.widget.CircleImageView;

import butterknife.BindView;

/**
 * Created by forest on 2018/5/7 0007.
 */

public class SheActivity extends BaseActivity {


    public static void start(Context context,String parm){
        Intent intent = new Intent(context,SheActivity.class);
        context.startActivity(intent);
    }

    public static String IMAGEURL = "http://7xi8d6.com1.z0.glb.clouddn.com/20171219224721_wFH5PL_Screenshot.jpeg";

    @BindView(R.id.she_header)
    CircleImageView sheHeader;

    @Override
    protected void initView() {
        ImageLoader loader = ImageLoader.getInstance();
        loader.setImageCache(new DoubleCache());
        loader.displayImage(IMAGEURL,sheHeader);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_she;
    }
}
