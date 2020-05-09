package com.vpfinace.cloud_cat.ui.mine.activity;

import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.MsgBean;
import com.vpfinace.cloud_cat.http.HttpManager;
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
    private List<MsgBean> list;
    private MyAdapter myAdapter;

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
        list = new ArrayList<>();
        myAdapter = new MyAdapter(list);
        rv.setLayoutManager(new WrapContentLinearLayoutManager(this));
        rv.setAdapter(myAdapter);
        requestMsgList();
    }

    public void requestMsgList() {
        HttpManager.toRequst(HttpManager.getApi().getMsgList(), new BaseObserver<List<MsgBean>>(this) {
            @Override
            public void _onNext(List<MsgBean> msgList) {
                list.addAll(msgList);
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

    class MyAdapter extends BaseQuickAdapter<MsgBean, BaseViewHolder> {


        public MyAdapter(@Nullable List<MsgBean> data) {
            super(R.layout.item_msg, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MsgBean item) {
            helper.setText(R.id.tv_time,item.getCreateTime());
            helper.setText(R.id.tv_title,item.getTitle());
            helper.setText(R.id.tv_content,item.getContent());
        }
    }
}
