package com.vpfinace.cloud_cat.ui.mine.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.utils.CountDownTimerUtils;
import com.vpfinace.cloud_cat.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindPhoneActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_btn_get_code)
    TextView tvBtnGetCode;
    @BindView(R.id.et_vercode)
    EditText etVercode;
    @BindView(R.id.btn_save)
    TextView btnSave;
    private CountDownTimerUtils countDownTimerUtils;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
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

    private boolean isPhoneEmpty = true;
    private boolean isCodeEmpty = true;

    @Override
    protected void initView() {
        super.initView();
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    tvBtnGetCode.setTextColor(MyUtils.getColor(R.color.black_9));
                    tvBtnGetCode.setBackgroundResource(R.drawable.shape_gray_f4_c12);
                    tvBtnGetCode.setEnabled(false);
                    isPhoneEmpty = true;
                    btnSave.setEnabled(false);
                } else {
                    if (countDownTimerUtils != null && !countDownTimerUtils.isTicking()) { //不在进行倒计时
                        tvBtnGetCode.setTextColor(MyUtils.getColor(R.color.white));
                        tvBtnGetCode.setBackgroundResource(R.drawable.shape_red_554_c12);
                        tvBtnGetCode.setEnabled(true);
                    }
                    isPhoneEmpty = false;
                    if (!isCodeEmpty) {
                        btnSave.setEnabled(true);
                    }
                }
            }
        });

        etVercode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    isCodeEmpty = true;
                    btnSave.setEnabled(false);
                } else {
                    isCodeEmpty = false;
                    if (!isPhoneEmpty) {
                        btnSave.setEnabled(true);
                    }
                }
            }
        });
    }

    @OnClick({R.id.tv_btn_get_code, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_get_code:
                countDownTimerUtils = new CountDownTimerUtils(tvBtnGetCode, 10000 + 50, 1000,
                        R.drawable.shape_gray_f4_c12, R.drawable.shape_red_554_c12, MyUtils.getColor(R.color.black_9), MyUtils.getColor(R.color.white), "s 可重发", "重新获取");
                countDownTimerUtils.start();
                break;
            case R.id.btn_save:
                if (isPhoneEmpty) {
                    ToastUtils.showShort("手机号码不能为空");
                    return;
                }
                if (isPhoneEmpty) {
                    ToastUtils.showShort("验证码不能为空");
                    return;
                }
                break;
        }
    }
}
