package com.vpfinace.cloud_cat.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class TopTypeBean implements MultiItemEntity {
    private int type;
    public static final int top1 = 0;
    public static final int top2 = 1;
    public static final int top3 = 2;
    String date;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
