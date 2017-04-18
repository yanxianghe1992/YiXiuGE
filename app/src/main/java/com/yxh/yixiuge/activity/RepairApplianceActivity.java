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
    int pidIndex = -1;  //用于检测是否选择了品牌
    int categoryIndex = -1;
    List<RepairjBeen> lists; //品牌的数据源

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

    @OnClick({R.id.repair_appliance_ll_pinpai, R.id.repair_appliance_ll_leixing, R.id.repair_appliance_ll_xinghao, R.id.repair_appliance_ll_guzhang, R.id.repair_appliance_bt_queren})
    public void onViewClicked(View view) {//品牌======品牌======品牌
        switch (view.getId()) {
            case R.id.repair_appliance_ll_pinpai:
                Y.get(new RequestParams(YURL.FIND_APPLIANCE_BRAND), new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), RepairjBeen.class);
                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(RepairApplianceActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    if (pidIndex == lists.get(options1).getId()) {
                                        repairApplianceTvPinpai.setText(lists.get(options1).getName());
                                        repairApplianceTvPinpai.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                        repairApplianceTvPinpai.setTextColor(Color.parseColor("#00cccc"));
                                        pidIndex = lists.get(options1).getId(); // 当前选择的索引
                                    } else {
                                        repairApplianceTvPinpai.setText(lists.get(options1).getName());
                                        repairApplianceTvPinpai.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                        repairApplianceTvPinpai.setTextColor(Color.parseColor("#00cccc"));
                                        pidIndex = lists.get(options1).getId(); // 当前选择的索引
                                        repairApplianceTvXinghao.setText("请选择您的家电型号");
                                        repairApplianceTvLeixing.setText("请选择您的家电类型");
                                        repairApplianceTvXinghao.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                                        repairApplianceTvLeixing.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                                        repairApplianceTvXinghao.setTextColor(Color.parseColor("#6b6b6b"));
                                        repairApplianceTvLeixing.setTextColor(Color.parseColor("#6b6b6b"));

                                        categoryIndex = -1;
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
            case R.id.repair_appliance_ll_leixing:
                //类型========类型=======类型
                if (pidIndex != -1) {
                    RequestParams requestParams1 = new RequestParams(YURL.FIND_APPLIANCE_CATEGORY);
                    requestParams1.addBodyParameter("pid", pidIndex + "");
                    //发起请求
                    Y.get(requestParams1, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), RepairjBeen.class);
                                //创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(RepairApplianceActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        if (categoryIndex == lists.get(options1).getId()) {
                                            repairApplianceTvLeixing.setText(lists.get(options1).getName());
                                            repairApplianceTvLeixing.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                            repairApplianceTvLeixing.setTextColor(Color.parseColor("#00cccc"));
                                            categoryIndex = lists.get(options1).getId(); // 当前选择的索引
                                        } else {
                                            repairApplianceTvLeixing.setText(lists.get(options1).getName());
                                            repairApplianceTvLeixing.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                            repairApplianceTvLeixing.setTextColor(Color.parseColor("#00cccc"));
                                            categoryIndex = lists.get(options1).getId(); // 当前选择的索引
                                            repairApplianceTvXinghao.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                                            repairApplianceTvXinghao.setText("请选择您的家电型号");
                                            repairApplianceTvXinghao.setTextColor(Color.parseColor("#6b6b6b"));
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
                } else {
                    Y.t("请先选择品牌");
                }
                break;
            case R.id.repair_appliance_ll_xinghao:
                //型号=========型号=======型号
                if (categoryIndex != -1) {
                    RequestParams requestParams = new RequestParams(YURL.FIND_APPLIANCE_MODEL);
                    requestParams.addBodyParameter("pid", pidIndex + "");
                    requestParams.addBodyParameter("category", categoryIndex + "");
                    //发起请求
                    Y.get(requestParams, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), RepairjBeen.class);
                                //创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(RepairApplianceActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        repairApplianceTvXinghao.setText(lists.get(options1).getName());
                                        repairApplianceTvXinghao.setTextColor(Color.parseColor("#00cccc"));
                                        repairApplianceTvXinghao.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
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
                    if (pidIndex == -1) {
                        Y.t("请先选择品牌");
                    } else {
                        Y.t("请先选择类型");
                    }
                }
                break;
            case R.id.repair_appliance_ll_guzhang:
                //发起请求
                Y.get(new RequestParams(YURL.FIND_PHONE_FAULT), new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), RepairjBeen.class);
                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(RepairApplianceActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    repairApplianceTvGuzhang.setText(lists.get(options1).getName());
                                    repairApplianceTvGuzhang.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                    repairApplianceTvGuzhang.setTextColor(Color.parseColor("#00cccc"));
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
            case R.id.repair_appliance_bt_queren:
                Intent intent = new Intent(this, CallServiceActivity.class);
                startActivity(intent);
                break;
        }
    }
}
