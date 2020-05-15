package com.vpfinace.cloud_cat.bean;

public class MergeBean {
    /**
     * currLevel : 10
     * msg : 合成成功
     * cat : {"img":"https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat10.png","params":null,"updateTime":null,"price3":26982400,"id":10,"price2":26982400,"level":10,"output":1024,"createTime":"2020-04-14 14:55:39","title":"孟买猫","gcprice":2560,"buyStatus":null,"price1":2560000}
     * levelUp : true
     * remainLevel : 28
     */

    public int currLevel;
    public String msg;
    public CatBean cat;
    public boolean levelUp;
    public int remainLevel;

    public int getCurrLevel() {
        return currLevel;
    }

    public void setCurrLevel(int currLevel) {
        this.currLevel = currLevel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CatBean getCat() {
        return cat;
    }

    public void setCat(CatBean cat) {
        this.cat = cat;
    }

    public boolean isLevelUp() {
        return levelUp;
    }

    public void setLevelUp(boolean levelUp) {
        this.levelUp = levelUp;
    }

    public int getRemainLevel() {
        return remainLevel;
    }

    public void setRemainLevel(int remainLevel) {
        this.remainLevel = remainLevel;
    }

    public static class CatBean {
        /**
         * img : https://cloudcat.oss-cn-shenzhen.aliyuncs.com/cat10.png
         * params : null
         * updateTime : null
         * price3 : 26982400
         * id : 10
         * price2 : 26982400
         * level : 10
         * output : 1024
         * createTime : 2020-04-14 14:55:39
         * title : 孟买猫
         * gcprice : 2560
         * buyStatus : null
         * price1 : 2560000
         */

        public String img;
        public Object params;
        public Object updateTime;
        public int price3;
        public int id;
        public int price2;
        public int level;
        public int output;
        public String createTime;
        public String title;
        public int gcprice;
        public Object buyStatus;
        public int price1;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public Object getParams() {
            return params;
        }

        public void setParams(Object params) {
            this.params = params;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public int getPrice3() {
            return price3;
        }

        public void setPrice3(int price3) {
            this.price3 = price3;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPrice2() {
            return price2;
        }

        public void setPrice2(int price2) {
            this.price2 = price2;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getOutput() {
            return output;
        }

        public void setOutput(int output) {
            this.output = output;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getGcprice() {
            return gcprice;
        }

        public void setGcprice(int gcprice) {
            this.gcprice = gcprice;
        }

        public Object getBuyStatus() {
            return buyStatus;
        }

        public void setBuyStatus(Object buyStatus) {
            this.buyStatus = buyStatus;
        }

        public int getPrice1() {
            return price1;
        }

        public void setPrice1(int price1) {
            this.price1 = price1;
        }
    }
}
