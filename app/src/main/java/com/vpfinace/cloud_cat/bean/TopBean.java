package com.vpfinace.cloud_cat.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class TopBean {
    public List<TopListBean> coinRankList;
    public List<TopListBean> totalEarningRank;
    public List<TopListBean> luckyCatEarningRank;

    public List<TopListBean> getCoinRankList() {
        return coinRankList;
    }

    public void setCoinRankList(List<TopListBean> coinRankList) {
        this.coinRankList = coinRankList;
    }

    public List<TopListBean> getTotalEarningRank() {
        return totalEarningRank;
    }

    public void setTotalEarningRank(List<TopListBean> totalEarningRank) {
        this.totalEarningRank = totalEarningRank;
    }

    public List<TopListBean> getLuckyCatEarningRank() {
        return luckyCatEarningRank;
    }

    public void setLuckyCatEarningRank(List<TopListBean> luckyCatEarningRank) {
        this.luckyCatEarningRank = luckyCatEarningRank;
    }

    public static class TopListBean implements MultiItemEntity {
        /**
         * rank : 1
         * headImg : https://i.52112.com/icon/jpg/256/20190705/46779/2169925.jpg
         * name : 测试数据1
         * getWay : 拖枪挂帅Lv.38
         * money : 0
         * coinNum : 99
         */

        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int rank;
        public String headImg;
        public String name;
        public String getWay;
        public int money;
        public int coinNum;

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGetWay() {
            return getWay;
        }

        public void setGetWay(String getWay) {
            this.getWay = getWay;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getCoinNum() {
            return coinNum;
        }

        public void setCoinNum(int coinNum) {
            this.coinNum = coinNum;
        }

        @Override
        public int getItemType() {
            return type;
        }
    }
}
