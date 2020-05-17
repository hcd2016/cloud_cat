package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseFragment;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.UserCenter;
import com.vpfinace.cloud_cat.global.EventStrings;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.ui.mine.activity.MyWalletActivity;
import com.vpfinace.cloud_cat.utils.ArithUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 红包弹窗
 */
public class RedPacketDialog extends TBaseDialog {
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_app_name)
    TextView tvAppName;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.iv_open)
    ImageView ivOpen;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.ll_my_packet_container)
    LinearLayout llMyPacketContainer;
    @BindView(R.id.iv_btn_withdraw)
    ImageView ivBtnWithdraw;
    @BindView(R.id.ll_packet_result_container)
    LinearLayout llPacketResultContainer;
    @BindView(R.id.ll_open_packet_container)
    LinearLayout llOpenPacketContainer;
    BaseActivity baseFragment;
    UserCenter.RedpackBean redPack;

    public RedPacketDialog(Context context, UserCenter.RedpackBean redPack, BaseActivity baseFragment) {
        super(context, R.layout.dialog_red_packet);
        setWindowParam(0.8f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        tvAmount.setText(ArithUtil.div(redPack.getAmount(),100)+"");
        tvDesc.setText(redPack.getExplain());
        this.baseFragment = baseFragment;
        this.redPack = redPack;
    }

    public void requestOpenRedPack() {
        HttpManager.toRequst(HttpManager.getApi().redPackOpen(redPack.getId()+""), new BaseObserver(baseFragment) {
            @Override
            public void _onNext(Object o) {
                llOpenPacketContainer.setVisibility(View.GONE);
                llPacketResultContainer.setVisibility(View.VISIBLE);
                tvDesc.setText("恭喜您开红包获得");
                EventBus.getDefault().post(EventStrings.MINE_REFRESH);
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    @OnClick({R.id.fl_close, R.id.iv_open, R.id.iv_btn_withdraw, R.id.ll_my_packet_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                dismiss();
                break;
            case R.id.iv_open:
                requestOpenRedPack();
                break;
            case R.id.iv_btn_withdraw:
                Intent intent = new Intent(mContext, MyWalletActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ll_my_packet_container:
                Intent intent1 = new Intent(mContext, MyWalletActivity.class);
                mContext.startActivity(intent1);
                break;
        }
    }


}
