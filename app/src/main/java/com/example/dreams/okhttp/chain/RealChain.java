package com.example.dreams.okhttp.chain;

import com.example.dreams.okhttp.Interceptor;
import com.example.dreams.okhttp.RealCall;
import com.example.dreams.okhttp.Request;
import com.example.dreams.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by likaiyu on 2020/1/22.
 */
public class RealChain implements Chain {

    private List<Interceptor> interceptors;
    private int index;
    private Request request;
    private RealCall call;

    public RealChain(List<Interceptor> interceptors, int index, Request request, RealCall call) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
        this.call = call;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public int getIndex() {
        return index;
    }

    public RealCall getCall() {
        return call;
    }

    @Override
    public Request getRequest() {
        return request;
    }

    @Override
    public Response getResponse(Request request) throws IOException {
        if (index >= interceptors.size()) {
            throw new AssertionError();
        }
        if (interceptors.isEmpty()) {
            throw new IOException("interceptors is empty");
        }

        Interceptor interceptor = interceptors.get(index);

        System.out.println("RealChain-getResponse-Interceptor -- "+interceptor.getClass().getSimpleName());

        RealChain nextChain = new RealChain(interceptors, index + 1, request, call);
        return interceptor.doNext(nextChain);
    }
}
