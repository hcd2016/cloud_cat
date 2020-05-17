package com.vpfinace.cloud_cat.utils;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.vpfinace.cloud_cat.ad.activity.RewardVideoActivity;
import com.vpfinace.cloud_cat.ad.config.TTAdManagerHolder;
import com.vpfinace.cloud_cat.app.App;

public class AdManager {
    private static final String  codeId = "945179884";
    private static boolean mHasShowDownloadActive = false;
    /**
     * 激励广告
     */
    public static void playRewardVideo(Activity activity){

        TTAdManager ttAdManager = TTAdManagerHolder.get();
        //step3:创建TTAdNative对象,用于调用广告请求接口
       TTAdNative mTTAdNative = ttAdManager.createAdNative(App.getContext());
       loadAd(codeId,TTAdConstant.VERTICAL,mTTAdNative,activity);
    }

    public static void loadAd(final String codeId, int orientation, TTAdNative mTTAdNative, Activity activity) {
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
                    .setUserID("user123")//用户id,必传参数
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

//                ToastUtils.showShort(  "rewardVideoAd loaded 广告类型：" + getAdType(ad.getRewardVideoAdType()));
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
                        ToastUtils.showShort(  "verify:" + rewardVerify + " amount:" + rewardAmount +
                                " name:" + rewardName);
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
                            ToastUtils.showShort(  "下载中，点击下载区域暂停", Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {
                        Log.d("DML", "onDownloadPaused===totalBytes=" + totalBytes + ",currBytes=" + currBytes + ",fileName=" + fileName + ",appName=" + appName);
                        ToastUtils.showShort(  "下载暂停，点击下载区域继续", Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
                        Log.d("DML", "onDownloadFailed==totalBytes=" + totalBytes + ",currBytes=" + currBytes + ",fileName=" + fileName + ",appName=" + appName);
                        ToastUtils.showShort(  "下载失败，点击下载区域重新下载", Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onDownloadFinished(long totalBytes, String fileName, String appName) {
                        Log.d("DML", "onDownloadFinished==totalBytes=" + totalBytes + ",fileName=" + fileName + ",appName=" + appName);
                        ToastUtils.showShort(  "下载完成，点击下载区域重新下载", Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onInstalled(String fileName, String appName) {
                        Log.d("DML", "onInstalled==" + ",fileName=" + fileName + ",appName=" + appName);
                        ToastUtils.showShort(  "安装完成，点击下载区域打开", Toast.LENGTH_LONG);
                    }
                });

                ad.showRewardVideoAd(activity, TTAdConstant.RitScenes.CUSTOMIZE_SCENES, "scenes_test");
                ad = null;
            }
        });
    }
}
