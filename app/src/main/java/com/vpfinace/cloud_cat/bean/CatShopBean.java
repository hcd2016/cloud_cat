package com.vpfinace.cloud_cat.bean;

public class CatShopBean {
    /**
     * createTime : 2020-04-14 14:55:39
     * updateTime : null
     * params : null
     * id : 1
     * level : 1
     * title : 暹罗猫
     * img : https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat01.png
     * output : 2
     * price1 : 1000
     * price2 : 10540
     * price3 : 10540
     * gcprice : 1
     * buyStatus : 0
     */

    public String createTime;
    public Object updateTime;
    public Object params;
    public int id;
    public int level;
    public String title;
    public String img;
    public long output;
    public long price1;
    public long price2;
    public long price3;
    public long gcprice;
    public int buyStatus;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getOutput() {
        return output;
    }

    public void setOutput(long output) {
        this.output = output;
    }

    public long getPrice1() {
        return price1;
    }

    public void setPrice1(long price1) {
        this.price1 = price1;
    }

    public long getPrice2() {
        return price2;
    }

    public void setPrice2(long price2) {
        this.price2 = price2;
    }

    public long getPrice3() {
        return price3;
    }

    public void setPrice3(long price3) {
        this.price3 = price3;
    }

    public long getGcprice() {
        return gcprice;
    }

    public void setGcprice(long gcprice) {
        this.gcprice = gcprice;
    }

    public int getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(int buyStatus) {
        this.buyStatus = buyStatus;
    }
}
