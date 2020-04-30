package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.CatBean;
import com.vpfinace.cloud_cat.bean.User;
import com.vpfinace.cloud_cat.global.EventStrings;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.utils.MyUtils;
import com.vpfinace.cloud_cat.weight.WrapContentLinearLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 仓库
 */
public class StoreListDialog extends TBaseDialog {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_btn_dilatation)
    TextView tvBtnDilatation;
    @BindView(R.id.tv_btn_get_out)
    TextView tvBtnGetOut;
    @BindView(R.id.tv_capacity)
    TextView tvCapacity;
    private List<CatBean> list;
    private MyAdapter myAdapter;
    public OnClickListener onClickListener;
    BaseActivity baseActivity;
    int dilatation = 0;//扩容价格

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public StoreListDialog(Context context) {
        super(context, R.layout.dialog_store);
        setWindowParam(0.9f, ConvertUtils.dp2px(542), Gravity.CENTER, 0);
    }

    public void init(BaseActivity activity,int dilatation) {
        EventBus.getDefault().register(this);
        baseActivity = activity;
        this.dilatation = dilatation;
        list = new ArrayList<>();
        requestStoreList(activity);
        myAdapter = new MyAdapter(list);
        rv.setLayoutManager(new WrapContentLinearLayoutManager(mContext));
        rv.setAdapter(myAdapter);
    }

    @OnClick({R.id.iv_close, R.id.tv_btn_dilatation, R.id.tv_btn_get_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_btn_dilatation://扩容
                dismiss();
                StoreDilationDialog storeDilationDialog = new StoreDilationDialog(mContext,dilatation);
                storeDilationDialog.setOnConfirmClickListener(new StoreDilationDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        //todo 总金额是否够判断
                        requestDilation(baseActivity);
                    }
                });
                storeDilationDialog.show();
                break;
            case R.id.tv_btn_get_out://取出
                if (list.size() == 0) {
                    ToastUtils.showShort("没有可取出猫咪!");
                    return;
                }
                if (selectId == -1) {
                    ToastUtils.showShort("请先选择要取出的猫咪!");
                    return;
                }
                if (onClickListener != null) {
                    for (CatBean catBean : list) {
                        if (catBean.getId() == selectId) {
                            onClickListener.onGetOutClick(catBean);
                        }
                    }
                }
                break;
        }
    }

    public void refresh(BaseActivity activity){
        list.clear();
        requestStoreList(activity);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (event.equals(EventStrings.STORE_GET_OUT_SUCCESS)) {//取出成功
            refresh(baseActivity);
        }
    }

    public void requestStoreList(BaseActivity baseview) {
        HttpManager.toRequst(HttpManager.getApi().getStoreList(), new BaseObserver<List<CatBean>>(baseview) {
            @Override
            public void _onNext(List<CatBean> catBeanList) {
                if (catBeanList != null && catBeanList.size() != 0) {
                    list.addAll(catBeanList);
                }
                requestGetUserInfo(baseview);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    //扩容
    public void requestDilation(BaseActivity baseActivity) {
        HttpManager.toRequst(HttpManager.getApi().storeDilation(), new BaseObserver<Object>(baseActivity) {
            @Override
            public void _onNext(Object o) {
                ToastUtils.showShort("扩容成功!");
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    public void requestGetUserInfo(BaseActivity baseActivity) {
        HttpManager.toRequst(HttpManager.getApi().getUserInfo(), new BaseObserver<User>(baseActivity) {
            @Override
            public void _onNext(User user) {
                tvCapacity.setText(list.size()+"/"+user.getStorageNum());
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    private int selectId = -1;//当前选中id

    class MyAdapter extends BaseQuickAdapter<CatBean, BaseViewHolder> {
        public MyAdapter(@Nullable List<CatBean> data) {
            super(R.layout.item_dialog_store, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CatBean item) {
            ImageView iv_img = helper.getView(R.id.iv_img);
            TextView tv_level = helper.getView(R.id.tv_level);
            TextView tv_title = helper.getView(R.id.tv_title);
            TextView tv_counts = helper.getView(R.id.tv_counts);


            if (item.getImg() != null) {
                Glide.with(mContext).load(item.getImg()).into(iv_img);
            }
            tv_level.setText(item.getCatLevel() + "");
            tv_title.setText(item.getTitle());

            LinearLayout ll_item_container = helper.getView(R.id.ll_item_container);
            RelativeLayout rl_cat_container = helper.getView(R.id.rl_cat_container);
            if (selectId != -1 && selectId == item.getId()) {
                rl_cat_container.setBackgroundResource(R.drawable.shape_gray_ff2_s1_554_c10);
                ll_item_container.setBackgroundColor(MyUtils.getColor(R.color.red_ceb));
            } else {
                rl_cat_container.setBackgroundResource(R.drawable.shape_gray_ff2_c10);
                ll_item_container.setBackgroundColor(MyUtils.getColor(R.color.white));
            }

            ll_item_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectId = item.getId();
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);
        EventBus.getDefault().unregister(this);
    }

    public interface OnClickListener {

        void onGetOutClick(CatBean catBean);
    }
}
