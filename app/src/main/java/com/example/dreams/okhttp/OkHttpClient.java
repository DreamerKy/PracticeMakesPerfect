package com.example.dreams.okhttp;

/**
 * Created by likaiyu on 2020/1/21.
 */
public class OkHttpClient {
    private Dispatcher dispatcher;
    private boolean isCancled;
    private int reCount;

    public OkHttpClient(Builder builder) {
        dispatcher = builder.dispatcher;
        isCancled = builder.isCancled;
        reCount = builder.reCount;
    }

    public boolean getCancled(){
        return isCancled;
    }

    public int getRecount(){
        return reCount;
    }

    public OkHttpClient(){
        this(new Builder());
    }


    public static class Builder {
        Dispatcher dispatcher;
        boolean isCancled;
        int reCount = 3;
        public Builder(){
            dispatcher = new Dispatcher();
        }
        public Builder dispatcher(Dispatcher dispatcher){
            this.dispatcher = dispatcher;
            return this;
        }
        public Builder cancled(){
            isCancled = true;
            return this;
        }

        public Builder setReCount(int reCount){
            this.reCount = reCount;
            return this;
        }

        public OkHttpClient build(){
            System.out.println("构建OKHttpClient");
            return new OkHttpClient(this);
        }
    }

    public Call newCall(Request request){
        System.out.println("构建RealCall");
        return new RealCall(this,request);
    }

    public Dispatcher dispatcher(){
        return dispatcher;
    }

}
