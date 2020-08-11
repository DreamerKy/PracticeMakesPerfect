package com.example.dreams.networkframework;

import com.example.dreams.net.ResponseCallback;

/**
 * Created by likaiyu on 2019/10/28.
 */
public class HttpUtils {
    public static <M, T> void sendJsonRequest(String url, M requestData, ResponseCallback<T> responseCallback) {
        IHttpRequest httpRequest = new JsonHttpRequest();
        CallBackListener callBackListener = new JsonCallBackListener<>(responseCallback);
        HttpTask httpTask = new HttpTask(url, requestData, httpRequest, callBackListener);
        ThreadPoolManager.getInstance().addTask(httpTask);
    }

    public static <M, T> void sendXXXRequest(String url, M requestData, ResponseCallback<T> responseCallback) {
        IHttpRequest httpRequest = new JsonHttpRequest();
        CallBackListener callBackListener = new JsonCallBackListener<>(responseCallback);
        HttpTask httpTask = new HttpTask(url, requestData, httpRequest, callBackListener);
        ThreadPoolManager.getInstance().addTask(httpTask);
    }
}
