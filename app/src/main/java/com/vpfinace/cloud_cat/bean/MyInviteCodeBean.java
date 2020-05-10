package com.vpfinace.cloud_cat.bean;

import java.util.List;

public class MyInviteCodeBean  {
    /**
     * diffusionList : [{"createTime":"2020-05-10 18:32:09","updateTime":null,"params":null,"id":null,"name":"Roland","sex":null,"phone":null,"idCard":null,"policy":null,"openId":null,"state":null,"nickname":null,"realname":null,"headImgUrl":null,"unionId":null,"vipLv":null,"removeFlag":null,"memberId":null,"storageNum":null,"userLevel":5,"wechat":null,"qq":null,"alipay":null,"userFund":null,"settingMap":null}]
     * inviteCode : 6483183
     * diffusionNum : 0
     * userInviteList : [{"createTime":"2020-05-10 18:32:09","updateTime":null,"params":null,"id":null,"name":"喵壮士","sex":null,"phone":null,"idCard":null,"policy":null,"openId":null,"state":null,"nickname":null,"realname":null,"headImgUrl":null,"unionId":null,"vipLv":null,"removeFlag":null,"memberId":null,"storageNum":null,"userLevel":5,"wechat":null,"qq":null,"alipay":null,"userFund":null,"settingMap":null}]
     * inviteNum : 1
     */

    private String inviteCode;
    private int diffusionNum;
    private int inviteNum;
    private List<DiffusionListBean> diffusionList;
    private List<UserInviteListBean> userInviteList;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public int getDiffusionNum() {
        return diffusionNum;
    }

    public void setDiffusionNum(int diffusionNum) {
        this.diffusionNum = diffusionNum;
    }

    public int getInviteNum() {
        return inviteNum;
    }

    public void setInviteNum(int inviteNum) {
        this.inviteNum = inviteNum;
    }

    public List<DiffusionListBean> getDiffusionList() {
        return diffusionList;
    }

    public void setDiffusionList(List<DiffusionListBean> diffusionList) {
        this.diffusionList = diffusionList;
    }

    public List<UserInviteListBean> getUserInviteList() {
        return userInviteList;
    }

    public void setUserInviteList(List<UserInviteListBean> userInviteList) {
        this.userInviteList = userInviteList;
    }

    public static class DiffusionListBean {
        /**
         * createTime : 2020-05-10 18:32:09
         * updateTime : null
         * params : null
         * id : null
         * name : Roland
         * sex : null
         * phone : null
         * idCard : null
         * policy : null
         * openId : null
         * state : null
         * nickname : null
         * realname : null
         * headImgUrl : null
         * unionId : null
         * vipLv : null
         * removeFlag : null
         * memberId : null
         * storageNum : null
         * userLevel : 5
         * wechat : null
         * qq : null
         * alipay : null
         * userFund : null
         * settingMap : null
         */

        private String createTime;
        private Object updateTime;
        private Object params;
        private Object id;
        private String name;
        private Object sex;
        private Object phone;
        private Object idCard;
        private Object policy;
        private Object openId;
        private Object state;
        private Object nickname;
        private String realname;
        private Object headImgUrl;
        private Object unionId;
        private Object vipLv;
        private Object removeFlag;
        private Object memberId;
        private Object storageNum;
        private int userLevel;
        private String wechat;
        private String qq;
        private Object alipay;
        private Object userFund;
        private Object settingMap;

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

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
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

        public Object getOpenId() {
            return openId;
        }

        public void setOpenId(Object openId) {
            this.openId = openId;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public Object getHeadImgUrl() {
            return headImgUrl;
        }

        public void setHeadImgUrl(Object headImgUrl) {
            this.headImgUrl = headImgUrl;
        }

        public Object getUnionId() {
            return unionId;
        }

        public void setUnionId(Object unionId) {
            this.unionId = unionId;
        }

        public Object getVipLv() {
            return vipLv;
        }

        public void setVipLv(Object vipLv) {
            this.vipLv = vipLv;
        }

        public Object getRemoveFlag() {
            return removeFlag;
        }

        public void setRemoveFlag(Object removeFlag) {
            this.removeFlag = removeFlag;
        }

        public Object getMemberId() {
            return memberId;
        }

        public void setMemberId(Object memberId) {
            this.memberId = memberId;
        }

        public Object getStorageNum() {
            return storageNum;
        }

        public void setStorageNum(Object storageNum) {
            this.storageNum = storageNum;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public Object getAlipay() {
            return alipay;
        }

        public void setAlipay(Object alipay) {
            this.alipay = alipay;
        }

        public Object getUserFund() {
            return userFund;
        }

        public void setUserFund(Object userFund) {
            this.userFund = userFund;
        }

        public Object getSettingMap() {
            return settingMap;
        }

        public void setSettingMap(Object settingMap) {
            this.settingMap = settingMap;
        }
    }

    public static class UserInviteListBean {
        /**
         * createTime : 2020-05-10 18:32:09
         * updateTime : null
         * params : null
         * id : null
         * name : 喵壮士
         * sex : null
         * phone : null
         * idCard : null
         * policy : null
         * openId : null
         * state : null
         * nickname : null
         * realname : null
         * headImgUrl : null
         * unionId : null
         * vipLv : null
         * removeFlag : null
         * memberId : null
         * storageNum : null
         * userLevel : 5
         * wechat : null
         * qq : null
         * alipay : null
         * userFund : null
         * settingMap : null
         */

        private String createTime;
        private Object updateTime;
        private Object params;
        private Object id;
        private String name;
        private Object sex;
        private Object phone;
        private Object idCard;
        private Object policy;
        private Object openId;
        private Object state;
        private Object nickname;
        private String realname;
        private Object headImgUrl;
        private Object unionId;
        private Object vipLv;
        private Object removeFlag;
        private Object memberId;
        private Object storageNum;
        private int userLevel;
        private String wechat;
        private String qq;
        private Object alipay;
        private Object userFund;
        private Object settingMap;

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

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
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

        public Object getOpenId() {
            return openId;
        }

        public void setOpenId(Object openId) {
            this.openId = openId;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public Object getHeadImgUrl() {
            return headImgUrl;
        }

        public void setHeadImgUrl(Object headImgUrl) {
            this.headImgUrl = headImgUrl;
        }

        public Object getUnionId() {
            return unionId;
        }

        public void setUnionId(Object unionId) {
            this.unionId = unionId;
        }

        public Object getVipLv() {
            return vipLv;
        }

        public void setVipLv(Object vipLv) {
            this.vipLv = vipLv;
        }

        public Object getRemoveFlag() {
            return removeFlag;
        }

        public void setRemoveFlag(Object removeFlag) {
            this.removeFlag = removeFlag;
        }

        public Object getMemberId() {
            return memberId;
        }

        public void setMemberId(Object memberId) {
            this.memberId = memberId;
        }

        public Object getStorageNum() {
            return storageNum;
        }

        public void setStorageNum(Object storageNum) {
            this.storageNum = storageNum;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public Object getAlipay() {
            return alipay;
        }

        public void setAlipay(Object alipay) {
            this.alipay = alipay;
        }

        public Object getUserFund() {
            return userFund;
        }

        public void setUserFund(Object userFund) {
            this.userFund = userFund;
        }

        public Object getSettingMap() {
            return settingMap;
        }

        public void setSettingMap(Object settingMap) {
            this.settingMap = settingMap;
        }
    }
}
