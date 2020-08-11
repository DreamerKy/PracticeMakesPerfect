package com.example.dreams.okhttp;

/**
 * Created by likaiyu on 2020/1/21.
 */
public interface Call {
    void enqueue(CallBack responseCallBack);
}
