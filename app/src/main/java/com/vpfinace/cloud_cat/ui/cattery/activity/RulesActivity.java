package com.vpfinace.cloud_cat.ui.cattery.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.CatteryBean;
import com.vpfinace.cloud_cat.http.HttpManager;

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
    private List<CatteryBean.SteplistBean> list;

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
        list = new ArrayList<>();
        requestCatteyInfo();
    }

    private void initRelusItems() {
        llRulesContainer.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            View view = View.inflate(this,R.layout.item_rules,null);
            TextView tv_step = view.findViewById(R.id.tv_step);
            TextView tv_target = view.findViewById(R.id.tv_target);
            TextView tv_sl = view.findViewById(R.id.tv_sl);

            tv_step.setText("第"+list.get(i).getStep()+"阶段");
            tv_target.setText(list.get(i).getTargetNum()+"");
            tv_sl.setText("x"+list.get(i).getRate());
            llRulesContainer.addView(view);
        }
    }

    public void requestCatteyInfo() {
        HttpManager.toRequst(HttpManager.getApi().getCatteryInfo(), new BaseObserver<CatteryBean>(this) {
            @Override
            public void _onNext(CatteryBean catteryBean) {
                if(catteryBean != null && catteryBean.getSteplist() != null && catteryBean.getSteplist().size() >0) {
                    list.addAll(catteryBean.getSteplist());
                    initRelusItems();
                }
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
