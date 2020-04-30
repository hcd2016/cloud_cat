package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.bean.CatBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 存入仓库dialog
 */
public class ToStoreDialog extends TBaseDialog {
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;
    private OnComfirmClickListener onComfirmClickListener;

    public void setOnComfirmClickListener(OnComfirmClickListener onComfirmClickListener) {
        this.onComfirmClickListener = onComfirmClickListener;
    }

    public ToStoreDialog(Context context,CatBean catBean) {
        super(context, R.layout.dialog_to_store);
        setWindowParam(0.8f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        init(catBean);
    }

    public void init(CatBean catBean) {
        Glide.with(mContext).load(catBean.getImg()).into(ivImg);
        tvLevel.setText(catBean.getCatLevel()+"");
    }

    @OnClick({R.id.iv_close, R.id.tv_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_btn_confirm:
                if(onComfirmClickListener != null) {
                    onComfirmClickListener.onComfirmClick();
                }
                dismiss();
                break;
        }
    }

    public interface OnComfirmClickListener {
        void onComfirmClick();
    }
}
