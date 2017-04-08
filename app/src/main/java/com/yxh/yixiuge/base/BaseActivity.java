package com.yxh.yixiuge.base;

import android.app.Activity;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by zykj on 2017/4/8.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);//友盟统计应用暂停
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);//友盟统计应用重启
    }
}
