package com.example.dreams.okhttp_upload_process_listener_and_cache.intercept;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by likaiyu on 2020/4/26.
 * 设置缓存策略，此拦截器必须放在所有拦截器之后（CallServerInterceptor之前）
 * 在请求回来后立即设置，等请求到达CacheInterceptor时就会去缓存，
 * 下次请求如果在时间范围之内，就会直接拿缓存而不走网络
 */
public class CacheResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        //指定过期时间为30秒
        response.newBuilder()
                .removeHeader("Cache-Control")
                .removeHeader("Pragma")
                .addHeader("Cache-Control", "max-age=" + 30)
                .build();

        return response;
    }
}
