package com.vpfinace.cloud_cat.ui;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;

public class MailFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.tab_1;
    }

    @Override
    public void initPresenter() {

    }

    public static MailFragment homeFragment;

    public static MailFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new MailFragment();
        }
        return homeFragment;
    }
}
