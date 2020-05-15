package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.bean.MergeBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 合成新最高等级猫
 */
public class MergeMaxDialog extends TBaseDialog {
    @BindView(R.id.iv_cat_img)
    ImageView ivCatImg;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;
    @BindView(R.id.tv_desc_level)
    TextView tvDescLevel;
    @BindView(R.id.tv_dividends_account)
    TextView tvDividendsAccount;
    @BindView(R.id.ll_dividends_cat_container)
    LinearLayout llDividendsCatContainer;
    @BindView(R.id.iv_close)
    ImageView ivClose;

    public MergeMaxDialog(Context context, MergeBean mergeBean, double aderanings) {
        super(context, R.layout.dialog_merge_max);
        setWindowParam(0.8f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
        Glide.with(mContext).load(mergeBean.getCat().getImg()).into(ivCatImg);
        tvLevel.setText(mergeBean.getCat().getLevel()+"");
        tvName.setText(mergeBean.getCat().getTitle());
        tvDescLevel.setText(mergeBean.getRemainLevel()+"");
        tvDividendsAccount.setText(aderanings+"");
    }


    @OnClick({R.id.tv_btn_confirm, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_confirm:
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }
}
