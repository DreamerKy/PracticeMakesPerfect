package com.kotlin.vip.datastructure.algorithm.singleton;

/**
 * Created by likaiyu on 2020/1/7.
 */
public class SingletonHunger2 {
    private static SingletonHunger2 mInstance;
    static{
        mInstance = new SingletonHunger2();
    }
    public static SingletonHunger2 getInstance(){
        return mInstance;
    }
    private SingletonHunger2(){}
}
