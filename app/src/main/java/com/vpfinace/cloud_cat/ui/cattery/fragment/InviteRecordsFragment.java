package com.vpfinace.cloud_cat.ui.cattery.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.MyInviteCodeBean;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.utils.GlideUtils;
import com.vpfinace.cloud_cat.weight.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class InviteRecordsFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView rv;
    private int type = 0;//0为直邀好友,1为扩散好友.
    private List<MyInviteCodeBean.UserInviteListBean> inviteList;
    private List<MyInviteCodeBean.DiffusionListBean> diffusionList;
    private InviteAdapter inviteAdapter;
    private DiffusionAdapter diffusionAdapter;

    public static InviteRecordsFragment getInstance(int type) {
        InviteRecordsFragment inviteRecordsFragment = new InviteRecordsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        inviteRecordsFragment.setArguments(bundle);
        return inviteRecordsFragment;
    }

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
        inviteList = new ArrayList<>();
        diffusionList = new ArrayList<>();
        type = getArguments().getInt("type", 0);
        rv.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        if (type == 0) {
            inviteAdapter = new InviteAdapter(inviteList);
            rv.setAdapter(inviteAdapter);
        } else {
            diffusionAdapter = new DiffusionAdapter(diffusionList);
            rv.setAdapter(diffusionAdapter);
        }
        requestList();
    }

    public void requestList() {
        HttpManager.toRequst(HttpManager.getApi().getInviteCode(), new BaseObserver<MyInviteCodeBean>(this) {
            @Override
            public void _onNext(MyInviteCodeBean myInviteCodeBean) {
                if (type == 0) {
                    inviteList.addAll(myInviteCodeBean.getUserInviteList());
                    inviteAdapter.notifyDataSetChanged();
                } else {
                    diffusionList.addAll(myInviteCodeBean.getDiffusionList());
                    diffusionAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    class InviteAdapter extends BaseQuickAdapter<MyInviteCodeBean.UserInviteListBean, BaseViewHolder> {

        public InviteAdapter(@Nullable List<MyInviteCodeBean.UserInviteListBean> data) {
            super(R.layout.item_invite_records, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyInviteCodeBean.UserInviteListBean item) {
            if (helper.getLayoutPosition() == inviteList.size() - 1) {
                helper.getView(R.id.v_space).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.v_space).setVisibility(View.GONE);
            }
            ImageView ivHeader= helper.getView(R.id.iv_header);
            ImageView ivWechatIcon= helper.getView(R.id.iv_wechat_icon);
            ImageView ivQqIcon= helper.getView(R.id.iv_qq_icon);
            TextView tvAuthDesc= helper.getView(R.id.tv_auth_desc);
            TextView tvName= helper.getView(R.id.tv_name);
            TextView tvDate= helper.getView(R.id.tv_date);
            TextView tvLevel= helper.getView(R.id.tv_level);

            if(item.getHeadImgUrl() != null) {
                GlideUtils.loadCircle(getContext(),item.getHeadImgUrl(),ivHeader);
            }
            if(!TextUtils.isEmpty(item.getWechat())) {
                ivWechatIcon.setVisibility(View.VISIBLE);
            }else {
                ivWechatIcon.setVisibility(View.GONE);
            }
            if(!TextUtils.isEmpty(item.getQq())) {
                ivQqIcon.setVisibility(View.VISIBLE);
            }else {
                ivQqIcon.setVisibility(View.GONE);
            }
            tvName.setText(item.getName());
            tvDate.setText(item.getCreateTime());
            tvLevel.setText("Lv."+item.getUserLevel());
            if(TextUtils.isEmpty(item.getRealname())) {
                tvAuthDesc.setText("(未实名)");
            }else {
                tvAuthDesc.setText("(已实名)");
            }
        }
    }

    class DiffusionAdapter extends BaseQuickAdapter<MyInviteCodeBean.DiffusionListBean, BaseViewHolder> {

        public DiffusionAdapter(@Nullable List<MyInviteCodeBean.DiffusionListBean> data) {
            super(R.layout.item_invite_records, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyInviteCodeBean.DiffusionListBean item) {
            if (helper.getLayoutPosition() == diffusionList.size() - 1) {
                helper.getView(R.id.v_space).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.v_space).setVisibility(View.GONE);
            }
            ImageView ivHeader= helper.getView(R.id.iv_header);
            ImageView ivWechatIcon= helper.getView(R.id.iv_wechat_icon);
            ImageView ivQqIcon= helper.getView(R.id.iv_qq_icon);
            TextView tvAuthDesc= helper.getView(R.id.tv_auth_desc);
            TextView tvName= helper.getView(R.id.tv_name);
            TextView tvDate= helper.getView(R.id.tv_date);
            TextView tvLevel= helper.getView(R.id.tv_level);

            if(item.getHeadImgUrl() != null) {
                GlideUtils.loadCircle(getContext(),item.getHeadImgUrl(),ivHeader);
            }
            if(!TextUtils.isEmpty(item.getWechat())) {
                ivWechatIcon.setVisibility(View.VISIBLE);
            }else {
                ivWechatIcon.setVisibility(View.GONE);
            }
            if(!TextUtils.isEmpty(item.getQq())) {
                ivQqIcon.setVisibility(View.VISIBLE);
            }else {
                ivQqIcon.setVisibility(View.GONE);
            }
            tvName.setText(item.getName());
            tvDate.setText(item.getCreateTime());
            tvLevel.setText("Lv."+item.getUserLevel());
            if(TextUtils.isEmpty(item.getRealname())) {
                tvAuthDesc.setText("(未实名)");
            }else {
                tvAuthDesc.setText("(已实名)");
            }
        }
    }
}
