package com.example.dreams.okhttp_download;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by likaiyu on 2020/4/26.
 */
final class FileManager {
    private static final FileManager sFileManager = new FileManager();
    private File mRootDir;
    private Context mContext;

    public static FileManager getInstance() {
        return sFileManager;
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void rootDir(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file.exists() && file.isDirectory()) {
            mRootDir = file;
        }
    }

    /**
     * 通过网络路径获取一个本地文件路径
     *
     * @param url
     * @return
     */
    public File getFile(String url) {
        String fileName = Utils.md5Url(url);
        if (mRootDir == null) {
            mRootDir = mContext.getCacheDir();
        }
        File file = new File(Environment.getExternalStorageDirectory() + "/dreams.apk");
//        File file = new File(mRootDir, fileName);
        return file;
    }

}
