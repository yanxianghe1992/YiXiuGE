package com.yxh.yixiuge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.yxh.yixiuge.R;
import com.yxh.yixiuge.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/20.
 */

public class AddressManagementActivity extends BaseActivity {

    @Bind(R.id.address_mana_lv)
    ListView addressManaLv;
    @Bind(R.id.address_mana_rl_add)
    RelativeLayout addressManaRlAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_management);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.address_mana_rl_add)
    public void onViewClicked() {
        Intent intent = new Intent();
        startActivity(intent);

    }
}
