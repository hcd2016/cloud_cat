package com.vpfinace.cloud_cat.ui.cattery.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;
import com.vpfinace.cloud_cat.dialog.MyInviteFriendDialog;
import com.vpfinace.cloud_cat.ui.cattery.activity.InviteRecordsActivity;
import com.vpfinace.cloud_cat.ui.cattery.activity.RulesActivity;

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
    }

    public static CatteryFragment homeFragment;

    public static CatteryFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new CatteryFragment();
        }
        return homeFragment;
    }

    @OnClick({R.id.ll_invite_records_container, R.id.tv_btn_invite, R.id.tv_btn_rules,R.id.ll_my_invite_friend_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_invite_records_container:
                startActivity(InviteRecordsActivity.class);
                break;
            case R.id.tv_btn_invite:
                break;
            case R.id.tv_btn_rules:
                startActivity(RulesActivity.class);
                break;
            case R.id.ll_my_invite_friend_container:
                MyInviteFriendDialog myInviteFriendDialog = new MyInviteFriendDialog(getActivity());
                myInviteFriendDialog.show();
                break;
        }
    }
}
