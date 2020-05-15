package com.vpfinace.cloud_cat.bean;

public class UserCenter {

    /**
     * processing : 0.05
     * redpack : {"id":137,"amount":116,"reason":"升级至5级获得","status":1}
     */

    public String processing;
    public RedpackBean redpack;

    public String getProcessing() {
        return processing;
    }

    public void setProcessing(String processing) {
        this.processing = processing;
    }

    public RedpackBean getRedpack() {
        return redpack;
    }

    public void setRedpack(RedpackBean redpack) {
        this.redpack = redpack;
    }

    public static class RedpackBean {
        /**
         * id : 137
         * amount : 116
         * reason : 升级至5级获得
         * status : 1
         */

        public int id;
        public int amount;
        public String explain;
        public int status;
        public int userId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String reason) {
            this.explain = explain;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
