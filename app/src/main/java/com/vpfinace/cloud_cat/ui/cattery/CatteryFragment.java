package com.vpfinace.cloud_cat.ui.cattery;

import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.BarUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;

import butterknife.BindView;

/**
 * 猫舍
 */
public class CatteryFragment extends BaseFragment {
    @BindView(R.id.v_status_view)
    View vStatusView;

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
}
