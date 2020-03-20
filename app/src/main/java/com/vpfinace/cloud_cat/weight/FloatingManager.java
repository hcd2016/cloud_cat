package com.vpfinace.cloud_cat.weight;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/**
 * 设置在指定view的位置添加一个新view(以view在activity显示的位置)
 */
public class FloatingManager {

    private View srcView;

    private View targetView;

    public void setParentView(ViewGroup parentView) {
        this.parentView = parentView;
    }

    private ViewGroup parentView;

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private FloatingManager mManager;

        public FloatingManager build() {
            return mManager;
        }

        public Builder() {
            mManager = new FloatingManager();
        }

        public Builder setSrcView(View view) {
            mManager.setSrcView(view);
            return this;
        }

        public Builder setTargetView(View view) {
            mManager.setTargetView(view);
            return this;
        }
        public Builder setParentView(ViewGroup view) {
            mManager.setParentView(view);
            return this;
        }

    }

    public void setSrcView(View view) {
        srcView = view;
    }
    public void setTargetView(View targetView) {
        this.targetView = targetView;
    }

    public void showCenterView() {
        if (srcView == null) {
            return;
        }
        Rect anchorRect = new Rect();
        srcView.getGlobalVisibleRect(anchorRect);
        //设置与原view宽高相同
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(srcView.getWidth(), srcView.getHeight());
        targetView.setLayoutParams(layoutParams);
        //设置与原view位置相同
        targetView.setY(anchorRect.top);
        targetView.setX(anchorRect.left);
        parentView.addView(targetView);
    }

    public void clearView(View targetView,ViewGroup parentView){
        parentView.removeView(targetView);
    }

    //移动view的位置
    public void  moveViewLocation(View srcView,View targetView){
        Rect anchorRect = new Rect();
        srcView.getGlobalVisibleRect(anchorRect);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(srcView.getWidth(), srcView.getHeight());
//        targetView.setLayoutParams(layoutParams);
//        //设置与原view位置相同
//        targetView.setY(anchorRect.top);
//        targetView.setX(anchorRect.left);
//        parentView.addView(targetView);

//        targetView.layout(anchorRect.left,anchorRect.top,anchorRect.right,anchorRect.right);

        targetView.setY(anchorRect.top);
        targetView.setX(anchorRect.left);
//        Rect anchorRect = new Rect();
//        srcView.getGlobalVisibleRect(anchorRect);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(srcView.getWidth(), srcView.getHeight());
//        targetView.setLayoutParams(layoutParams);
//        //设置与原view位置相同
//        targetView.setY(anchorRect.top);
//        targetView.setX(anchorRect.left);
//        parentView.addView(targetView);
    }

}
