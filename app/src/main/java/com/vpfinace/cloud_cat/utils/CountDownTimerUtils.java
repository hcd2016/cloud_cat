package com.vpfinace.cloud_cat.utils;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

/**
 * 获取验证码倒计时
 */
public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;
    private int unClickBgColor;//按钮置灰时的背景颜色
    private int normalBgColor;//按钮原来的背景颜色
    private int onClickTextColor;//按钮计时时文字颜色
    private int onFinishTextColor;//倒计时完成时文字颜色
    private String onTickStr;//计时时文字内容 如 56+s后重新获取
    private String onFinishStr;//计时完成后按钮文字内容
    private boolean isTicking = false;//是否正在计时

    public boolean isTicking() {
        return isTicking;
    }

    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval, int unClickBgColor, int normalBgColor,
                               int onClickTextColor, int onFinishTextColor,
                               String onTickStr, String onFinishStr) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
        this.unClickBgColor = unClickBgColor;
        this.normalBgColor = normalBgColor;
        this.onClickTextColor = onClickTextColor;
        this.onFinishTextColor = onFinishTextColor;
        this.onTickStr = onTickStr;
        this.onFinishStr = onFinishStr;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        isTicking = true;
        Log.i("onTick", "onTick: "+millisUntilFinished);
        mTextView.setEnabled(false); //设置不可点击
        mTextView.setClickable(false);
        mTextView.setText(millisUntilFinished / 1000 + onTickStr);  //设置倒计时时间
        mTextView.setBackgroundResource(unClickBgColor);//设置按钮为灰色，这时是不能点击的
        mTextView.setTextColor(onClickTextColor);
    }

    @Override
    public void onFinish() {
        isTicking = false;
        mTextView.setText(onFinishStr);
        mTextView.setEnabled(true);//重新获得点击
        mTextView.setClickable(true);
        mTextView.setBackgroundResource(normalBgColor);  //还原背景色
        mTextView.setTextColor(onFinishTextColor);
    }



    /**
     * 使用示例
     *   countDownTimerUtils = new CountDownTimerUtils(tvBtnGetVerCode, 60000+50, 1000,
     *                         R.drawable.shape_blue8ff_c4, R.drawable.shape_blue8ff_c4, MyUtils.getColor(R.color.white), MyUtils.getColor(R.color.white), "s", "重新获取");
     *                 countDownTimerUtils.start();
     */
}