package com.vpfinace.cloud_cat.ui.cattery.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.http.HttpManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 社交信息
 */
public class SocialInfoActivity extends BaseActivity {
    @BindView(R.id.et_weixin)
    EditText etWeixin;
    @BindView(R.id.et_qq)
    EditText etQq;
    @BindView(R.id.btn_save)
    TextView btnSave;

    @Override
    public int getLayoutId() {
        return R.layout.activity_social_info;
    }

    @Override
    public void initPresenter() {

    }

    boolean isWeixinEmpty = true;
    boolean isQQEmpty = true;
    @Override
    protected void initView() {
        super.initView();
        etQq.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isQQEmpty = TextUtils.isEmpty(s);
                if(!TextUtils.isEmpty(s)) {
                    if(!isWeixinEmpty) {
                        btnSave.setEnabled(true);
                    }
                }else {
                    btnSave.setEnabled(false);
                }
            }
        });
        etWeixin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isWeixinEmpty = TextUtils.isEmpty(s);
                if(!TextUtils.isEmpty(s)) {
                    if(!isQQEmpty) {
                        btnSave.setEnabled(true);
                    }
                }else {
                    btnSave.setEnabled(false);
                }
            }
        });
    }

    public void requestCommit(){
        HttpManager.toRequst(HttpManager.getApi().socialInfoCommit(etQq.getText().toString(), etWeixin.getText().toString()), new BaseObserver(this) {
            @Override
            public void _onNext(Object o) {
                ToastUtils.showShort("保存成功!");
                finish();
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

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        requestCommit();
    }
}
