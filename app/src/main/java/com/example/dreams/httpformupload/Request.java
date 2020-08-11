package com.example.dreams.httpformupload;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by likaiyu on 2020/4/25.
 */
public class Request {
    final String url;
    final Method method;
    final Map<String, String> headers;
    final RequestBody requestBody;

    public Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.requestBody = builder.requestBody;
    }

    public static class Builder {
        String url;
        Method method;
        Map<String, String> headers;
        RequestBody requestBody;

        public Builder() {
            method = Method.GET;
            headers = new HashMap<>();
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder get() {
            this.method = Method.GET;
            return this;
        }

        public Builder post(RequestBody requestBody) {
            this.method = Method.POST;
            this.requestBody = requestBody;
            return this;
        }

        public Builder header(String key, String value) {
            headers.put(key, value);
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }
}
