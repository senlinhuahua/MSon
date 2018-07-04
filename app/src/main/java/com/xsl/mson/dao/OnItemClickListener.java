package com.xsl.mson.dao;

import android.view.View;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public interface OnItemClickListener<T> {
    void onItemClick(View view, T data, int position);
}
