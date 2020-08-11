package com.example.dreams.httpformupload;

import java.io.IOException;

/**
 * Created by likaiyu on 2020/4/25.
 */
public interface CallBack {
    void onResponse(Call call, Response response) throws IOException;

    void onFailure(Call call, IOException e);
}
