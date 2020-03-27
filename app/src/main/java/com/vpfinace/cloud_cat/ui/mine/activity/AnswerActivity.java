package com.vpfinace.cloud_cat.ui.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.weight.MyTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 答题
 */
public class AnswerActivity extends BaseActivity {

    @BindView(R.id.my_title)
    MyTitle myTitle;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_answer_desc)
    TextView tvAnswerDesc;
    @BindView(R.id.rb_1)
    RadioButton rb1;
    @BindView(R.id.rb_2)
    RadioButton rb2;
    @BindView(R.id.rb_3)
    RadioButton rb3;
    @BindView(R.id.rb_4)
    RadioButton rb4;
    @BindView(R.id.btn_next)
    TextView btnNext;
    @BindView(R.id.rg_1)
    RadioGroup rg1;
    @BindView(R.id.cb1)
    CheckBox cb1;
    @BindView(R.id.cb2)
    CheckBox cb2;
    @BindView(R.id.cb3)
    CheckBox cb3;
    @BindView(R.id.cb4)
    CheckBox cb4;
    @BindView(R.id.rg_2)
    RadioGroup rg2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_answer;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    int type = 0;//0为单选,1为多选
    int position = 1;//当前第几题
    @Override
    protected void initView() {
        super.initView();
        myTitle.setTitleBackgroudColor(MyUtils.getColor(R.color.gray_f5));
        if(type == 0) {
            rg1.setVisibility(View.VISIBLE);
            rg2.setVisibility(View.GONE);
        }else {
            rg1.setVisibility(View.GONE);
            rg2.setVisibility(View.VISIBLE);
        }
        btnNext.setEnabled(true);
    }

    @OnClick({R.id.rb_1, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_1:
                break;
            case R.id.btn_next:
                startActivity(AnswerResultActivity.class);
                break;
        }
    }
}
