package com.example.dreams.sixprinciple;

/**
 * Created by likaiyu on 2020/4/7.
 */
public interface ICache {
    void saveCache(String url, String data);

    String getCache(String url);

}
