package com.example.dreams.okhttp;

import com.example.dreams.okhttp.chain.Chain;

import java.io.IOException;

/**
 * Created by likaiyu on 2020/1/22.
 */
public interface Interceptor {

    Response doNext(Chain chain) throws IOException;

}
