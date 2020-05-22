package com.vpfinace.cloud_cat.dialog;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.utils.AdManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 领取转盘券弹窗
 */
public class GetQuanDialog extends TBaseDialog {
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.tv_btn_commit)
    TextView tvBtnCommit;
    private BaseActivity baseActivity;

    public GetQuanDialog(BaseActivity context) {
        super(context, R.layout.dialog_get_quan);
        setWindowParam(0.8f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        this.baseActivity = context;
    }

    @OnClick({R.id.fl_close, R.id.tv_btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                dismiss();
                break;
            case R.id.tv_btn_commit:
                AdManager.playRewardVideo(baseActivity,6,0);
                dismiss();
                break;
        }
    }
}
