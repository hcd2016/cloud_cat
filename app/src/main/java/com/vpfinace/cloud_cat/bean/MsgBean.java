package com.vpfinace.cloud_cat.bean;

public class MsgBean {

    /**
     * createTime : 2020-05-08 12:10:20
     * updateTime : null
     * params : null
     * title : 提现成功
     * content : 你提现的￥0.3元提现成功，扣除手续费实际到帐0.3元。请在微信零钱查看
     * readStatus : 1
     */

    public String createTime;
    public Object updateTime;
    public Object params;
    public String title;
    public String content;
    public int readStatus;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }
}
