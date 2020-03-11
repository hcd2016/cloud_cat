package com.vpfinace.cloud_cat.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 保存图片到系统相册
 */
public class PhotoSaveUtils {

//    /*
//     * 保存文件，文件名为当前日期
//     */
//    public static void saveBitmap(Context context, Bitmap bitmap, String bitName) {
//        String fileName;
//        File file;
//        if (Build.BRAND.equals("xiaomi")) { // 小米手机
//            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
//        } else if (Build.BRAND.equals("Huawei")) {
//            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
//        } else {  // Meizu 、Oppo
//            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + bitName;
//        }
//        file = new File(fileName);
//
//        if (file.exists()) {
//            file.delete();
//        }
//        FileOutputStream out;
//        try {
//            out = new FileOutputStream(file);
//            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
//            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
//                out.flush();
//                out.close();
//// 插入图库
//                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), bitName, null);
//                ToastUtils.showShort("保存成功");
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//        // 发送广播，通知刷新图库的显示
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));
//
//    }

    //保存文件到系统相册
    public static void saveImageToGallery(Context context, Bitmap bmp,String filename) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "fnsc");
        if (!appDir.exists()) {

            appDir.mkdir();
        }

        String fileName = filename + System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {

            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            //保存成功 弹出自定义toast
//            View toastview = LayoutInflater.from(context).inflate(R.layout.toast_text_layout, null);
//            TextView text = (TextView) toastview.findViewById(R.id.tv_message);
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ConvertUtils.dp2px(320),
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            text.setLayoutParams(lp);
//            text.setText(R.string.bccg);
//            Toast toast = new Toast(context);
//            toast.setGravity(Gravity.TOP, 0, 30);
//            toast.setDuration(Toast.LENGTH_LONG);
//            toast.setView(toastview);
//            toast.show();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {

            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        // 最后通知图库更新
        // context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(file.getPath()))));
    }


}
