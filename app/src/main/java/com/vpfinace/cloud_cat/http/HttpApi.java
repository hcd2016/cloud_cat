package com.vpfinace.cloud_cat.http;


/**
 *
 */
public interface HttpApi {
//    /**
//     * {
//     * 	"page": 1,
//     * 	"rows": 3
//     * }
//     * 商城列表
//     * @param body
//     * @return
//     */
//    @POST("api/apis/product/mallProductList")
//    Observable<BaseResponse<MailProductBean>> getProductList(@Body RequestBody body);
//
//    /**
//     * 获取验证码
//     * @param body  请求示例参数：
//     * {
//     * "phone": "18611893708",
//     * "type": 3
//     * }
//     *        type=0.身份验证;
//     *      * type=1.商城登录验证码;
//     *      * type=2.用户信息变更验证码;
//     *      * type=3.用户注册验证码;
//     *      * type=4.修改密码验证码;
//     *      * type=5.修改钱包地址验证码;
//     *      * type=6.登录异常验证码
//     *              7.付款提醒， 8.启动机器验证码
//     * @return
//     */
//    @POST("api/apis/user/mallSendValidCode")
//    Observable<BaseResponse<JSONObject>> getValidCode(@Body RequestBody body);
//
//    /**
//     * 注册
//     * {
//     * "nickName": "xiaoli",
//     * "password": "123456",
//     * "phone": "18611893708",
//     * "validCode": "123456"
//     * }
//     * @return
//     */
//    @POST("api/apis/user/mallUserReg")
//    Observable<BaseResponse<User>> register(@Body RequestBody body);
//
//    /**
//     * 账号密码登录
//     * "phone": "18611893708",
//     * "password": "123456"
//     * @param body
//     * @return
//     */
//    @POST("api/apis//user/mallPhonePwdLogin")
//    Observable<BaseResponse<LoginBean>> loginFromPwd(@Body RequestBody body);
//
//    /**
//     * 手机+验证码登录
//     * {
//     * "phone": "18611893708",
//     * "validCode": "923046"
//     * }
//     * @param body
//     * @return
//     */
//    @POST("api/apis//user/mallPhoneLogin")
//    Observable<BaseResponse<LoginBean>> loginFromCode(@Body RequestBody body);
//
//    /**
//     * 忘记密码 重置密码
//     *
//     * 请求示例参数：
//     * {
//     * "phone": "18611893708",
//     * "password": "123456",
//     * "validCode": "923046"
//     * }
//     * @param body
//     * @return
//     */
//    @POST("api/apis/user/mallResetPwd")
//    Observable<BaseResponse<User>> resetPwd(@Body RequestBody body);
//
//    /**
//     * BTC账户余额
//     * {
//     * 	"id": 1
//     * }
//     * @param body
//     * @return
//     */
//    @POST("api/apis/user/queryUserBtcPayStati")
//    Observable<BaseResponse<BTCBalanceBean>> getBtcBalance(@Body RequestBody body);
//
//    /**
//     * 到帐记录
//     * {
//     * 	"id": 1,
//     * 	"page": 1,
//     * 	"rows": 10
//     * }
//     * @param body
//     * @return
//     */
//    @POST("api/apis/user/queryUserBtcPayList")
//    Observable<BaseResponse<AccountRecordListBean>> getAccountRecordList(@Body RequestBody body);
//
//
//    /**
//     * 购买记录
//     * {
//     * "user_id": 1,
//     * "type": 2,
//     * "page": 1,
//     * "rows": 3
//     * }
//     * @param body
//     * @return
//     */
//    @POST("api/apis/buy/querUserBuyRecod")
//    Observable<BaseResponse<PurchaseRecordsBean>> getPurchaseRecordList(@Body RequestBody body);
//
//    /**
//     * 取消订单
//     * @param body
//     * @return
//     */
//    @POST("api/apis/buy/delOrder")
//    Observable<BaseResponse<Object>> delOrder(@Body RequestBody body);
//
//    /**
//     * 产品详情
//     *{
//     *    	"id": 1
//     * }
//     * @param body
//     * @return
//     */
//    @POST("api/apis/product/mallProductDetail")
//    Observable<BaseResponse<ProductDetailBean>> getProductDetail(@Body RequestBody body);
//
//    /**
//     * {
//     * 	"model": "蜂鸟矿机 H7 pro",
//     * 	"buy_count": "1",
//     * 	"pay_amount": "11420.00",
//     * 	"type":"1",
//     * 	"flag_type": "1",
//     * 	"user_id": "1",
//     * 	"electric_charge": "860.00",
//     * 	"miner_total_price":"10560.00",
//     * 	"product_ids": [1]
//     * }
//     * 添加生成订单
//     * @param body
//     * @return
//     */
//    @POST("api/apis/buy/addOrder")
//    Observable<BaseResponse<OrderBean>> addOrder(@Body RequestBody body);
//
//    /**
//     * 上传凭证图片
//     * @return
//     */
//    @Multipart
//    @POST("api/FileUpload/uploadAppPsn")
//    Observable<BaseResponse<String>> uploadEleImg(@Part("file\";filename=\"ele.jpg") RequestBody file);
//
//    /**
//     * 支付确认
//     * @param orderId
//     * @param imgUrl
//     * @return
//     */
//    @GET("api/apis/buy/auditOrder")
//    Observable<BaseResponse<JSONObject>> payCommit(@Query("orderId") int orderId,
//                                                   @Query("imgUrl") String imgUrl);
//
//    /**
//     * 用户矿机信息
//     * {
//     *   "user_id": 1
//     * }
//     * @param body
//     * @return
//     */
//    @POST("api/apis/user/queryUserMinerStati")
//    Observable<BaseResponse<MillInfoBean>> getUserMillInfo(@Body RequestBody body);
//
//    /**
//     * "user_id": 1,
//     * "type": 2,
//     * "page": 1,
//     * "rows": 3
//     * 矿机列表
//     * @param body
//     * @return
//     */
//    @POST("api/apis/user/queryUserMiner")
//    Observable<BaseResponse<UserMillListBean>> getUserMillList(@Body RequestBody body);
//
//    /**
//     * 托管获取电费总额和剩余天数
//     * {
//     *     "id": 1
//     *  }
//     * @param body
//     * @return
//     */
//    @POST("api/apis/user/userPowerChargeDays")
//    Observable<BaseResponse<JSONObject>> getTotalPowerAccount(@Body RequestBody body);
//
//    /**
//     * 试用转购买添加订单
//     * user_id: "31"
//     * pay_amount: "9898.56"
//     * workerIds: ",9217429715178237731"
//     * model: "蜂鸟矿机H7 Pro 48T"
//     * buy_count: 1
//     * flag_type: "1"
//     * electric_charge: "898.56"
//     * receiver_name: ""
//     * receiver_addr: ""
//     * receiver_phone: ""
//     * discount_price: 1000
//     * under_price: 10000
//     * @param body
//     * @return
//     */
//    @POST("api/apis/buy/tryUseToAddOrder")
//    Observable<BaseResponse<Integer>> addTryOrder(@Body RequestBody body);
//
//    /**
//     * 增加试用期
//     * user_id: "31"
//     * pay_amount: "898.56"
//     * workerIds: ",9217429715178237731"
//     * model: "蜂鸟矿机H7 Pro 48T"
//     * month_num: 1
//     * buy_count: 1
//     * @param body
//     * @return
//     */
//    @POST("api/apis/buy/addTryTime")
//    Observable<BaseResponse<Integer>> addTryTimeOrder(@Body RequestBody body);
//
//    /**
//     * 启用矿机
//     * @return
//     */
//    @POST("api/apis/user/setRunMiner")
//    Observable<BaseResponse<String>> setRunMiner();
//
//    /**
//     * 确认提货
//     * @return
//     */
//    @POST("api/apis/buy/confirmPickUpGoods")
//    Observable<BaseResponse<JSONObject>> confimTh(@Body RequestBody body);
//
//    /**
//     * 查询用户信息
//     * @return
//     */
//    @POST("api/apis/user/mallQueryUser")
//    Observable<BaseResponse<User>> queryUser(@Body RequestBody body);
//
//    /**
//     * 修改绑定手机号
//     * @return
//     */
//    @POST("api/apis/user/mallUserUpNeedCheck")
//    Observable<BaseResponse<LoginBean>> changeBindPhone(@Body RequestBody body);
//
//    /**
//     * 修改绑定邮箱
//     * @param email
//     * @return
//     */
//    @GET("api/apis/user/mallSendValidCodeByEmail")
//    Observable<BaseResponse<Object>> changeBindEmail(@Query("email") String email);
//
//    /**
//     * 身份证正面上传
//     * @param file
//     * @return
//     */
//    @Multipart
//    @POST("api/FileUpload/uploadIdCard")
//    Observable<BaseResponse<String>> uploadIdCard(@Part("file\";filename=\"idCard.jpg") RequestBody file);
//
//    /**
//     * 身份证背面上传
//     * @param file
//     * @return
//     */
//    @Multipart
//    @POST("api/FileUpload/uploadIdCardBack")
//    Observable<BaseResponse<String>> uploadIdCardBack(@Part("file\";filename=\"idCardBack.jpg") RequestBody file);
//
//    /**
//     * 身份证上传提交
//     * @return
//     */
//    @POST("api/apis/user/mallUserUp")
//    Observable<BaseResponse<Object>> idCardUploadCommit(@Body RequestBody body);
//
//
//    /**
//     * 查询开票信息
//     * @param body
//     * @return
//     */
//    @POST("api/apis/bill/queryMallUserBill")
//    Observable<BaseResponse<BillInfoBean>> getBillInfo(@Body RequestBody body);
//
//    /**
//     * 添加开票信息
//     * @param body
//     * @return
//     */
//    @POST("api/apis/bill/mallBillAdd")
//    Observable<BaseResponse<BillInfoBean>> addBillInfo(@Body RequestBody body);
//    /**
//     * 修改开票信息
//     * @param body
//     * @return
//     */
//    @POST("api/apis/bill/mallBillUp")
//    Observable<BaseResponse<Object>> updateBillInfo(@Body RequestBody body);
//
//    /**
//     * 历史收益记录
//     * @param body
//     * @return
//     */
//    @POST("api/apis/user/queryUserBtcHistoryList")
//    Observable<BaseResponse<EainingsRecordBean>> queryEarningsList(@Body RequestBody body);
//
//    /**
//     * 提币
//     * @param body
//     * @return
//     */
//    @POST("api/apis/user/drawBtc")
//    Observable<BaseResponse<String>> tbCommit(@Body RequestBody body);
//
//    /**
//     * 提币记录
//     * @param body
//     * @return
//     */
//    @POST("api/apis/user/queryDrawBtcApplyList")
//    Observable<BaseResponse<TbBtcBean>> getTbBtcList(@Body RequestBody body);
//
//    /**
//     * 购买记录订单开发票
//     * @param body
//     * @return
//     */
//    @POST("api/apis/order/orderBillAdd")
//    Observable<BaseResponse<OrderBillBean>> commitOrderBill(@Body RequestBody body);
}
