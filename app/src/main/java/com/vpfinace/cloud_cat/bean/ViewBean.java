package com.vpfinace.cloud_cat.bean;

import android.view.View;

public class ViewBean {
    private View view;//view对象
    private int level=1;//猫等级
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
