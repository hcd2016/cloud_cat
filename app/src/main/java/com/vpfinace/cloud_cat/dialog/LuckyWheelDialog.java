package com.vpfinace.cloud_cat.dialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.WheelResultBean;
import com.vpfinace.cloud_cat.http.HttpManager;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class LuckyWheelDialog extends TBaseDialog {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_wheel)
    ImageView ivWheel;
    @BindView(R.id.iv_btn)
    ImageView ivBtn;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_btn_start)
    TextView tvBtnStart;
    @BindView(R.id.tv_voucher_counts)
    TextView tvVoucherCounts;
    private ObjectAnimator objectAnimator;
    BaseActivity baseActivity;

    public LuckyWheelDialog(Context context, BaseActivity baseActivity) {
        super(context, R.layout.dialog_lucky_wheel);
        setWindowParam(0.9f, ConvertUtils.dp2px(469), Gravity.CENTER, 0);
        this.baseActivity = baseActivity;
        requestGetVideoTimes();
    }

    @OnClick({R.id.iv_close, R.id.tv_btn_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_btn_start:
                tvBtnStart.setEnabled(false);
                requestWheelResult(baseActivity);
                break;
        }
    }

    public void requestWheelResult(BaseActivity baseActivity) {
        HttpManager.toRequst(HttpManager.getApi().getWheelResult(), new BaseObserver<WheelResultBean>(baseActivity) {
            @Override
            public void _onNext(WheelResultBean wheelResultBean) {
                startWheel(wheelResultBean);
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
                tvBtnStart.setEnabled(true);
            }
        });
    }

    //获取转盘券和视频观看次数
    public void requestGetVideoTimes() {
        HttpManager.toRequst(HttpManager.getApi().getAdAndVoucherTimes(), new BaseObserver<Object>(baseActivity) {
            @Override
            public void _onNext(Object obj) {
                try {
                    JSONObject jsonObject = new JSONObject(obj.toString());
                    int adRemainTimes = jsonObject.optInt("adRemainTimes");
                    int voucherRemainTimes = jsonObject.optInt("voucherRemainTimes");
                    tvVoucherCounts.setText(voucherRemainTimes + "");
                    if (voucherRemainTimes <= 0) {
                        tvBtnStart.setEnabled(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    /**
     * result
     * * 1.少量
     * * 2.中量
     * * 3.大量
     * * 4.大量
     * * 5.海量
     * * 6.5倍
     * * 7.10倍
     * * 8.红包
     */
    private void startWheel(WheelResultBean wheelResultBean) {
//        wheelResultBean.setResult(7);
        int result = wheelResultBean.getResult();
        String message = "";
        int angle = 0;
        switch (result) {
            case 1://少量
                angle = 0;
                message = "少量" + "reuslt=" + result;
                break;
            case 2://中量
                angle = 45;
                message = "中量" + "reuslt=" + result;
                break;
            case 3://中量
                angle = 315;
                message = "中量" + "reuslt=" + result;
                break;
            case 4://大量
                angle = 180;
                message = "大量" + "reuslt=" + result;
                break;
            case 5://海量
                angle = 225;
                message = "海量" + "reuslt=" + result;
                break;
            case 6://5倍
                angle = 270;
                message = "5倍" + "reuslt=" + result;
                break;
            case 7://10倍
                angle = 135;
                message = "10倍" + "reuslt=" + result;
                break;
            case 8://红包
                message = "红包" + "reuslt=" + result;
                angle = 90;
                break;
        }

        /** 设置旋转动画 */
        objectAnimator = ObjectAnimator.ofFloat(ivWheel, "rotation", 0f, 360 * 3 + angle).setDuration(3000);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        String finalMessage = message;
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                tvBtnStart.setEnabled(true);
//                ToastUtils.showShort(finalMessage);
                switch (result) {
                    case 1://少量
                        LuckMoneyDialog luckMoneyDialog = new LuckMoneyDialog(mContext, wheelResultBean);
                        luckMoneyDialog.show();
                        break;
                    case 2://中量
                        LuckMoneyDialog luckMoneyDialog1 = new LuckMoneyDialog(mContext, wheelResultBean);
                        luckMoneyDialog1.show();
                        break;
                    case 3://中量
                        LuckMoneyDialog luckMoneyDialog2 = new LuckMoneyDialog(mContext, wheelResultBean);
                        luckMoneyDialog2.show();
                        break;
                    case 4://大量
                        LuckMoneyDialog luckMoneyDialog3 = new LuckMoneyDialog(mContext, wheelResultBean);
                        luckMoneyDialog3.show();
                        break;
                    case 5://海量
                        LuckMoneyDialog luckMoneyDialog4 = new LuckMoneyDialog(mContext, wheelResultBean);
                        luckMoneyDialog4.show();
                        break;
                    case 6://5倍
                        LuckyBoxDialogBoxDialog luckyBoxDialogBoxDialog = new LuckyBoxDialogBoxDialog(mContext, wheelResultBean, baseActivity);
                        luckyBoxDialogBoxDialog.show();
                        break;
                    case 7://10倍
                        LuckyBoxDialogBoxDialog luckyBoxDialogBoxDialog2 = new LuckyBoxDialogBoxDialog(mContext, wheelResultBean, baseActivity);
                        luckyBoxDialogBoxDialog2.show();
                        break;
                    case 8://红包
                        RedPacketDialog redPacketDialog = new RedPacketDialog(mContext, wheelResultBean.getRedpack(), baseActivity);
                        redPacketDialog.show();
                        break;
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
    }
}
