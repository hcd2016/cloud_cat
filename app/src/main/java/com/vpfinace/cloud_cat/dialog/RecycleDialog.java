package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.bean.CatBean;
import com.vpfinace.cloud_cat.utils.UnitUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RecycleDialog extends TBaseDialog {
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_amount)
    TextView tvAmount;

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    private OnConfirmClickListener onConfirmClickListener;

    public RecycleDialog(Context context, CatBean catBean) {
        super(context, R.layout.dialog_recycle);
        setWindowParam(ConvertUtils.dp2px(270), WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        Glide.with(mContext).load(catBean.getImg()).into(ivImg);
        if(catBean.getGcprice() != 0) {
            tvAmount.setText(UnitUtils.coin2String(catBean.getGcprice()));
        }
    }

    @OnClick({R.id.iv_close, R.id.tv_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_btn_confirm:
                dismiss();
                if (onConfirmClickListener != null) {
                    onConfirmClickListener.onConfirmClick();
                }
                break;
        }
    }

    public interface OnConfirmClickListener {
        void onConfirmClick();
    }

}
