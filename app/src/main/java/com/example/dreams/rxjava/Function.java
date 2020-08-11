package com.example.dreams.rxjava;

/**
 * Created by likaiyu on 2020/1/31.
 * 变换方法（T-->R）
 */
public interface Function<T,R> {
    R apply(T t);
}
