package com.example.dreams.okhttp;

/**
 * Created by likaiyu on 2020/1/21.
 */
public class Response {
    public int statusCode;
    private String body;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
