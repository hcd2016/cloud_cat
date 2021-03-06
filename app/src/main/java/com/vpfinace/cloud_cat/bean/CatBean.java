package com.vpfinace.cloud_cat.bean;

import android.view.View;

public class CatBean {
    public  View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    /**
     * createTime : null
     * updateTime : null
     * params : null
     * id : 30
     * userId : 57
     * catId : 1
     * catLevel : 1
     * img : https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat01.jpg
     * storageId : 1
     * output : null
     */

    private transient View view;//view对象


    public Object createTime;
    public Object updateTime;
    public Object params;
    public int id;
    public int userId;
    public int catId;
    public int catLevel;
    public String img;
    public int storageId;
    public long output;
    public String title;
    public String price;
    public String price1;
    public long gcPrice;
    public String catName;
    private long offlineoutput;

    public long getOfflineoutput() {
        return offlineoutput;
    }

    public void setOfflineoutput(long offlineoutput) {
        this.offlineoutput = offlineoutput;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }



    public long getGcPrice() {
        return gcPrice;
    }

    public void setGcPrice(long gcPrice) {
        this.gcPrice = gcPrice;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int level;//全猫咪列表时候的等级

    public long getGcprice() {
        return gcPrice;
    }

    public void setGcprice(long gcprice) {
        this.gcPrice = gcprice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getCatLevel() {
        return catLevel;
    }

    public void setCatLevel(int catLevel) {
        this.catLevel = catLevel;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public long getOutput() {
        return output;
    }

    public void setOutput(long output) {
        this.output = output;
    }
}
