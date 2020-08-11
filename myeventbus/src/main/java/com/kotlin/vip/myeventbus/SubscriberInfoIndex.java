package com.kotlin.vip.myeventbus;

/**
 * Created by likaiyu on 2020/1/1.
 * 所有的事件订阅方法，生成索引接口
 */
public interface SubscriberInfoIndex {

    //生成索引接口，通过订阅者对象,获取所有订阅方法
    SubscriberInfo getSubscriberInfo(Class<?> subscriberClass);

}
