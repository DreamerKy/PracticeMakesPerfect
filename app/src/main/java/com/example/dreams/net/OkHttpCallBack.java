package com.example.dreams.net;

/**
 * Created by likaiyu on 2020/2/4.
 */
public class OkHttpCallBack<Result,T> extends HttpCallback<Result> {
    private T t;
    @Override
    public void onSuccess(Result object) {
        object.getClass().getSimpleName();
    }

    public T getT(Result object){
        return t;
    }
}
