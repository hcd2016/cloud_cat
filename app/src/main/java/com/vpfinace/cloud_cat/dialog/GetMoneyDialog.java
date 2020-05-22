package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.ad.config.TTAdManagerHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 领取金币
 */
public class GetMoneyDialog extends TBaseDialog {
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;
    OnCommitClickListener onCommitClickListener;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.ll_container)
    RelativeLayout llContainer;
    @BindView(R.id.fl_ad_container)
    FrameLayout flAdContainer;
    private long earnins = 0;

    private static boolean mHasShowDownloadActive = false;

    public void setOnCommitClickListener(OnCommitClickListener onCommitClickListener) {
        this.onCommitClickListener = onCommitClickListener;
    }

    public GetMoneyDialog(Context context, long earnings) {
        super(context, R.layout.dialog_get_money);
        this.earnins = earnings;
        setWindowParam(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, Gravity.BOTTOM, 0);
    }

    public void setBanner() {
        addBanner(mContext, flAdContainer,llContainer.getMeasuredWidth());
    }

    @OnClick(R.id.tv_btn_confirm)
    public void onViewClicked() {
        if (onCommitClickListener != null) {
            onCommitClickListener.onClick();
        }
        dismiss();
    }

    public void addBanner(Context context, ViewGroup fl_ad_container,int width) {
        TTAdNative mTTAdNative = TTAdManagerHolder.get().createAdNative(context);
        //设置广告参数
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId("945186774") //广告位id
                .setSupportDeepLink(true)
                .setAdCount(1) //请求广告数量为1到3条
                .setExpressViewAcceptedSize(10000, 100) //期望个性化模板广告view的size,单位dp
                .setImageAcceptedSize(640, 320)//这个参数设置即可，不影响个性化模板广告的size
                .build();
        //加载广告
        mTTAdNative.loadBannerExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {
            @Override
            public void onError(int code, String message) {
                fl_ad_container.removeAllViews();
                ToastUtils.showShort(code+message);
            }

            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                if (ads == null || ads.size() == 0) {
                    return;
                }
                TTNativeExpressAd mTTAd = ads.get(0);
                mTTAd.setSlideIntervalTime(30 * 1000);//设置轮播间隔 ms,不调用则不进行轮播展示
                mTTAd.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() {
                    @Override
                    public void onAdClicked(View view, int type) {
                        ToastUtils.showShort("广告被点击");
                    }

                    @Override
                    public void onAdShow(View view, int type) {
                        ToastUtils.showShort("广告展示");
                    }

                    @Override
                    public void onRenderFail(View view, String msg, int code) {
//                Log.e("ExpressView","render fail:"+(System.currentTimeMillis() - startTime);
                        ToastUtils.showShort(msg + " code:" + code);
                    }

                    @Override
                    public void onRenderSuccess(View view, float width, float height) {
                        //返回view的宽高 单位 dp
                        ToastUtils.showShort("渲染成功");
                        //在渲染成功回调时展示广告，提升体验
                        fl_ad_container.removeAllViews();
                        fl_ad_container.addView(view);
                    }
                });
                mTTAd.render();//调用render开始渲染广告
                //dislike设置
//                bindDislike(mTTAd, false);
                if (mTTAd.getInteractionType() != TTAdConstant.INTERACTION_TYPE_DOWNLOAD) {
                    return;
                }
                //可选，下载监听设置
                mTTAd.setDownloadListener(new TTAppDownloadListener() {
                    @Override
                    public void onIdle() {
                        ToastUtils.showShort("点击开始下载");
                    }

                    @Override
                    public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
                        if (!mHasShowDownloadActive) {
                            mHasShowDownloadActive = true;
                            ToastUtils.showShort("下载中，点击暂停");
                        }
                    }

                    @Override
                    public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {
                        ToastUtils.showShort("下载暂停，点击继续");
                    }

                    @Override
                    public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
                        ToastUtils.showShort("下载失败，点击重新下载");
                    }

                    @Override
                    public void onInstalled(String fileName, String appName) {
                        ToastUtils.showShort("安装完成，点击图片打开");
                    }

                    @Override
                    public void onDownloadFinished(long totalBytes, String fileName, String appName) {
                        ToastUtils.showShort("点击安装");
                    }
                });
            }
        });
    }

//    /**
//     * 设置广告的不喜欢, 注意：强烈建议设置该逻辑，如果不设置dislike处理逻辑，则模板广告中的 dislike区域不响应dislike事件。
//     *
//     * @param ad
//     * @param customStyle 是否自定义样式，true:样式自定义
//     */
//    private void bindDislike(TTNativeExpressAd ad, boolean customStyle) {
//        if (customStyle) {
//            //使用自定义样式
//            List<FilterWord> words = ad.getFilterWords();
//            if (words == null || words.isEmpty()) {
//                return;
//            }
//
//            final DislikeDialog dislikeDialog = new DislikeDialog(this, words);
//            dislikeDialog.setOnDislikeItemClick(new DislikeDialog.OnDislikeItemClick() {
//                @Override
//                public void onItemClick(FilterWord filterWord) {
//                    //屏蔽广告
//                    TToast.show(mContext, "点击 " + filterWord.getName());
//                    //用户选择不喜欢原因后，移除广告展示
//                    mExpressContainer.removeAllViews();
//                }
//            });
//            ad.setDislikeDialog(dislikeDialog);
//            return;
//        }
//        //使用默认模板中默认dislike弹出样式
//        ad.setDislikeCallback(BannerExpressActivity.this, new TTAdDislike.DislikeInteractionCallback() {
//            @Override
//            public void onSelected(int position, String value) {
//                TToast.show(mContext, "点击 " + value);
//                //用户选择不喜欢原因后，移除广告展示
//                mExpressContainer.removeAllViews();
//            }
//
//            @Override
//            public void onCancel() {
//                TToast.show(mContext, "点击取消 ");
//            }
//        });
//    }


    public interface OnCommitClickListener {
        void onClick();
    }
}
