package com.vpfinace.cloud_cat.ui.home.activity;

import android.os.Bundle;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.weight.MyTitle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DividendIntroduceActivity extends BaseActivity {
    @BindView(R.id.title)
    MyTitle title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dividend_introduce;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        title.setTitleBackgroud(MyUtils.getColor(R.color.gray_f5));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
