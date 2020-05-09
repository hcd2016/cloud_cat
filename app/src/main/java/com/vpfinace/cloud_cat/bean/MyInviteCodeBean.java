package com.vpfinace.cloud_cat.bean;

import java.util.List;

public class MyInviteCodeBean  {
    /**
     * diffusionList : []
     * inviteCode : 6483183
     * diffusionNum : 0
     * userInviteList : []
     * inviteNum : 1
     */

    public String inviteCode;
    public int diffusionNum;
    public int inviteNum;
    public List<?> diffusionList;
    public List<?> userInviteList;

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

    public List<?> getDiffusionList() {
        return diffusionList;
    }

    public void setDiffusionList(List<?> diffusionList) {
        this.diffusionList = diffusionList;
    }

    public List<?> getUserInviteList() {
        return userInviteList;
    }

    public void setUserInviteList(List<?> userInviteList) {
        this.userInviteList = userInviteList;
    }
}
