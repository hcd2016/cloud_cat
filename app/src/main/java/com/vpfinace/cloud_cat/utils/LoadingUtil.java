package com.vpfinace.cloud_cat.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;


/**
 * 加载框展示.隐藏,自定义布局添加
 */
public class LoadingUtil {
    private static View loadingView;
    private static AlertDialog dialog;
    private static Window window;

    public static void setLoadingView(View loadingView) {
        LoadingUtil.loadingView = loadingView;
    }

    /**
     * @param activity
     * @param content 传""为显示默认文字:加载中...,/自定义布局时不影响显示文字
     */
    public static void showLoading(Activity activity, String content) {
        finishLoading();
        dialog = new AlertDialog.Builder(activity, R.style.transparent_dialog_style).create();
        dialog.setCancelable(false);
        window = dialog.getWindow();
        dialog.show();
        if (!activity.isFinishing()) {
            if (loadingView == null) {
                window.setContentView(R.layout.dialog_default_loading);
                if (!TextUtils.isEmpty(content)) {
                    TextView textView = window.findViewById(R.id.content);
                    textView.setText(content);
                }
            } else {
                window.setContentView(loadingView);
                loadingView = null;//这次的自定义loadingView不影响下次加载.
            }
        }
    }

    public static void finishLoading() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if (loadingView != null) {//如果设置了loadingView,移除dialog中添加的loadingView
                ViewParent parent = loadingView.getParent();
                if (parent instanceof ViewGroup) {
                    ViewGroup viewgroup = (ViewGroup) parent;
                    viewgroup.removeView(loadingView);
                }
            }
            dialog = null;
        }
    }
}
