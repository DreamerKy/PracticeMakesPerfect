package com.example.dreams.okhttp_download;

import android.util.Log;

import com.example.dreams.okhttp_download.db.DaoManagerHelper;
import com.example.dreams.okhttp_download.db.DownloadEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Response;

/**
 * Created by likaiyu on 2020/4/26.
 */
public class DownloadRunnable implements Runnable {
    private static final int STATUS_DOWNLOADING = 1;
    private static final int STATUS_STOP = 2;
    private final long start;
    private final long end;
    private final int threadId;
    private final String url;
    private final DownloadCallBack mCallBack;
    private int mStatus = STATUS_DOWNLOADING;
    private long mProcess = 0;
    private DownloadEntity mDownloadEntity;


    public DownloadRunnable(String mUrl, int i, long start, long end, long progress, DownloadEntity downloadEntity, DownloadCallBack callBack) {
        this.url = mUrl;
        this.threadId = i;
        this.start = start + progress;
        this.end = end;
        this.mProcess = progress;
        this.mCallBack = callBack;
        this.mDownloadEntity = downloadEntity;
    }

    @Override
    public void run() {
        //真正去下载数据(部分)
        RandomAccessFile randomAccessFile = null;
        InputStream inputStream = null;
        try {
            Response response = OkHttpManager.getInstance().syncResponse(url, start, end);
            Log.e("TAG",this.toString());
            inputStream = response.body().byteStream();
            //数据写入目标文件
            File file = FileManager.getInstance().getFile(url);
            randomAccessFile = new RandomAccessFile(file,"rwd");
            //开始位置
            randomAccessFile.seek(start);
            int len = 0;
            byte[] buffer = new byte[10 * 1024];
            while ((len = inputStream.read(buffer)) != -1) {
                //执行了停止操作
                if (mStatus == STATUS_STOP) {
                    break;
                }
                mProcess += len;
                randomAccessFile.write(buffer, 0, len);
            }
            Log.e("DownloadRunnable", "Success " + threadId);
            mCallBack.onSuccess(file);
        } catch (IOException e) {
            Log.e("DownloadRunnable", "Failure " + threadId);
            mCallBack.onFailure(e);
        }finally {
            Utils.close(inputStream);
            Utils.close(randomAccessFile);
            //存到数据库
            mDownloadEntity.setProgress(mProcess);
            DaoManagerHelper.getInstance().addEntity(mDownloadEntity);
        }

    }

    @Override
    public String toString() {
        return "DownloadRunnable{" +
                "start=" + start +
                ", end=" + end +
                ", threadId=" + threadId +
                '}';
    }

    public void stop(){
        mStatus = STATUS_STOP;
    }

}
