package com.vpfinace.cloud_cat.ui;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;

public class MyMillFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.tab_1;
    }

    @Override
    public void initPresenter() {

    }
    public static MyMillFragment homeFragment;

    public static MyMillFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new MyMillFragment();
        }
        return homeFragment;
    }

}
