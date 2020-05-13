package com.vpfinace.cloud_cat.ui.cattery.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.CatteryBean;
import com.vpfinace.cloud_cat.dialog.MyInviteFriendDialog;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.ui.cattery.activity.InviteRecordsActivity;
import com.vpfinace.cloud_cat.ui.cattery.activity.RulesActivity;
import com.vpfinace.cloud_cat.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 猫舍
 */
public class CatteryFragment extends BaseFragment {
    @BindView(R.id.v_status_view)
    View vStatusView;
    @BindView(R.id.tv_btn_invite)
    TextView tvBtnInvite;
    @BindView(R.id.tv_btn_rules)
    LinearLayout tvBtnRules;
    @BindView(R.id.ll_invite_records_container)
    LinearLayout llInviteRecordsContainer;
    @BindView(R.id.tv_friend_counts)
    TextView tvFriendCounts;
    @BindView(R.id.tv_current_earnings)
    TextView tvCurrentEarnings;
    @BindView(R.id.tv_speed_total_amount)
    TextView tvSpeedTotalAmount;
    @BindView(R.id.tv_total_earnings)
    TextView tvTotalEarnings;
    @BindView(R.id.tv_invite_amount)
    TextView tvInviteAmount;
    @BindView(R.id.tv_spread_amount)
    TextView tvSpreadAmount;
    @BindView(R.id.ll_my_invite_friend_container)
    LinearLayout llMyInviteFriendContainer;
    @BindView(R.id.iv_inviter)
    ImageView ivInviter;
    @BindView(R.id.tv_inviter_desc)
    TextView tvInviterDesc;
    @BindView(R.id.tv_inviter_amount)
    TextView tvInviterAmount;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.tv_pb_desc)
    TextView tvPbDesc;
    @BindView(R.id.tv_step_desc)
    TextView tvStepDesc;
    @BindView(R.id.tv_speed_desc)
    TextView tvSpeedDesc;
    private CatteryBean catteryBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cattery;
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
        requestCatteyInfo();
    }

    public static CatteryFragment homeFragment;

    public static CatteryFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new CatteryFragment();
        }
        return homeFragment;
    }

    public void requestCatteyInfo() {
        HttpManager.toRequst(HttpManager.getApi().getCatteryInfo(), new BaseObserver<CatteryBean>(this) {
            @Override
            public void _onNext(CatteryBean catteryBean) {
                setViewData(catteryBean);
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    private void setViewData(CatteryBean catteryBean) {
        if (catteryBean == null) return;
        this.catteryBean = catteryBean;
        tvFriendCounts.setText(catteryBean.getFriendNum() + "");
        tvCurrentEarnings.setText(catteryBean.getTotalEarning() + "");
        tvSpeedTotalAmount.setText(catteryBean.getTarget() + "");

        double sl = 1.0;
        List<CatteryBean.SteplistBean> steplist = catteryBean.getSteplist();
        for (int i = 0; i < steplist.size(); i++) {
            if (steplist.get(i).getStep() == catteryBean.getStep()) {
                sl = steplist.get(i).getRate();
            }

        }
        tvStepDesc.setText("第" + catteryBean.getStep() + "阶段×" + sl + "倍加速");
        tvSpeedDesc.setText(sl+"倍加速中");
        pb.setProgress((int) catteryBean.getRate());
        tvPbDesc.setText("已解锁" + catteryBean.getRate() + "%，解锁后" + catteryBean.getTarget() + "元现金将自动存入钱包");

        tvTotalEarnings.setText(catteryBean.getTotalEarning() + "");
        tvInviteAmount.setText(catteryBean.getTodayInviteEarning() + "");
        tvSpreadAmount.setText(catteryBean.getTodayDiffusionEarning() + "");

        GlideUtils.loadCircle(getActivity(), catteryBean.getMyInviter().getHeadImgUrl(), ivInviter);

        tvInviterDesc.setText("他邀请了" + catteryBean.getMyInviterNum() + "人，累计收益");
        tvInviterAmount.setText(catteryBean.getMyInviterEarning() + "元");


    }

    @OnClick({R.id.ll_invite_records_container, R.id.tv_btn_invite, R.id.tv_btn_rules, R.id.ll_my_invite_friend_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_invite_records_container:
                startActivity(InviteRecordsActivity.class);
                break;
            case R.id.tv_btn_invite:
                //todo
                break;
            case R.id.tv_btn_rules:
                startActivity(RulesActivity.class);
                break;
            case R.id.ll_my_invite_friend_container:
                if (catteryBean != null) {
                    MyInviteFriendDialog myInviteFriendDialog = new MyInviteFriendDialog(getActivity(), catteryBean.getMyInviter());
                    myInviteFriendDialog.show();
                }
                break;
        }
    }
}
