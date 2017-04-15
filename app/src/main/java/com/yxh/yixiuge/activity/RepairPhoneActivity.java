package com.yxh.yixiuge.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    @Bind(R.id.repair_phone_bt_queren)
    Button repairPhoneBtQueren;
    private List<String> list = new ArrayList();
    private PictureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_phone);
        ButterKnife.bind(this);
        list.add("");
        repairPhoneRvPicture.setLayoutManager(new GridLayoutManager(RepairPhoneActivity.this, 3));
        adapter = new PictureAdapter(list, RepairPhoneActivity.this);
        repairPhoneRvPicture.setAdapter(adapter);
        adapter.setOnItemClickL(new PictureAdapter.OnItemClickL() {
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

    @OnClick({R.id.repair_phone_ll_pinpai, R.id.repair_phone_ll_xinghao, R.id.repair_phone_ll_guzhang, R.id.repair_phone_bt_queren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.repair_phone_ll_pinpai:
                break;
            case R.id.repair_phone_ll_xinghao:
                break;
            case R.id.repair_phone_ll_guzhang:
                break;
            case R.id.repair_phone_bt_queren:
                break;
        }
    }
}
