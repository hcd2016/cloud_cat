package com.vpfinace.cloud_cat.ui.mine.activity;

import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.WalletRecordBean;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.weight.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletRecordsActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView rv;
    private MyAdapter myAdapter;
    private List<WalletRecordBean.ListBean> list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wallet_records;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        list = new ArrayList<>();
        requestWalletRecord();
        rv.setLayoutManager(new WrapContentLinearLayoutManager(this));
        myAdapter = new MyAdapter(list);
        rv.setAdapter(myAdapter);
    }

    public void requestWalletRecord(){
        HttpManager.toRequst(HttpManager.getApi().getWalletRecordList(), new BaseObserver<WalletRecordBean>(this) {
            @Override
            public void _onNext(WalletRecordBean walletRecordBean) {
                List<WalletRecordBean.ListBean> walletRecordBeanList = walletRecordBean.getList();
                if(walletRecordBeanList != null && walletRecordBeanList.size() != 0) {
                    list.addAll(walletRecordBeanList);
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    class MyAdapter extends BaseQuickAdapter<WalletRecordBean.ListBean, BaseViewHolder> {

        public MyAdapter(@Nullable List<WalletRecordBean.ListBean> data) {
            super(R.layout.item_wallet_records, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WalletRecordBean.ListBean item) {
            helper.setText(R.id.tv_title,item.getTitle());
            helper.setText(R.id.tv_amount,"+"+item.getMoney());
            if(item.getCreateTime() != null) {
                helper.setText(R.id.tv_date,item.getCreateTime());
            }
        }
    }
}
