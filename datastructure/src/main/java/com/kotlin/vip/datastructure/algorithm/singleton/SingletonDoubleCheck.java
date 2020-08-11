package com.kotlin.vip.datastructure.algorithm.singleton;

/**
 * Created by likaiyu on 2020/1/7.
 */
public class SingletonDoubleCheck {
    private static volatile SingletonDoubleCheck mInstance;

    public static SingletonDoubleCheck getmInstance() {
        if (null == mInstance) {
            synchronized (SingletonDoubleCheck.class) {
                if (null == mInstance) {
                    mInstance = new SingletonDoubleCheck();
                }
            }
        }
        return mInstance;
    }

    private SingletonDoubleCheck() {
    }

}
