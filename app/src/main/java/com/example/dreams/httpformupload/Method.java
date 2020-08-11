package com.example.dreams.httpformupload;

/**
 * Created by likaiyu on 2020/4/25.
 */
public enum Method {
    POST("POST"),
    GET("GET"),
    HEAD("HEAD"),
    DELETE("DELETE"),
    PUT("PUT"),
    PATCH("PATCH");

    String name;

    Method(String name) {
        this.name = name;
    }

    public boolean doOutPut() {
        switch (this) {
            case POST:
            case PUT:
                return true;
        }
        return false;
    }

}
