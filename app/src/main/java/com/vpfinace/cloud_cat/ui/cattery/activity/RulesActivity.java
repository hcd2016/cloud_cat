package com.vpfinace.cloud_cat.ui.cattery.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 猫舍玩法规则
 */
public class RulesActivity extends BaseActivity {
    @BindView(R.id.ll_rules_container)
    LinearLayout llRulesContainer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rules;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        initRelusItems();
    }

    private void initRelusItems() {
        llRulesContainer.removeAllViews();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            View view = View.inflate(this,R.layout.item_rules,null);
            list.add(""+i);
            llRulesContainer.addView(view);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
