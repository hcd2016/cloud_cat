package com.vpfinace.cloud_cat.weight;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.ui.home.fragment.HomeFragment;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 猫控制类,添加,删除,指定位置添加,删除
 */
public class CatManager {

    private View targetView;

    private ViewGroup parentView;

    private HomeFragment.MyAdapter myAdapter;
    private RecyclerView rv;

    public CatManager(View targetView, ViewGroup parentView, HomeFragment.MyAdapter myAdapter, RecyclerView rv) {
        this.targetView = targetView;
        this.parentView = parentView;
        this.myAdapter = myAdapter;
        this.rv = rv;
    }

    public RecyclerView getRv() {
        return rv;
    }

    public void setRv(RecyclerView rv) {
        this.rv = rv;
    }

    public View getTargetView() {
        return targetView;
    }

    public void setTargetView(View targetView) {
        this.targetView = targetView;
    }

    public View getParentView() {
        return parentView;
    }

    public void setParentView(ViewGroup parentView) {
        this.parentView = parentView;
    }

    public HomeFragment.MyAdapter getMyAdapter() {
        return myAdapter;
    }

    public void setMyAdapter(HomeFragment.MyAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }

    //指定位置添加猫
    public void addCatView(int postion, DragView dragView) {
        DragView srcView = (DragView) myAdapter.getViewByPosition(rv,postion, R.id.item_dragView);
        if (srcView == null) {
            return;
        }

        Rect anchorRect = new Rect();
        srcView.getGlobalVisibleRect(anchorRect);
        //设置与原view宽高相同
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(srcView.getWidth(), srcView.getHeight());
        dragView.setLayoutParams(layoutParams);
        //设置与原view位置相同
        dragView.setY(anchorRect.top);
        dragView.setX(anchorRect.left);
        parentView.addView(dragView);
    }

    //删除指定位置的猫
    public void  delCatView(View targetView) {
        parentView.removeView(targetView);
    }
}
