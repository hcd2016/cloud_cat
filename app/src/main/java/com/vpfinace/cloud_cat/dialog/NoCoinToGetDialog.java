package com.vpfinace.cloud_cat.dialog;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.utils.AdManager;
import com.vpfinace.cloud_cat.utils.ArithUtil;
import com.vpfinace.cloud_cat.utils.UnitUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 金币不足弹窗
 */
public class NoCoinToGetDialog extends TBaseDialog {
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;
    @BindView(R.id.tv_times_desc)
    TextView tvTimesDesc;
    BaseActivity baseActivity;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    private long coinNum;

    public NoCoinToGetDialog(BaseActivity context, long rewardSec, long coinNum) {
        super(context, R.layout.dialog_no_coin_to_get);
        setWindowParam(0.8f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        this.baseActivity = context;
        this.coinNum = coinNum;
        setViewData(rewardSec, coinNum);
    }

    private void setViewData(long rewardSec, long coinNum) {
        tvAmount.setText(UnitUtils.coin2String(coinNum));
        double hTime = ArithUtil.div(rewardSec, 60 * 60);
        if (hTime >= 1) {
            tvBtnConfirm.setText("立即获得" + UnitUtils.nPoint(hTime) + "小时收益");
        } else {
            int mTime = (int) ArithUtil.div(rewardSec, 60, 0);
            tvBtnConfirm.setText("立即获得" + mTime + "分钟收益");
        }
        requestGetVideoTimes();
    }

    //获取转盘券和视频观看次数
    public void requestGetVideoTimes() {
        HttpManager.toRequst(HttpManager.getApi().getAdAndVoucherTimes(), new BaseObserver<Object>(baseActivity) {
            @Override
            public void _onNext(Object obj) {
                try {
                    JSONObject jsonObject = new JSONObject(obj.toString());
                    int adRemainTimes = jsonObject.optInt("adRemainTimes");
                    tvTimesDesc.setText("每日中午、晚上12点重置视频次数(剩余" + adRemainTimes + "次)");

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

    @OnClick({R.id.iv_close, R.id.tv_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_btn_confirm:
                AdManager.playRewardVideo(baseActivity, 1, coinNum);
                dismiss();
                break;
        }
    }
}
