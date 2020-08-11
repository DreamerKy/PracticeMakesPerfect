package com.example.dreams.net;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by likaiyu on 2019/10/24.
 */
public abstract class HttpCallback<Result> implements ICallBack {

    //将网络访问框架的到的数据转换为Json对象，在转换为实体对象
    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> clazz = analysisClassInfo(this);
        Result o = (Result) gson.fromJson(result, clazz);
        onSuccess(o);
    }

    public abstract void onSuccess(Result object);

    private Class<?> analysisClassInfo(Object object) {
        Type[] actualTypeArguments = ((ParameterizedType) object.getClass().getGenericSuperclass()).getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];

        Type[] actualTypeArguments2 = ((ParameterizedType) (object.getClass().getGenericSuperclass())).getActualTypeArguments();
        Type type;
        type = actualTypeArguments2[0];
        return (Class<?>) type;
    }


    @Override
    public void onFailure() {

    }
}
