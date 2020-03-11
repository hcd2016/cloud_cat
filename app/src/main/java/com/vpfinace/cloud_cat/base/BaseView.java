package com.vpfinace.cloud_cat.base;

public interface BaseView {
    /**
     * 展示加载
     */
    void showLoading();

    /**
     * 加载完成
     */
    void FinishLoading();

    /**
     * 加载失败
     * @param msg
     */
    void onError(String msg);
}
