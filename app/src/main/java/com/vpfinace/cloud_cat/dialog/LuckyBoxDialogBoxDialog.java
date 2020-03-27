package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 抽奖宝箱弹窗
 */
public class LuckyBoxDialogBoxDialog extends TBaseDialog {
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.tv_btn_look_sp)
    TextView tvBtnLookSp;

    public LuckyBoxDialogBoxDialog(Context context) {
        super(context, R.layout.dialog_lucky_box);
        setWindowParam(0.7f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
    }

    @OnClick({R.id.fl_close, R.id.tv_btn_look_sp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                dismiss();
                break;
            case R.id.tv_btn_look_sp:
                break;
        }
    }
}
