package com.vpfinace.cloud_cat.dialog;

import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.ShareBean;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.utils.WXShareUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 分享弹窗
 */
public class ShareDialog extends TBaseDialog {
    @BindView(R.id.rl_img_container)
    RelativeLayout rlImgContainer;
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.ll_btn_wechat)
    LinearLayout llBtnWechat;
    @BindView(R.id.ll_btn_content)
    LinearLayout llBtnContent;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.tv_invite_code_desc)
    TextView tvInviteCodeDesc;

    public ShareDialog(BaseActivity context) {
        super(context, R.layout.dialog_share);
        setWindowParam(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM, 0);
        requestGetShareInfo(context);
    }

    public void requestGetShareInfo(BaseActivity baseActivity) {
        HttpManager.toRequst(HttpManager.getApi().getShareInfo(), new BaseObserver<ShareBean>(baseActivity) {
            @Override
            public void _onNext(ShareBean shareBean) {
                Glide.with(mContext).load(shareBean.getHeadImg()).into(ivHeader);
                tvTitle.setText(shareBean.getTitle());
                tvDesc.setText(shareBean.getDesc());
                tvInviteCodeDesc.setText("我的邀请码："+shareBean.getInviteCode());
                Bitmap image = CodeUtils.createImage(shareBean.getUrl(), 400, 400, null);
                ivCode.setImageBitmap(image);
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    @OnClick({R.id.fl_close, R.id.ll_btn_wechat, R.id.ll_btn_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                dismiss();
                break;
            case R.id.ll_btn_wechat:
                WXShareUtil.shareImg(mContext, ImageUtils.view2Bitmap(rlImgContainer), 1);
                break;
            case R.id.ll_btn_content:
                WXShareUtil.shareImg(mContext, ImageUtils.view2Bitmap(rlImgContainer), 2);
                break;
        }
    }
}
