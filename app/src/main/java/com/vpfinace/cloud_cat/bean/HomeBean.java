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
    public long offline_earning;//离线收益
    public int draw_earning_way;//领取离线收益方式,1是广告,2是分享

    public int getDraw_earning_way() {
        return draw_earning_way;
    }

    public void setDraw_earning_way(int draw_earning_way) {
        this.draw_earning_way = draw_earning_way;
    }

    public long getOffline_earning() {
        return offline_earning;
    }

    public void setOffline_earning(long offline_earning) {
        this.offline_earning = offline_earning;
    }

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
        public long coin;
        public double cash;

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public long getCoin() {
            return coin;
        }

        public void setCoin(long coin) {
            this.coin = coin;
        }

        public double getCash() {
            return cash;
        }

        public void setCash(double cash) {
            this.cash = cash;
        }
    }
}
