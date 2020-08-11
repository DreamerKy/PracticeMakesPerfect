package com.example.dreams.httpformupload;

/**
 * Created by likaiyu on 2020/4/25.
 */
public interface Call {
    void enqueue(CallBack callback);

    Response execute();
}
