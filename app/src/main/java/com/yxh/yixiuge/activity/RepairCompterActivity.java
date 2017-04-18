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

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;


/**
 * Created by zykj on 2017/4/11.
 */

public class RepairCompterActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_pinpai, ll_leixing, ll_xinghao, ll_guzhang;
    private TextView tv_pinpai, tv_leixing, tv_xinghao, tv_guzhang;
    private EditText et_guzhangmiaoshu;
    private RecyclerView rv;
    private Button bt_queren;
    List<RepairjBeen> lists; //品牌的数据源
    int pidIndex = -1;  //用于检测是否选择了品牌
    int categoryIndex = -1;
    List<String> list = new ArrayList<>();
    private PictureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_compter);
        initView();
        initEvent();
        list.add("");
        rv.setLayoutManager(new GridLayoutManager(RepairCompterActivity.this, 3));
        adapter = new PictureAdapter(list, RepairCompterActivity.this);
        rv.setAdapter(adapter);
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

    private void initEvent() {
        ll_pinpai.setOnClickListener(this);
        ll_xinghao.setOnClickListener(this);
        ll_leixing.setOnClickListener(this);
        ll_guzhang.setOnClickListener(this);
        bt_queren.setOnClickListener(this);


    }

    private void initView() {
        ll_pinpai = (LinearLayout) findViewById(R.id.repair_computer_ll_pinpai);
        ll_leixing = (LinearLayout) findViewById(R.id.repair_computer_ll_leixing);
        ll_xinghao = (LinearLayout) findViewById(R.id.repair_computer_ll_xinghao);
        ll_guzhang = (LinearLayout) findViewById(R.id.repair_computer_ll_guzhang);
        tv_pinpai = (TextView) findViewById(R.id.repair_computer_tv_pinpai);
        tv_leixing = (TextView) findViewById(R.id.repair_computer_tv_leixing);
        tv_xinghao = (TextView) findViewById(R.id.repair_computer_tv_xinghao);
        tv_guzhang = (TextView) findViewById(R.id.repair_computer_tv_guzhang);
        bt_queren = (Button) findViewById(R.id.repair_computer_bt_queren);
        et_guzhangmiaoshu = (EditText) findViewById(R.id.repair_computer_et_guzhangmiaoshu);
        rv = (RecyclerView) findViewById(R.id.repair_computer_rv_picture);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.repair_computer_ll_pinpai:
                //发起请求
                Y.get(new RequestParams(YURL.FIND_COMPUTER_BRAND), new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), RepairjBeen.class);
                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(RepairCompterActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    if (pidIndex == lists.get(options1).getId()) {
                                        tv_pinpai.setText(lists.get(options1).getName());
                                        tv_pinpai.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                        tv_pinpai.setTextColor(Color.parseColor("#00cccc"));
                                        pidIndex = lists.get(options1).getId(); // 当前选择的索引
                                    } else {
                                        tv_pinpai.setText(lists.get(options1).getName());
                                        tv_pinpai.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                        tv_pinpai.setTextColor(Color.parseColor("#00cccc"));
                                        pidIndex = lists.get(options1).getId(); // 当前选择的索引
                                        tv_leixing.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                                        tv_xinghao.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                                        tv_leixing.setText("请选择您的电脑类型");
                                        tv_xinghao.setText("请选择您的电脑型号");
                                        tv_leixing.setTextColor(Color.parseColor("#6b6b6b"));
                                        tv_xinghao.setTextColor(Color.parseColor("#6b6b6b"));
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
            case R.id.repair_computer_ll_leixing:
                if (pidIndex != -1) {
                    RequestParams requestParams1 = new RequestParams(YURL.FIND_COMPUTER_CATEGORY);
                    requestParams1.addBodyParameter("pid", pidIndex + "");
                    //发起请求
                    Y.get(requestParams1, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), RepairjBeen.class);
                                //创建选择器
                                 OptionsPickerView opv= new OptionsPickerView.Builder(RepairCompterActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        if (categoryIndex == lists.get(options1).getId()) {
                                            tv_leixing.setText(lists.get(options1).getName());
                                            tv_leixing.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                            tv_leixing.setTextColor(Color.parseColor("#00cccc"));
                                            categoryIndex = lists.get(options1).getId(); // 当前选择的索引
                                        } else {
                                            tv_leixing.setText(lists.get(options1).getName());
                                            tv_leixing.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                            tv_leixing.setTextColor(Color.parseColor("#00cccc"));
                                            categoryIndex = lists.get(options1).getId(); // 当前选择的索引
                                            tv_xinghao.setText("请选择您的电脑型号");
                                            tv_xinghao.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                                            tv_xinghao.setTextColor(Color.parseColor("#6b6b6b"));
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
            case R.id.repair_computer_ll_xinghao:
                if (categoryIndex != -1) {
                    RequestParams requestParams = new RequestParams(YURL.FIND_COMPUTER_MODEL);
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
                                OptionsPickerView opv = new OptionsPickerView.Builder(RepairCompterActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        tv_xinghao.setText(lists.get(options1).getName());
                                        tv_xinghao.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                        tv_xinghao.setTextColor(Color.parseColor("#00cccc"));
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
            case R.id.repair_computer_ll_guzhang:
                //发起请求
                Y.get(new RequestParams(YURL.FIND_PHONE_FAULT), new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), RepairjBeen.class);
                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(RepairCompterActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    tv_guzhang.setText(lists.get(options1).getName());
                                    tv_guzhang.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                                    tv_guzhang.setTextColor(Color.parseColor("#00cccc"));
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
            case R.id.repair_computer_bt_queren:
                Intent intent = new Intent(this, CallServiceActivity.class);
                startActivity(intent);
                break;
        }
    }
}
