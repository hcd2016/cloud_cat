package com.vpfinace.cloud_cat.app;


import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.ad.config.TTAdManagerHolder;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.AdSettingBean;
import com.vpfinace.cloud_cat.config.ConfigUtil;
import com.vpfinace.cloud_cat.global.SpContant;
import com.vpfinace.cloud_cat.http.HttpManager;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


public class App extends MultiDexApplication {

    public static App mApp;
    public static final String wxAppId = "wx82046ad76a77c638";
    public static final String wxAppSecret = "9a7f48fb72917f9beb3c5cd17443588f";
    public static boolean isAdInit = false;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        configUtil = new ConfigUtil();
        MultiDex.install(this);
        SPUtils.getInstance().put(SpContant.IS_SHOW_OFFLINE, true);

        reqeustAdSetting();

        //微信
        IWXAPI mWxApi = WXAPIFactory.createWXAPI(this, wxAppId, true);
        // 注册
        mWxApi.registerApp(wxAppId);

    }

    public static IWXAPI getWxApi() {
        return WXAPIFactory.createWXAPI(getContext(), wxAppId, true);
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

    public void reqeustAdSetting() {
        HttpManager.toRequst(HttpManager.getApi().getAdSetting(), new BaseObserver<AdSettingBean>(null) {
            @Override
            public void _onNext(AdSettingBean adSettingBean) {
                isAdInit = true;
                if(adSettingBean.getToutiao_ad_switch() == 1) {
                    isAdInit = true;
                    //穿山甲
                    TTAdManagerHolder.init(App.getContext(), adSettingBean.getToutiao_app_code());
                }else {
                    isAdInit = false;
                    SPUtils.getInstance().put(SpContant.TOUTIAO_APP_CODE, adSettingBean.getToutiao_app_code());
                }
            }

            @Override
            public void _onError(String message) {
                isAdInit = false;
                ToastUtils.showShort(message);
            }
        });
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
