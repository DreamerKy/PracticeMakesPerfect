package com.example.dreams.sixprinciple;

import android.content.Context;

import java.util.Map;

/**
 * Created by likaiyu on 2020/4/7.
 */
public interface IHttpRequest {
    <T> void get(Context context, String url, Map<String, Object> params, HttpCallBack<T> callback, boolean cache);
}
