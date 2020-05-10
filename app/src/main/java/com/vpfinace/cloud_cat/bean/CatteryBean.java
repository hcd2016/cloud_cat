package com.vpfinace.cloud_cat.bean;

import java.util.List;

//猫舍
public class CatteryBean {
    /**
     * myInviter : {"createTime":"2020-04-13 10:55:25","updateTime":"2020-05-09 14:42:11","params":null,"id":57,"name":"15889479929","sex":1,"phone":"15889479929","idCard":null,"policy":null,"openId":"oi6crv8WFjfVaCETvvQ0Cqqo7B2c","state":"0","nickname":"嘎嘎嘎","realname":"发的","headImgUrl":"http://cdn.mtbsoft.net/images/2020/05/08/15889287254151161.jpg","unionId":null,"vipLv":"0","removeFlag":null,"memberId":"158674652526414","storageNum":54,"userLevel":1,"wechat":null,"qq":null,"alipay":null,"userFund":null,"settingMap":null}
     * todayDiffusionEarning : 0
     * rate : 10
     * friendNum : 0
     * todayInviteEarning : 0
     * step : 1
     * steplist : [{"createTime":null,"updateTime":null,"params":null,"step":1,"targetNum":20,"rate":1.1},{"createTime":null,"updateTime":null,"params":null,"step":2,"targetNum":50,"rate":1.2},{"createTime":null,"updateTime":null,"params":null,"step":3,"targetNum":100,"rate":1.3},{"createTime":null,"updateTime":null,"params":null,"step":4,"targetNum":200,"rate":1.4},{"createTime":null,"updateTime":null,"params":null,"step":5,"targetNum":500,"rate":1.5},{"createTime":null,"updateTime":null,"params":null,"step":6,"targetNum":1000,"rate":1.6},{"createTime":null,"updateTime":null,"params":null,"step":7,"targetNum":2000,"rate":1.7},{"createTime":null,"updateTime":null,"params":null,"step":8,"targetNum":3000,"rate":1.8},{"createTime":null,"updateTime":null,"params":null,"step":9,"targetNum":4000,"rate":1.9},{"createTime":null,"updateTime":null,"params":null,"step":10,"targetNum":5000,"rate":2}]
     * myInviterEarning : 0
     * totalEarning : 0
     * todayTotalEarning : 0
     * myInviterNum : 0
     * target : 20
     */

    private MyInviterBean myInviter;
    private double todayDiffusionEarning;
    private double rate;
    private int friendNum;
    private double todayInviteEarning;
    private int step;
    private double myInviterEarning;
    private double totalEarning;
    private double todayTotalEarning;
    private int myInviterNum;
    private double target;
    private List<SteplistBean> steplist;

    public MyInviterBean getMyInviter() {
        return myInviter;
    }

    public void setMyInviter(MyInviterBean myInviter) {
        this.myInviter = myInviter;
    }

    public double getTodayDiffusionEarning() {
        return todayDiffusionEarning;
    }

    public void setTodayDiffusionEarning(double todayDiffusionEarning) {
        this.todayDiffusionEarning = todayDiffusionEarning;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getFriendNum() {
        return friendNum;
    }

    public void setFriendNum(int friendNum) {
        this.friendNum = friendNum;
    }

    public double getTodayInviteEarning() {
        return todayInviteEarning;
    }

    public void setTodayInviteEarning(double todayInviteEarning) {
        this.todayInviteEarning = todayInviteEarning;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public double getMyInviterEarning() {
        return myInviterEarning;
    }

    public void setMyInviterEarning(double myInviterEarning) {
        this.myInviterEarning = myInviterEarning;
    }

    public double getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(double totalEarning) {
        this.totalEarning = totalEarning;
    }

    public double getTodayTotalEarning() {
        return todayTotalEarning;
    }

    public void setTodayTotalEarning(double todayTotalEarning) {
        this.todayTotalEarning = todayTotalEarning;
    }

    public int getMyInviterNum() {
        return myInviterNum;
    }

    public void setMyInviterNum(int myInviterNum) {
        this.myInviterNum = myInviterNum;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public List<SteplistBean> getSteplist() {
        return steplist;
    }

    public void setSteplist(List<SteplistBean> steplist) {
        this.steplist = steplist;
    }

    public static class MyInviterBean {
        /**
         * createTime : 2020-04-13 10:55:25
         * updateTime : 2020-05-09 14:42:11
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
         * userFund : null
         * settingMap : null
         */

        private String createTime;
        private String updateTime;
        private Object params;
        private int id;
        private String name;
        private int sex;
        private String phone;
        private Object idCard;
        private Object policy;
        private String openId;
        private String state;
        private String nickname;
        private String realname;
        private String headImgUrl;
        private Object unionId;
        private String vipLv;
        private Object removeFlag;
        private String memberId;
        private int storageNum;
        private int userLevel;
        private Object wechat;
        private Object qq;
        private Object alipay;
        private Object userFund;
        private Object settingMap;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

    public static class SteplistBean {
        /**
         * createTime : null
         * updateTime : null
         * params : null
         * step : 1
         * targetNum : 20
         * rate : 1.1
         */

        private Object createTime;
        private Object updateTime;
        private Object params;
        private int step;
        private int targetNum;
        private double rate;

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

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public int getTargetNum() {
            return targetNum;
        }

        public void setTargetNum(int targetNum) {
            this.targetNum = targetNum;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }
    }
}
