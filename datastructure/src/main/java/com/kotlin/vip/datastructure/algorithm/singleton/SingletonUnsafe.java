package com.kotlin.vip.datastructure.algorithm.singleton;

/**
 * Created by likaiyu on 2020/1/7.
 */
public class SingletonUnsafe {
    private static SingletonUnsafe mInstance;
    public static SingletonUnsafe getInstance(){
        if(null == mInstance){
            mInstance = new SingletonUnsafe();
        }
        return mInstance;
    }
    private SingletonUnsafe(){}
}
