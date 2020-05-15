package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.utils.UnitUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class StoreDilationDialog extends TBaseDialog {
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_dilatation)
    TextView tvDilatation;
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;
    public OnConfirmClickListener onConfirmClickListener;

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    public StoreDilationDialog(Context context, int dilatation) {
        super(context, R.layout.dialog_store_dilation);
        setWindowParam(0.8f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        init(dilatation);
    }

    private void init(int dilatation) {
        tvDilatation.setText(UnitUtils.coin2String((long)dilatation));
    }

    @OnClick({R.id.iv_close, R.id.tv_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_btn_confirm:
                if(onConfirmClickListener != null) {
                    onConfirmClickListener.onConfirmClick();
                }
                dismiss();
                break;
        }
    }


    public interface OnConfirmClickListener {
        void onConfirmClick();
    }
}
