package com.vpfinace.cloud_cat.ui.home;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseFragment;
import com.vpfinace.cloud_cat.bean.ViewBean;
import com.vpfinace.cloud_cat.dialog.GetMoneyDialog;
import com.vpfinace.cloud_cat.dialog.LuckyWheelDialog;
import com.vpfinace.cloud_cat.dialog.RecycleDialog;
import com.vpfinace.cloud_cat.utils.GlideUtils;
import com.vpfinace.cloud_cat.weight.CatManager;
import com.vpfinace.cloud_cat.weight.DragView;
import com.vpfinace.cloud_cat.weight.SpaceItemDecoration;

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
    private MyAdapter myAdapter;
    private List<ViewBean> currentViewList;//当前显示的view
    private List<ViewBean> list;

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
        for (int i = 0; i < 12; i++) {
            ViewBean viewBean = new ViewBean();
            list.add(viewBean);
        }
        rv.setAdapter(myAdapter);
        llCatContainer.setEnabled(false);
        llAddCatContainer.setEnabled(false);
        initCat();
    }

    private void initCat() {
        rv.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {//初始化
                    DragView dragView = (DragView) View.inflate(getActivity(), R.layout.drag_cat_layout, null);
                    CatManager catManager = new CatManager(dragView, rlContainer, myAdapter, rv);
                    catManager.addCatView(i, dragView);
                    ViewBean srcViewBean = list.get(i);
                    srcViewBean.setView(dragView);
                    dragView.setVisibility(View.GONE);
                    ImageView iv_img = dragView.findViewById(R.id.iv_img);
                    TextView tv_counts = dragView.findViewById(R.id.tv_counts);
                    tv_counts.setText(srcViewBean.getLevel() + "");
                    RelativeLayout rl_item_container = (RelativeLayout) myAdapter.getViewByPosition(rv, i, R.id.rl_item_container);
                    ImageView iv_gif = rl_item_container.findViewById(R.id.iv_gif);
                    int finalI = i;
                    dragView.setOnActionUpListener(new DragView.OnActionUpListener() {
                        @Override
                        public void onActionUp(int lastX, int lastY, int startLeft, int startTop, int startRight, int startBottom, int rawX, int rawY) {
                            int postion = rangle(rawX, rawY, rl_item_container);
                            if (finalI == postion) {
                                return;
                            }
                            if (postion != -1) {//在范围内
                                ViewBean viewBean = list.get(postion);
                                if (viewBean.getView().getVisibility() == View.GONE) {//当前移动到的格子是空的
//                                    viewBean.getView().setVisibility(View.VISIBLE);
                                    viewBean.getView().setVisibility(View.VISIBLE);
                                    dragView.setVisibility(View.GONE);
                                    //替换除view外的元素
                                    list.get(postion).setLevel(list.get(finalI).getLevel());
                                    notifyViewDataChange();

                                } else {//不是空的,看等级是否相同
                                    if (list.get(finalI).getLevel() == list.get(postion).getLevel()) {//等级相同,合成
                                        viewBean.setLevel(viewBean.getLevel() + 1);
                                        dragView.setVisibility(View.GONE);
                                        RelativeLayout rl_item_container = (RelativeLayout) myAdapter.getViewByPosition(rv, postion, R.id.rl_item_container);
                                        ImageView iv_gif = rl_item_container.findViewById(R.id.iv_gif);
                                        iv_gif.setVisibility(View.VISIBLE);
                                        viewBean.getView().setVisibility(View.GONE);
                                        GlideUtils.loadOneTimeGif(getActivity(),R.drawable.compund1,iv_gif, new GlideUtils.GifListener() {
                                            @Override
                                            public void gifPlayComplete() {
                                                iv_gif.setVisibility(View.GONE);
                                                notifyViewDataChange();
                                                viewBean.getView().setVisibility(View.VISIBLE);
                                            }
                                        });

                                    } else {//等级不同,互换位置
                                        int srcLevel = list.get(finalI).getLevel();
                                        int targetLevel = list.get(postion).getLevel();

                                        list.get(postion).setLevel(srcLevel);
                                        list.get(finalI).setLevel(targetLevel);
                                        //换位不能换view对象
//                                        Collections.swap(list, finalI, postion);

                                        notifyViewDataChange();
                                    }
                                }
                            }
                            //删除范围
                            int minX = ivDel.getLeft();
                            int maxX = ivDel.getLeft() + ivDel.getWidth();
                            int minY = llDelParent.getTop();//用父类gettop()
                            int maxY = llDelParent.getTop() + ivDel.getHeight();
                            Log.i("test", "rawX=" + rawX + ",rawY=" + rawY + ",minX=" + minX + ",ivDel.getLeft()=" + ivDel.getLeft() + ",ivDel.getWidth()=" + ivDel.getWidth());
                            Log.i("test", "rawX=" + rawX + ",rawY=" + rawY + ",ivDel.getTop()=" + ivDel.getTop() + ",ivDel.getHeight()=" + ivDel.getHeight());
                            if (rawX >= minX && rawX <= maxX && rawY >= minY && rawY <= maxY) {
                                RecycleDialog recycleDialog = new RecycleDialog(getActivity());
                                recycleDialog.setOnConfirmClickListener(new RecycleDialog.OnConfirmClickListener() {
                                    @Override
                                    public void onConfirmClick() {
                                        dragView.setVisibility(View.GONE);
                                    }
                                });
                                recycleDialog.show();
                            }
                        }
                    });
                }
                llCatContainer.setEnabled(true);
                llAddCatContainer.setEnabled(true);
            }
        }, 1000);
    }

    //更新界面信息,如等级等
    public void notifyViewDataChange() {
        for (int i = 0; i < list.size(); i++) {
            ViewBean viewBean = list.get(i);
            TextView tv_counts = viewBean.getView().findViewById(R.id.tv_counts);
            ImageView iv_img = viewBean.getView().findViewById(R.id.iv_img);
            int level = viewBean.getLevel();
            tv_counts.setText(level + "");
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
     * 添加猫
     */
    public void addCat() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getView().getVisibility() == View.GONE) {
                list.get(i).getView().setVisibility(View.VISIBLE);
                list.get(i).setLevel(1);
                notifyViewDataChange();
                return;
            } else if (i == list.size() - 1 && list.get(i).getView().getVisibility() != View.GONE) {
                ToastUtils.showShort("已经添加满了");
            }
        }
    }

    /**
     * 移除猫
     */
    public void delCat() {

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

    @OnClick({R.id.ll_add_cat_container, R.id.ll_cat_container, R.id.rl_get_money_container, R.id.iv_lucky_wheel, R.id.iv_top, R.id.iv_how_to_play, R.id.ll_dividends_cat_container, R.id.ll_pic_guide_container, R.id.ll_store_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_cat_container:
                addCat();
                break;
            case R.id.ll_add_cat_container:
                addCat();
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
                break;
            case R.id.iv_how_to_play:
                break;
            case R.id.ll_dividends_cat_container:
                break;
            case R.id.ll_pic_guide_container:
                break;
            case R.id.ll_store_container:
                break;
        }
    }

    public class MyAdapter extends BaseQuickAdapter<ViewBean, BaseViewHolder> {

        public MyAdapter(@Nullable List<ViewBean> data) {
            super(R.layout.item_cat, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ViewBean item) {

        }
    }


}
