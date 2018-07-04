package com.xsl.mson.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;

import com.xsl.mson.R;
import com.xsl.mson.base.BaseActivity;

/**
 * Created by forest on 2017/9/25 0025.
 */

public class GuideActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new GuideTask().execute();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    private class GuideTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            startActivity(new Intent(GuideActivity.this,MainActivity.class));
            finish();
        }
    }

}
