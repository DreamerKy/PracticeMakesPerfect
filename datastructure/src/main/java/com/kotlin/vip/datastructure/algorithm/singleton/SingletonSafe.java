package com.kotlin.vip.datastructure.algorithm.singleton;

/**
 * Created by likaiyu on 2020/1/7.
 */
public class SingletonSafe {
    private static SingletonSafe mInstance;
    public synchronized static SingletonSafe getmInstance(){
        if(null == mInstance){
            mInstance = new SingletonSafe();
        }
        return mInstance;
    }
    private SingletonSafe(){}

}
