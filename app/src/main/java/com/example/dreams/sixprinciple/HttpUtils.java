package com.example.dreams.sixprinciple;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by likaiyu on 2020/4/7.
 */
public class HttpUtils {

    private final Context mContext;
    private final HashMap<String, Object> mParams;
    private IHttpRequest mHttpRequest;
    private static IHttpRequest mInitHttpRequest;
    private String mUrl;
    private boolean cache;

    private HttpUtils(Context context) {
        mHttpRequest = mInitHttpRequest != null ? mInitHttpRequest : new OkHttpRequest();
        mParams = new HashMap<>();
        cache = false;
        this.mContext = context;
        PreferencesUtils.getInstance().init(context);
    }

    public static HttpUtils with(Context context) {
        return new HttpUtils(context);
    }

    public static void initHttpRequest(IHttpRequest httpRequest) {
        mInitHttpRequest = httpRequest;
    }

    public HttpUtils httpRequest(IHttpRequest httpRequest){
        mHttpRequest = httpRequest;
        return this;
    }

    public HttpUtils params(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public HttpUtils utl(String url) {
        this.mUrl = url;
        return this;
    }

    public HttpUtils chche(boolean cache) {
        this.cache = cache;
        return this;
    }

    public void request() {
        request(null);
    }

    public <T> void request(final HttpCallBack<T> callBack) {
        mHttpRequest.get(mContext, mUrl, mParams, callBack, cache);
    }


}
