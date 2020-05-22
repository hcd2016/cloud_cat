package com.vpfinace.cloud_cat.utils;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.ad.config.TTAdManagerHolder;
import com.vpfinace.cloud_cat.app.App;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.AdSettingBean;
import com.vpfinace.cloud_cat.global.EventStrings;
import com.vpfinace.cloud_cat.http.HttpManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class AdManager {
    private static boolean mHasShowDownloadActive = false;
    /**
     * 激励广告
     */
    public static void playRewardVideo(BaseActivity activity,int type,long num){
        reqeustAdSetting(activity,type,num);
    }

    private static void loadAd(final String codeId, int orientation, TTAdNative mTTAdNative, BaseActivity activity, int type, long num) {
        //step4:创建广告请求参数AdSlot,具体参数含义参考文档
        AdSlot adSlot;
//        if (mIsExpress) {
//            //个性化模板广告需要传入期望广告view的宽、高，单位dp，
//            adSlot = new AdSlot.Builder()
//                    .setCodeId(codeId)
//                    .setSupportDeepLink(true)
//                    .setAdCount(20)
//                    .setRewardName("金币") //奖励的名称
//                    .setRewardAmount(3)  //奖励的数量
//                    //模板广告需要设置期望个性化模板广告的大小,单位dp,激励视频场景，只要设置的值大于0即可
//                    .setExpressViewAcceptedSize(500,500)
//                    .setUserID("user123")//用户id,必传参数
//                    .setMediaExtra("media_extra") //附加参数，可选
//                    .setOrientation(orientation) //必填参数，期望视频的播放方向：TTAdConstant.HORIZONTAL 或 TTAdConstant.VERTICAL
//                    .build();
//        } else {
            //模板广告需要设置期望个性化模板广告的大小,单位dp,代码位是否属于个性化模板广告，请在穿山甲平台查看
            adSlot = new AdSlot.Builder()
                    .setCodeId(codeId)
                    .setSupportDeepLink(true)
                    .setRewardName("金币") //奖励的名称
                    .setRewardAmount(3)  //奖励的数量
                    .setUserID(UserUtil.getUid())//用户id,必传参数
                    .setMediaExtra("media_extra") //附加参数，可选
                    .setOrientation(orientation) //必填参数，期望视频的播放方向：TTAdConstant.HORIZONTAL 或 TTAdConstant.VERTICAL
                    .build();
//        }
        //step5:请求广告
        mTTAdNative.loadRewardVideoAd(adSlot, new TTAdNative.RewardVideoAdListener() {
            @Override
            public void onError(int code, String message) {
                ToastUtils.showShort(  message);
            }

            //视频广告加载后，视频资源缓存到本地的回调，在此回调后，播放本地视频，流畅不阻塞。
            @Override
            public void onRewardVideoCached() {
//                ToastUtils.showShort(  "rewardVideoAd video cached");
            }

            //视频广告的素材加载完毕，比如视频url等，在此回调后，可以播放在线视频，网络不好可能出现加载缓冲，影响体验。
            @Override
            public void onRewardVideoAdLoad(TTRewardVideoAd ad) {

//                ToastUtils.showShort(  "rewardVideoAd loaded 广告类型：" + getAdType(ad.getRewardVideoAdType());
//                mttRewardVideoAd = ad;
                ad.setRewardAdInteractionListener(new TTRewardVideoAd.RewardAdInteractionListener() {

                    @Override
                    public void onAdShow() {
//                        ToastUtils.showShort(  "rewardVideoAd show");
                    }

                    @Override
                    public void onAdVideoBarClick() {
//                        ToastUtils.showShort(  "rewardVideoAd bar click");
                    }

                    @Override
                    public void onAdClose() {
//                        ToastUtils.showShort(  "rewardVideoAd close");
                    }

                    //视频播放完成回调
                    @Override
                    public void onVideoComplete() {
//                        ToastUtils.showShort(  "rewardVideoAd complete");
                    }

                    @Override
                    public void onVideoError() {
//                        ToastUtils.showShort(  "rewardVideoAd error");
                    }

                    //视频播放完成后，奖励验证回调，rewardVerify：是否有效，rewardAmount：奖励梳理，rewardName：奖励名称
                    @Override
                    public void onRewardVerify(boolean rewardVerify, int rewardAmount, String rewardName) {
//                        ToastUtils.showShort(  "verify:" + rewardVerify + " amount:" + rewardAmount +
//                                " name:" + rewardName);
                        requestVideoCommit(activity,type,num);
                    }

                    @Override
                    public void onSkippedVideo() {
//                        ToastUtils.showShort(  "rewardVideoAd has onSkippedVideo");
                    }
                });
                ad.setDownloadListener(new TTAppDownloadListener() {
                    @Override
                    public void onIdle() {
                        mHasShowDownloadActive = false;
                    }

                    @Override
                    public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
                        Log.d("DML", "onDownloadActive==totalBytes=" + totalBytes + ",currBytes=" + currBytes + ",fileName=" + fileName + ",appName=" + appName);

                        if (!mHasShowDownloadActive) {
                            mHasShowDownloadActive = true;
                            ToastUtils.showShort(  "下载中，点击下载区域暂停");
                        }
                    }

                    @Override
                    public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {
                        Log.d("DML", "onDownloadPaused===totalBytes=" + totalBytes + ",currBytes=" + currBytes + ",fileName=" + fileName + ",appName=" + appName);
                        ToastUtils.showShort(  "下载暂停，点击下载区域继续");
                    }

                    @Override
                    public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
                        Log.d("DML", "onDownloadFailed==totalBytes=" + totalBytes + ",currBytes=" + currBytes + ",fileName=" + fileName + ",appName=" + appName);
                        ToastUtils.showShort(  "下载失败，点击下载区域重新下载");
                    }

                    @Override
                    public void onDownloadFinished(long totalBytes, String fileName, String appName) {
                        Log.d("DML", "onDownloadFinished==totalBytes=" + totalBytes + ",fileName=" + fileName + ",appName=" + appName);
                        ToastUtils.showShort(  "下载完成，点击下载区域重新下载");
                    }

                    @Override
                    public void onInstalled(String fileName, String appName) {
                        Log.d("DML", "onInstalled==" + ",fileName=" + fileName + ",appName=" + appName);
                        ToastUtils.showShort(  "安装完成，点击下载区域打开");
                    }
                });

                ad.showRewardVideoAd(activity, TTAdConstant.RitScenes.CUSTOMIZE_SCENES, "scenes_test");
                ad = null;
            }
        });
    }

    private static void requestVideoCommit(BaseActivity activity,int type, long num){
        HttpManager.toRequst(HttpManager.getApi().videoCommit(2 + "", 0 + "", 0 + "", type, num), new BaseObserver(activity) {
            @Override
            public void _onNext(Object o) {
                ToastUtils.showShort("视频看完提交成功!");
                EventBus.getDefault().post(EventStrings.VIDEO_COMPLETE);
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    private static void reqeustAdSetting(BaseActivity activity,int type,long num) {
        HttpManager.toRequst(HttpManager.getApi().getAdSetting(), new BaseObserver<AdSettingBean>(activity) {
            @Override
            public void _onNext(AdSettingBean adSettingBean) {
                if (adSettingBean.getToutiao_ad_switch() == 1) {
                    if(adSettingBean.getToutiao_video_ad() == 1) {
                        TTAdManager ttAdManager = TTAdManagerHolder.get();
                        //step3:创建TTAdNative对象,用于调用广告请求接口
                        TTAdNative mTTAdNative = ttAdManager.createAdNative(App.getContext());
                        loadAd(adSettingBean.getToutiao_video_ad_code(),TTAdConstant.VERTICAL,mTTAdNative,activity,type,num);
                    }else {
                        ToastUtils.showShort("广告暂未开放,敬请期待!");
                    }
                } else {
                    ToastUtils.showShort("广告暂未开放,敬请期待!");
                }
            }

            @Override
            public void _onError(String message) {
//                ToastUtils.showShort(message);
                ToastUtils.showShort("广告暂未开放,敬请期待!");
            }
        });
    }


    /**
     * 添加banner广告,返回加载好广告的view
     */

    public static View addBanner(Context context){
        View view = View.inflate(context, R.layout.ad_banner,null);
        FrameLayout fl_ad_container = view.findViewById(R.id.fl_ad_container);
        TTAdNative mTTAdNative = TTAdManagerHolder.get().createAdNative(context);
        //设置广告参数
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId("945186774") //广告位id
                .setSupportDeepLink(true)
                .setAdCount(1) //请求广告数量为1到3条
                .setExpressViewAcceptedSize(350,200) //期望个性化模板广告view的size,单位dp
                .setImageAcceptedSize(640,320 )//这个参数设置即可，不影响个性化模板广告的size
                .build();
        //加载广告
        mTTAdNative.loadBannerExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {
            @Override
            public void onError(int code, String message) {
                fl_ad_container.removeAllViews();
            }

            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                if (ads == null || ads.size() == 0){
                    return;
                }
                TTNativeExpressAd mTTAd = ads.get(0);
                mTTAd.setSlideIntervalTime(30*1000);//设置轮播间隔 ms,不调用则不进行轮播展示
                mTTAd.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() {
                    @Override
                    public void onAdClicked(View view, int type) {
                        ToastUtils.showShort( "广告被点击");
                    }

                    @Override
                    public void onAdShow(View view, int type) {
                        ToastUtils.showShort( "广告展示");
                    }

                    @Override
                    public void onRenderFail(View view, String msg, int code) {
//                Log.e("ExpressView","render fail:"+(System.currentTimeMillis() - startTime);
                        ToastUtils.showShort( msg+" code:"+code);
                    }

                    @Override
                    public void onRenderSuccess(View view, float width, float height) {
                        //返回view的宽高 单位 dp
                        ToastUtils.showShort( "渲染成功");
                        //在渲染成功回调时展示广告，提升体验
                        fl_ad_container.removeAllViews();
                        fl_ad_container.addView(view);
                    }
                });
                //dislike设置
//                bindDislike(mTTAd, false);
                if (mTTAd.getInteractionType() != TTAdConstant.INTERACTION_TYPE_DOWNLOAD){
                    return;
                }
                //可选，下载监听设置
                mTTAd.setDownloadListener(new TTAppDownloadListener() {
                    @Override
                    public void onIdle() {
                        ToastUtils.showShort( "点击开始下载");
                    }

                    @Override
                    public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
                        if (!mHasShowDownloadActive) {
                            mHasShowDownloadActive = true;
                            ToastUtils.showShort( "下载中，点击暂停");
                        }
                    }

                    @Override
                    public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {
                        ToastUtils.showShort( "下载暂停，点击继续");
                    }

                    @Override
                    public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
                        ToastUtils.showShort( "下载失败，点击重新下载");
                    }

                    @Override
                    public void onInstalled(String fileName, String appName) {
                        ToastUtils.showShort( "安装完成，点击图片打开");
                    }

                    @Override
                    public void onDownloadFinished(long totalBytes, String fileName, String appName) {
                        ToastUtils.showShort( "点击安装");
                    }
                });
                mTTAd.render();//调用render开始渲染广告
            }
        });
        return view;
    }
}
