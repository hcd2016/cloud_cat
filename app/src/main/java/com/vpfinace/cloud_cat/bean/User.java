package com.vpfinace.cloud_cat.bean;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * createTime : 2020-04-13 10:55:25
     * updateTime : null
     * params : null
     * id : 57
     * name : 15889479929
     * sex : 1
     * carNumber : null
     * phone : 15889479929
     * idCard : null
     * policy : null
     * openId : oi6crv8WFjfVaCETvvQ0Cqqo7B2c
     * state : 0
     * nickname : 今晚打老虎
     * headImgUrl : http://thirdwx.qlogo.cn/mmopen/P2SBLVO6oreV7sRF2jb0VhpRAPwn3vqyW8npziaa5WHUrCvv8AXQVrRmiabJmX7CsrJVJX23BzWX4cuaY8eDIiczFsvicZibdrp8s/132
     * unionId : null
     * vipLv : 0
     * removeFlag : null
     * memberId : 158674652526414
     * carFrameNumber : null
     */

    public String createTime;
    public Object updateTime;
    public Object params;
    public String id;
    public String name;
    public int sex;
    public Object carNumber;
    public String phone;
    public Object idCard;
    public Object policy;
    public String openId;
    public String state;
    public String nickname;
    public String headImgUrl;
    public Object unionId;
    public String vipLv;
    public Object removeFlag;
    public String memberId;
    public Object carFrameNumber;
    public int storageNum;//仓库总容量

    public int getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(int storageNum) {
        this.storageNum = storageNum;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Object getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Object carNumber) {
        this.carNumber = carNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getIdCard() {
        return idCard;
    }

    public void setIdCard(Object idCard) {
        this.idCard = idCard;
    }

    public Object getPolicy() {
        return policy;
    }

    public void setPolicy(Object policy) {
        this.policy = policy;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Object getUnionId() {
        return unionId;
    }

    public void setUnionId(Object unionId) {
        this.unionId = unionId;
    }

    public String getVipLv() {
        return vipLv;
    }

    public void setVipLv(String vipLv) {
        this.vipLv = vipLv;
    }

    public Object getRemoveFlag() {
        return removeFlag;
    }

    public void setRemoveFlag(Object removeFlag) {
        this.removeFlag = removeFlag;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Object getCarFrameNumber() {
        return carFrameNumber;
    }

    public void setCarFrameNumber(Object carFrameNumber) {
        this.carFrameNumber = carFrameNumber;
    }
}
