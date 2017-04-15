package com.yxh.yixiuge.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yxh.yixiuge.R;
import com.yxh.yixiuge.adapter.PictureAdapter;
import com.yxh.yixiuge.base.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zykj on 2017/4/11.
 */

public class RepairPhoneActivity extends BaseActivity {
    private static final int REQUEST_CODE_GALLERY = 1001;
    @Bind(R.id.repair_phone_tv_pinpai)
    TextView repairPhoneTvPinpai;
    @Bind(R.id.repair_phone_ll_pinpai)
    LinearLayout repairPhoneLlPinpai;
    @Bind(R.id.repair_phone_tv_xinghao)
    TextView repairPhoneTvXinghao;
    @Bind(R.id.repair_phone_ll_xinghao)
    LinearLayout repairPhoneLlXinghao;
    @Bind(R.id.repair_phone_tv_guzhang)
    TextView repairPhoneTvGuzhang;
    @Bind(R.id.repair_phone_ll_guzhang)
    LinearLayout repairPhoneLlGuzhang;
    @Bind(R.id.repair_phone_et_guzhangmiaoshu)
    EditText repairPhoneEtGuzhangmiaoshu;
    @Bind(R.id.repair_phone_rv_picture)
    RecyclerView repairPhoneRvPicture;
    @Bind(R.id.repair_phone_ll_picture)
    LinearLayout repairPhoneLlPicture;
    @Bind(R.id.repair_phone_bt_queren)
    Button repairPhoneBtQueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_phone);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.repair_phone_ll_pinpai, R.id.repair_phone_ll_xinghao, R.id.repair_phone_ll_guzhang, R.id.repair_phone_ll_picture, R.id.repair_phone_bt_queren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.repair_phone_ll_pinpai:
                break;
            case R.id.repair_phone_ll_xinghao:
                break;
            case R.id.repair_phone_ll_guzhang:
                break;
            case R.id.repair_phone_ll_picture:
                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, 1024, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        repairPhoneRvPicture.setLayoutManager(new LinearLayoutManager(RepairPhoneActivity.this, LinearLayoutManager.HORIZONTAL, true));
                        PictureAdapter adapter = new PictureAdapter(resultList, RepairPhoneActivity.this);
                        repairPhoneRvPicture.setAdapter(adapter);


                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });


                break;
            case R.id.repair_phone_bt_queren:
                break;
        }
    }
}
