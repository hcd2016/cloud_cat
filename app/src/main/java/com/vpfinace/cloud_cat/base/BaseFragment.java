package com.vpfinace.cloud_cat.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.utils.LoadingUtil;
import com.vpfinace.cloud_cat.utils.TUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;


public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView{
    public T mPresenter;
    public Context mContext;
    public BaseActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),getLayoutId(),null);
        ButterKnife.bind(this,view);
        mContext = getContext();
        mActivity= (BaseActivity) getActivity();
        mPresenter = TUtil.getT(this, 0);
        initPresenter();
        initView();
        return view;
    }


    //获取布局文件
    public abstract int getLayoutId();

    //初始化
    protected void initView() {

    }

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //展示加载框,如有需要子类重写
    @Override
    public void showLoading() {
        LoadingUtil.showLoading(getActivity(),"加载中...");
    }

    //隐藏加载框,如有需要子类重写
    @Override
    public void FinishLoading() {
        LoadingUtil.finishLoading();
    }

    //网络请求失败
    @Override
    public void onError(String msg) {
        ToastUtils.showShort(msg);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
