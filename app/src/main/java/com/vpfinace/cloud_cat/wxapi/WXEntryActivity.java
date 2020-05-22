package com.vpfinace.cloud_cat.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.vpfinace.cloud_cat.MainActivity;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.app.App;
import com.vpfinace.cloud_cat.base.BaseActivity;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.bean.LoginBean;
import com.vpfinace.cloud_cat.bean.WxTokenBean;
import com.vpfinace.cloud_cat.global.SpContant;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.utils.SpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private static long dataShareId;
    private IWXAPI api;

    public static void setTypeAndDataShareId(long srcId) {
        dataShareId = srcId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.wx_entry_activity;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        initData();
    }

    private void initData() {
        api = App.getWxApi();
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        String result = null;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK: {
                switch (resp.getType()) {
                    case ConstantsAPI.COMMAND_SENDAUTH: //登录成功的回调
                        //拿到了微信返回的code,立马再去请求access_token
                        String code = ((SendAuth.Resp) resp).code;
                        requestToken(code);
                        break;
                    case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX://分享成功的回调
                        result = "分享成功";
                        break;
                }
            }
            break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                switch (resp.getType()) {
                    case ConstantsAPI.COMMAND_SENDAUTH: //登录取消的回调;
                        break;
                    case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX://分享取消的回调
                        result = "分享取消";
                        break;
                }
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "分享被拒绝";
                break;
            default:
                switch (resp.getType()) {//返回
                    case ConstantsAPI.COMMAND_SENDAUTH://登录失败的回调
                        break;
                    case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX://分享失败的回调
                        result = "分享失败";
                        break;
                    default:
                        break;
                }
                break;
        }

//        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        this.finish();
    }

    //获取token
    public void requestToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + App.wxAppId + "&secret=" + App.wxAppSecret + "&" + "code=" + code + "&" + "grant_type=authorization_code";
        Request request = new Request.Builder().url(url).build();
        HttpManager.getInstance().createOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.getMessage();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {//授权成功
                String body = response.body().string();
                WxTokenBean wxTokenBean = GsonUtils.fromJson(body, WxTokenBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestGetWxInfo(wxTokenBean);
//                                        requestWxLogin(wxTokenBean);
                    }
                });
            }
        });
    }

    //获取微信用户信息
    public void requestGetWxInfo(WxTokenBean wxTokenBean) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + wxTokenBean.getAccess_token() + "&openid=" + wxTokenBean.getOpenid();
        Request request = new Request.Builder().url(url).build();
        HttpManager.getInstance().createOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String body = response.body().string();
                            requestWxLogin(body);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    //微信登录
    private void requestWxLogin(String json) {
        HttpManager.toRequst(HttpManager.getApi().wxLogin(json), new BaseObserver<LoginBean>(this) {
            @Override
            public void _onNext(LoginBean loginBean) {
                SPUtils.getInstance().put(SpContant.SP_SESSION_ID, loginBean.getSessionId());
                SpUtil.saveBean2Sp(WXEntryActivity.this, loginBean.getUser(), SpContant.SP_USER);
                ToastUtils.showShort("登录成功");
                startActivity(MainActivity.class);
                finish();
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

}
