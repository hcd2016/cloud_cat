package com.vpfinace.cloud_cat.http;


import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vpfinace.cloud_cat.app.App;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.global.SpContant;
import com.vpfinace.cloud_cat.utils.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Retrofit请求管理类<p>
 */
public class HttpManager {

    private HttpApi mHttpApi;

    private static HttpManager instance = null;

    /**
     * 获取单例
     *
     * @return 实例
     */
    public static HttpManager getInstance() {

        if (instance == null) {
            instance = new HttpManager();
            return instance;
        }
        return instance;
    }

    public static HttpApi getApi() {
        return getInstance().mHttpApi;
    }

    private HttpManager() {
        //为了避免使用Gson时遇到locale影响Date格式的问题，使用GsonBuilder来创建Gson对象，在创建过程中调用GsonBuilder.setDateFormat(String)指定一个固定的格式即可。
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(App.getConfig().getBaseUrl())
                .client(createOkHttpClient())
                .addConverterFactory(StringConverterFactory.create())//返回类型转成String,这个解析器会先执行,取不到String才会解析Bean类,api中需申明泛型为String
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mHttpApi = retrofit.create(HttpApi.class);
    }

    public OkHttpClient createOkHttpClient() {

//        Cache cache = new Cache(new File(App.getContext().getCacheDir(), "cache-jinwandalaohu"),
//                1024 * 1024 * 100);

        //添加全局统一请求头
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
//                SPUtils.getInstance().getString()
//                String sessionid = SpUtil.getString(Constant.SHARE_TAG_SESSIONID);
//                if (App.getConfig().getLoginStatus()&&!TextUtils.isEmpty(sessionid)){
//                    builder.addHeader("Cookie", "SESSIONID="+sessionid);
//                }
//                builder.addHeader("APP-VERSION", AppUtils.getAppVersionName());
//                builder.addHeader("APP-VERSION", "4.0.0");
                if (!TextUtils.isEmpty(SPUtils.getInstance().getString(SpContant.SP_TOKEN))) {
                    builder.addHeader("token", SPUtils.getInstance().getString(SpContant.SP_TOKEN));
                }
                if(!MyUtils.isZh()) {//英文加header
                    builder.addHeader("lang","en_US");
                }
                Response response = chain.proceed(builder.build());
                return response;
            }
        };
        //添加全局统一请求参数
        Interceptor paramsInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl httpUrl = request.url();
                HttpUrl url = httpUrl.newBuilder()
//                        .addQueryParameter("clientType", "android")
//                        .addQueryParameter("appVersion", ViewUtil.getAppVersion(App.getContext()))
//                        .addQueryParameter("deviceId", ViewUtil.getDeviceId(App.getContext()))
//                        .addQueryParameter("mobilePhone", App.getConfig().getLoginStatus()?SpUtil.getString(Constant.SHARE_TAG_USERNAME):"")
//                        .addQueryParameter("deviceName", ViewUtil.getDeviceName().trim())
//                        .addQueryParameter("osVersion", ViewUtil.getOsVersion())
//                        .addQueryParameter("appMarket", App.getConfig().getChannelName()).build();
//                        .addQueryParameter("APP-VERSION", AppUtils.getAppVersionName()).build();
                        .build();
                Request.Builder builder = request.newBuilder().url(url);
                Response response = chain.proceed(builder.build());
//                if(url.toString().contains("tryUseToAddOrder")) {
//                    String string = response.body().string();//此代码只能调用一次,调试完必须注释掉
//                }
                return response;
            }
        };


        //是否显示"请求繁忙"倒计时dialog  PS：需要显示的在HttpApi接口添加请求头@Headers("showDialog:true")
        Interceptor respomseInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
//                String showDialog = request.header("showDialog");
                Response response = chain.proceed(chain.request());
                String body = response.body().string();
                JSONObject jsonResponse = null;
                try {
                    jsonResponse = new JSONObject(body);
                    String code = jsonResponse.getString("code");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return response.newBuilder().body(ResponseBody.create(MediaType.parse("UTF-8"), jsonResponse.toString())).build();
            }
        };

        /**
         * 获取缓存
         */
        Interceptor baseInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {


                CacheControl.Builder cacheBuilder = new CacheControl.Builder();
                cacheBuilder.maxAge(0, TimeUnit.SECONDS);
                cacheBuilder.maxStale(365, TimeUnit.DAYS);
                CacheControl cacheControl = cacheBuilder.build();

                Request request = chain.request();
                if (NetworkUtils.isConnected()) {
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                Response originalResponse = chain.proceed(request);
                if (NetworkUtils.isConnected()) {
                    int maxAge = 0; // read from cache
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public ,max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }

//                if (!NetworkUtils.isConnected()) {
//                    /**
//                     * 离线缓存控制  总的缓存时间=在线缓存时间+设置离线缓存时间
//                     */
//                    int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周,单位:秒
//                    CacheControl tempCacheControl = new CacheControl.Builder()
//                            .onlyIfCached()
//                            .maxStale(maxStale, TimeUnit.SECONDS)
//                            .build();
//                    request = request.newBuilder()
//                            .cacheControl(tempCacheControl)
//                            .build();
////                    Log.i(TAG, "intercept:no network ");
//                }else {
//                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
//                    return originalResponse.newBuilder()
//                            .removeHeader("Pragma")
//                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                            .build();
//                }
//                return chain.proceed(request);
            }
        };
        //只有 网络拦截器环节 才会写入缓存写入缓存,在有网络的时候 设置缓存时间
        Interceptor rewriteCacheControlInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                int maxAge = 60 * 60; // 有网络时 设置缓存超时时间1个小时
                int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                Request request = chain.request();
                if (NetworkUtils.isConnected()) {
                    request = request.newBuilder()
                            .addHeader("apikey", "2ffc3e48c7086e0e1faa003d781c0e69")
                            .cacheControl(CacheControl.FORCE_NETWORK)//有网络时只从网络获取
                            .build();
                } else {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)//无网络时只从缓存中读取
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected()) {
                    response = response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    response = response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
                return response;
            }
        };
        //设置缓存路径 内置存储
        //File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        //外部存储
//        File httpCacheDirectory = new File(App.getContext().getExternalCacheDir(), "responses");
        File cacheFile = new File(App.getContext().getCacheDir().getAbsolutePath(), "HttpCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);//缓存文件为10MB

//        设置缓存 10M
//        int cacheSize = 10 * 1024 * 1024;
//        Cache cache = new Cache(httpCacheDirectory, cacheSize);
//        //日志拦截器
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                Logger.t("http").e(message);
//            }
//        });
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

//        ClearableCookieJar cookieJar =
//                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getContext()));
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(paramsInterceptor)
//                .addInterceptor(respomseInterceptor)
//                .addInterceptor(baseInterceptor)
                .addInterceptor(rewriteCacheControlInterceptor)
//                .addNetworkInterceptor(rewriteCacheControlInterceptor)
//                .cookieJar(new JavaNetCookieJar(cookieManager))//设置持续化cookie
//                .cookieJar(cookieJar)//设置持续化cookie
//                .addInterceptor(logging)    //打印日志
                .retryOnConnectionFailure(true)//失败重连
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .cache(cache)   //缓存
                .build();
        return mOkHttpClient;
    }

    /**
     * 不用MVP,直接网络请求
     *
     * @param observable HttpManager.getApi().xxx();
     * @param observer   new BaseObserver<HomeIndexBean>(this)
     */
    public static void toRequst(Observable<?> observable, BaseObserver observer) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
