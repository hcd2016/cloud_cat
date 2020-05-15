package com.vpfinace.cloud_cat.http;


import com.vpfinace.cloud_cat.base.BaseResponse;
import com.vpfinace.cloud_cat.bean.CatBean;
import com.vpfinace.cloud_cat.bean.CatPicBean;
import com.vpfinace.cloud_cat.bean.CatShopBean;
import com.vpfinace.cloud_cat.bean.CatteryBean;
import com.vpfinace.cloud_cat.bean.GetEarningsBean;
import com.vpfinace.cloud_cat.bean.HomeBean;
import com.vpfinace.cloud_cat.bean.LoginBean;
import com.vpfinace.cloud_cat.bean.MergeBean;
import com.vpfinace.cloud_cat.bean.MsgBean;
import com.vpfinace.cloud_cat.bean.MyInviteCodeBean;
import com.vpfinace.cloud_cat.bean.TopBean;
import com.vpfinace.cloud_cat.bean.User;
import com.vpfinace.cloud_cat.bean.UserCenter;
import com.vpfinace.cloud_cat.bean.WalletBean;
import com.vpfinace.cloud_cat.bean.WalletRecordBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
//    Observable<BaseResponse<TestBean>> getCatList();

    /**
     * 合成,拖拽
     */
    @FormUrlEncoded
    @POST("api/front/action/merge")
    Observable<BaseResponse<MergeBean>> mergeCat(@Field("storageIdFrom") int storageIdFrom, @Field("storageIdTo") int storageIdTo);

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
    Observable<BaseResponse<TopBean>> getTopList();
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
    @FormUrlEncoded
    @POST("api/front/action/dilation")
    Observable<BaseResponse<Object>> storeDilation(@Field("coinNum") int coinNum);

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


    /**
     * 我的邀请码(扩散数量,列表,邀请,列表等)
     * @return
     */
    @POST("api/front/client/inviteCode")
    Observable<BaseResponse<MyInviteCodeBean>> getInviteCode();

    /**
     * 实名认证
     * @return
     */
    @FormUrlEncoded
    @POST("api/front/client/auth")
    Observable<BaseResponse<Object>> auth(@Field("realname") String realname);

    /**
     * 消息列表
     * @return
     */
    @POST("api/front/client/userMsg")
    Observable<BaseResponse<List<MsgBean>>> getMsgList();

    /**
     * 修改头像上传
     * @param file
     * @return
     */
    @Multipart
    @POST("api/front/client/editHeadImg")
    Observable<BaseResponse<Object>> uploadHeaderImg(@Part("file\";filename=\"header.jpg") RequestBody file);

    /**
     * 修改昵称
     * @return
     */
    @FormUrlEncoded
    @POST("api/front/client/editnickname")
    Observable<BaseResponse<Object>> editNickName(@Field("nickname") String nickname);

    /**
     * 绑定(更换)手机号
     * @return
     */
    @FormUrlEncoded
    @POST("api/front/client/bindPhone")
    Observable<BaseResponse<Object>> bindPhone(@Field("phone") String phone,@Field("code") String code);

    /**
     * 隐私设置
     * @return
     */
    @FormUrlEncoded
    @POST("api/front/client/privacySetting")
    Observable<BaseResponse<Object>> privacySetting(@Field("friendVisible") String friendVisible,@Field("inviterVisible") String inviterVisible);


    /**
     * 社交信息提交
     * @return
     */
    @FormUrlEncoded
    @POST("api/front/client/editSocial")
    Observable<BaseResponse<Object>> socialInfoCommit(@Field("qq") String qq,@Field("wechat") String wechat);

    /**
     * 猫咪图鉴
     * @return
     */
    @POST("api/front/action/pictoria")
    Observable<BaseResponse<List<CatPicBean>>> getCatPicList();

    /**
     * 领取收益
     * action=1获取剩余时间,action=2领取金币
     */
    @FormUrlEncoded
    @POST("api/front/action/drawearning")
    Observable<BaseResponse<GetEarningsBean>> getEarnings(@Field("action") String action);

    /**
     * 猫舍信息
     */
    @POST("api/front/client/cattery")
    Observable<BaseResponse<CatteryBean>> getCatteryInfo();

    /**
     * 资产记录
     */
    @POST("api/front/client/capitalrecord")
    Observable<BaseResponse<WalletRecordBean>> getWalletRecordList();

    /**
     * 我的钱包
     */
    @POST("api/front/client/wallet")
    Observable<BaseResponse<WalletBean>> getWallet();

    /**
     * 提现
     */
    @FormUrlEncoded
    @POST("wx/withdraw/dotransfer")
    Observable<BaseResponse<Object>> wxWithDraw(@Field("money") String money);

    /**
     * 用户中心
     */
    @POST("api/front/client/ucenter")
    Observable<BaseResponse<UserCenter>> getUserCenter();

    /**
     * 红包领取提交
     */
    @FormUrlEncoded
    @POST("api/front/client/drawredpack")
    Observable<BaseResponse<Object>> redPackOpen(@Field("redPackId") String redPackId);
}
