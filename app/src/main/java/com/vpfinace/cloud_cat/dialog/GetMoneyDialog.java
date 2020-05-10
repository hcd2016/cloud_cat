package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 领取金币
 */
public class GetMoneyDialog extends TBaseDialog {
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;
    OnCommitClickListener onCommitClickListener;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    private long earnins = 0;

    public void setOnCommitClickListener(OnCommitClickListener onCommitClickListener) {
        this.onCommitClickListener = onCommitClickListener;
    }

    public GetMoneyDialog(Context context, long earnings) {
        super(context, R.layout.dialog_get_money);
        this.earnins = earnings;
        setWindowParam(0.8f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        tvAmount.setText(earnings+"");
    }

    @OnClick(R.id.tv_btn_confirm)
    public void onViewClicked() {
        if (onCommitClickListener != null) {
            onCommitClickListener.onClick();
        }
        dismiss();
    }


    public interface OnCommitClickListener {
        void onClick();
    }
}
