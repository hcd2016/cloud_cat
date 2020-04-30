package com.vpfinace.cloud_cat.bean;

import java.util.List;

public class HomeBean {
    /**
     * dilation : 2000
     * storageNum : 24
     * fund : {"createTime":null,"updateTime":"2020-04-15 10:39:30","params":null,"id":55,"userId":57,"coin":0,"cash":0}
     * cat : null
     * list : [{"createTime":null,"updateTime":"2020-04-20 11:25:17","params":null,"id":32,"userId":57,"catId":4,"catLevel":4,"img":"https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat03.jpg","storageId":3,"output":null},{"createTime":null,"updateTime":null,"params":null,"id":33,"userId":57,"catId":1,"catLevel":1,"img":"https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat01.jpg","storageId":4,"output":null},{"createTime":null,"updateTime":null,"params":null,"id":35,"userId":57,"catId":2,"catLevel":2,"img":"https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat02.jpg","storageId":6,"output":null},{"createTime":null,"updateTime":null,"params":null,"id":36,"userId":57,"catId":3,"catLevel":3,"img":"https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat03.jpg","storageId":7,"output":null},{"createTime":null,"updateTime":null,"params":null,"id":37,"userId":57,"catId":4,"catLevel":4,"img":"https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat04.jpg","storageId":8,"output":null},{"createTime":null,"updateTime":null,"params":null,"id":38,"userId":57,"catId":5,"catLevel":5,"img":"https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat05.jpg","storageId":9,"output":null},{"createTime":null,"updateTime":null,"params":null,"id":39,"userId":57,"catId":4,"catLevel":4,"img":"https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat04.jpg","storageId":10,"output":null},{"createTime":null,"updateTime":null,"params":null,"id":40,"userId":57,"catId":1,"catLevel":1,"img":"https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat01.jpg","storageId":11,"output":null}]
     * aderanings : 159.98
     */

    public int dilation;
    public int storageNum;
    public FundBean fund;
    public CatShopBean cat;
    public double aderanings;
    public List<CatBean> list;

    public int getDilation() {
        return dilation;
    }

    public void setDilation(int dilation) {
        this.dilation = dilation;
    }

    public int getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(int storageNum) {
        this.storageNum = storageNum;
    }

    public FundBean getFund() {
        return fund;
    }

    public void setFund(FundBean fund) {
        this.fund = fund;
    }

    public CatShopBean getCat() {
        return cat;
    }

    public void setCat(CatShopBean cat) {
        this.cat = cat;
    }

    public double getAderanings() {
        return aderanings;
    }

    public void setAderanings(double aderanings) {
        this.aderanings = aderanings;
    }

    public List<CatBean> getList() {
        return list;
    }

    public void setList(List<CatBean> list) {
        this.list = list;
    }

    public static class FundBean {
        /**
         * createTime : null
         * updateTime : 2020-04-15 10:39:30
         * params : null
         * id : 55
         * userId : 57
         * coin : 0
         * cash : 0
         */

        public Object createTime;
        public String updateTime;
        public Object params;
        public int id;
        public int userId;
        public int coin;
        public int cash;
    }
}
