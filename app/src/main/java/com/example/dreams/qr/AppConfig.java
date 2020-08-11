package com.example.dreams.qr;

import android.os.Environment;

/**
 * 常量配置类
 *
 * @author likaiyu 2019-1-15
 */
public class AppConfig {
    /**
     * 存储目录
     */
    public static final String SDCARD_FOLDER = Environment.getExternalStorageDirectory() + "/scan-qr";

    public static final String OPEN_USER_PIC_FOLDER = SDCARD_FOLDER + "/pic/";

    public static final String FILE_PROVIDER = "com.example.dreams.fileprovider";


}
