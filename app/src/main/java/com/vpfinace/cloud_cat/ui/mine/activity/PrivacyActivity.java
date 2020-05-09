package com.vpfinace.cloud_cat.ui.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.User;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.ui.cattery.activity.SocialInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrivacyActivity extends BaseActivity {
    @BindView(R.id.iv_master)
    ImageView ivMaster;
    @BindView(R.id.iv_friend)
    ImageView ivFriend;
    private int masterVisible = 1;//师傅可见,0为不可见,1为可见
    private int friendVisible = 1;//朋友可见,0为不可见,1为可见

    @Override
    public int getLayoutId() {
        return R.layout.activity_privacy;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        requestGetUserInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //获取用户信息
    public void requestGetUserInfo() {
        HttpManager.toRequst(HttpManager.getApi().getUserInfo(), new BaseObserver<User>(this) {
            @Override
            public void _onNext(User user) {
                masterVisible = user.getSettingMap().getInviterVisible();
                friendVisible = user.getSettingMap().getFriendVisible();
                if (masterVisible == 0) {
                    ivMaster.setImageResource(R.drawable.setting_switch_close);
                } else {
                    ivMaster.setImageResource(R.drawable.setting_switch_open);
                }
                if (friendVisible == 0) {
                    ivFriend.setImageResource(R.drawable.setting_switch_close);
                } else {
                    ivFriend.setImageResource(R.drawable.setting_switch_open);
                }
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }


    public void requestCommitVisible() {
        HttpManager.toRequst(HttpManager.getApi().privacySetting(masterVisible + "", friendVisible + ""), new BaseObserver(this) {
            @Override
            public void _onNext(Object o) {
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    @OnClick({R.id.iv_master, R.id.iv_friend, R.id.ll_setting_socialinfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_socialinfo:
                startActivity(SocialInfoActivity.class);
                break;
            case R.id.iv_master:
                if (masterVisible == 0) {
                    ivMaster.setImageResource(R.drawable.setting_switch_open);
                    masterVisible = 1;
                } else {
                    ivMaster.setImageResource(R.drawable.setting_switch_close);
                    masterVisible = 0;
                }
                requestCommitVisible();
                break;
            case R.id.iv_friend:
                if (friendVisible == 0) {
                    ivFriend.setImageResource(R.drawable.setting_switch_open);
                    friendVisible = 1;
                } else {
                    ivFriend.setImageResource(R.drawable.setting_switch_close);
                    friendVisible = 0;
                }
                requestCommitVisible();
                break;
        }
    }
}
