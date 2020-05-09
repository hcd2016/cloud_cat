package com.vpfinace.cloud_cat.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class TopTypeBean implements MultiItemEntity {
    private int type;
    public static final int top1 = 0;
    public static final int top2 = 1;
    public static final int top3 = 2;
    public int rank;
    public String headImg;
    public String name;
    public String getWay;
    public int money;
    public int coinNum;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGetWay() {
        return getWay;
    }

    public void setGetWay(String getWay) {
        this.getWay = getWay;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(int coinNum) {
        this.coinNum = coinNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public TopTypeBean(int type) {
        this.type = type;
    }

    public static int getTop1() {
        return top1;
    }

    public static int getTop2() {
        return top2;
    }

    public static int getTop3() {
        return top3;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
