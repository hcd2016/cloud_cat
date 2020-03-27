package com.vpfinace.cloud_cat.ui.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的帮助中心
 */
public class HelpActiviy extends BaseActivity {
    @BindView(R.id.tv_q1)
    TextView tvQ1;
    @BindView(R.id.tv_q2)
    TextView tvQ2;
    @BindView(R.id.tv_q3)
    TextView tvQ3;
    @BindView(R.id.tv_q4)
    TextView tvQ4;
    @BindView(R.id.tv_q5)
    TextView tvQ5;
    @BindView(R.id.tv_q6)
    TextView tvQ6;
    @BindView(R.id.tv_q7)
    TextView tvQ7;
    @BindView(R.id.tv_q8)
    TextView tvQ8;

    @Override
    public int getLayoutId() {
        return R.layout.activity_help;
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

    @OnClick({R.id.tv_q1, R.id.tv_q2, R.id.tv_q3, R.id.tv_q4, R.id.tv_q5, R.id.tv_q6, R.id.tv_q7, R.id.tv_q8})
    public void onViewClicked(View view) {
        startActivity(HelpDetailActivity.class);
        switch (view.getId()) {
            case R.id.tv_q1:
                break;
            case R.id.tv_q2:
                break;
            case R.id.tv_q3:
                break;
            case R.id.tv_q4:
                break;
            case R.id.tv_q5:
                break;
            case R.id.tv_q6:
                break;
            case R.id.tv_q7:
                break;
            case R.id.tv_q8:
                break;
        }
    }
}
