package com.kotlin.vip.myeventbus;

import com.kotlin.vip.myeventbus.mode.ThreadMode;

import java.lang.reflect.Method;

/**
 * Created by likaiyu on 2020/1/1.
 */
public class SubscriberMethod {

    //方法名
    private String methodName;
    //订阅方法，用于最后自动执行订阅方法
    private Method method;
    //线程模式
    private ThreadMode threadMode;
    //事件对象
    private Class<?> eventType;
    //优先级
    private int priority;
    //是否粘性事件
    private boolean sticky;

    public SubscriberMethod(Class subscriberClass,String methodName, Class<?> eventType, ThreadMode threadMode, int priority, boolean sticky) {
        this.methodName = methodName;
        this.threadMode = threadMode;
        this.eventType = eventType;
        this.priority = priority;
        this.sticky = sticky;
        try {
            this.method = subscriberClass.getDeclaredMethod(methodName,eventType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public void setThreadMode(ThreadMode threadMode) {
        this.threadMode = threadMode;
    }

    public Class<?> getEventType() {
        return eventType;
    }

    public void setEventType(Class<?> eventType) {
        this.eventType = eventType;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isSticky() {
        return sticky;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }
}
