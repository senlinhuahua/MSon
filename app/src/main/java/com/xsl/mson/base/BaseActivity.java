package com.xsl.mson.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by forest on 2017/8/28 0028.
 *
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnBinder = ButterKnife.bind(this);

        mContext = this;
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();



    protected abstract int getLayoutId();



    public void inJect(Activity activity){
        //DaggerActivityComponent.builder().build().inject(activity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
