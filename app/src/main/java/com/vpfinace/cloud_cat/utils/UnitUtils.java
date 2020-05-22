package com.vpfinace.cloud_cat.utils;

/**
 * 单位换算
 * 1k = 1,000 = 10^3
 * 1m = 1,000,000 = 10^6
 * 1b = 1,000,000,000 = 10^9
 * 1t =  1,000,000,000,000 = 10^12
 * 1aa = 1,000,000,000,000,000 = 10^15
 * 1ab = 1,000,000,000,000,000,000 = 10^18
 */
public class UnitUtils {
    public static String coin2String(long coin) {
        double pow = Math.pow(10, 3);
        if (coin <= Math.pow(10, 5)) {
            return coin + "";
        }
        if (coin > Math.pow(10, 5) && coin <= Math.pow(10, 8)) {//k

            return nPoint(ArithUtil.div(coin, Math.pow(10, 3), 0)) + "k";
        }
        if (coin > Math.pow(10, 8) && coin <= Math.pow(10, 11)) {//m
            return nPoint(ArithUtil.div(coin, Math.pow(10, 6), 0)) + "m";
        }
        if (coin > Math.pow(10, 11) && coin <= Math.pow(10, 14)) {//b
            return nPoint(ArithUtil.div(coin, Math.pow(10, 9), 0)) + "b";
        }
        if (coin > Math.pow(10, 14) && coin <= Math.pow(10, 17)) {//t
            return nPoint(ArithUtil.div(coin, Math.pow(10, 12), 0)) + "t";
        }
        if (coin > Math.pow(10, 17) && coin <= Math.pow(10, 20)) {//aa
            return nPoint(ArithUtil.div(coin, Math.pow(10, 15), 0)) + "aa";
        } else {
            return nPoint(ArithUtil.div(coin, Math.pow(10, 18), 0)) + "ab";
        }
    }

    /**
     * 去除小数点
     */
    public static String nPoint(Double value){
        if(String.valueOf(value).contains(".")) {
            return value.toString().split("\\.")[0];
        }else {
            return value.toString();
        }
    }
}
