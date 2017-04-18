package com.yxh.yixiuge.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.yxh.yixiuge.R;
import com.yxh.yixiuge.base.BaseActivity;
import com.yxh.yixiuge.been.RepairjBeen;
import com.yxh.yixiuge.utils.Y;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class CallServiceActivity extends BaseActivity {

    @Bind(R.id.call_service_tv_time)
    TextView callServiceTvTime;
    @Bind(R.id.call_service_ll_time)
    LinearLayout callServiceLlTime;
    @Bind(R.id.call_service_tv_adress)
    TextView callServiceTvAdress;
    @Bind(R.id.call_service_ll_adress)
    LinearLayout callServiceLlAdress;
    @Bind(R.id.call_service_bt_queren)
    Button callServiceBtQueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.call_service_ll_time, R.id.call_service_ll_adress, R.id.call_service_bt_queren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.call_service_ll_time:
                OptionsPickerView opv = new OptionsPickerView.Builder(CallServiceActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //选择后的监听器
                        Calendar calendar = Calendar.getInstance();
                        int i = calendar.get(Calendar.MONTH) + 1;
                        int i1 = calendar.get(Calendar.DAY_OF_MONTH);
                        callServiceTvTime.setText(i + "月" + (i1 + options1) + "日" + options2 + "点" + (options3 + 1) + "0分");
                        callServiceTvTime.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                        callServiceTvTime.setTextColor(Color.parseColor("#00cccc"));

                    }
                }).build();
                //把lists 进行转换
                List<String> strs1 = new ArrayList<String>();
                strs1.add("今天");
                strs1.add("明天");
                strs1.add("后天");
                List<List<String>> strs2 = new ArrayList<List<String>>();
                List<String>l1=new ArrayList<>();
                List<String>l2=new ArrayList<>();
                List<String>l3=new ArrayList<>();
                for (int i = 0; i <= 24; i++) {
                    l1.add(i + "点");
                    l2.add(i + "点");
                    l3.add(i + "点");
                }
                strs2.add(l1);
                strs2.add(l2);
                strs2.add(l3);
                List<String> strs3 = new ArrayList<String>();
                for (int i = 1; i <= 6; i++) {
                    strs3.add(i + "0分");
                }
                //添加数据
                opv.setPicker(strs1, strs2, strs3);
                //显示选择器
                opv.show();
                break;
            case R.id.call_service_ll_adress:
                break;
            case R.id.call_service_bt_queren:
                break;
        }
    }
}
