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
    public static String coin2String(Long coin) {
        double pow = Math.pow(10, 3);
        if(coin > Math.pow(10, 5) && coin <= Math.pow(10, 6)) {//k
            return ArithUtil.div(coin,Math.pow(10, 3),2) + "k";
        }
        if(coin > Math.pow(10, 6) && coin <= Math.pow(10, 9)) {//m
            return ArithUtil.div(coin,Math.pow(10, 6),2) + "m";
        }
        if(coin > Math.pow(10, 9) && coin <= Math.pow(10, 12)) {//b
            return ArithUtil.div(coin,Math.pow(10, 9),2) + "b";
        }
        if(coin > Math.pow(10, 12) && coin <= Math.pow(10, 15)) {//t
            return ArithUtil.div(coin,Math.pow(10, 12),2) + "t";
        }
        if(coin > Math.pow(10, 15) && coin <= Math.pow(10, 18)) {//aa
            return ArithUtil.div(coin,Math.pow(10, 15),2) + "aa";
        }
        if(coin > Math.pow(10, 18) && coin <= Math.pow(10, 22)) {//ab
            return ArithUtil.div(coin,Math.pow(10, 18),2) + "ab";
        }
        else {
            return coin + "";
        }
    }
}
