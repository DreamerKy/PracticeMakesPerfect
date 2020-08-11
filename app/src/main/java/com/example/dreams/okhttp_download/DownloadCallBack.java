package com.example.dreams.okhttp_download;

import java.io.File;
import java.io.IOException;

/**
 * Created by likaiyu on 2020/4/26.
 */
public interface DownloadCallBack {
    void onFailure(IOException e);

    void onSuccess(File file);
}
