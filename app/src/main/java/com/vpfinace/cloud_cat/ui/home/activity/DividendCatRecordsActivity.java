package com.vpfinace.cloud_cat.ui.home.activity;

import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.weight.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分红记录
 */
public class DividendCatRecordsActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dividend_cat_records;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "");
        }
        rv.setLayoutManager(new WrapContentLinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(list);
        rv.setAdapter(myAdapter);
    }

    class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyAdapter(@Nullable List<String> data) {
            super(R.layout.item_dividend_cat_records, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
