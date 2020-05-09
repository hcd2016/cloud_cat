package com.vpfinace.cloud_cat.ui.home.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.TopBean;
import com.vpfinace.cloud_cat.bean.TopTypeBean;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.utils.GlideUtils;
import com.vpfinace.cloud_cat.weight.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class TopFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView rv;
    private int type = 0;//0为金币排行,1为分红喵排行,2为收益排行
    private List<TopBean.TopListBean> list;
    private MyAdapter myAdapter;

    public static TopFragment getInstance(int type) {
        TopFragment topFragment = new TopFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        topFragment.setArguments(bundle);
        return topFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_top;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        type = getArguments().getInt("type", 0);


        list = new ArrayList<>();
        myAdapter = new MyAdapter(list);
        rv.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        rv.setAdapter(myAdapter);
        requestTopList();
    }

    public void requestTopList() {
        HttpManager.toRequst(HttpManager.getApi().getTopList(), new BaseObserver<TopBean>(this) {
            @Override
            public void _onNext(TopBean topBean) {
                if (type == 0) {
                    list.addAll(topBean.getCoinRankList());
                } else if (type == 1) {
                    list.addAll(topBean.getLuckyCatEarningRank());
                } else {
                    list.addAll(topBean.getTotalEarningRank());
                }
                //复制类型
                for (TopBean.TopListBean topListBean : list) {
                    topListBean.setType(type);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    class MyAdapter extends BaseMultiItemQuickAdapter<TopBean.TopListBean, BaseViewHolder> {


        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data A new list is created out of this one to avoid mutable list
         */
        public MyAdapter(List<TopBean.TopListBean> data) {
            super(data);
            addItemType(TopTypeBean.top1, R.layout.item_top1);
            addItemType(TopTypeBean.top2, R.layout.item_top2);
            addItemType(TopTypeBean.top3, R.layout.item_top3);
        }

        @Override
        protected void convert(BaseViewHolder helper, TopBean.TopListBean item) {
            ImageView iv_header = helper.getView(R.id.iv_header);
            GlideUtils.loadCircle(getContext(), R.mipmap.ic_launcher, iv_header);
            if (item.getType() == TopTypeBean.top1 || item.getType() == TopTypeBean.top3) {
                ImageView iv_top = helper.getView(R.id.iv_top);
                TextView tv_top = helper.getView(R.id.tv_top);
                if (helper.getLayoutPosition() == 0) {
                    iv_top.setVisibility(View.VISIBLE);
                    tv_top.setVisibility(View.GONE);
                    iv_top.setImageResource(R.drawable.list_icon_01);
                } else if (helper.getLayoutPosition() == 1) {
                    iv_top.setVisibility(View.VISIBLE);
                    tv_top.setVisibility(View.GONE);
                    iv_top.setImageResource(R.drawable.list_icon_02);
                } else if (helper.getLayoutPosition() == 2) {
                    iv_top.setVisibility(View.VISIBLE);
                    tv_top.setVisibility(View.GONE);
                    iv_top.setImageResource(R.drawable.list_icon_03);
                } else {
                    iv_top.setVisibility(View.GONE);
                    tv_top.setVisibility(View.VISIBLE);
//                    tv_top.setText(helper.getLayoutPosition() + 1 + "");
                }
                tv_top.setText(item.getRank()+"");
            }

            if (type == 0) {//金币排行
                ImageView ivHeader =  helper.getView(R.id.iv_header);
                TextView tvName = helper.getView(R.id.tv_name);
                TextView tvCatDesc = helper.getView(R.id.tv_cat_desc);
                TextView tvAmount = helper.getView(R.id.tv_amount);
                GlideUtils.loadCircle(getActivity(),item.getHeadImg(),ivHeader);
                tvName.setText(item.getName());
                tvCatDesc.setText(item.getGetWay());
                tvAmount.setText(item.getCoinNum()+"");
            } else if (type == 1) {//分红瞄
                ImageView ivHeader =  helper.getView(R.id.iv_header);
                TextView tvName = helper.getView(R.id.tv_name);
                TextView tvAmount = helper.getView(R.id.tv_money);
                GlideUtils.loadCircle(getActivity(),item.getHeadImg(),ivHeader);
                tvName.setText(item.getName());
                tvAmount.setText(item.getMoney()+"");
            } else {//收益排行
                ImageView ivHeader =  helper.getView(R.id.iv_header);
                TextView tvName = helper.getView(R.id.tv_name);
                TextView tvAmount = helper.getView(R.id.tv_amount);
                GlideUtils.loadCircle(getActivity(),item.getHeadImg(),ivHeader);
                tvName.setText(item.getName());
                tvAmount.setText(item.getMoney()+"元");
            }
        }
    }
}
