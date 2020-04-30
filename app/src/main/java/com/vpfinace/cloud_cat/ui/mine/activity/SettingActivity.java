package com.vpfinace.cloud_cat.ui.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.ui.user.activity.LoginActivity;
import com.vpfinace.cloud_cat.utils.UserUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.ll_btn_nickname_container)
    LinearLayout llBtnNicknameContainer;
    @BindView(R.id.ll_btn_phone_container)
    LinearLayout llBtnPhoneContainer;
    @BindView(R.id.ll_btn_privacy_container)
    LinearLayout llBtnPrivacyContainer;
    @BindView(R.id.ll_btn_about_container)
    LinearLayout llBtnAboutContainer;
    @BindView(R.id.ll_btn_sound_container)
    LinearLayout llBtnSoundContainer;
    @BindView(R.id.btn_logo_out)
    TextView btnLogoOut;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_btn_nickname_container, R.id.ll_btn_phone_container, R.id.ll_btn_privacy_container, R.id.ll_btn_about_container, R.id.ll_btn_sound_container, R.id.btn_logo_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_btn_nickname_container:
                startActivity(UpdateNickNameActivity.class);
                break;
            case R.id.ll_btn_phone_container:
                startActivity(BindPhoneActivity.class);
                break;
            case R.id.ll_btn_privacy_container:
                startActivity(PrivacyActivity.class);
                break;
            case R.id.ll_btn_about_container:
                break;
            case R.id.ll_btn_sound_container:
                break;
            case R.id.btn_logo_out:
                requestLoginOut();
                break;
        }
    }

    public void requestLoginOut() {
        HttpManager.toRequst(HttpManager.getApi().loginOut(), new BaseObserver(this) {
            @Override
            public void _onNext(Object o) {
                UserUtil.loginOut();
                startActivity(LoginActivity.class);
                ToastUtils.showShort("退出登录成功!");
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }
}
