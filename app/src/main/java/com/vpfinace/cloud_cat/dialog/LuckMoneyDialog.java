package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.bean.WheelResultBean;
import com.vpfinace.cloud_cat.utils.ArithUtil;
import com.vpfinace.cloud_cat.utils.UnitUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LuckMoneyDialog extends TBaseDialog {
    @BindView(R.id.tv_earnings_desc)
    TextView tvEarningsDesc;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;

    public LuckMoneyDialog(Context context, WheelResultBean wheelResultBean) {
        super(context, R.layout.dialog_lucky_money);
        setWindowParam(0.7f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        setData(wheelResultBean);
    }

    private void setData(WheelResultBean wheelResultBean) {
        tvAmount.setText(UnitUtils.coin2String(wheelResultBean.getCoin()));
        long second = wheelResultBean.getSecond();
        double hTime = ArithUtil.div(second, 60 * 60);
        if(hTime >= 1) {
            tvEarningsDesc.setText(hTime+"小时收益");
        }else {
            int mTime = (int) ArithUtil.div(second, 60,0);
            tvEarningsDesc.setText(mTime+"分钟收益");
        }
    }

    @OnClick({R.id.tv_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_confirm:
                dismiss();
                break;
        }
    }
}
