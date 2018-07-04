package com.xsl.mson.chat.listpanel;

import com.xsl.mson.R;

/**
 * Created by forest on 2018/4/11 0011.
 */

public final class SimpleLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.nim_simple_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
