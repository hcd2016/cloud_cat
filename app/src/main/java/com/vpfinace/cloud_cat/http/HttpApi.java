package com.vpfinace.cloud_cat.http;


import com.vpfinace.cloud_cat.base.BaseResponse;
import com.vpfinace.cloud_cat.bean.CatBean;
import com.vpfinace.cloud_cat.bean.CatShopBean;
import com.vpfinace.cloud_cat.bean.HomeBean;
import com.vpfinace.cloud_cat.bean.LoginBean;
import com.vpfinace.cloud_cat.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 *
 */
public interface HttpApi {

    /**
     * 短信验证码
     */
    @FormUrlEncoded
    @POST("api/verify/zxySendSms")
    Observable<BaseResponse<Object>> getVerifyCode(@Field("phone") String phone);

    /**
     * 手机登录
     * @param code
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("api/front/client/login")
    Observable<BaseResponse<LoginBean>> login(@Field("code") String code, @Field("phone") String phone);

    /**
     * 首页猫列表
     * @return
     */
    @POST("api/front/action/list")
    Observable<BaseResponse<HomeBean>> getCatList();

    /**
     * 合成,拖拽
     */
    @FormUrlEncoded
    @POST("api/front/action/merge")
    Observable<BaseResponse<Object>> mergeCat(@Field("storageIdFrom") int storageIdFrom,@Field("storageIdTo") int storageIdTo);

    /**
     * 退出登录
     */
    @POST("api/front/client/logout")
    Observable<BaseResponse<Object>> loginOut();

    /**
     * 仓库列表
     */
    @POST("api/front/action/storage")
    Observable<BaseResponse<List<CatBean>>> getStoreList();

    /**
     * 排行榜
     */
    @POST("api/front/action/rank")
    Observable<BaseResponse<Object>> getTopList();
    /**
     * 商店可购买列表
     */
    @POST("api/front/action/allcatlist")
    Observable<BaseResponse<List<CatShopBean>>> getShopList();

    /**
     * 放入仓库
     */
    @FormUrlEncoded
    @POST("api/front/action/putin")
    Observable<BaseResponse<Object>> putInStore(@Field("catId") int catId,@Field("storageId") int storageId);

    /**
     * 回收猫咪
     */
    @FormUrlEncoded
    @POST("api/front/action/gc")
    Observable<BaseResponse<Object>> gcCat(@Field("catId") int catId,@Field("storageId") int storageId);

    /**
     * 取出猫咪
     */
    @FormUrlEncoded
    @POST("api/front/action/getout")
    Observable<BaseResponse<Object>> getCatFromStore(@Field("catId") int catId);

    /**
     * 获取用户信息
     * @return
     */
    @POST("api/front/client/userinfo")
    Observable<BaseResponse<User>> getUserInfo();

    /**
     * 仓库扩容
     * @return
     */
    @POST("api/front/action/dilation")
    Observable<BaseResponse<Object>> storeDilation();

    /**
     * 购买猫咪
     * @return
     */
    @FormUrlEncoded
    @POST("api/front/action/buy")
    Observable<BaseResponse<Object>> buyCat(@Field("catId") int catId);

    /**
     * 金币同步提交
     * @return
     */
    @FormUrlEncoded
    @POST("api/front/action/coinsync")
    Observable<BaseResponse<Object>> amountSync(@Field("coinNum") long coinNum);


}
