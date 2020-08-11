package com.example.dreams.net;

import java.util.Map;

/**
 * Created by likaiyu on 2019/10/24.
 * 中介公司
 */
public interface IHttpProcessor {

    void post(String url, Map<String, Object> params, ICallBack callBack);

}
