package com.example.dreams.rxjava;

/**
 * Created by likaiyu on 2020/1/31.
 */
public interface Observer<T> {

    void onSubscribe();
    void onNext(T item);
    void onError(Throwable e);
    void onComplete();

}
