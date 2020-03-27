package com.vpfinace.cloud_cat.ui.mine.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.weight.MyTitle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswerResultActivity extends BaseActivity {
    @BindView(R.id.my_title)
    MyTitle myTitle;
    @BindView(R.id.btn_save)
    TextView btnSave;

    @Override
    public int getLayoutId() {
        return R.layout.activity_answer_result;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        myTitle.setTitleBackgroudColor(MyUtils.getColor(R.color.gray_f5));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
