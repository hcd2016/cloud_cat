package com.vpfinace.cloud_cat.splash;


import android.os.Bundle;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.ui.user.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.tv_desc)
    TextView tvDesc;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        tvDesc.postDelayed(new Runnable() {
            @Override
            public void run() {
//                if(UserUtil.isLogin()) {
//                    startActivity(MainActivity.class);
//                }else {
                    startActivity(LoginActivity.class);
//                }
//                finish();
            }
        },2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
