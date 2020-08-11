package com.example.dreams.okhttp_upload_process_listener_and_cache;

import android.os.Environment;
import android.text.TextUtils;

import com.example.dreams.net.MApplication;
import com.example.dreams.okhttp_upload_process_listener_and_cache.intercept.CacheRequestInterceptor;
import com.example.dreams.okhttp_upload_process_listener_and_cache.intercept.CacheResponseInterceptor;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by likaiyu on 2020/4/26.
 */
public class test {
    public static void main(String[] args) {
        String url = "https://xxx";
        File file = new File(Environment.getExternalStorageDirectory(), "cache");
        Cache cache = new Cache(file, 10 * 1024 * 1024);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                //加在最前，过期时间缓存多少秒
                .cache(cache)
                .addInterceptor(new CacheRequestInterceptor(MApplication.context))
                //加在最后，数据缓存，过期时间30秒
                .addNetworkInterceptor(new CacheResponseInterceptor())
                .build();
        //上传文件构建请求体
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        builder.addFormDataPart("platform", "android");
        builder.addFormDataPart("file", file.getName(),
                RequestBody.create(MediaType.parse(guessMimeType(file.getAbsolutePath())), file));

        MultipartBodyProxy bodyProxy = new MultipartBodyProxy(builder.build(), new UpdateProcessListener() {
            @Override
            public void onProcess(long total, long current) {

            }
        });

        Request request = new Request.Builder()
                .url(url)
                .post(bodyProxy)
                .build();

        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }

    private static String guessMimeType(String absolutePath) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(absolutePath);
        if (TextUtils.isEmpty(contentTypeFor)) {
            return "application/octet-stream";
        }
        return contentTypeFor;
    }
}
