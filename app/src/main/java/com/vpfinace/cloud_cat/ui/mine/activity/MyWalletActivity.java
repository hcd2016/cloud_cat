package com.vpfinace.cloud_cat.ui.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends BaseActivity {
    @BindView(R.id.v_status_view)
    View vStatusView;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_record)
    TextView tvRecord;
    @BindView(R.id.ll_1_container)
    LinearLayout ll1Container;
    @BindView(R.id.tv_first_btn)
    TextView tvFirstBtn;
    @BindView(R.id.ll_20_container)
    LinearLayout ll20Container;
    @BindView(R.id.ll_50_container)
    LinearLayout ll50Container;
    @BindView(R.id.ll_100_container)
    LinearLayout ll100Container;
    @BindView(R.id.ll_500_container)
    LinearLayout ll500Container;
    @BindView(R.id.ll_1000_container)
    LinearLayout ll1000Container;
    @BindView(R.id.btn_save)
    TextView btnSave;
    @BindView(R.id.tv_1_1)
    TextView tv11;
    @BindView(R.id.tv_1_2)
    TextView tv12;
    @BindView(R.id.tv_20_1)
    TextView tv201;
    @BindView(R.id.tv_20_2)
    TextView tv202;
    @BindView(R.id.tv_50_1)
    TextView tv501;
    @BindView(R.id.tv_50_2)
    TextView tv502;
    @BindView(R.id.tv_100_1)
    TextView tv1001;
    @BindView(R.id.tv_100_2)
    TextView tv1002;
    @BindView(R.id.tv_500_1)
    TextView tv5001;
    @BindView(R.id.tv_500_2)
    TextView tv5002;
    @BindView(R.id.tv_1000_1)
    TextView tv10001;
    @BindView(R.id.tv_1000_2)
    TextView tv10002;
    @BindView(R.id.ll_wechat_container)
    LinearLayout llWechatContainer;
    @BindView(R.id.ll_alipay_container)
    LinearLayout llAlipayContainer;
    @BindView(R.id.iv_wechat_icon)
    ImageView ivWechatIcon;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.iv_aplipay_icon)
    ImageView ivAplipayIcon;
    @BindView(R.id.tv_alipay)
    TextView tvAlipay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        ViewGroup.LayoutParams layoutParams = vStatusView.getLayoutParams();
        layoutParams.height = BarUtils.getStatusBarHeight();
        vStatusView.setLayoutParams(layoutParams);
        btnSave.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    double choiseAmount = 0.5;
    int withDrawWay = 0;//提现方式,0为微信,1为支付宝

    @OnClick({R.id.tv_record, R.id.iv_close, R.id.ll_1_container, R.id.ll_20_container, R.id.ll_50_container, R.id.ll_100_container, R.id.ll_500_container, R.id.ll_1000_container, R.id.btn_save, R.id.ll_alipay_container, R.id.ll_wechat_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.ll_1_container:
                changeBg(ll1Container);
                choiseAmount = 0.5;
                break;
            case R.id.ll_20_container:
                changeBg(ll20Container);
                choiseAmount = 20;
                break;
            case R.id.ll_50_container:
                changeBg(ll50Container);
                choiseAmount = 50;
                break;
            case R.id.ll_100_container:
                changeBg(ll100Container);
                choiseAmount = 100;
                break;
            case R.id.ll_500_container:
                changeBg(ll500Container);
                choiseAmount = 500;
                break;
            case R.id.ll_1000_container:
                changeBg(ll1000Container);
                choiseAmount = 1000;
                break;
            case R.id.btn_save:
                startActivity(WithdrawResultActivity.class);
                break;
            case R.id.ll_alipay_container:
                llAlipayContainer.setBackgroundResource(R.drawable.shape_blue_6db_c8);
                llWechatContainer.setBackgroundResource(R.drawable.shape_gray_6f8_c8);
                ivAplipayIcon.setImageResource(R.drawable.wallet_alipay);
                tvAlipay.setTextColor(MyUtils.getColor(R.color.white));
                ivWechatIcon.setImageResource(R.drawable.wallet_wechat_gray);
                tvWechat.setTextColor(MyUtils.getColor(R.color.black_6));
                withDrawWay = 1;
                break;
            case R.id.ll_wechat_container:
                llAlipayContainer.setBackgroundResource(R.drawable.shape_gray_6f8_c8);
                llWechatContainer.setBackgroundResource(R.drawable.shape_green_d36_c8);
                ivAplipayIcon.setImageResource(R.drawable.wallet_alipay_gray);
                tvAlipay.setTextColor(MyUtils.getColor(R.color.black_6));
                ivWechatIcon.setImageResource(R.drawable.wallet_wechat);
                tvWechat.setTextColor(MyUtils.getColor(R.color.white));
                withDrawWay = 0;
                break;
            case R.id.tv_record://提现记录
                startActivity(WalletRecordsActivity.class);
                break;
        }
    }

    public void changeBg(ViewGroup clickView) {
        tv11.setTextColor(MyUtils.getColor(R.color.black_6));
        tv12.setTextColor(MyUtils.getColor(R.color.black_6));
        tv201.setTextColor(MyUtils.getColor(R.color.black_6));
        tv202.setTextColor(MyUtils.getColor(R.color.black_6));
        tv501.setTextColor(MyUtils.getColor(R.color.black_6));
        tv502.setTextColor(MyUtils.getColor(R.color.black_6));
        tv1001.setTextColor(MyUtils.getColor(R.color.black_6));
        tv1002.setTextColor(MyUtils.getColor(R.color.black_6));
        tv5001.setTextColor(MyUtils.getColor(R.color.black_6));
        tv5002.setTextColor(MyUtils.getColor(R.color.black_6));
        tv10001.setTextColor(MyUtils.getColor(R.color.black_6));
        tv10002.setTextColor(MyUtils.getColor(R.color.black_6));
        ll1Container.setBackgroundResource(R.drawable.shape_gray_6f8_c8);
        ll20Container.setBackgroundResource(R.drawable.shape_gray_6f8_c8);
        ll50Container.setBackgroundResource(R.drawable.shape_gray_6f8_c8);
        ll100Container.setBackgroundResource(R.drawable.shape_gray_6f8_c8);
        ll500Container.setBackgroundResource(R.drawable.shape_gray_6f8_c8);
        ll1000Container.setBackgroundResource(R.drawable.shape_gray_6f8_c8);

        TextView child1 = (TextView) clickView.getChildAt(0);
        TextView child2 = (TextView) clickView.getChildAt(1);
        child1.setTextColor(MyUtils.getColor(R.color.white));
        child2.setTextColor(MyUtils.getColor(R.color.white));
        clickView.setBackgroundResource(R.drawable.shape_red_8e_c8);
    }
}
