package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;

import butterknife.BindView;
import butterknife.OnClick;

public class LuckMoneyDialog extends TBaseDialog {
    @BindView(R.id.tv_btn_look_sp)
    TextView tvBtnLookSp;

    public LuckMoneyDialog(Context context) {
        super(context, R.layout.dialog_lucky_money);
        setWindowParam(0.7f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
    }

    @OnClick({ R.id.tv_btn_look_sp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_look_sp:
                dismiss();
                break;
        }
    }
}
