package com.vpfinace.cloud_cat.bean;

public class ShareBean {
    /**
     * headImg : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLAjpkKlg6JakTXPFA4r6PsewsOggvSooXYmxYRYklXQ3DLxWaS4UVBxcNicjs4gTwib2HicTnDEUlJNw/132
     * inviteCode : 47
     * title : Hi,我是黄绍清
     * desc : 送你一只哈瓦那棕猫让我们一起撸猫挣钱吧!
     */

    public String headImg;
    public int inviteCode;
    public String title;
    public String desc;
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(int inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
