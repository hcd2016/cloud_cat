package com.vpfinace.cloud_cat.ui.mine.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.dialog.MyInviteFriendDialog;
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.weight.MyTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的邀请码
 */
public class MyInviteCodeActivity extends BaseActivity {
    @BindView(R.id.tv_btn_copy)
    TextView tvBtnCopy;
    @BindView(R.id.tv_btn_invite)
    TextView tvBtnInvite;
    @BindView(R.id.tv_invite_code)
    TextView tvInviteCode;
    @BindView(R.id.title)
    MyTitle title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_invite_code;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        title.setTitleBackgroudColor(MyUtils.getColor(R.color.gray_f5)).setIsRightTextVisible(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_btn_copy, R.id.tv_btn_invite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_copy:
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", tvInviteCode.getText().toString());
                clipboard.setPrimaryClip(clip);
                ToastUtils.showShort("复制成功");
                break;
            case R.id.tv_btn_invite:
                MyInviteFriendDialog myInviteFriendDialog = new MyInviteFriendDialog(this);
                myInviteFriendDialog.show();
                break;
        }
    }
}
