package com.example.dreams.mvppractice.proxy;

import com.example.dreams.mvppractice.annotation.PresenterInjection;
import com.example.dreams.mvppractice.base.BasePresenter;
import com.example.dreams.mvppractice.base.BaseView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by likaiyu on 2020/4/19.
 */
public class MvpProxyImpl<V extends BaseView> implements IMvpProxy {

    private V mView;
    private List<BasePresenter> mPresenters;

    public MvpProxyImpl(V view) {
        if (view == null) {
            throw new IllegalArgumentException("argument can not be null!");
        }
        this.mView = view;
        mPresenters = new ArrayList<>();
    }

    @Override
    public void bindAndCreatePresenter() {
        //通过注解获取被标记的成员
        Field[] fields = mView.getClass().getDeclaredFields();
        for (Field field : fields) {
            PresenterInjection presenterInjection = field.getAnnotation(PresenterInjection.class);
            if (presenterInjection != null) {
                Class<? extends BasePresenter> presenterClazz = null;
                Class<?> type = field.getType();
                if (BasePresenter.class.isAssignableFrom(type.getSuperclass())) {
                    try {
                        presenterClazz = (Class<? extends BasePresenter>) type;
                    } catch (Exception e) {
                        throw new RuntimeException("Not support inject presenter type " + field.getType().getName());
                    }
                } else {
                    throw new RuntimeException("Not support inject presenter type " + type.getSuperclass().getName());
                }

                try {
                    BasePresenter basePresenter = presenterClazz.newInstance();
                    basePresenter.attach(mView);
                    field.setAccessible(true);
                    field.set(mView, basePresenter);
                    mPresenters.add(basePresenter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void unbindPresenter() {
        for (BasePresenter mPresenter : mPresenters) {
            mPresenter.detach();
        }
        mView = null;
    }
}
