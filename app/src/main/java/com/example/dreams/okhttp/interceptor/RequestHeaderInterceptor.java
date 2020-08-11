package com.example.dreams.okhttp.interceptor;

import com.example.dreams.okhttp.Interceptor;
import com.example.dreams.okhttp.Request;
import com.example.dreams.okhttp.RequestBody;
import com.example.dreams.okhttp.Response;
import com.example.dreams.okhttp.SocketRequestServer;
import com.example.dreams.okhttp.chain.Chain;
import com.example.dreams.okhttp.chain.RealChain;

import java.io.IOException;
import java.util.Map;

/**
 * Created by likaiyu on 2020/1/23.
 */
public class RequestHeaderInterceptor implements Interceptor {
    @Override
    public Response doNext(Chain chain) throws IOException {

        System.out.println("RequestHerderInterceptor真正工作");

        RealChain realChain = (RealChain) chain;
        Request request = realChain.getRequest();

        Map<String,String> mHeaderList =  request.getmHeaderList();
        mHeaderList.put("Host",new SocketRequestServer().getHost(request));
        if("POST".equalsIgnoreCase(request.getRequestMethod())){
            //请求体数据
            /**
             * Content-Length: 48
             * Content-Type: application/x-www-form-urlencoded
             */
            mHeaderList.put("Content-Length",request.getRequestBody().getBody().length()+"");
            mHeaderList.put("Content-Type", RequestBody.TYPE);
        }
        Response response = realChain.getResponse(request);
        System.out.println("RequestHeaderInterceptor 拿到Response返回");
        return response;
    }
}
