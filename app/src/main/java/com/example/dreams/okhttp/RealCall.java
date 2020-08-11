package com.example.dreams.okhttp;

import com.example.dreams.okhttp.chain.RealChain;
import com.example.dreams.okhttp.interceptor.ConnectionServiceInterceptor;
import com.example.dreams.okhttp.interceptor.ReRequestInterceptor;
import com.example.dreams.okhttp.interceptor.RequestHeaderInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by likaiyu on 2020/1/21.
 */
public class RealCall implements Call {
    private OkHttpClient okHttpClient;
    private Request request;
    private boolean executed;

    public OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }
    public Request getRequest(){
        return request;
    }
    public boolean isExecuted(){
        return executed;
    }

    public RealCall(OkHttpClient okHttpClient, Request request){
        this.okHttpClient = okHttpClient;
        this.request = request;
    }

    @Override
    public void enqueue(CallBack responseCallBack) {
        //不能重复执行enqueue
        synchronized(this) {
            if (!executed) {
                executed = true;
            }else{
                throw new IllegalStateException("不能被重复执行");
            }

            okHttpClient.dispatcher().enqueue(new AsyncCall(responseCallBack));
        }
    }

    final class AsyncCall implements Runnable{

        private CallBack callBack;

        public AsyncCall(CallBack callBack){
            this.callBack = callBack;
        }

        public Request getRequest(){
            return RealCall.this.request;
        }

        @Override
        public void run() {
            System.out.println("真正执行Runnable代码");
            boolean signalledCallBack = false;
           try {
               Response response = getResponseWithInterceptorChain();
               if (okHttpClient.getCancled()) {
                   signalledCallBack = true;
                   callBack.onFailure(RealCall.this, new IOException("用户取消了 Canceled"));
               } else {
                   signalledCallBack = true;
                   callBack.onResponse(RealCall.this, response);
               }
           }catch (IOException e){
               if(signalledCallBack){
                   System.out.println("用户使用过程中出现错误");
               }else{
                   callBack.onFailure(RealCall.this,new IOException("OKHttp 内部错误"));
               }
           }finally {
               okHttpClient.dispatcher().finish(this);
           }
        }

        private Response getResponseWithInterceptorChain() throws IOException {
            System.out.println("getResponseWithInterceptorChain");
            List<Interceptor> interceptors = new ArrayList<>();
            interceptors.add(new ReRequestInterceptor());
            interceptors.add(new RequestHeaderInterceptor());
            interceptors.add(new ConnectionServiceInterceptor());
            RealChain realChain = new RealChain(interceptors,0,request,RealCall.this);
            return realChain.getResponse(request);
        }
    }


}
