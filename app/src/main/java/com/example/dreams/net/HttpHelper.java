package com.example.dreams.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by likaiyu on 2019/10/24.
 * 房产公司销售
 */
public class HttpHelper {

    private static HttpHelper instance;

    private HttpHelper() {
    }

    public static HttpHelper getInstance() {
        if (instance == null) {
            synchronized (HttpHelper.class) {
                if (instance == null) {
                    instance = new HttpHelper();
                }
            }
        }
        return instance;
    }

    private static IHttpProcessor mHttpProcessor;

    public static void init(IHttpProcessor iHttpProcessor) {
        mHttpProcessor = iHttpProcessor;
    }

    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        String pjurl = appParams(url, params);
        mHttpProcessor.post(pjurl, params, callBack);
    }

    private String appParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        StringBuilder stringBuilder = new StringBuilder(url);
        if (stringBuilder.indexOf("?") <= 0) {
            stringBuilder.append("?");
        } else {
            if (stringBuilder.toString().endsWith("?")) {
                stringBuilder.append("&");
            }
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            stringBuilder.append("&").append(entry.getKey()).append("=").append(neEncode(entry.getValue().toString()));
        }
        return stringBuilder.toString();
    }

    private String neEncode(String value) {
        try {
            return URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("exception");
        }
    }
}
