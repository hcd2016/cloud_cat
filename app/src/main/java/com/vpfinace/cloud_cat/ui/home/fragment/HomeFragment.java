package com.vpfinace.cloud_cat.ui.home.fragment;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunoraz.gifview.library.GifView;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseFragment;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.CatBean;
import com.vpfinace.cloud_cat.bean.CatShopBean;
import com.vpfinace.cloud_cat.bean.HomeBean;
import com.vpfinace.cloud_cat.bean.ViewBean;
import com.vpfinace.cloud_cat.dialog.CatShopDialog;
import com.vpfinace.cloud_cat.dialog.GetMoneyDialog;
import com.vpfinace.cloud_cat.dialog.LuckyWheelDialog;
import com.vpfinace.cloud_cat.dialog.RecycleDialog;
import com.vpfinace.cloud_cat.dialog.StoreListDialog;
import com.vpfinace.cloud_cat.dialog.ToStoreDialog;
import com.vpfinace.cloud_cat.global.EventStrings;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.ui.home.activity.DividendCatActivity;
import com.vpfinace.cloud_cat.ui.home.activity.PicListActivity;
import com.vpfinace.cloud_cat.ui.home.activity.TopActivity;
import com.vpfinace.cloud_cat.utils.BeanUtils;
import com.vpfinace.cloud_cat.weight.CatManager;
import com.vpfinace.cloud_cat.weight.DragView;
import com.vpfinace.cloud_cat.weight.SpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.v_status_view)
    View vStatusView;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.rl_container)
    RelativeLayout rlContainer;
    @BindView(R.id.iv_del)
    ImageView ivDel;
    @BindView(R.id.ll_cat_container)
    LinearLayout llCatContainer;
    @BindView(R.id.ll_del_parent)
    LinearLayout llDelParent;
    @BindView(R.id.rl_get_money_container)
    RelativeLayout rlGetMoneyContainer;
    @BindView(R.id.iv_lucky_wheel)
    ImageView ivLuckyWheel;
    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.iv_how_to_play)
    ImageView ivHowToPlay;
    @BindView(R.id.ll_dividends_cat_container)
    LinearLayout llDividendsCatContainer;
    @BindView(R.id.ll_pic_guide_container)
    LinearLayout llPicGuideContainer;
    @BindView(R.id.ll_store_container)
    LinearLayout llStoreContainer;
    @BindView(R.id.ll_add_cat_container)
    LinearLayout llAddCatContainer;
    @BindView(R.id.tv_dividends_account)
    TextView tvDividendsAccount;
    @BindView(R.id.iv_shop)
    ImageView ivShop;
    @BindView(R.id.iv_cat_img)
    ImageView ivCatImg;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_out_put)
    TextView tvOutPut;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    private MyAdapter myAdapter;
    private List<ViewBean> currentViewList;//当前显示的view
    private List<CatBean> list;
    private long amount = 0;//当前总金额
    private int outPut = 0;//当前产出

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        ViewGroup.LayoutParams layoutParams = vStatusView.getLayoutParams();
        layoutParams.height = BarUtils.getStatusBarHeight();
        vStatusView.setLayoutParams(layoutParams);
        int column = 4;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        rv.setLayoutManager(gridLayoutManager);
//         减掉RecyclerView父布局两侧padding和item的宽度，然后平分，默认每个item右侧会填充剩余空间
//        int spaceWidth = (ScreenUtils.getScreenWidth() -
//                (int) (ConvertUtils.dp2px(16) * 2) -
//                (int) (ConvertUtils.dp2px(78) * column)) / (column * (column - 1));
//        rv.addItemDecoration(new SpaceItemDecoration(spaceWidth,column));
        rv.addItemDecoration(new SpaceItemDecoration(ConvertUtils.dp2px(10), column));
        rv.setNestedScrollingEnabled(false);
        list = new ArrayList<>();
        myAdapter = new MyAdapter(list);
        rv.setAdapter(myAdapter);
        llCatContainer.setEnabled(false);
        llAddCatContainer.setEnabled(false);
        requestHomeData();
