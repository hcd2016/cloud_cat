package com.vpfinace.cloud_cat.base;

import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver<T> extends DisposableObserver<BaseResponse<T>> {
    protected BaseView view;
    protected boolean isShowDialog;

    public BaseObserver(BaseView view) {
        this.view = view;
    }

    public BaseObserver(BaseView view, boolean isShowDialog) {
        this.view = view;
        this.isShowDialog = isShowDialog;
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        //在这边对 基础数据 进行统一处理  举个例子：
        if (response.getCode() == 0 || response.getCode() == 200) {
            _onNext(response.getData());
        } else if (response.getCode() == 401) {
//            ToastUtils.showShort(MyUtils.getRecouseString(R.string.dlygq_qcxdl));
//            UserUtil.loginOut();
//            Intent intent = new Intent(App.getContext(), LoginActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            App.getContext().startActivity(intent);
        } else {
            _onError(response.getMsg());
        }
        view.FinishLoading();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isShowDialog) {
            view.showLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        if(isShowDialog) {
            view.FinishLoading();
        }
        e.printStackTrace();
        if (!NetworkUtils.isConnected() || !NetworkUtils.isAvailableByPing()) {
            _onError("网络不可用");
        } else if (e instanceof retrofit2.HttpException) {
            retrofit2.HttpException exception = (retrofit2.HttpException) e;
//            if (exception.code() == 401) {
//                ToastUtils.showShort(MyUtils.getRecouseString(R.string.dlygq_qcxdl));
//                UserUtil.loginOut();
//                Intent intent = new Intent(App.getContext(), LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                App.getContext().startActivity(intent);
//            } else
                if (exception.code() == 500) {
                _onError("服务器异常，请稍后重试");
            } else {
                _onError("连接服务器失败，请稍后再试");
            }
        } else if (e instanceof SocketTimeoutException) {
            _onError("连接服务器超时，请稍后再试");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof IllegalStateException) {
            _onError("解析异常");
        } else {
            _onError("连接服务器失败，请稍后再试");
        }
    }

    @Override
    public void onComplete() {
        if(isShowDialog) {
            view.FinishLoading();
        }
    }

    public abstract void _onNext(T t);

    public abstract void _onError(String message);

}
