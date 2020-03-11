package com.vpfinace.cloud_cat.ui;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;

public class SettingFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.tab_1;
    }

    @Override
    public void initPresenter() {

    }

    public static SettingFragment homeFragment;

    public static SettingFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new SettingFragment();
        }
        return homeFragment;
    }
}
