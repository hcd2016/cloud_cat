package com.vpfinace.cloud_cat.bean;

public class WheelResultBean {
    /**
     * result : 3
     * coin : 0
     * second : 2700
     */

    private int result;
    private long coin;
    private long second;
    private UserCenter.RedpackBean redpack;

    public UserCenter.RedpackBean getRedpack() {
        return redpack;
    }

    public void setRedpack(UserCenter.RedpackBean redpack) {
        this.redpack = redpack;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public long getCoin() {
        return coin;
    }

    public void setCoin(long coin) {
        this.coin = coin;
    }

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        this.second = second;
    }
}
