package com.vpfinace.cloud_cat.ui.mine.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.weight.MyTitle;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 实名认证
 */
public class AuthActivity extends BaseActivity {
    @BindView(R.id.my_title)
    MyTitle myTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.btn_save)
    TextView btnSave;

    @Override
    public int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        myTitle.setTitleBackgroudColor(MyUtils.getColor(R.color.gray_f5));
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s.toString())) {
                    btnSave.setEnabled(false);
                }else {
                    btnSave.setEnabled(true);
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
}
