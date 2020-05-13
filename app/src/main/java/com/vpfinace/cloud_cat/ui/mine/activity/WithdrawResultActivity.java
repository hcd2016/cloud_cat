package com.vpfinace.cloud_cat.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 提现结果页
 */
public class WithdrawResultActivity extends BaseActivity {
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_fee)
    TextView tvFee;

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw_result;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        String amount = getIntent().getStringExtra("amount");
        String fee = getIntent().getStringExtra("fee");
        tvAmount.setText("¥"+amount);
        tvFee.setText("¥"+fee);
    }

    public static void goThis(Context context, String amount, String fee) {
        Intent intent = new Intent(context, WithdrawResultActivity.class);
        intent.putExtra("amount", amount);
        intent.putExtra("fee", fee);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
