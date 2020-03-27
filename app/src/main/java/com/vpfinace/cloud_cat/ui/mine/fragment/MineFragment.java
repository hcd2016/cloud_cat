package com.vpfinace.cloud_cat.ui.mine.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.BarUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;
import com.vpfinace.cloud_cat.ui.mine.activity.AnswerActivity;
import com.vpfinace.cloud_cat.ui.mine.activity.AuthActivity;
import com.vpfinace.cloud_cat.ui.mine.activity.ChannelActivity;
import com.vpfinace.cloud_cat.ui.mine.activity.HelpActiviy;
import com.vpfinace.cloud_cat.ui.mine.activity.MsgActivity;
import com.vpfinace.cloud_cat.ui.mine.activity.MyInviteCodeActivity;
import com.vpfinace.cloud_cat.ui.mine.activity.MyWalletActivity;
import com.vpfinace.cloud_cat.ui.mine.activity.SettingActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {
    @BindView(R.id.v_status_view)
    View vStatusView;
    @BindView(R.id.ll_btn_red_packet)
    LinearLayout llBtnRedPacket;
    @BindView(R.id.ll_btn_msg)
    LinearLayout llBtnMsg;
    @BindView(R.id.ll_my_wallet)
    LinearLayout llMyWallet;
    @BindView(R.id.ll_btn_channel)
    LinearLayout llBtnChannel;
    @BindView(R.id.ll_btn_my_invite_code)
    LinearLayout llBtnMyInviteCode;
    @BindView(R.id.ll_btn_answer)
    LinearLayout llBtnAnswer;
    @BindView(R.id.ll_btn_auth)
    LinearLayout llBtnAuth;
    @BindView(R.id.ll_btn_help)
    LinearLayout llBtnHelp;
    @BindView(R.id.ll_btn_setting)
    LinearLayout llBtnSetting;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
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
    }

    public static MineFragment homeFragment;

    public static MineFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new MineFragment();
        }
        return homeFragment;
    }

    @OnClick({R.id.ll_btn_red_packet, R.id.ll_btn_msg, R.id.ll_my_wallet, R.id.ll_btn_channel, R.id.ll_btn_my_invite_code, R.id.ll_btn_answer, R.id.ll_btn_auth, R.id.ll_btn_help, R.id.ll_btn_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_btn_red_packet:
                break;
            case R.id.ll_btn_msg:
                startActivity(MsgActivity.class);
                break;
            case R.id.ll_my_wallet:
                startActivity(MyWalletActivity.class);
                break;
            case R.id.ll_btn_channel:
                startActivity(ChannelActivity.class);
                break;
            case R.id.ll_btn_my_invite_code:
                startActivity(MyInviteCodeActivity.class);
                break;
            case R.id.ll_btn_answer:
                startActivity(AnswerActivity.class);
                break;
            case R.id.ll_btn_auth:
                startActivity(AuthActivity.class);
                break;
            case R.id.ll_btn_help:
                startActivity(HelpActiviy.class);
                break;
            case R.id.ll_btn_setting:
                startActivity(SettingActivity.class);
                break;
        }
    }
}
