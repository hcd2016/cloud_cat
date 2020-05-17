package com.vpfinace.cloud_cat.ui.user.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.ad.config.TTAdManagerHolder;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.utils.GlideUtils;
import com.vpfinace.cloud_cat.utils.StatusTextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.ll_btn_wechat_login)
    LinearLayout llBtnWechatLogin;
    @BindView(R.id.tv_btn_eles_login)
    TextView tvBtnElesLogin;
    @BindView(R.id.iv_select_icon)
    ImageView ivSelectIcon;
    @BindView(R.id.fl_select_icon)
    FrameLayout flSelectIcon;
    @BindView(R.id.tv_btn_yhxy)
    TextView tvBtnYhxy;
    @BindView(R.id.tv_btn_yszc)
    TextView tvBtnYszc;
    @BindView(R.id.v_status_view)
    View vStatusView;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        ViewGroup.LayoutParams layoutParams = vStatusView.getLayoutParams();
        layoutParams.height = BarUtils.getStatusBarHeight();
        vStatusView.setLayoutParams(layoutParams);
        StatusTextUtils.setLightStatusBar(this, true);
        GlideUtils.loadCorner(this,R.mipmap.logo,ivLogo,10);
        TTAdManagerHolder.get().requestPermissionIfNecessary(this);//广告权限申请
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    boolean isAgreen = true;

    @OnClick({R.id.ll_btn_wechat_login, R.id.tv_btn_eles_login, R.id.fl_select_icon, R.id.tv_btn_yhxy, R.id.tv_btn_yszc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_btn_wechat_login:
                break;
            case R.id.tv_btn_eles_login:
                startActivity(PhoneLoginActivity.class);
                break;
            case R.id.fl_select_icon:
                if (isAgreen) {
                    ivSelectIcon.setImageResource(R.drawable.icon_unselected);
                    llBtnWechatLogin.setBackgroundResource(R.drawable.shape_gray_beb_c30);
                } else {
                    ivSelectIcon.setImageResource(R.drawable.icon_selected);
                    llBtnWechatLogin.setBackgroundResource(R.drawable.shape_red_554_c30);
                }
                isAgreen = !isAgreen;
                break;
            case R.id.tv_btn_yhxy:
                break;
            case R.id.tv_btn_yszc:
                break;
        }
    }
}
