package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 你的邀请人
 */
public class InviterDialog extends TBaseDialog {
    @BindView(R.id.fl_close)
    TextView flClose;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_commit)
    TextView btnCommit;

    public InviterDialog(Context context) {
        super(context, R.layout.dialog_inviter);
        setWindowParam(0.8f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        init();
    }

    public void init() {
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s.toString())) {
                    btnCommit.setEnabled(false);
                }else {
                    btnCommit.setEnabled(true);
                }
            }
        });
    }

    @OnClick({R.id.fl_close, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                dismiss();
                break;
            case R.id.btn_commit:
                dismiss();
                ToastUtils.showShort("提交成功");
                break;
        }
    }
}
