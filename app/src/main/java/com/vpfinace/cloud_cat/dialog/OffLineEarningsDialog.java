package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.utils.AdManager;
import com.vpfinace.cloud_cat.utils.UnitUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 离线收益
 */
public class OffLineEarningsDialog extends TBaseDialog {
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    private Long amount;
    private BaseActivity activity;

    public TextView getTvBtnConfirm() {
        return tvBtnConfirm;
    }

    public void setTvBtnConfirm(TextView tvBtnConfirm) {
        this.tvBtnConfirm = tvBtnConfirm;
    }

    public OffLineEarningsDialog(Context context, long amount, BaseActivity activity) {
        super(context, R.layout.dialog_offline_earnings);
        setWindowParam(0.8f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        this.amount = amount;
        init(amount);
        this.activity = activity;
    }

    private void init(long amount) {
        tvAmount.setText(UnitUtils.coin2String(amount));
    }

    @OnClick(R.id.tv_btn_confirm)
    public void onViewClicked() {
        AdManager.playRewardVideo(activity);
        dismiss();
    }
}
