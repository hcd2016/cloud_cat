package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.weight.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * 仓库
 */
public class StoreListDialog extends TBaseDialog {
    @BindView(R.id.rv)
    RecyclerView rv;

    public StoreListDialog(Context context) {
        super(context, R.layout.dialog_store);
        setWindowParam(0.9f, ConvertUtils.dp2px(542), Gravity.CENTER, 0);
    }

    public void init() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(""+i);
        }
        MyAdapter myAdapter = new MyAdapter(list);
        rv.setLayoutManager(new WrapContentLinearLayoutManager(mContext));
        rv.setAdapter(myAdapter);
    }

    class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyAdapter(@Nullable List<String> data) {
            super(R.layout.item_dialog_store, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
