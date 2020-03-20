package com.vpfinace.cloud_cat.ui.mine;

import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.BarUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;

import butterknife.BindView;

public class MineFragment extends BaseFragment {
    @BindView(R.id.v_status_view)
    View vStatusView;

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

}
