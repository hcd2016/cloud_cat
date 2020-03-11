package com.vpfinace.cloud_cat.config;


public class ConfigUtil {

    //服务器地址
//    public static String baseUrl = "http://new.vpfinance.cn/";//测试服务器
//    public static String baseUrl = "http://www.vpfinance.cn/";//正式服务器
    public static String baseUrl = "http://111.231.249.34/";//正式服务器

    private boolean isDebug = false;//是否调试模式
    public static Long difServerTime = 0L;//服务器与客户端时间差


    public boolean isDebug() {
        return isDebug;
    }
    public void setDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }
    public String getBaseUrl() {
        return baseUrl;
    }
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
