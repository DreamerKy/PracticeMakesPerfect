package com.example.dreams.httpformupload;

/**
 * Created by likaiyu on 2020/4/21.
 */
public class OkHttpClient {

    public final Dispatcher dispatcher;

    public OkHttpClient(){
        this(new Builder());
    }

    private OkHttpClient(Builder builder) {
        this.dispatcher = builder.dispatcher;
    }

    public Call newCall(Request request) {
        return RealCall.newCall(request, this);
    }

    public static class Builder {
        Dispatcher dispatcher;

        public Builder() {
            dispatcher = new Dispatcher();
        }

        public OkHttpClient build() {
            return new OkHttpClient(this);
        }
    }

}
