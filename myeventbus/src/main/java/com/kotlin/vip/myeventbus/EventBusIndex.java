package com.kotlin.vip.myeventbus;

import com.kotlin.vip.myeventbus.mode.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by likaiyu on 2020/1/1.
 */
public class EventBusIndex implements SubscriberInfoIndex {
    private static final Map<Class, SubscriberInfo> SUBSCRIBER_INDEX;

    static {
        SUBSCRIBER_INDEX = new HashMap<>();
        putIndex(new EventBeans(EventBus.class, new SubscriberMethod[]{
                new SubscriberMethod(EventBus.class, "XXX", EventBus.class, ThreadMode.ASYNC, 0, false)
        }));
    }

    private static void putIndex(SubscriberInfo info) {
        SUBSCRIBER_INDEX.put(info.getSubscriberClass(), info);
    }

    @Override
    public SubscriberInfo getSubscriberInfo(Class<?> subscriberClass) {

        return SUBSCRIBER_INDEX.get(subscriberClass);
    }
}
