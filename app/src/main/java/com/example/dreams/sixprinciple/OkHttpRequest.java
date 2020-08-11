package com.example.dreams.sixprinciple;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by likaiyu on 2020/4/7.
 */
class OkHttpRequest implements IHttpRequest {
    private SpHttpCache mHttpCache;

    public OkHttpRequest() {
        mHttpCache = new SpHttpCache();
    }

    @Override
    public <T> void get(Context context, String url, Map<String, Object> params, final HttpCallBack<T> callback, final boolean cache) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        // 公共参数
        params.put("app_name", "joke_essay");
        params.put("version_name", "5.7.0");
        params.put("ac", "wifi");
        params.put("device_id", "30036118478");

        final String jointUrl = Utils.jointParams(url, params);
        final String cacheData = mHttpCache.getCache(jointUrl);
        if (cache && !TextUtils.isEmpty(cacheData)) {
            Gson gson = new Gson();
            T result = (T) gson.fromJson(cacheData, Utils.analysisClazzInfo(callback));
            callback.onSuccess(result);
        }
        Request.Builder requestBuilder = new Request.Builder().url(jointUrl).tag(context);
        //可以省略，默认是GET请求
        Request request = requestBuilder.build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resultJson = response.body().string();
                if (cache && cacheData.endsWith(resultJson)) {
                    return;
                }

                Gson gson = new Gson();
                T result = (T) gson.fromJson(resultJson, Utils.analysisClazzInfo(callback));
                callback.onSuccess(result);
                if (cache) {
                    mHttpCache.saveCache(jointUrl, resultJson);
                }
            }
        });

    }
}
