package com.example.dreams.sixprinciple;

/**
 * Created by likaiyu on 2020/4/7.
 */
public class SpHttpCache implements ICache {
    @Override
    public void saveCache(String url, String data) {
        PreferencesUtils.getInstance().save(url, data);
    }

    @Override
    public String getCache(String url) {
        return (String) PreferencesUtils.getInstance().getObject(url);
    }
}
