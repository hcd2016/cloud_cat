package com.vpfinace.cloud_cat.ui.cattery.fragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;
import com.vpfinace.cloud_cat.weight.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class InviteRecordsFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView rv;
    private List<String> list;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invite_records;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(""+i);
        }
        rv.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        MyAdapter myAdapter = new MyAdapter(list);
        rv.setAdapter(myAdapter);
    }

    class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyAdapter( @Nullable List<String> data) {
            super(R.layout.item_invite_records, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            if(helper.getLayoutPosition() == list.size()-1) {
                helper.getView(R.id.v_space).setVisibility(View.VISIBLE);
            }else {
                helper.getView(R.id.v_space).setVisibility(View.GONE);
            }
        }
    }
}
