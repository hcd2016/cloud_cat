package com.vpfinace.cloud_cat.bean;

public class CatShopBean {
    /**
     * createTime : 2020-04-14 14:55:39
     * updateTime : null
     * params : null
     * id : 18
     * level : 1
     * title : 1级猫咪
     * img : https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat1.jpg
     * output : 1
     * price1 : null
     * price2 : null
     * price3 : null
     */

    public String createTime;
    public Object updateTime;
    public Object params;
    public int id;
    public int level;
    public String title;
    public String img;
    public int output;
    public String price1;
    public String price2;
    public String price3;
    public String gcprice;
    public int buyStatus;//1.可购买 0.不可购买

    public String getGcprice() {
        return gcprice;
    }

    public void setGcprice(String gcprice) {
        this.gcprice = gcprice;
    }



    public int getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(int buyStatus) {
        this.buyStatus = buyStatus;
    }



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

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public Object getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getPrice3() {
        return price3;
    }

    public void setPrice3(String price3) {
        this.price3 = price3;
    }
}
