package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.bean.CatBean;
import com.vpfinace.cloud_cat.ui.home.activity.CatDetailActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CatDetailDialog extends TBaseDialog {
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_cat_name)
    TextView tvCatName;
    @BindView(R.id.tv_btn_detail)
    TextView tvBtnDetail;
    @BindView(R.id.tv_out_put)
    TextView tvOutPut;
    @BindView(R.id.tv_offline_output)
    TextView tvOfflineOutput;

    public CatDetailDialog(Context context, CatBean catBean) {
        super(context, R.layout.dialog_cat_detail);
        setViewData(catBean);
    }

    private void setViewData(CatBean catBean) {
        Glide.with(mContext).load(catBean.getImg()).into(ivImg);
        tvLevel.setText("Lv."+catBean.getCatLevel());
        tvCatName.setText(catBean.getCatName());
        tvOutPut.setText(catBean.getOutput()+"/秒");
        tvOfflineOutput.setText(catBean.getOfflineoutput()+"/秒");
    }

    @OnClick({R.id.fl_close, R.id.tv_btn_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                dismiss();
                break;
            case R.id.tv_btn_detail:
                mContext.startActivity(new Intent(mContext, CatDetailActivity.class));
                break;
        }
    }
}
