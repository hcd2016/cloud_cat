package com.vpfinace.cloud_cat.dialog;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.vpfinace.cloud_cat.R;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.ALPHA;

public class LuckyWheelDialog extends TBaseDialog {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_wheel)
    ImageView ivWheel;
    @BindView(R.id.iv_btn)
    ImageView ivBtn;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    private ObjectAnimator objectAnimator;

    public LuckyWheelDialog(Context context) {
        super(context, R.layout.dialog_lucky_wheel);
        setWindowParam(0.9f, ConvertUtils.dp2px(469), Gravity.CENTER, 0);
    }

    @OnClick({R.id.iv_close,R.id.tv_btn_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_btn_start:
                startWheel(4);
                break;
        }
    }

    private void startWheel(int result) {
        int angle = (result-1)*45;

//        switch (result) {
//            case 1:
//                angle=0;
//                break;
//            case 2:
//                angle=45;
//                break;
//            case 3:
//                angle=90;
//                break;
//            case 4:
//                angle=135;
//                break;
//            case 5:
//                angle=90;
//                break;
//            case 6:
//                break;
//            case 7:
//                break;
//            case 8:
//                break;
//        }

        /** 设置旋转动画 */
        objectAnimator = ObjectAnimator.ofFloat(ivWheel, "rotation", 0f,360*3).setDuration(3000);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
    }
}
