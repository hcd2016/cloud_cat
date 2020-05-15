package com.vpfinace.cloud_cat.app;


import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.ad.config.TTAdManagerHolder;
import com.vpfinace.cloud_cat.config.ConfigUtil;
import com.vpfinace.cloud_cat.global.SpContant;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


public class App extends MultiDexApplication {

    public static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        configUtil = new ConfigUtil();
//        DoraemonKit.install(this);
//        MyUtils.init(this);
        MultiDex.install(this);
        SPUtils.getInstance().put(SpContant.IS_SHOW_OFFLINE,true);

        TTAdManagerHolder.init(this);
    }

    //保存一些常用的配置
    private static ConfigUtil configUtil = null;

    public static ConfigUtil getConfig() {
        return configUtil;
    }

    public static Context getContext() {
        return mApp.getApplicationContext();
    }

    public static String getAPPName() {
        return getContext().getResources().getString(R.string.app_name);
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
//                return new BaseRefreshHeaderView(context);//自定义下拉刷新
//                return new BezierRadarHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                return new com.scwang.smartrefresh.header.BezierCircleHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                //指定为经典Footer，默认是 BallPulseFooter
//                return new ClassicsFooter(context).setDrawableSize(20);
                return new com.scwang.smartrefresh.layout.footer.BallPulseFooter(context);
            }
        });
    }
}
