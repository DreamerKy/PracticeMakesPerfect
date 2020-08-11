package com.kotlin.vip.datastructure.algorithm.singleton;

/**
 * Created by likaiyu on 2020/1/7.
 */
public class SingleInnerClass {
    private static class SingleHolder {
        private static SingleInnerClass mInstance = new SingleInnerClass();
    }

    public static SingleInnerClass getInstance() {
        return SingleHolder.mInstance;
    }

    private SingleInnerClass() {
    }

}
