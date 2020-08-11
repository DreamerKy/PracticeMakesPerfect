package com.example.dreams.okhttp_download;

import android.content.Context;

import com.example.dreams.okhttp_download.db.DaoManagerHelper;

/**
 * Created by likaiyu on 2020/4/26.
 */
public class OkHttpFileDownloader {

    private static final OkHttpFileDownloader sDownloader = new OkHttpFileDownloader();

    private OkHttpFileDownloader() {

    }

    public static OkHttpFileDownloader getInstance() {
        return sDownloader;
    }

    public void init(Context context) {
        FileManager.getInstance().init(context);
        DaoManagerHelper.getInstance().init(context);
    }

    public void startDownload(String url, DownloadCallBack callBack) {
        DaoManagerHelper.getInstance().remove(url);
        DownloadDispatcher.getDispatcher().startDownload(url, callBack);
    }


}
