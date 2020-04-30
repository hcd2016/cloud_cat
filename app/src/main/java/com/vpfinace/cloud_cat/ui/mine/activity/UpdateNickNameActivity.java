package com.vpfinace.cloud_cat.ui.mine.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.weight.MyTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateNickNameActivity extends BaseActivity {
    @BindView(R.id.my_title)
    MyTitle myTitle;
    @BindView(R.id.et_nick_name)
    EditText etNickName;
    @BindView(R.id.iv_clean)
    ImageView ivClean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_nick_name;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        myTitle.setIsRightTextVisible(View.VISIBLE).setTvRightTextColor(MyUtils.getColor(R.color.red_554)).setTvRightText("保存").setOnRightTextClickListener(new MyTitle.OnRightTextClickListener() {
            @Override
            public void rightTextClick() {
                if (TextUtils.isEmpty(etNickName.getText().toString())) {
                    ToastUtils.showShort("昵称不能为空!");
                } else {
                    ToastUtils.showShort("保存成功!");
                    finish();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_clean)
    public void onViewClicked() {
        etNickName.setText("");
    }
}
