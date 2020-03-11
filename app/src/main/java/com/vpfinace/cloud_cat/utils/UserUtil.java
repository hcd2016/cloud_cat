package com.vpfinace.cloud_cat.utils;

import com.blankj.utilcode.util.SPUtils;
import com.vpfinace.cloud_cat.app.App;
import com.vpfinace.cloud_cat.global.EventStrings;
import com.vpfinace.cloud_cat.global.SpContant;

import org.greenrobot.eventbus.EventBus;

public class UserUtil {
//    /**
//     * 用户是否登录
//     *
//     * @return
//     */
//    public static boolean isLogin() {
//        User user = SpUtil.getBeanFromSp(App.getContext(), SpContant.SP_USER);
//        if (user == null) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    /**
//     * 获取用户id
//     *
//     * @return
//     */
//    public static String getUid() {
//        User user = SpUtil.getBeanFromSp(App.getContext(), SpContant.SP_USER);
//        if (user != null) {
//            return user.getId();
//        }
//        return null;
//    }
//
//    public static User getUser() {
//        User user = SpUtil.getBeanFromSp(App.getContext(), SpContant.SP_USER);
//        return user;
//    }

    /**
     * 退出登录
     */
    public static void loginOut(){
        SPUtils.getInstance().put(SpContant.SP_TOKEN,"");
        SpUtil.saveBean2Sp(App.getContext(),null,SpContant.SP_USER);
        EventBus.getDefault().post(EventStrings.LOGIN_OUT);
    }

}
