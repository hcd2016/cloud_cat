package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.weight.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
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
        String selectId = "";//当前选中id
        public MyAdapter(@Nullable List<String> data) {
            super(R.layout.item_dialog_store, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            LinearLayout ll_item_container = helper.getView(R.id.ll_item_container);
            RelativeLayout rl_cat_container = helper.getView(R.id.rl_cat_container);
            if(!TextUtils.isEmpty(selectId) && selectId == item) {
                rl_cat_container.setBackgroundResource(R.drawable.shape_gray_ff2_s1_554_c10);
                ll_item_container.setBackgroundColor(MyUtils.getColor(R.color.red_ceb));
            }else {
                rl_cat_container.setBackgroundResource(R.drawable.shape_gray_ff2_c10);
                ll_item_container.setBackgroundColor(MyUtils.getColor(R.color.white));
            }

            ll_item_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectId = item;
                    notifyDataSetChanged();
                }
            });
        }
    }
}
