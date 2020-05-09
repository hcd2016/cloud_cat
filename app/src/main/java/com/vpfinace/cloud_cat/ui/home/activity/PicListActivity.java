package com.vpfinace.cloud_cat.ui.home.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.CatPicBean;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.weight.MyTitle;
import com.vpfinace.cloud_cat.weight.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PicListActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.title)
    MyTitle title;
    private List<CatPicBean> list;
    private MyAdapter myAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pic_list;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        title.setTitleBackgroudColor(MyUtils.getColor(R.color.gray_f5));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(list);
        rv.setLayoutManager(new WrapContentLinearLayoutManager(this));
        rv.setAdapter(myAdapter);
        requestGetPicList();
    }

    class MyAdapter extends BaseQuickAdapter<CatPicBean, BaseViewHolder> {

        public MyAdapter(@Nullable List<CatPicBean> data) {
            super(R.layout.item_pic_list, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CatPicBean item) {
            TextView tv_hot_desc = helper.getView(R.id.tv_hot_desc);
            if(!TextUtils.isEmpty(item.getTag())) {
                tv_hot_desc.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_hot_desc,item.getTag());
            }else {
                tv_hot_desc.setVisibility(View.GONE);
            }
            ImageView ivImg = helper.getView(R.id.iv_img);
            Glide.with(PicListActivity.this).load(item.getCatImg()).into(ivImg);
            if(!TextUtils.isEmpty(item.getExplain())) {
                helper.setText(R.id.tv_desc,item.getExplain());
            }
            helper.setText(R.id.tv_get_way,item.getGetWay());
            helper.setText(R.id.tv_name,item.getName());
            if (helper.getLayoutPosition() == list.size() - 1) {
                helper.getView(R.id.v_space).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.v_space).setVisibility(View.GONE);
            }
        }
    }

    public void requestGetPicList() {
        HttpManager.toRequst(HttpManager.getApi().getCatPicList(), new BaseObserver<List<CatPicBean>>(this) {
            @Override
            public void _onNext(List<CatPicBean> catPicBeanList) {
                list.addAll(catPicBeanList);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
