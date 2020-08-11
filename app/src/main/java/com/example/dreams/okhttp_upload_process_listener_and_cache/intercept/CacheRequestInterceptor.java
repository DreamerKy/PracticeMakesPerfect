package com.example.dreams.okhttp_upload_process_listener_and_cache.intercept;


import android.content.Context;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by likaiyu on 2020/4/26.
 * 没有网就是用本地缓存，此拦截器应该放在所有拦截器之前
 */
public class CacheRequestInterceptor implements Interceptor {
    private Context context;

    public CacheRequestInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!hasNetWork()) {
            //如果没网，只读缓存
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        return chain.proceed(request);
    }

    private boolean hasNetWork() {
        return false;
    }
}
