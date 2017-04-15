package com.yxh.yixiuge.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yxh.yixiuge.R;
import com.yxh.yixiuge.adapter.PictureAdapter;
import com.yxh.yixiuge.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zykj on 2017/4/11.
 */

public class RepairApplianceActivity extends BaseActivity {
    @Bind(R.id.repair_appliance_tv_pinpai)
    TextView repairApplianceTvPinpai;
    @Bind(R.id.repair_appliance_ll_pinpai)
    LinearLayout repairApplianceLlPinpai;
    @Bind(R.id.repair_appliance_tv_leixing)
    TextView repairApplianceTvLeixing;
    @Bind(R.id.repair_appliance_ll_leixing)
    LinearLayout repairApplianceLlLeixing;
    @Bind(R.id.repair_appliance_tv_xinghao)
    TextView repairApplianceTvXinghao;
    @Bind(R.id.repair_appliance_ll_xinghao)
    LinearLayout repairApplianceLlXinghao;
    @Bind(R.id.repair_appliance_tv_guzhang)
    TextView repairApplianceTvGuzhang;
    @Bind(R.id.repair_appliance_ll_guzhang)
    LinearLayout repairApplianceLlGuzhang;
    @Bind(R.id.repair_appliance_et_guzhangmiaoshu)
    EditText repairApplianceEtGuzhangmiaoshu;
    @Bind(R.id.repair_appliance_rv_picture)
    RecyclerView repairApplianceRvPicture;
    @Bind(R.id.repair_appliance_bt_queren)
    Button repairApplianceBtQueren;
    private List<String> list = new ArrayList();
    private PictureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_appliance);
        ButterKnife.bind(this);
        list.add("");
        repairApplianceRvPicture.setLayoutManager(new GridLayoutManager(RepairApplianceActivity.this, 3));
        adapter = new PictureAdapter(list, RepairApplianceActivity.this);
        repairApplianceRvPicture.setAdapter(adapter);
        adapter.setOnItemClickL(new PictureAdapter.OnItemClickL() {
            public static final int REQUEST_CODE_GALLERY = 1001;

            @Override
            public void onClick(PictureAdapter.MyViewHodler holder, int pos) {
                if ((pos + 1) == list.size()) {
                    GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, 1024, new GalleryFinal.OnHanlderResultCallback() {
                        @Override
                        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                            list.remove((list.size() - 1));
                            for (int i = 0; i < resultList.size(); i++) {
                                PhotoInfo photoInfo = resultList.get(i);
                                String photoPath = photoInfo.getPhotoPath();
                                list.add(photoPath);
                            }
                            list.add("");
                            adapter.setList(list);
                        }

                        @Override
                        public void onHanlderFailure(int requestCode, String errorMsg) {

                        }
                    });
                } else {

                }
            }
        });
    }

    @OnClick({R.id.repair_appliance_ll_pinpai, R.id.repair_appliance_ll_leixing, R.id.repair_appliance_ll_xinghao, R.id.repair_appliance_ll_guzhang, R.id.repair_appliance_bt_queren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.repair_appliance_ll_pinpai:
                break;
            case R.id.repair_appliance_ll_leixing:
                break;
            case R.id.repair_appliance_ll_xinghao:
                break;
            case R.id.repair_appliance_ll_guzhang:
                break;
            case R.id.repair_appliance_bt_queren:
                break;
        }
    }
}
