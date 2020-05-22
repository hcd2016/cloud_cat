package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.WheelResultBean;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.utils.AdManager;

import org.json.JSONException;
import org.json.JSONObject;

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
    @BindView(R.id.tv_box_desc)
    TextView tvBoxDesc;
    @BindView(R.id.tv_desc2)
    TextView tvDesc2;
    @BindView(R.id.tv_times_desc)
    TextView tvTimesDesc;
    private BaseActivity baseActivity;
    private long num;//倍数

    public LuckyBoxDialogBoxDialog(Context context, WheelResultBean wheelResultBean, BaseActivity baseActivity) {
        super(context, R.layout.dialog_lucky_box);
        setWindowParam(0.7f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        this.baseActivity = baseActivity;
        setData(wheelResultBean);
    }

    private void setData(WheelResultBean wheelResultBean) {
        if(wheelResultBean.getResult() == 6) {
            tvBoxDesc.setText("恭喜获得5倍宝箱");
            tvDesc2.setText("发财啦，下次奖励翻5倍！");
            num = 5;
        }else if(wheelResultBean.getResult() == 7) {
            tvBoxDesc.setText("恭喜获得10倍宝箱");
            tvDesc2.setText("发财啦，下次奖励翻10倍！");
            num = 10;
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
                    int voucherRemainTimes = jsonObject.optInt("voucherRemainTimes");
                    tvTimesDesc.setText("每日中午、晚上12点重置视频次数(剩余"+adRemainTimes+"次)");
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

    @OnClick({R.id.fl_close, R.id.tv_btn_look_sp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                dismiss();
                break;
            case R.id.tv_btn_look_sp:
                AdManager.playRewardVideo(baseActivity,2,10);
                dismiss();
                break;
        }
    }
}
