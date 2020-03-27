package com.vpfinace.cloud_cat.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import com.vpfinace.cloud_cat.R;

/**
 * 存入仓库dialog
 */
public class ToStoreDialog extends TBaseDialog{
    public ToStoreDialog(Context context) {
        super(context, R.layout.dialog_to_store);
        setWindowParam(0.8f, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 0);
    }
}
