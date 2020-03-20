package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.vpfinace.cloud_cat.R;

import butterknife.BindView;

public class LuckyWheelDialog extends TBaseDialog {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_wheel)
    ImageView ivWheel;
    @BindView(R.id.iv_btn)
    ImageView ivBtn;

    public LuckyWheelDialog(Context context) {
        super(context, R.layout.dialog_lucky_wheel);
        setWindowParam(0.9f, ConvertUtils.dp2px(469), Gravity.CENTER, 0);
    }
}
