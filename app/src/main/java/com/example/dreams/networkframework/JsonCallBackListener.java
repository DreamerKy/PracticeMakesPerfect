package com.example.dreams.networkframework;

import android.os.Handler;
import android.os.Looper;

import com.example.dreams.net.ResponseCallback;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by likaiyu on 2019/10/28.
 */
public class JsonCallBackListener<T> implements CallBackListener {

    private ResponseCallback<T> responseCallback;
    private Handler handler = new Handler(Looper.getMainLooper());

    public JsonCallBackListener(ResponseCallback<T> responseCallback) {
        this.responseCallback = responseCallback;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        //将流转换为bean
        String response = getContent(inputStream);
        Class<?> classInfo = getClassInfo(this);
        final T responseBean = (T) new Gson().fromJson(response, classInfo);
        handler.post(new Runnable() {
            @Override
            public void run() {
                responseCallback.onSuccess(responseBean);
            }
        });
    }

    private Class<?> getClassInfo(Object object) {

        ParameterizedType genericSuperclass = (ParameterizedType) (object.getClass().getGenericSuperclass());
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
        return (Class<?>) actualTypeArgument;

    }

    private String getContent(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while (((line = bufferedReader.readLine()) != null)) {
                stringBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }


    @Override
    public void onFailure() {
        responseCallback.onFailure();
    }
}
