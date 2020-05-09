package com.vpfinace.cloud_cat.ui.mine.activity;

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
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.weight.MyTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                if (TextUtils.isEmpty(s.toString())) {
                    btnSave.setEnabled(false);
                } else {
                    btnSave.setEnabled(true);
                }
            }
        });
    }

    public void requestAuth(){
        HttpManager.toRequst(HttpManager.getApi().auth(etName.getText().toString()), new BaseObserver(this) {
            @Override
            public void _onNext(Object o) {
                startActivity(AuthResultActivity.class);
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
        requestAuth();
    }
}
