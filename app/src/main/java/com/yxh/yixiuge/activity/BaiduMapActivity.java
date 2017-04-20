package com.yxh.yixiuge.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.mapapi.map.MapView;
import com.yxh.yixiuge.R;
import com.yxh.yixiuge.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/20.
 */

public class BaiduMapActivity extends BaseActivity {

    @Bind(R.id.baidumap_bt)
    Button baidumapBt;
    @Bind(R.id.baidumap_et)
    EditText baidumapEt;
    @Bind(R.id.baidumap_map)
    MapView baidumapMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidumap);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.baidumap_bt)
    public void onViewClicked() {


    }
}
