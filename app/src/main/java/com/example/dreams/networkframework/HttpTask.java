package com.example.dreams.networkframework;

import java.io.UnsupportedEncodingException;

/**
 * Created by likaiyu on 2019/10/28.
 */
public class HttpTask<T> implements Runnable {
    private IHttpRequest request;

    HttpTask(String url, T requestData, IHttpRequest httpRequest, CallBackListener listener) {
        this.request = httpRequest;
        httpRequest.setUrl(url);
        String content = requestData.toString();
        try {
            httpRequest.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpRequest.setListener(listener);
    }

    @Override
    public void run() {
        this.request.execute();
    }
}
