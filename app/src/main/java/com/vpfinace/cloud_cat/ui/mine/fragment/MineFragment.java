package com.vpfinace.cloud_cat.ui.mine.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseFragment;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.MyInviteCodeBean;
import com.vpfinace.cloud_cat.bean.User;
import com.vpfinace.cloud_cat.bean.UserCenter;
import com.vpfinace.cloud_cat.dialog.RedPacketDialog;
import com.vpfinace.cloud_cat.global.EventStrings;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.ui.mine.activity.AnswerActivity;
import com.vpfinace.cloud_cat.ui.mine.activity.AuthActivity;
import com.vpfinace.cloud_cat.ui.mine.activity.ChannelActivity;
import com.vpfinace.cloud_cat.ui.mine.activity.HelpActiviy;
import com.vpfinace.cloud_cat.ui.mine.activity.MsgActivity;
import com.vpfinace.cloud_cat.ui.mine.activity.MyInviteCodeActivity;
import com.vpfinace.cloud_cat.ui.mine.activity.MyWalletActivity;
import com.vpfinace.cloud_cat.ui.mine.activity.SettingActivity;
import com.vpfinace.cloud_cat.utils.GlideUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    @BindView(R.id.tv_invite_code)
    TextView tvInviteCode;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.v_line_gone)
    View vLineGone;
    private UserCenter userCenter;

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
        EventBus.getDefault().register(this);
        requestMyInviteCode();
        requestGetUserInfo();
        requestUserCenter();
    }

    public static MineFragment homeFragment;

    public static MineFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new MineFragment();
        }
        return homeFragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (event.equals(EventStrings.MINE_REFRESH)) {//刷新
            requestMyInviteCode();
            requestGetUserInfo();
            requestUserCenter();
        }
    }

    public void requestMyInviteCode() {
        HttpManager.toRequst(HttpManager.getApi().getInviteCode(), new BaseObserver<MyInviteCodeBean>(this) {
            @Override
            public void _onNext(MyInviteCodeBean myInviteCodeBean) {
                tvInviteCode.setText(myInviteCodeBean.getInviteCode() + "");
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    //用户中心,红包信息
    public void requestUserCenter() {
        HttpManager.toRequst(HttpManager.getApi().getUserCenter(), new BaseObserver<UserCenter>(this) {
            @Override
            public void _onNext(UserCenter userCenter) {
                MineFragment.this.userCenter = userCenter;
                if(userCenter.getRedpack() == null) {
                    llBtnRedPacket.setVisibility(View.GONE);
                    vLineGone.setVisibility(View.GONE);
                }else {
                    llBtnRedPacket.setVisibility(View.VISIBLE);
                    vLineGone.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    //获取用户信息
    public void requestGetUserInfo() {
        HttpManager.toRequst(HttpManager.getApi().getUserInfo(), new BaseObserver<User>(this) {
            @Override
            public void _onNext(User user) {
                setViewData(user);
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    private void setViewData(User user) {
        if (user == null) return;
        tvNickName.setText(user.getNickname());
        tvId.setText("ID:" + user.getId());
        tvAmount.setText(user.getUserFund().getCash() + "");
        GlideUtils.loadCircle(getActivity(), user.getHeadImgUrl(), ivHeader);
    }

    @OnClick({R.id.ll_btn_red_packet, R.id.ll_btn_msg, R.id.ll_my_wallet, R.id.ll_btn_channel, R.id.ll_btn_my_invite_code, R.id.ll_btn_answer, R.id.ll_btn_auth, R.id.ll_btn_help, R.id.ll_btn_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_btn_red_packet:
                if(userCenter != null) {
                    RedPacketDialog redPacketDialog = new RedPacketDialog(getActivity(),userCenter.getRedpack(),(BaseActivity) getActivity());
                    redPacketDialog.show();
                }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
