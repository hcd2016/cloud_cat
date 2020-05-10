package com.vpfinace.cloud_cat.ui.cattery.activity;

import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.ui.cattery.fragment.InviteRecordsFragment;
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.weight.MyTitle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InviteRecordsActivity extends BaseActivity {
    @BindView(R.id.my_title)
    MyTitle myTitle;
    @BindView(R.id.table_layout)
    SlidingTabLayout tableLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private String[] tabs;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite_records;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        myTitle.setTitleBackgroudColor(MyUtils.getColor(R.color.gray_f5));
        tabs = new String[]{"直邀好友","扩散好友"};
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tableLayout.setViewPager(viewPager, tabs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return InviteRecordsFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return tabs.length;
        }
    }
}
