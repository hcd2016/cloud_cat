package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.bean.CatteryBean;
import com.vpfinace.cloud_cat.ui.cattery.activity.SocialInfoActivity;
import com.vpfinace.cloud_cat.utils.GlideUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的邀请好友
 */
public class MyInviteFriendDialog extends TBaseDialog {
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.ll_btn_social_info_setting)
    LinearLayout llBtnSocialInfoSetting;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_level_and_name)
    TextView tvLevelAndName;

    public MyInviteFriendDialog(Context context, CatteryBean.MyInviterBean myInviterBean) {
        super(context, R.layout.dialog_my_invite_friend);
        setWindowParam(0.7f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        setData(myInviterBean);
    }

    private void setData(CatteryBean.MyInviterBean myInviterBean) {
        GlideUtils.loadCircle(mContext,myInviterBean.getHeadImgUrl(),ivHeader);
        tvLevelAndName.setText("Lv."+myInviterBean.getUserLevel()+myInviterBean.getNickname());
    }

    @OnClick({R.id.fl_close, R.id.ll_btn_social_info_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                dismiss();
                break;
            case R.id.ll_btn_social_info_setting:
                dismiss();
                mContext.startActivity(new Intent(mContext, SocialInfoActivity.class));
                break;
        }
    }
}
