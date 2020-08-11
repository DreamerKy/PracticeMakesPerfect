package com.kotlin.vip.myeventbus;

/**
 * Created by likaiyu on 2020/1/1.
 */
public class EventBeans implements SubscriberInfo {
    private final Class subscriberClass;
    private final SubscriberMethod[] methodInfos;
    public EventBeans(Class subscriberClass,SubscriberMethod[] methodInfos){
        this.subscriberClass = subscriberClass;
        this.methodInfos = methodInfos;
    }


    @Override
    public Class<?> getSubscriberClass() {
        return subscriberClass;
    }

    @Override
    public SubscriberMethod[] getSubscriberMethods() {
        return methodInfos;
    }
}
