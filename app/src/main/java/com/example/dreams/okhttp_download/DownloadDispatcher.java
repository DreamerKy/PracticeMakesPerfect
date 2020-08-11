package com.example.dreams.okhttp_download;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by likaiyu on 2020/4/26.
 */
public class DownloadDispatcher {
    private static final DownloadDispatcher sDispatcher = new DownloadDispatcher();
    private final Deque<DownloadTask> readyTasks = new ArrayDeque<>();
    private final Deque<DownloadTask> runningTasks = new ArrayDeque<>();
    private final Deque<DownloadTask> stopTasks = new ArrayDeque<>();

    private DownloadDispatcher(){

    }

    public static DownloadDispatcher getDispatcher(){
        return sDispatcher;
    }

    public void startDownload(final String url, final DownloadCallBack callBack){
        Call call = OkHttpManager.getInstance().asyncCall(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取文件大小
                long contentLength = response.body().contentLength();
                if(contentLength<=-1){
                    //没有获取到文件大小
                    //只能用单线程下载
                    //或者和后端商量
                    return;
                }
                DownloadTask downLoadTask = new DownloadTask(url,contentLength,callBack);
                downLoadTask.init();

            }
        });
    }


    public void recyclerTask(DownloadTask downloadTask) {
        runningTasks.remove(downloadTask);
    }
}
