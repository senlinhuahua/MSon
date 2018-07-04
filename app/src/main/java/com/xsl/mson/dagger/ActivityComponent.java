package com.xsl.mson.dagger;

import com.xsl.mson.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by forest on 2017/8/29 0029.
 */

@Component
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
