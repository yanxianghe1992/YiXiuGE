package com.yxh.yixiuge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youth.banner.Banner;
import com.yxh.yixiuge.R;
import com.yxh.yixiuge.base.BaseActivity;
import com.yxh.yixiuge.imageloader.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {
    @Bind(R.id.home_banner)
    Banner homeBanner;
    @Bind(R.id.home_ll_mobile)
    LinearLayout homeLlMobile;
    @Bind(R.id.home_ll_computer)
    LinearLayout homeLlComputer;
    @Bind(R.id.home_ll_appliance)
    LinearLayout homeLlAppliance;
    @Bind(R.id.home_iv_person)
    ImageView homeIvPerson;
    private Intent intent;
    private List images = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        images.add(R.mipmap.u6);
        images.add(R.mipmap.timg);
        images.add(R.mipmap.u6);
        images.add(R.mipmap.timg);
        homeBanner.setImageLoader(new GlideImageLoader());
        homeBanner.setImages(images);
        homeBanner.start();


    }

    @OnClick({R.id.home_ll_mobile, R.id.home_ll_computer, R.id.home_ll_appliance, R.id.home_iv_person})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_ll_mobile:
                intent = new Intent(this, RepairPhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.home_ll_computer:
                intent = new Intent(this, RepairCompterActivity.class);
                startActivity(intent);
                break;
            case R.id.home_ll_appliance:
                intent = new Intent(this, RepairApplianceActivity.class);
                startActivity(intent);
                break;
            case R.id.home_iv_person:
                intent = new Intent(this, PersonalCenterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
