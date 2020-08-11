package com.example.dreams.sixprinciple;

/**
 * Created by likaiyu on 2020/4/7.
 */
public class Test {

    public static void main(String[] args) {
        HttpUtils.with(null)
                .httpRequest(new OkHttpRequest())
                .chche(true)
                .params("1", 1)
                .params("2", 2)
                .params("3", 3)
                .request(new HttpCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
    }

}
