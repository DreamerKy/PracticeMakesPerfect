package com.example.dreams.okhttp.chain;

import com.example.dreams.okhttp.Request;
import com.example.dreams.okhttp.Response;

import java.io.IOException;

/**
 * Created by likaiyu on 2020/1/22.
 */
public interface Chain {

    Request getRequest();

    Response getResponse(Request request) throws IOException;

}
