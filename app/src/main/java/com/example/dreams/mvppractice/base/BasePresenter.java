package com.example.dreams.mvppractice.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * Created by likaiyu on 2020/4/18.
 */
public class BasePresenter<V extends BaseView, M extends BaseModel> {
    //如果要用建议使用SoftReference 会在OOM之前回收
    //因为WeakReference一GC就没了
//    private WeakReference<V> mViewReference;
    private V mView;
    private V mProxyView;
    private M mModel;

    @SuppressWarnings("unchecked")
    public void attach(V view) {
        this.mView = view;

        // 用代理对象
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 执行这个方法 ，调用的是被代理的对象
                if(mView == null) {
                    return null;
                }
                // 没解绑执行的是原始被代理 View 的方法
                return method.invoke(mView,args);
            }
        });
        this.mModel = createModel();
    }

    private M createModel() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        try {
            return (M) ((Class) actualTypeArguments[1]).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void detach() {
        this.mView = null;
//        this.mProxyView = null;
    }


    public V getView() {
        return mProxyView;
    }

    public M getModel() {
        return mModel;
    }

}
