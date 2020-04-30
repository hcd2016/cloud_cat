package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.CatShopBean;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.weight.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class CatShopDialog extends TBaseDialog {
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.rv)
    RecyclerView rv;
    private List<CatShopBean> list;
    private MyAdapter myAdapter;
    OnBuyClickListener onBuyClickListener;

    public void setOnBuyClickListener(OnBuyClickListener onBuyClickListener) {
        this.onBuyClickListener = onBuyClickListener;
    }

    public CatShopDialog(Context context, BaseActivity activity) {
        super(context, R.layout.dialog_cat_shop);
        setWindowParam(0.9f, ConvertUtils.dp2px(542), Gravity.CENTER, 0);
        init(activity);
    }

    public void init(BaseActivity activity) {
        list = new ArrayList<>();
        rv.setLayoutManager(new WrapContentLinearLayoutManager(mContext));
        myAdapter = new MyAdapter(list);
        rv.setAdapter(myAdapter);
        requestShopList(activity);
    }

    public void requestShopList(BaseActivity activity) {
        HttpManager.toRequst(HttpManager.getApi().getShopList(), new BaseObserver<List<CatShopBean>>(activity) {
            @Override
            public void _onNext(List<CatShopBean> shopBeanList) {
                if (shopBeanList != null && shopBeanList.size() != 0) {
                    list.addAll(shopBeanList);
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    @OnClick(R.id.iv_close)
    public void onViewClicked() {
        dismiss();
    }

    class MyAdapter extends BaseQuickAdapter<CatShopBean, BaseViewHolder> {



        public MyAdapter(@Nullable List<CatShopBean> data) {
            super(R.layout.item_cat_shop, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CatShopBean item) {


            ImageView ivImg = helper.getView(R.id.iv_img);
            TextView tvLevel = helper.getView(R.id.tv_level);
            TextView tvTitle = helper.getView(R.id.tv_title);
            TextView tvOutPut = helper.getView(R.id.tv_out_put);
            TextView tvPrice = helper.getView(R.id.tv_price);
            TextView tvUnlockLevel = helper.getView(R.id.tv_unlock_level);//todo

            Glide.with(mContext).load(item.getImg()).into(ivImg);
            tvLevel.setText(item.getLevel()+"");
            tvTitle.setText(item.getTitle());
            tvOutPut.setText(item.getOutput()+"/s");
            if(item.getPrice1() != null) {
                tvPrice.setText(item.getPrice1());
            }

            if (item.getBuyStatus() == 1) {
                helper.getView(R.id.ll_btn_unlock).setVisibility(View.VISIBLE);
                helper.getView(R.id.ll_btn_locked).setVisibility(View.GONE);
            } else {
                helper.getView(R.id.ll_btn_unlock).setVisibility(View.GONE);
                helper.getView(R.id.ll_btn_locked).setVisibility(View.VISIBLE);
            }

            helper.getView(R.id.ll_item_container).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onBuyClickListener != null) {
                        onBuyClickListener.OnBuyClick(item);
                    }
                }
            });
        }
    }

    public interface OnBuyClickListener{
        void OnBuyClick(CatShopBean catShopBean);
    }
}
