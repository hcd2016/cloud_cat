package com.vpfinace.cloud_cat.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import androidx.annotation.Nullable;

public class GlideUtils {
    public static void loadOneTimeGif(Context context, Object model, final ImageView imageView, final GifListener gifListener) {
        RequestOptions requestOptions = new RequestOptions().skipMemoryCache(true);//配置
        Glide.with(context).asGif().apply(requestOptions).load(model).listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                try {
//                    Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
//                    Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder");
//                    Field field = GifDecoder.class.getDeclaredField("header");
//                    field.setAccessible(true);
//                    GifHeader header = (GifHeader) field.get(gifDecoderField);
//                    Field field2 = GifHeader.class.getDeclaredField("frames");
//                    field2.setAccessible(true);
//                    List frames = (List) field2.get(header);
//                    if (frames.size()>0){
//                        Field delay = frames.get(0).getClass().getDeclaredField("delay");
//                        delay.setAccessible(true);
//                        for (Object frame : frames) {
//                            delay.set(frame,20);//这里直接给修改成了20
//                        }
//                    }

                    Field gifStateField = GifDrawable.class.getDeclaredField("state");
                    gifStateField.setAccessible(true);
                    Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
                    Field gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader");
                    gifFrameLoaderField.setAccessible(true);
                    Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
                    Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder");
                    gifDecoderField.setAccessible(true);
                    Class gifDecoderClass = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder");
                    Object gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)));
                    Method getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", int.class);
                    getDelayMethod.setAccessible(true);
                    //设置只播放一次
                    resource.setLoopCount(1);
                    //获得总帧数
                    int count = resource.getFrameCount();
                    int delay = 0;
                    for (int i = 0; i < count; i++) {
                        //计算每一帧所需要的时间进行累加
                        delay += (int) getDelayMethod.invoke(gifDecoder, i);
                    }
                    imageView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (gifListener != null) {
                                gifListener.gifPlayComplete();
                            }
                        }
                    }, delay);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        }).into(imageView);
    }

    /**
     * 圆形
     */
    public static void loadCircle(Context context, Object src, ImageView imageView) {
        Glide.with(context).load(src).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);//标准圆形图片。
    }

    /**
     * 圆角
     */
    public static void loadCorner(Context context, Object src, ImageView imageView, int radius) {
        Glide.with(context).load(src).apply(RequestOptions.bitmapTransform(new RoundedCorners(radius))).into(imageView);//四周都是圆角的圆角矩形图片。
    }

    /**
     * Gif播放完毕回调
     */
    public interface GifListener {
        void gifPlayComplete();
    }
}
