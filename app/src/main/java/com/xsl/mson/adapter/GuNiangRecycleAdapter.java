package com.xsl.mson.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xsl.mson.R;
import com.xsl.mson.dao.OnItemClickListener;
import com.xsl.mson.entity.Guniang;
import com.xsl.mson.ui.widget.ScaleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class GuNiangRecycleAdapter extends LoadMoreRecycleAdapter<GuNiangRecycleAdapter.ViewHolder> {

    private static final String TAG = GuNiangRecycleAdapter.class.getSimpleName();

    private Context mContext;
    private List<Guniang> mDataList;
    private OnItemClickListener<Guniang> mClickListener;

    public GuNiangRecycleAdapter(List<Guniang> data) {
        mDataList = data;
    }

    @Override
    public int getDataSize() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gunaing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindView(final ViewHolder holder, int position) {
        final Guniang data = mDataList.get(position);
        final int posi = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onItemClick(v, data, posi);
                }
            }
        });
        holder.itemView.setVisibility(View.GONE);
        Glide.with(mContext)
                .load(data.getUrl())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        holder.itemView.setVisibility(View.VISIBLE);
                        holder.mMeiziIv.setOriginSize(resource.getWidth(), resource.getHeight());
                        holder.mMeiziIv.setImageBitmap(resource);
                    }
                });
    }

    public void setOnItemClickListener(OnItemClickListener<Guniang> listener) {
        mClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ScaleImageView mMeiziIv;

        public ViewHolder(View itemView) {
            super(itemView);
            mMeiziIv = (ScaleImageView) itemView.findViewById(R.id.iv_meizi);
        }
    }
}
