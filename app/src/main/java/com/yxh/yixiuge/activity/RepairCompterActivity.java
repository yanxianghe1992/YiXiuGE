package com.yxh.yixiuge.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yxh.yixiuge.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/11.
 */

public class RepairCompterActivity extends RepairApplianceActivity {
    @Bind(R.id.repair_computer_tv_pinpai)
    TextView repairComputerTvPinpai;
    @Bind(R.id.repair_computer_ll_pinpai)
    LinearLayout repairComputerLlPinpai;
    @Bind(R.id.repair_computer_tv_leixing)
    TextView repairComputerTvLeixing;
    @Bind(R.id.repair_computer_ll_leixing)
    LinearLayout repairComputerLlLeixing;
    @Bind(R.id.repair_computer_tv_xinghao)
    TextView repairComputerTvXinghao;
    @Bind(R.id.repair_computer_ll_xinghao)
    LinearLayout repairComputerLlXinghao;
    @Bind(R.id.repair_computer_tv_guzhang)
    TextView repairComputerTvGuzhang;
    @Bind(R.id.repair_computer_ll_guzhang)
    LinearLayout repairComputerLlGuzhang;
    @Bind(R.id.repair_computer_et_guzhangmiaoshu)
    EditText repairComputerEtGuzhangmiaoshu;
    @Bind(R.id.repair_computer_rv_picture)
    RecyclerView repairComputerRvPicture;
    @Bind(R.id.repair_computer_bt_queren)
    Button repairComputerBtQueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_compter);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.repair_computer_ll_pinpai, R.id.repair_computer_ll_leixing, R.id.repair_computer_ll_xinghao, R.id.repair_computer_ll_guzhang, R.id.repair_computer_bt_queren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.repair_computer_ll_pinpai:
                break;
            case R.id.repair_computer_ll_leixing:
                break;
            case R.id.repair_computer_ll_xinghao:
                break;
            case R.id.repair_computer_ll_guzhang:
                break;
            case R.id.repair_computer_bt_queren:
                break;
        }
    }
}
