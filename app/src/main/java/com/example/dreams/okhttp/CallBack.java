package com.example.dreams.okhttp;

import java.io.IOException;

/**
 * Created by likaiyu on 2020/1/21.
 */
public interface CallBack {
    void onFailure(Call call, IOException e);
    void onResponse(Call call,Response response) throws IOException;
}
