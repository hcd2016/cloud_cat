package com.vpfinace.cloud_cat.bean;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * createTime : 2020-04-13 10:55:25
     * updateTime : 2020-05-08 17:51:56
     * params : null
     * id : 57
     * name : 15889479929
     * sex : 1
     * phone : 15889479929
     * idCard : null
     * policy : null
     * openId : oi6crv8WFjfVaCETvvQ0Cqqo7B2c
     * state : 0
     * nickname : 嘎嘎嘎
     * realname : 发的
     * headImgUrl : http://cdn.mtbsoft.net/images/2020/05/08/15889287254151161.jpg
     * unionId : null
     * vipLv : 0
     * removeFlag : null
     * memberId : 158674652526414
     * storageNum : 54
     * userLevel : 1
     * wechat : null
     * qq : null
     * alipay : null
     * userFund : {"createTime":null,"updateTime":"2020-05-09 14:31:49","params":null,"id":55,"userId":57,"coin":5590,"cash":0}
     * settingMap : {"inviterVisible":"1","friendVisible":"1"}
     */

    public String createTime;
    public String updateTime;
    public Object params;
    public String id;
    public String name;
    public int sex;
    public String phone;
    public Object idCard;
    public Object policy;
    public String openId;
    public String state;
    public String nickname;
    public String realname;
    public String headImgUrl;
    public Object unionId;
    public String vipLv;
    public Object removeFlag;
    public String memberId;
    public int storageNum;
    public int userLevel;
    public Object wechat;
    public Object qq;
    public Object alipay;
    public HomeBean.FundBean userFund;

    public HomeBean.FundBean getUserFund() {
        return userFund;
    }

    public void setUserFund(HomeBean.FundBean userFund) {
        this.userFund = userFund;
    }

    public SettingMapBean settingMap;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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

    public int getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(int storageNum) {
        this.storageNum = storageNum;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public Object getWechat() {
        return wechat;
    }

    public void setWechat(Object wechat) {
        this.wechat = wechat;
    }

    public Object getQq() {
        return qq;
    }

    public void setQq(Object qq) {
        this.qq = qq;
    }

    public Object getAlipay() {
        return alipay;
    }

    public void setAlipay(Object alipay) {
        this.alipay = alipay;
    }

    public SettingMapBean getSettingMap() {
        return settingMap;
    }

    public void setSettingMap(SettingMapBean settingMap) {
        this.settingMap = settingMap;
    }

    public static class SettingMapBean {
        /**
         * inviterVisible : 1
         * friendVisible : 1
         */

        public int inviterVisible;
        public int friendVisible;

        public int getInviterVisible() {
            return inviterVisible;
        }

        public void setInviterVisible(int inviterVisible) {
            this.inviterVisible = inviterVisible;
        }

        public int getFriendVisible() {
            return friendVisible;
        }

        public void setFriendVisible(int friendVisible) {
            this.friendVisible = friendVisible;
        }
    }
}
