package com.vpfinace.cloud_cat.bean;

public class WalletBean {
    /**
     * firstWithsraw : 0
     * fund : {"createTime":null,"updateTime":"2020-05-12 16:57:08","params":null,"id":55,"userId":57,"coin":173866,"cash":10}
     * fee : 3
     */

    public int firstWithsraw;//0为非首次提现,1为首次提现
    public int fee;

    public int getFirstWithsraw() {
        return firstWithsraw;
    }

    public void setFirstWithsraw(int firstWithsraw) {
        this.firstWithsraw = firstWithsraw;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    /**
     * fund : {"createTime":null,"updateTime":"2020-05-10 15:59:34","params":null,"id":55,"userId":57,"coin":134443,"cash":0}
     */

    private FundBean fund;

    public FundBean getFund() {
        return fund;
    }

    public void setFund(FundBean fund) {
        this.fund = fund;
    }

    public static class FundBean {
        /**
         * createTime : null
         * updateTime : 2020-05-10 15:59:34
         * params : null
         * id : 55
         * userId : 57
         * coin : 134443
         * cash : 0
         */

        private Object createTime;
        private String updateTime;
        private Object params;
        private int id;
        private int userId;
        private long coin;
        private double cash;

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
