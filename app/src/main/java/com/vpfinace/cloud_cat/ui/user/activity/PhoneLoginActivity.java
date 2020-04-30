package com.vpfinace.cloud_cat.ui.user.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.MainActivity;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.LoginBean;
import com.vpfinace.cloud_cat.global.SpContant;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.utils.CountDownTimerUtils;
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.utils.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneLoginActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_btn_get_code)
    TextView tvBtnGetCode;
    @BindView(R.id.et_vercode)
    EditText etVercode;
    @BindView(R.id.ll_code_container)
    LinearLayout llCodeContainer;
    @BindView(R.id.btn_next)
    TextView btnNext;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.v_line)
    View vLine;
    private CountDownTimerUtils countDownTimerUtils;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_bind_phone;
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
                if(TextUtils.isEmpty(s.toString())) {
                    isPhoneEmpty = true;
                    btnNext.setEnabled(false);

                    tvBtnGetCode.setTextColor(MyUtils.getColor(R.color.black_9));
                    tvBtnGetCode.setBackgroundResource(R.drawable.shape_gray_f4_c12);
                    tvBtnGetCode.setEnabled(false);
                }else {
                    isPhoneEmpty = false;
                    if(step == 1) {
                        btnNext.setEnabled(true);
                    }else {
                        if(isCodeEmpty) {
                            btnNext.setEnabled(false);
                        }else {
                            btnNext.setEnabled(true);
                        }
                    }
                    if (countDownTimerUtils != null && !countDownTimerUtils.isTicking()) { //不在进行倒计时
                        tvBtnGetCode.setTextColor(MyUtils.getColor(R.color.white));
                        tvBtnGetCode.setBackgroundResource(R.drawable.shape_red_554_c12);
                        tvBtnGetCode.setEnabled(true);
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
                if(TextUtils.isEmpty(s.toString())) {
                    isCodeEmpty = true;
                    btnNext.setEnabled(false);
                }else {
                    isCodeEmpty = false;
                    if(isPhoneEmpty) {
                        btnNext.setEnabled(false);
                    }else {
                        btnNext.setEnabled(true);
                    }
                }
            }
        });
    }

    public void requestGetVerCode(){
        HttpManager.toRequst(HttpManager.getApi().getVerifyCode(etPhone.getText().toString()), new BaseObserver(this) {
            @Override
            public void _onNext(Object o) {
                ToastUtils.showShort("发送成功!");
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    public void requestLogin(){
        HttpManager.toRequst(HttpManager.getApi().login(etVercode.getText().toString(),etPhone.getText().toString()), new BaseObserver<LoginBean>(this) {
            @Override
            public void _onNext(LoginBean loginBean) {
                SPUtils.getInstance().put(SpContant.SP_SESSION_ID,loginBean.getSessionId());
                SpUtil.saveBean2Sp(PhoneLoginActivity.this,loginBean.getUser(), SpContant.SP_USER);
                ToastUtils.showShort("登录成功");
                startActivity(MainActivity.class);
                finish();
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }


    int step = 1;

    @OnClick({R.id.tv_btn_get_code, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_get_code:
                countDownTimerUtils = new CountDownTimerUtils(tvBtnGetCode, 10000 + 50, 1000,
                        R.drawable.shape_gray_f4_c12, R.drawable.shape_red_554_c12, MyUtils.getColor(R.color.black_9), MyUtils.getColor(R.color.white), "s 可重发", "重新获取");
                countDownTimerUtils.start();
                requestGetVerCode();
                break;
            case R.id.btn_next:
                if(step == 1) {
                    tvBtnGetCode.setVisibility(View.VISIBLE);
                    llCodeContainer.setVisibility(View.VISIBLE);
                    vLine.setVisibility(View.VISIBLE);
                    tvDesc.setVisibility(View.GONE);
                    step = 2;
                    btnNext.setEnabled(false);
                    btnNext.setText("登录");
                }else {
                    requestLogin();
                }
                break;
        }
    }
}
