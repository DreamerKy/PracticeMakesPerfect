package com.example.dreams.sixprinciple;

/**
 * Created by likaiyu on 2020/4/7.
 */
public abstract class HttpCallBack<T> {
    public abstract void onSuccess(T result);

    public abstract void onFailure(Exception e);
}
