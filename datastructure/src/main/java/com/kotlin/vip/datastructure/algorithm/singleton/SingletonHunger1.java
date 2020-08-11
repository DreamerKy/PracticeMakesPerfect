package com.kotlin.vip.datastructure.algorithm.singleton;

/**
 * Created by likaiyu on 2020/1/7.
 */
class SingletonHunger1 {

    private static final SingletonHunger1 mInstance= new SingletonHunger1();

    public static SingletonHunger1 getInstance(){
        return mInstance;
    }
    private SingletonHunger1(){}

}
