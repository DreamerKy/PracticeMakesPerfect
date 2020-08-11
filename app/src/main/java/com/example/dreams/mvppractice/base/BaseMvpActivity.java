package com.example.dreams.mvppractice.base;

import android.os.Bundle;

import com.example.dreams.mvppractice.proxy.IActivityMvpProxy;
import com.example.dreams.mvppractice.proxy.MvpActivityProxyImpl;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Created by likaiyu on 2020/4/18.
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

//    private P mPresenter;
    private IActivityMvpProxy mMvpProxy;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
//        mPresenter = createPresenter();
//        mPresenter.attach(this);
        mMvpProxy = createMvpProxy();
        mMvpProxy.bindAndCreatePresenter();
        initView();
        initData();
    }

    private IActivityMvpProxy createMvpProxy() {
        if (mMvpProxy == null) {
            mMvpProxy = new MvpActivityProxyImpl<>(this);
        }
        return mMvpProxy;
    }

    protected abstract void initData();

    protected abstract void initView();

//    protected abstract P createPresenter();

    protected abstract void setContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mPresenter.detach();
        mMvpProxy.unbindPresenter();
    }

//    public P getPresenter(){
//        return mPresenter;
//    }
}
