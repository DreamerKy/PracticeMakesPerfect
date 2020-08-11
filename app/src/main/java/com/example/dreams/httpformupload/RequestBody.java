package com.example.dreams.httpformupload;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by likaiyu on 2020/4/25.
 */
public class RequestBody {
    public static final String FORM = "multipart/form-data";
    public static final String GRGN = "\r\n";
    private String type;
    private String boundary = createBoundary();
    private String startBoundary = "--" + boundary;
    private String endBoundary = startBoundary + "--";
    //参数，文件
    private Map<String, Object> params;


    public RequestBody() {
        params = new HashMap<>();
    }

    private String createBoundary() {
        return "OkHttp" + UUID.randomUUID().toString();
    }


    public String getContentType() {
        //规范
        return type + ";boundary = " + boundary;
    }

    public long getContentLength() {
        //计算请求参数的长度
        long length = 0;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                String postTextStr = getText(key, (String) value);
                length += postTextStr.getBytes().length;
            }
            if (value instanceof Binary) {
                Binary binaryValue = (Binary) value;
                String postTextStr = getText(key, binaryValue);
                length += postTextStr.getBytes().length;
                length += binaryValue.fileLength() + GRGN.getBytes().length;
            }
        }
        if (params.size() != 0) {
            length += endBoundary.getBytes().length;
        }
        return length;
    }

    /**
     startBoundary + "\r\n"
     Content-Disposition; form-data; name = "pageSize"
     Context-Type: text/plain


     1
     */
    private String getText(String key, String value) {
        return startBoundary + GRGN +
                "Content-Disposition: form-data; name = \"" + key + "\"\r\n" +
                "Content-Type: text/plain\r\n" +
                GRGN +
                value +
                GRGN;
    }

    private String getText(String key, Binary value) {
        return startBoundary + GRGN +
                "Content-Disposition: from-data; name = \"" + key + "\" filename = \"" + value.fileName() + "\"" +
                "Content-Type: " + value.mimType() + GRGN + GRGN;
    }

    public RequestBody addParam(String key,String value){
        params.put(key,value);
        return this;
    }

    public RequestBody addParam(String key,Binary value){
        params.put(key,value);
        return this;
    }

    public RequestBody type(String type){
        this.type = type;
        return this;
    }

    public void onWriteBody(OutputStream outputStream) throws IOException {
        for(Map.Entry<String,Object> entry:params.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof String){
                String postTextStr = getText(key, (String) value);
                outputStream.write(postTextStr.getBytes());
            }
            if(value instanceof Binary){
                Binary binary = (Binary) value;
                String postTextStr = getText(key,binary);
                outputStream.write(postTextStr.getBytes());
                binary.onWrite(outputStream);
                outputStream.write(GRGN.getBytes());
            }
        }
    }

    public static Binary create(final File file){
        return new Binary() {
            @Override
            public long fileLength() {
                return file.length();
            }

            @Override
            public String mimType() {
                FileNameMap fileNameMap = URLConnection.getFileNameMap();
                String mimType = fileNameMap.getContentTypeFor(file.getAbsolutePath());
                if(TextUtils.isEmpty(mimType)){
                    return "application/octet-stream";
                }
                return mimType;
            }

            @Override
            public String fileName() {
                return file.getName();
            }

            @Override
            public void onWrite(OutputStream outputStream) throws IOException {
                InputStream inputStream = new FileInputStream(file);
                byte[] buffer = new byte[2048];
                int len = 0;
                while((len = inputStream.read(buffer))!=-1){
                    outputStream.write(buffer,0,len);
                }
                inputStream.close();

            }
        };
    }


}
