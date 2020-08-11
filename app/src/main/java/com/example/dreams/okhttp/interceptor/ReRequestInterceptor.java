package com.example.dreams.okhttp.interceptor;

import android.util.Log;

import com.example.dreams.okhttp.Interceptor;
import com.example.dreams.okhttp.OkHttpClient;
import com.example.dreams.okhttp.RealCall;
import com.example.dreams.okhttp.Request;
import com.example.dreams.okhttp.Response;
import com.example.dreams.okhttp.chain.Chain;
import com.example.dreams.okhttp.chain.RealChain;

import java.io.IOException;

/**
 * Created by likaiyu on 2020/1/22.
 */
public class ReRequestInterceptor implements Interceptor  {
    private final String TAG = ReRequestInterceptor.class.getSimpleName();
    @Override
    public Response doNext(Chain chain) throws IOException {

        System.out.println("ReRequestInterceptor真正工作");

        RealChain realChain = (RealChain) chain;
        RealCall call = realChain.getCall();
        Request request = realChain.getRequest();
        OkHttpClient okHttpClient = call.getOkHttpClient();
        IOException exception = null;
        if(okHttpClient.getRecount()!=0){
            for(int i=0;i<okHttpClient.getRecount();i++){
                try {
                    Response response = chain.getResponse(request);
                    System.out.println("ReRequestInterceptor 拿到Response返回");
                    return response;
                }catch (IOException e){
                    exception = e;
                }
            }
        }
        throw exception;
    }
}
