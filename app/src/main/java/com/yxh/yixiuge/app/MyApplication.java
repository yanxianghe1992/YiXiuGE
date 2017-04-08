package com.yxh.yixiuge.app;

import android.app.Application;

import org.xutils.x;

/**
 * Created by zykj on 2017/4/8.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化xutils
        x.Ext.init(this);


    }
}
