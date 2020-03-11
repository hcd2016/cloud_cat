package com.vpfinace.cloud_cat.base;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.vpfinace.cloud_cat.app.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class PermissionBaseActivity extends BaseActivity {
    private PermissionsListener mListener;

    /**
     * 请求权限封装
     *
     * @param permissions
     * @param listener
     */
    public void requestPermissions(String[] permissions, PermissionsListener listener) {
        if(Build.VERSION.SDK_INT < 23) return;
        mListener = listener;
        List<String> requestPermissions = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                requestPermissions.add(permission);
            }
        }
        if (!requestPermissions.isEmpty() && Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this, requestPermissions.toArray(new String[requestPermissions.size()]), 1);
        } else {
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                List<String> deniedPermissions = new ArrayList<>();
                //当所有拒绝的权限都勾选不再询问，这个值为true,这个时候可以引导用户手动去授权。
                boolean isNeverAsk = true;
                for (int i = 0; i < grantResults.length; i++) {
                    int grantResult = grantResults[i];
                    ;
                    String permission = permissions[i];
                    if (grantResult == PackageManager.PERMISSION_DENIED) {
                        deniedPermissions.add(permissions[i]);
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) { // 点击拒绝但没有勾选不再询问
                            isNeverAsk = false;
                        }
                    }
                }
                if (deniedPermissions.isEmpty()) {
                    try {
                        mListener.onGranted();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        mListener.onDenied(Arrays.asList(permissions), true);
                    }
                } else {
                    mListener.onDenied(deniedPermissions, isNeverAsk);
                }
                break;
            default:
                break;
        }
    }

    // 启动应用的设置弹窗
    public void toAppSettings(String message, final boolean isFinish) {
        if (TextUtils.isEmpty(message)) {
            message = "\"" + App.getAPPName() + "\"缺少必要权限";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (isFinish) {
            builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        } else {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }
        builder.setMessage(message + "\n请手动授予\"" + App.getAPPName() + "\"访问您的权限")
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        startActivity(intent);
                    }
                }).create().show();
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initPresenter() {

    }

    /**
     * 6.0权限封装接口
     */
    public interface PermissionsListener {
        void onGranted();

        /**
         * @param deniedPermissions 被拒绝的权限集合
         * @param isNeverAsk        是否所有被拒绝权限都勾选不再询问
         */
        void onDenied(List<String> deniedPermissions, boolean isNeverAsk);
    }

//    ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //请求示例:
    /**
     //         * 需要强制开启的权限
     //         */
//        if (!isRequsting) {
//            final String[] permissions = new String[]{Manifest.permission.CAMERA};
//            permissionsListener = new PermissionsListener() {
//                @Override
//                public void onGranted() {
//                    ToastUtils.showShort("成功!");
//                    isRequsting = true;
//                }
//
//                @Override
//                public void onDenied(List<String> deniedPermissions, boolean isNeverAsk) {
//                    if (!isNeverAsk) {//请求权限没有全被勾选不再提示然后拒绝
//                        new AlertDialog.Builder(MainActivity.this)
//                                .setNegativeButton("退出", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        finish();
//                                    }
//                                })
//                                .setMessage("为了能正常使用\"" + App.getAPPName() + "\"，请授予所需权限")
//                                .setPositiveButton("授权", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        requestPermissions(permissions, permissionsListener);
//                                    }
//                                }).create().show();
//                    } else {//全被勾选不再提示
//                        new AlertDialog.Builder(MainActivity.this)
//                                .setNegativeButton("退出", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        finish();
//                                    }
//                                })
//                                .setMessage("\"" + App.getAPPName() + "\"缺少必要权限\n请手动授予\"" + App.getAPPName() + "\"访问您的权限")
//                                .setPositiveButton("授权", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                        intent.setData(Uri.parse("package:" + getPackageName()));
//                                        startActivity(intent);
//                                        isRequsting = false;
//                                    }
//                                }).create().show();
//                    }
//                }
//            };
//            isRequsting = true;
//            requestPermissions(permissions, permissionsListener);
//        }

    //普通申请权限:
//        final String[] permissions = new String[]{Manifest.permission.CAMERA};
//        requestPermissions(permissions, new PermissionsListener() {
//            @Override
//            public void onGranted() {
//                ToastUtils.showShort("成功");
//            }
//
//            @Override
//            public void onDenied(List<String> deniedPermissions, boolean isNeverAsk) {
//                if(isNeverAsk) {
//                    toAppSettings("缺少相机权限",false);
//                }
//            }
//        });
}