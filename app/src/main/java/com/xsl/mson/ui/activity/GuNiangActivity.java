package com.xsl.mson.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xsl.mson.R;
import com.xsl.mson.adapter.GuNiangRecycleAdapter;
import com.xsl.mson.base.BaseActivity;
import com.xsl.mson.controller.GankSp;
import com.xsl.mson.controller.MeiziController;
import com.xsl.mson.dao.OnItemClickListener;
import com.xsl.mson.dao.OnPullUpRefreshListener;
import com.xsl.mson.entity.Guniang;
import com.xsl.mson.ui.widget.LoadMoreRecyclerView;
import com.xsl.mson.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by forest on 2017/9/2 0002.
 */

public class GuNiangActivity extends BaseActivity implements OnItemClickListener<Guniang> {

    @BindView(R.id.gniang_toolbar)
    Toolbar gToolbar;
    @BindView(R.id.rv_guniang_content)
    LoadMoreRecyclerView mMeiziRv;


    private List<Guniang> mMeiziList;
    private MeiziController mMeiziController;
    //private LoadMoreRecyclerView mMeiziRv;
    private GuNiangRecycleAdapter mRecycleAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected void initData() {
        mMeiziController.loadBaseData();
        //mMeiziRv = (LoadMoreRecyclerView) findViewById(R.id.rv_guniang_content);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mMeiziRv.setLayoutManager(layoutManager);
        mRecycleAdapter = new GuNiangRecycleAdapter(mMeiziList);
        mRecycleAdapter.setOnItemClickListener(this);
        mMeiziRv.setAdapter(mRecycleAdapter);
        mMeiziRv.setOnPullUpRefreshListener(onPullUpRefresh());

    }

    @Override
    protected void initView() {

        mMeiziList = new ArrayList<>();
        mMeiziController = new MeiziController(this);

        setSupportActionBar(gToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guniang;
    }

    private OnPullUpRefreshListener onPullUpRefresh() {
        return new OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                int counts = GankSp.getGankDateCounts(mContext);
                if (mMeiziList.size() >= counts) {
                    ToastUtils.show(mContext, "到底啦！没有姑娘了...");
                } else {
                    mMeiziController.startPullUpRefresh();
                }
            }
        };
    }

    public void updateMeizi(List<Guniang> data) {
        if (mMeiziList == null) {
            mMeiziList = new ArrayList<>();
        }
        mMeiziList.clear();
        mMeiziList.addAll(data);
        mRecycleAdapter.notifyDataSetChanged();
        ToastUtils.show(mContext, "加载成功，新增" + data.size() + "张妹子啦");
    }

    public void refreshMeizi(List<Guniang> data) {
        if (mMeiziList == null) {
            mMeiziList = new ArrayList<>();
        }
        int oldSize = mMeiziList.size();
        mMeiziList.addAll(data);
        mRecycleAdapter.notifyItemRangeInserted(oldSize, data.size());
        ToastUtils.show(mContext, "加载成功，新增" + data.size() + "张妹子拉");
    }

    @Override
    public void onItemClick(View view, Guniang data, int position) {
        ArrayList<String> images = new ArrayList<>();
        for (Guniang entity : mMeiziList) {
            images.add(entity.getUrl());
        }

        ToastUtils.show(mContext,"position:"+position+"。。。。");
        //ImageActivity.startActivity(mContext, position, images);
    }
}
