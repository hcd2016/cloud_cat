package com.vpfinace.cloud_cat.ui;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.tab_1;
    }

    @Override
    public void initPresenter() {

    }

    public static HomeFragment homeFragment;

    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }
}
