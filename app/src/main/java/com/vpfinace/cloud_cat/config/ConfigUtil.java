package com.vpfinace.cloud_cat.config;


public class ConfigUtil {

//    public static String baseUrl = "http://39.108.146.92:8088/admin/";//测试服务器
//    public static String baseUrl = "http://192.168.1.3:8088/admin/";//测试服务器
    public static String baseUrl = "http://39.108.106.10:8088/admin/";//测试服务器

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
