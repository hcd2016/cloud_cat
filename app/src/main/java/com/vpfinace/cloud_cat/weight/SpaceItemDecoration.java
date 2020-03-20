package com.vpfinace.cloud_cat.weight;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

// 自定义条目修饰类
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int column;
    private final int space;

    public SpaceItemDecoration(int space, int column) {
        this.space = space;
        this.column = column;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
////         第一列左边贴边、后边列项依次移动一个space和前一项移动的距离之和
//        int mod = parent.getChildAdapterPosition(view) % column;
//        outRect.left = space * mod;
//        outRect.bottom = ConvertUtils.dp2px(12);

//        //不是第一个的格子都设一个左边和底部的间距
//        outRect.left = space;
//        outRect.bottom = space;
//        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
//        if (parent.getChildLayoutPosition(view) %column==0) {
//            outRect.left = 0;
//        }

        //列数
        int spanCount = column;
        int position = parent.getChildLayoutPosition(view);
        int column = (position) % spanCount;
//        if (mIncludeEdge) {
//            outRect.left = space - column * space / spanCount;
//            outRect.right = (column + 1) * space / spanCount;
//            if (position < spanCount) {
//                outRect.top = space;
//            }
//            outRect.bottom = space;
//        } else {
            outRect.left = column * space / spanCount;
            outRect.right = space - (column + 1) * space / spanCount;
            if (position >= spanCount) {
                outRect.top = space;
            }
//        }

    }
}