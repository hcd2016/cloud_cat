package com.vpfinace.cloud_cat.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.global.EventStrings;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.weight.MyTitle;

import org.greenrobot.eventbus.EventBus;

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
                    requestEditNickName();
                }
            }
        });
        String nickName = getIntent().getStringExtra("nickName");
        etNickName.setText(nickName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void requestEditNickName(){
        HttpManager.toRequst(HttpManager.getApi().editNickName(etNickName.getText().toString()), new BaseObserver(this) {
            @Override
            public void _onNext(Object o) {
                ToastUtils.showShort("昵称修改成功!");
                EventBus.getDefault().post(EventStrings.SETTING_REFRESH);
                EventBus.getDefault().post(EventStrings.MINE_REFRESH);
                finish();
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    @OnClick(R.id.iv_clean)
    public void onViewClicked() {
        etNickName.setText("");
    }

    public static void goThis(Context context, String nickName) {
        Intent intent = new Intent(context, UpdateNickNameActivity.class);
        intent.putExtra("nickName", nickName);
        context.startActivity(intent);
    }
}
