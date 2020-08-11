package com.kotlin.vip.myeventbus;

/**
 * Created by likaiyu on 2020/1/1.
 */
public interface SubscriberInfo {

    //获取订阅所属类
    Class<?> getSubscriberClass();
    //获取订阅所属类中所有订阅事件的方法
    SubscriberMethod[] getSubscriberMethods();

}
