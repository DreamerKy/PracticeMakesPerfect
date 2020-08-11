package com.example.dreams.net;

/**
 * Created by likaiyu on 2019/10/28.
 */
public interface ResponseCallback<T> {

    void onSuccess(T responseBean);
    void onFailure();

}