//        initCat();
    }

    public HomeBean homeBean;

    /**
     * 获取首页数据
     */
    public void requestHomeData() {
        HttpManager.toRequst(HttpManager.getApi().getCatList(), new BaseObserver<HomeBean>(this) {
            @Override
            public void _onNext(HomeBean myHomeBean) {
                homeBean = myHomeBean;
                setHomeData(myHomeBean);
                List<CatBean> catBeanList = homeBean.getList();
                for (int i = 0; i < 12; i++) {
                    CatBean catBean = new CatBean();
                    catBean.setStorageId(i + 1);
                    catBean.setCatId(0);
                    HomeFragment.this.list.add(catBean);
                }
                myAdapter.notifyDataSetChanged();
                if (catBeanList != null && catBeanList.size() != 0) {
                    for (int i = 0; i < catBeanList.size(); i++) {
                        for (int j = 0; j < HomeFragment.this.list.size(); j++) {
                            if (catBeanList.get(i).getStorageId() == j + 1) {
                                CatBean catBean = HomeFragment.this.list.get(j);
                                BeanUtils.copy(catBean, catBeanList.get(i));
                            }
                        }
                    }
                }
                rlContainer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initCat();
                    }
                }, 1000);

            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
        if(amountRunnerble == null) {
            startTimer();
        }
    }

    private void setHomeData(HomeBean myHomeBean) {
        amount = myHomeBean.getFund().coin;
        tvDividendsAccount.setText(myHomeBean.getAderanings() + "");
        Glide.with(getActivity()).load(myHomeBean.getCat().getImg()).into(ivCatImg);
        tvLevel.setText("Lv." + myHomeBean.getCat().getLevel());
        tvPrice.setText(myHomeBean.getCat().getPrice1());
        List<CatBean> list = myHomeBean.getList();
        tvAmount.setText(amount + "");
        int outPut = 0;//速率计算
        if (list != null && list.size() != 0) {
            for (CatBean catBean : list) {
                outPut += catBean.getOutput();
            }
            this.outPut = outPut;
            tvOutPut.setText(outPut + "/s");
        } else {
            tvOutPut.setText("0/s");
        }
    }

    //刷新数据
    public void refresh() {
        list.clear();
        requestHomeData();
    }

    //合成或拖拽
    public void requestMergeCat(int from, int to) {
        HttpManager.toRequst(HttpManager.getApi().mergeCat(from, to), new BaseObserver<Object>(this) {
            @Override
            public void _onNext(Object o) {

            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    //存入仓库
    public void requestInPutStore(CatBean catBean) {
        HttpManager.toRequst(HttpManager.getApi().putInStore(catBean.getCatId(), catBean.getStorageId()), new BaseObserver<Object>(this) {
            @Override
            public void _onNext(Object o) {
                catBean.setCatId(0);
                notifyViewDataChange();
                ToastUtils.showShort("放入成功");
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    //回收猫咪
    public void requestGcCat(CatBean catBean) {
        HttpManager.toRequst(HttpManager.getApi().gcCat(catBean.getCatId(), catBean.getStorageId()), new BaseObserver<Object>(this) {
            @Override
            public void _onNext(Object o) {
                ToastUtils.showShort("回收成功!");
                refresh();
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    //取出猫咪
    private void requestGetOutCat(int catId) {
        HttpManager.toRequst(HttpManager.getApi().getCatFromStore(catId), new BaseObserver(this) {
            @Override
            public void _onNext(Object o) {
                refresh();
                ToastUtils.showShort("取出成功!");
                EventBus.getDefault().post(EventStrings.STORE_GET_OUT_SUCCESS);
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    //购买猫咪
    public void requestBuyCat(int catId) {
        HttpManager.toRequst(HttpManager.getApi().buyCat(catId), new BaseObserver(this) {
            @Override
            public void _onNext(Object o) {
                refresh();
                ToastUtils.showShort("购买成功!");
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    private void initCat() {
        rlContainer.removeAllViews();
        for (int i = 0; i < list.size(); i++) {//初始化
            DragView dragView = (DragView) View.inflate(getActivity(), R.layout.drag_cat_layout, null);
            CatManager catManager = new CatManager(dragView, rlContainer, myAdapter, rv);
            catManager.addCatView(i, dragView);
            CatBean catBean = list.get(i);
            catBean.setView(dragView);

            if (catBean.getCatId() == 0) {//空格子
                dragView.setVisibility(View.GONE);
            } else {
                dragView.setVisibility(View.VISIBLE);
                TextView tv_counts = dragView.findViewById(R.id.tv_counts);
                ImageView iv_img = dragView.findViewById(R.id.iv_img);
                Glide.with(getActivity()).load(list.get(i).getImg()).into(iv_img);
                tv_counts.setText(catBean.getCatLevel() + "");
            }
            RelativeLayout rl_item_container = (RelativeLayout) myAdapter.getViewByPosition(rv, i, R.id.rl_item_container);
            int finalI = i;
            dragView.setOnActionUpListener(new DragView.OnActionUpListener() {
                @Override
                public void onActionUp(int lastX, int lastY, int startLeft, int startTop, int startRight, int startBottom, int rawX, int rawY) {
                    int postion = rangle(rawX, rawY, rl_item_container);
                    if (finalI == postion) {
                        return;
                    }
                    if (postion != -1) {//在范围内
                        CatBean toCatBean = list.get(postion);
                        CatBean fromCatBean = list.get(finalI);
                        if (toCatBean.getCatId() == 0) {//当前移动到的格子是空的
                            int fromId = fromCatBean.getStorageId();
                            int toId = toCatBean.getStorageId();
                            BeanUtils.copyBeanWithoutView(toCatBean, fromCatBean);
                            notifyViewDataChange();
                            requestMergeCat(fromId, toId);
                        } else {//不是空的,看等级是否相同
                            if (fromCatBean.getCatLevel() == toCatBean.getCatLevel()) {//等级相同,合成
                                toCatBean.setCatLevel(toCatBean.getCatLevel() + 1);
                                fromCatBean.setCatId(0);
                                fromCatBean.getView().setVisibility(View.GONE);

                                RelativeLayout rl_item_container = (RelativeLayout) myAdapter.getViewByPosition(rv, postion, R.id.rl_item_container);
                                GifView gif_view = rl_item_container.findViewById(R.id.gif_view);
                                gif_view.setVisibility(View.VISIBLE);
                                gif_view.setGifResource(R.drawable.compund1);
                                gif_view.play();

                                CatBean finalViewBean = toCatBean;
                                gif_view.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        gif_view.pause();
                                        gif_view.setVisibility(View.GONE);
//                                        notifyViewDataChange();
                                        finalViewBean.getView().setVisibility(View.VISIBLE);
                                    }
                                }, 300);
                                toCatBean.getView().setVisibility(View.GONE);
                                requestMergeCat(fromCatBean.getStorageId(), toCatBean.getStorageId());
                            } else {//等级不同,互换位置
                                int fromId = fromCatBean.getStorageId();
                                int toId = toCatBean.getStorageId();
                                BeanUtils.copyBeanWithoutView(toCatBean, fromCatBean);
                                notifyViewDataChange();
                                requestMergeCat(fromId, toId);
                            }
                        }
                    }
                    //删除范围
                    int minX = ivDel.getLeft();
                    int maxX = ivDel.getLeft() + ivDel.getWidth();
                    int minY = llDelParent.getTop();//用父类gettop()
                    int maxY = llDelParent.getTop() + ivDel.getHeight();
                    if (rawX >= minX && rawX <= maxX && rawY >= minY && rawY <= maxY) {
                        RecycleDialog recycleDialog = new RecycleDialog(getActivity());
                        recycleDialog.setOnConfirmClickListener(new RecycleDialog.OnConfirmClickListener() {
                            @Override
                            public void onConfirmClick() {
                                dragView.setVisibility(View.GONE);
                                requestGcCat(list.get(finalI));
                            }
                        });
                        recycleDialog.show();
                    }

                    //仓库范围
                    int[] location = new int[2];
                    llStoreContainer.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    int minX2 = x - 30;
                    int maxX2 = x + llStoreContainer.getWidth() + 30;
                    int minY2 = y - 30;//用父类gettop()
                    int maxY2 = y + llStoreContainer.getHeight() + 30;
                    if (rawX >= minX2 && rawX <= maxX2 && rawY >= minY2 && rawY <= maxY2) {
                        ToStoreDialog toStoreDialog = new ToStoreDialog(getActivity(), list.get(finalI));
                        toStoreDialog.setOnComfirmClickListener(new ToStoreDialog.OnComfirmClickListener() {
                            @Override
                            public void onComfirmClick() {
                                requestInPutStore(list.get(finalI));
                            }
                        });
                        toStoreDialog.show();
                    }
                }
            });
        }
        llCatContainer.setEnabled(true);
        llAddCatContainer.setEnabled(true);
    }

    //更新界面信息,如等级等
    public void notifyViewDataChange() {
        for (int i = 0; i < list.size(); i++) {
            CatBean viewBean = list.get(i);
            if (viewBean.getCatId() == 0) {
                viewBean.getView().setVisibility(View.GONE);
            } else {
                viewBean.getView().setVisibility(View.VISIBLE);
                TextView tv_counts = viewBean.getView().findViewById(R.id.tv_counts);
                ImageView iv_img = viewBean.getView().findViewById(R.id.iv_img);
                Glide.with(getActivity()).load(list.get(i).getImg()).into(iv_img);
                tv_counts.setText(viewBean.getCatLevel() + "");
            }
        }
    }

    public static HomeFragment homeFragment;

    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

   /**
     * 计算移动到所属位置
     *
     * @param x
     * @param y
     * @param containerView
     * @return
     */
    public int rangle(int x, int y, View containerView) {
        int x1 = containerView.getWidth() + ConvertUtils.dp2px(10) / 2;
        int a1 = x - rv.getLeft();
        int y1 = y - rv.getTop();
        if (a1 < 0) {
            return -1;
        }
        if (y1 < 0) {
            return -1;
        }
        int y2 = containerView.getHeight() + ConvertUtils.dp2px(10) / 2;
        int i = (a1) / (x1);
        int j = (y1) / (y2);
        if (i >= 0 && i <= 3) {
            if (j >= 0 && j <= 2) {
                return i + 4 * j;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    boolean isHadEmpty = false;

    @OnClick({R.id.iv_shop, R.id.ll_add_cat_container, R.id.ll_cat_container, R.id.rl_get_money_container, R.id.iv_lucky_wheel, R.id.iv_top, R.id.iv_how_to_play, R.id.ll_dividends_cat_container, R.id.ll_pic_guide_container, R.id.ll_store_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_cat_container:
                requestBuyCat(homeBean.getCat().getId());
                break;
            case R.id.ll_add_cat_container:
                requestBuyCat(homeBean.getCat().getId());
                break;
            case R.id.rl_get_money_container://领取金币
                GetMoneyDialog getMoneyDialog = new GetMoneyDialog(getActivity());
                getMoneyDialog.show();
                break;
            case R.id.iv_lucky_wheel:
                LuckyWheelDialog luckyWheelDialog = new LuckyWheelDialog(getActivity());
                luckyWheelDialog.show();
                break;
            case R.id.iv_top:
                startActivity(TopActivity.class);
                break;
            case R.id.iv_how_to_play:
                refresh();
//                startActivity(LoginActivity.class);
//                InviterDialog inviterDialog = new InviterDialog(getActivity());
//                inviterDialog.show();
//                startActivity(HowToPlayActivity.class);
                break;
            case R.id.ll_dividends_cat_container:
                startActivity(DividendCatActivity.class);
                break;
            case R.id.ll_pic_guide_container:
                startActivity(PicListActivity.class);
                break;
            case R.id.ll_store_container:
                StoreListDialog storeListDialog = new StoreListDialog(getActivity());
                storeListDialog.init((BaseActivity) getActivity(), homeBean.getDilation());
                storeListDialog.show();
                storeListDialog.setOnClickListener(new StoreListDialog.OnClickListener() {
                    @Override
                    public void onGetOutClick(CatBean catBean) {//取出
                        isHadEmpty = false;
                        for (CatBean catBean1 : list) {
                            if (catBean1.getCatId() == 0) {
                                isHadEmpty = true;
                                requestGetOutCat(catBean.getCatId());
                                break;
                            }
                        }
                        if (!isHadEmpty) {//没有空格子
                            ToastUtils.showShort("猫咪已满,请先回收猫咪!");
                        }
                    }
                });
                break;
            case R.id.iv_shop:
                CatShopDialog catShopDialog = new CatShopDialog(getActivity(), (BaseActivity) getActivity());
                catShopDialog.show();
                catShopDialog.setOnBuyClickListener(new CatShopDialog.OnBuyClickListener() {
                    @Override
                    public void OnBuyClick(CatShopBean catShopBean) {//商店购买列表
                        isHadEmpty = false;
                        for (CatBean catBean1 : list) {
                            if (catBean1.getCatId() == 0) {
                                isHadEmpty = true;
                                requestBuyCat(catShopBean.getId());
                                break;
                            }
                        }
                        if (!isHadEmpty) {//没有空格子
                            ToastUtils.showShort("猫咪已满,请先回收猫咪!");
                        }
                    }
                });
                break;
        }
    }


    private Handler amountHandler = new Handler();
    private Handler requestHandler = new Handler();
    private Runnable amountRunnerble;
    private Runnable requestRunnerble;

    //计时器,每秒计算总金额,每一分钟发送一次同步数据
    public void startTimer() {
        if (amountRunnerble == null) {
            amountRunnerble = new Runnable() {
                @Override
                public void run() {
                    amount += outPut;
                    tvAmount.setText(amount + "");
                    amountHandler.postDelayed(this, 1000);
                }
            };
            amountHandler.postDelayed(amountRunnerble, 0);
        }

        if(requestRunnerble == null) {
            requestRunnerble = new Runnable() {
                @Override
                public void run() {
                    //todo 同步数据
                    requestAmountSync();
                    requestHandler.postDelayed(this, 5000);
                }
            };
            requestHandler.postDelayed(requestRunnerble,0);
        }
    }

    public void stopTimer() {
        requestHandler.removeCallbacksAndMessages(requestRunnerble);
        requestRunnerble = null;

        amountHandler.removeCallbacksAndMessages(amountRunnerble);
        amountRunnerble = null;
    }

    public void requestAmountSync(){
        HttpManager.toRequst(HttpManager.getApi().amountSync(amount), new BaseObserver(this,false) {
            @Override
            public void _onNext(Object o) {
//                ToastUtils.showShort("提交成功");
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    public class MyAdapter extends BaseQuickAdapter<CatBean, BaseViewHolder> {

        public MyAdapter(@Nullable List<CatBean> data) {
            super(R.layout.item_cat, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CatBean item) {

        }
    }


}
