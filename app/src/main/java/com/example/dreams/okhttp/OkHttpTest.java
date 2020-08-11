package com.example.dreams.okhttp;

import java.io.IOException;

/**
 * Created by likaiyu on 2020/1/23.
 */
public class OkHttpTest {
    private static final String PATH = "http://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=13cb58f5884f9749287abbead9c658f2";
    public static void main(String[] args){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().get().url(PATH).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new CallBack() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("请求成功: "+response.getBody()+" 请求结果码 : "+response.getStatusCode());
            }
        });


    }

}
