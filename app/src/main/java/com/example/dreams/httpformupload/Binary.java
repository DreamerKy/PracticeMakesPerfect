package com.example.dreams.httpformupload;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by likaiyu on 2020/4/25.
 * 二进制文件数据
 */
public interface Binary {
    long fileLength();

    String mimType();

    String fileName();

    void onWrite(OutputStream outputStream) throws IOException;

}
