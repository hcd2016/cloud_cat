package com.vpfinace.cloud_cat.ui.home.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分红喵
 */
public class DividendCatActivity extends BaseActivity {
    @BindView(R.id.v_status_view)
    View vStatusView;
    @BindView(R.id.tv_btn_konw_more)
    TextView tvBtnKonwMore;
    @BindView(R.id.ll_dividends_records_container)
    LinearLayout llDividendsRecordsContainer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dividend_cat;
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_btn_konw_more, R.id.ll_dividends_records_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_konw_more:
                startActivity(DividendIntroduceActivity.class);
                break;
            case R.id.ll_dividends_records_container:
                startActivity(DividendCatRecordsActivity.class);
                break;
        }
    }
}
