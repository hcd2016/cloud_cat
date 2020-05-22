package com.vpfinace.cloud_cat.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.blankj.utilcode.util.ImageUtils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.vpfinace.cloud_cat.app.App;

/**
 * 微信分享工具类
 */
public class WXShareUtil {
//    //分享渠道
//    SendMessageToWX.Req.WXSceneSession//微信会话
//    SendMessageToWX.Req.WXSceneTimeline//微信朋友圈
//    SendMessageToWX.Req.WXSceneFavorite//微信收藏

    /**
     * 微信分享图片
     * @param context
     * @param bmp
     * @param channel 1为会话,2为朋友圈
     */
    public static void shareImg(Context context,Bitmap bmp,int channel) {
        if(channel == 1) {//微信会话
            channel = SendMessageToWX.Req.WXSceneSession;
        }else {
            channel = SendMessageToWX.Req.WXSceneTimeline;
        }

    //初始化 WXImageObject 和 WXMediaMessage 对象
        WXImageObject imgObj = new WXImageObject(bmp);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

    //设置缩略图
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 200, 400, true);
        bmp.recycle();
        msg.thumbData = ImageUtils.bitmap2Bytes(thumbBmp, Bitmap.CompressFormat.PNG);

    //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "img";
        req.message = msg;
        req.scene = channel;
        //调用api接口，发送数据到微信
        App.getWxApi().sendReq(req);
    }
}
