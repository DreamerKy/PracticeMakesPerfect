package com.example.dreams.okhttp_download;

import com.example.dreams.okhttp_download.db.DaoManagerHelper;
import com.example.dreams.okhttp_download.db.DownloadEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by likaiyu on 2020/4/26.
 */
class DownloadTask {

    private final String mUrl;
    private final long mContentLength;
    private final DownloadCallBack mCallBack;
    //每一个文件下载中对应的多个子任务（多线程下载）
    //要放在线程池中去执行
    private List<DownloadRunnable> mRunnables;
    private int mSuccessNumbers;

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int THREAD_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));

    ExecutorService executorService;


    public DownloadTask(String url, long contentLength, DownloadCallBack callBack) {
        this.mUrl = url;
        this.mContentLength = contentLength;
        this.mCallBack = callBack;
        this.mRunnables = new ArrayList<>();
    }

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, THREAD_SIZE, 30, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r, "DownloadTask");
                    thread.setDaemon(false);
                    return thread;
                }
            });
        }
        return executorService;
    }

    public void init() {
        for (int i = 0; i < THREAD_SIZE; i++) {
            //计算出每个线程要下载的内容长度
            long threadSize = mContentLength / THREAD_SIZE;
            long start = i * threadSize;
            long end = (i + 1) * threadSize - 1;
            if (i == THREAD_SIZE - 1) {
                end = mContentLength - 1;
            }
            List<DownloadEntity> entities = DaoManagerHelper.getInstance().queryAll(mUrl);
            DownloadEntity downloadEntity = getEntity(i, entities);
            if (downloadEntity == null) {
                downloadEntity = new DownloadEntity(start, end, mUrl, i, 0, mContentLength);
            }

            DownloadRunnable downloadRunnable = new DownloadRunnable(mUrl,i,start,end,downloadEntity.getProgress(),downloadEntity, new DownloadCallBack() {
                @Override
                public void onFailure(IOException e) {
                    mCallBack.onFailure(e);
                }

                @Override
                public void onSuccess(File file) {
                    //同步处理
                    synchronized (DownloadTask.this) {
                        mSuccessNumbers += 1;
                        if (mSuccessNumbers == THREAD_SIZE) {
                            mCallBack.onSuccess(file);
                            DownloadDispatcher.getDispatcher().recyclerTask(DownloadTask.this);
                        }
                    }
                }
            });

            mRunnables.add(downloadRunnable);
            executorService().execute(downloadRunnable);
        }
    }

    private DownloadEntity getEntity(int threadId, List<DownloadEntity> entities) {
        for (DownloadEntity entity : entities) {
            if (threadId == entity.getThreadId()) {
                return entity;
            }

        }
        return null;
    }

    public void stop(){
        for (DownloadRunnable mRunnable : mRunnables) {
            mRunnable.stop();
        }
    }


}
