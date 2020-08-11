package com.example.dreams.mvppractice.proxy;

import com.example.dreams.mvppractice.base.BaseView;

/**
 * Created by likaiyu on 2020/4/19.
 */
public class MvpActivityProxyImpl<V extends BaseView> extends MvpProxyImpl<V> implements IActivityMvpProxy{
    public MvpActivityProxyImpl(V view) {
        super(view);
    }

    @Override
    public void someActivityMethod() {

    }
}
