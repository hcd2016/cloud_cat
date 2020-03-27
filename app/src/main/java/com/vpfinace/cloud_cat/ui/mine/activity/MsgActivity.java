package com.vpfinace.cloud_cat.ui.mine.activity;

import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.weight.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的消息
 */
public class MsgActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_msg;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }
        MyAdapter myAdapter = new MyAdapter(list);
        rv.setLayoutManager(new WrapContentLinearLayoutManager(this));
        rv.setAdapter(myAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyAdapter(@Nullable List<String> data) {
            super(R.layout.item_msg, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
