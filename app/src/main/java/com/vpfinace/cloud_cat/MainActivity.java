package com.vpfinace.cloud_cat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.vpfinace.cloud_cat.base.PermissionBaseActivity;
import com.vpfinace.cloud_cat.ui.home.fragment.HomeFragment;
import com.vpfinace.cloud_cat.ui.cattery.fragment.CatteryFragment;
import com.vpfinace.cloud_cat.ui.mine.fragment.MineFragment;
import com.vpfinace.cloud_cat.utils.StatusTextUtils;
import com.vpfinace.cloud_cat.weight.MyRadioButton;

import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends PermissionBaseActivity {
    @BindView(R.id.fl_fragment_container)
    FrameLayout flFragmentContainer;
    @BindView(R.id.rb_1)
    MyRadioButton rb1;
    @BindView(R.id.rb_2)
    MyRadioButton rb2;
    @BindView(R.id.rb_3)
    MyRadioButton rb3;
    @BindView(R.id.group)
    RadioGroup group;

    public static final String TAG1 = "tag1";
    public static final String TAG2 = "tag2";
    public static final String TAG3 = "tag3";

    private HomeFragment homeFragment;
    private CatteryFragment catteryFragment;
    private MineFragment mineFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        StatusTextUtils.setLightStatusBar(this, true);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1://首页
                        showFragmentByTag(TAG1);
                        break;
                    case R.id.rb_2://猫舍
                        showFragmentByTag(TAG2);
                        break;
                    case R.id.rb_3://我的
                        showFragmentByTag(TAG3);
                        break;
                }
            }
        });
        rb1.setChecked(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);//必须要写
        String tag = getIntent().getStringExtra("tag");
        if (!TextUtils.isEmpty(tag)) {
            switchTab(tag);
        } else {
            switchTab(TAG1);
        }
    }

    //显示fragment
    public void showFragmentByTag(String tag) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        switch (tag) {
            case TAG1:
                homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(TAG1);
                if (null == homeFragment) {//不存在,添加
                    homeFragment = HomeFragment.getInstance();
                    beginTransaction.add(R.id.fl_fragment_container, homeFragment, TAG1);
                }
                hideAll(beginTransaction);//隐藏所有fragment
                beginTransaction.show(homeFragment);//显示当前fragment
                beginTransaction.commitAllowingStateLoss();//提交事务
                break;
            case TAG2:
                catteryFragment = (CatteryFragment) getSupportFragmentManager().findFragmentByTag(TAG2);
                if (null == catteryFragment) {//不存在,添加
                    catteryFragment = CatteryFragment.getInstance();
                    beginTransaction.add(R.id.fl_fragment_container, catteryFragment, TAG2);
                }
                hideAll(beginTransaction);//隐藏所有fragment
                beginTransaction.show(catteryFragment);//显示当前fragment
                beginTransaction.commitAllowingStateLoss();//提交事务
                break;
            case TAG3:
                mineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(TAG3);
                if (null == mineFragment) {//不存在,添加
                    mineFragment = MineFragment.getInstance();
                    beginTransaction.add(R.id.fl_fragment_container, mineFragment, TAG3);
                }
                hideAll(beginTransaction);//隐藏所有fragment
                beginTransaction.show(mineFragment);//显示当前fragment
                beginTransaction.commitAllowingStateLoss();//提交事务
                break;
        }
    }

    public void switchTab(String tag) {
        switch (tag) {
            case TAG1:
                rb1.setChecked(true);
                StatusTextUtils.setLightStatusBar(this, false);
                break;
            case TAG2:
                rb2.setChecked(true);
                StatusTextUtils.setLightStatusBar(this, true);
                break;
            case TAG3:
                rb3.setChecked(true);
                StatusTextUtils.setLightStatusBar(this, true);
                break;
        }
    }

    //隐藏所有
    public void hideAll(FragmentTransaction beginTransaction) {
        if (null != homeFragment) {
            beginTransaction.hide(homeFragment);
        }
        if (null != catteryFragment) {
            beginTransaction.hide(catteryFragment);
        }
        if (null != mineFragment) {
            beginTransaction.hide(mineFragment);
        }
    }


    private long temptime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
    {
        // TODO Auto-generated method stub

        if ((keyCode == KeyEvent.KEYCODE_BACK) && (event.getAction() == KeyEvent.ACTION_DOWN)) {
            if (System.currentTimeMillis() - temptime > 2000) // 2s内再次选择back键有效
            {
                System.out.println(Toast.LENGTH_LONG);
                Toast.makeText(this, "请在按一次返回退出", Toast.LENGTH_LONG).show();
                temptime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0); //凡是非零都表示异常退出!0表示正常退出!
            }

            return true;

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public static void goThis(Context context, String tag) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("tag", tag);
        context.startActivity(intent);
    }
}
