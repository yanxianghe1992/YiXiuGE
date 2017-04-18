package com.yxh.yixiuge.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.yxh.yixiuge.R;
import com.yxh.yixiuge.adapter.PictureAdapter;
import com.yxh.yixiuge.base.BaseActivity;
import com.yxh.yixiuge.been.RepairjBeen;
import com.yxh.yixiuge.utils.Y;
import com.yxh.yixiuge.utils.YURL;

import org.xutils.http.RequestParams;

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
    List<RepairjBeen> lists; //品牌的数据源
    int mobileIndex = -1;  //用于检测是否选择了品牌

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
                    GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                        @Override
                        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                            list.clear();
                            PhotoInfo photoInfo = resultList.get(0);
                            String photoPath = photoInfo.getPhotoPath();
                            list.add(photoPath);
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
                //发起请求
                Y.get(new RequestParams(YURL.FIND_PHONE_BRAND), new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), RepairjBeen.class);
                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(RepairPhoneActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    if (mobileIndex != options1) {
                                        repairPhoneTvPinpai.setText(lists.get(options1).getName());
                                        repairPhoneTvPinpai.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                        repairPhoneTvPinpai.setTextColor(Color.parseColor("#00cccc"));
                                        mobileIndex = lists.get(options1).getId(); // 当前选择的索引
                                        repairPhoneTvXinghao.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                                        repairPhoneTvXinghao.setTextColor(Color.parseColor("#6b6b6b"));
                                        repairPhoneTvXinghao.setText("请选择您的手机型号");

                                    } else {
                                        repairPhoneTvPinpai.setText(lists.get(options1).getName());
                                        repairPhoneTvPinpai.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                        repairPhoneTvPinpai.setTextColor(Color.parseColor("#00cccc"));
                                        mobileIndex = lists.get(options1).getId(); // 当前选择的索引
                                    }

                                }
                            }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (RepairjBeen mb : lists) {
                                strs.add(mb.getName());
                            }
                            //添加数据
                            opv.setPicker(strs, null, null);
                            //显示选择器
                            opv.show();
                        } else {
                            //失败
                            Y.t("数据解析失败");
                        }
                    }
                });
                break;
            case R.id.repair_phone_ll_xinghao:
                //检测是否选择了品牌
                if (mobileIndex == -1) {
                    Y.t("请您先选择品牌");
                } else {
                    //开始获取型号数据
                    //发起请求
                    RequestParams rp = new RequestParams(YURL.FIND_PHONE_MODEL);
                    rp.addBodyParameter("pid", mobileIndex + "");
                    Y.get(rp, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), RepairjBeen.class);
                                //创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(RepairPhoneActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        repairPhoneTvXinghao.setText(lists.get(options1).getName());
                                        repairPhoneTvXinghao.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                        repairPhoneTvXinghao.setTextColor(Color.parseColor("#00cccc"));

                                    }
                                }).build();
                                //把lists 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (RepairjBeen mb : lists) {
                                    strs.add(mb.getName());
                                }
                                //添加数据
                                opv.setPicker(strs, null, null);
                                //显示选择器
                                opv.show();
                            } else {
                                //失败
                                Y.t("数据解析失败");
                            }
                        }
                    });
                }
                break;
            case R.id.repair_phone_ll_guzhang:
                if (mobileIndex != -1) {
                    //发起请求
                    Y.get(new RequestParams(YURL.FIND_PHONE_FAULT), new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), RepairjBeen.class);
                                //创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(RepairPhoneActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        repairPhoneTvGuzhang.setText(lists.get(options1).getName());
                                        repairPhoneTvGuzhang.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                        repairPhoneTvGuzhang.setTextColor(Color.parseColor("#00cccc"));
                                    }
                                }).build();
                                //把lists 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (RepairjBeen mb : lists) {
                                    strs.add(mb.getName());
                                }
                                //添加数据
                                opv.setPicker(strs, null, null);
                                //显示选择器
                                opv.show();
                            } else {
                                //失败
                                Y.t("数据解析失败");
                            }
                        }
                    });
                } else {
                    Y.t("请先选择品牌");
                }
                break;
            case R.id.repair_phone_bt_queren:
                Intent intent = new Intent(this, CallServiceActivity.class);
                startActivity(intent);
                break;
        }
    }
}
