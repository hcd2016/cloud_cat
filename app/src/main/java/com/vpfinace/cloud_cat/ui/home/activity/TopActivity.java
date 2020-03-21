package com.vpfinace.cloud_cat.ui.home.activity;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.ui.home.fragment.HomeFragment;
import com.vpfinace.cloud_cat.ui.home.fragment.TopFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopActivity extends BaseActivity {
    @BindView(R.id.v_status_view)
    View vStatusView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_update_desc)
    TextView tvUpdateDesc;
    @BindView(R.id.table_layout)
    SlidingTabLayout tableLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_btn_invite)
    TextView tvBtnInvite;
    private String[] tabs;

    @Override
    public int getLayoutId() {
        return R.layout.activity_top;
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
        tabs = new String[]{"金币排行", "分红喵排行", "收益排行"};
        viewPager.setAdapter(new TopAdapter(getSupportFragmentManager()));
        tableLayout.setViewPager(viewPager, tabs);
        for (int i = 0; i < tabs.length; i++) {
            tableLayout.getTitleView(i).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        tableLayout.getTitleView(0).setPadding(0, 0, 0, ConvertUtils.dp2px(5));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < tabs.length; i++) {
                    if(position == i) {
                        tableLayout.getTitleView(position).setPadding(0, 0, 0, ConvertUtils.dp2px(5));
                    }else {
                        tableLayout.getTitleView(i).setPadding(0, 0, 0, 0);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class TopAdapter extends FragmentPagerAdapter {

        public TopAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TopFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return tabs.length;
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_btn_invite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_btn_invite:
                break;
        }
    }
}
