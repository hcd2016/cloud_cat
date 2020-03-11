package com.vpfinace.cloud_cat.utils;


import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.vpfinace.cloud_cat.app.App;

import java.util.Locale;

import androidx.core.content.ContextCompat;

public class MyUtils {
    public static final int getColor(int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(App.getContext(), id);
        } else {
            return App.getContext().getResources().getColor(id);
        }
    }

    // 启动应用的设置弹窗
    public static void toAppSettings(String message, final boolean isFinish, final Context context) {
        if (TextUtils.isEmpty(message)) {
            message = "\"" + App.getAPPName() + "\"缺少必要权限";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (isFinish) {
            builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    finish();
                    dialog.dismiss();
                }
            });
        } else {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    finish();
                    dialog.dismiss();
                }
            });
        }
        builder.setMessage(message + "\n请手动授予\"" + App.getAPPName() + "\"访问您的权限")
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + context.getPackageName()));
                        context.startActivity(intent);
                    }
                }).create().show();
    }

    // 限制输入框不能输入汉字
    public static void setNoChineseEdit(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    for (int i = 0; i < s.length(); i++) {
                        char c = s.charAt(i);
                        if (c >= 0x4e00 && c <= 0X9fff) {
                            s.delete(i, i + 1);
                        }
                    }
                }
            }
        });
    }

    //获取系统String
    public static String getRecouseString(int id) {
        return App.getContext().getResources().getString(id);
    }

    //是否是中文
    public static boolean isZh(){
        String locale = Locale.getDefault().getLanguage();
        return locale.equals("zh");
    }

    public static String getTopActivity(Context context){
        ActivityManager am =   (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        ComponentName cn =   am.getRunningTasks(1).get(0).topActivity;
        return cn.getClassName();
    }
}
