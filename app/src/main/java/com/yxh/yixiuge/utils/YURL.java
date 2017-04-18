package com.yxh.yixiuge.utils;

/**
 * 地址工具类
 * Created by Administrator on 2017/4/15.
 */

public class YURL {


    // 服务器地址
    public static final String BASE_HOST = "http://221.207.184.124:7071/";


    //项目名称
    public static final String HOST = BASE_HOST + "yxg/";


    //查找手机品牌
    public static final String FIND_PHONE_BRAND = HOST + "findPhoneBrand";
    //根据品牌查找型号
    public static final String FIND_PHONE_MODEL = HOST + "findPhoneModel";
    //查询手机故障
    public static final String FIND_PHONE_FAULT = HOST + "findPhoneFault";

    //查询电脑品牌
    public static final String FIND_COMPUTER_BRAND = HOST + "findComputerBrand";
    //查询电脑型号
    public static final String FIND_COMPUTER_MODEL = HOST + "findByComputerModel";
    //查询电脑分类
    public static final String FIND_COMPUTER_CATEGORY = HOST + "findComputerCategory";

    //查询家电品牌
    public static final String FIND_APPLIANCE_BRAND = HOST + "findByApplianceBrand";
    //查询家电分类
    public static final String FIND_APPLIANCE_CATEGORY = HOST + "findApplianceCategory";
    //查询家电型号
    public static final String FIND_APPLIANCE_MODEL = HOST + "findByApplianceModel";


}
