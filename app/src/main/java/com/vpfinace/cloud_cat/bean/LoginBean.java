package com.vpfinace.cloud_cat.bean;

public class LoginBean {
    /**
     * sessionId : 578fc09b-9312-4dca-911f-0ae2ec3a6d1f
     * user : {"createTime":"2020-04-13 10:55:25","updateTime":null,"params":null,"id":57,"name":"15889479929","sex":1,"carNumber":null,"phone":"15889479929","idCard":null,"policy":null,"openId":"oi6crv8WFjfVaCETvvQ0Cqqo7B2c","state":"0","nickname":"今晚打老虎","headImgUrl":"http://thirdwx.qlogo.cn/mmopen/P2SBLVO6oreV7sRF2jb0VhpRAPwn3vqyW8npziaa5WHUrCvv8AXQVrRmiabJmX7CsrJVJX23BzWX4cuaY8eDIiczFsvicZibdrp8s/132","unionId":null,"vipLv":"0","removeFlag":null,"memberId":"158674652526414","carFrameNumber":null}
     */

    public String sessionId;
    public User user;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
