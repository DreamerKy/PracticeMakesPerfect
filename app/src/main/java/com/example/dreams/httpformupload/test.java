package com.example.dreams.httpformupload;

import java.io.File;
import java.io.IOException;

/**
 * Created by likaiyu on 2020/4/26.
 */
public class test {
    public static void main(String[] args) {
        File file = new File("");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new RequestBody()
                .type(RequestBody.FORM)
                .addParam("file1", RequestBody.create(file))
                .addParam("file2", RequestBody.create(file))
                .addParam("pageSize", 1 + "");
        Request request = new Request.Builder()
                .url("https://api.saiwuquan.com/api/appv2/sceneModel")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new CallBack() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }
}
