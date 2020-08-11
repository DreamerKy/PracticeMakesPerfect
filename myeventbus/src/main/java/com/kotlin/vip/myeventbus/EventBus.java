package com.kotlin.vip.myeventbus;

import android.os.Handler;
import android.os.Looper;

import com.kotlin.vip.myeventbus.annotation.Subscribe;
import com.kotlin.vip.myeventbus.core.MethodManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by likaiyu on 2019/12/31.
 */
public class EventBus {

    private static volatile EventBus instance;
    //用来保存这些带注解的方法（订阅者的回调方法）
    private final HashMap<Object, List<MethodManager>> cacheMap;
    private final Handler handler;
    private final ExecutorService executorService;

    private EventBus() {
        cacheMap = new HashMap<>();
        handler = new Handler(Looper.getMainLooper());
        executorService = Executors.newCachedThreadPool();
    }

    public static EventBus getDefault() {
        if (instance == null) {
            synchronized (EventBus.class) {
                if (instance == null) {
                    instance = new EventBus();
                }
            }
        }
        return instance;
    }

    public void register(Object getter) {
        //获取类中所有的方法
        List<MethodManager> methodList = cacheMap.get(getter);
        if (methodList == null) {
            methodList = findAnnotationMethod(getter);
            cacheMap.put(getter, methodList);
        }

    }

    /**
     * 获取类中所有注解的方法
     *
     * @param getter
     * @return
     */
    private List<MethodManager> findAnnotationMethod(Object getter) {
        List<MethodManager> methodList = new ArrayList<>();
        Class<?> clazz = getter.getClass();
        //所有的方法，包括父类以及接口
        Method[] methods = clazz.getMethods();
        while (clazz != null) {
            String clazzName = clazz.getName();
            if (clazzName.startsWith("java.") || clazzName.startsWith("javax.")
                    || clazzName.startsWith("android.")) {
                break;
            }
            for (Method method : methods) {
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if (subscribe == null) {
                    continue;
                }
                //方法返回必须为void
                Type returnType = method.getGenericReturnType();
                if (!"void".equals(returnType.toString())) {
                    throw new RuntimeException(method.getName() + "方法返回必须是void");
                }

                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new RuntimeException(method.getName() + "方法有且只有一个参数");
                }

                MethodManager manager = new MethodManager(parameterTypes[0], subscribe.threadMode(), method);
                methodList.add(manager);
            }
            clazz = clazz.getSuperclass();

        }
        return methodList;
    }

    //发送消息
    public void post(final Object setter) {
        Set<Object> keySet = cacheMap.keySet();
        for (final Object key : keySet) {
            List<MethodManager> methodList = cacheMap.get(key);
            if (methodList != null) {
                for (final MethodManager method : methodList) {
                    //遍历某个类中的所有方法看参数是否匹配setter
                    if (method.getType().isAssignableFrom(setter.getClass())) {
                        switch (method.getThreadMode()) {
                            case POSTING:
                                invoke(method, key, setter);
                                break;
                            case MAIN:
                                //判断发送方是否在主线程
                                if (Looper.myLooper() == Looper.getMainLooper()) {
                                    invoke(method, key, setter);
                                } else {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            invoke(method, key, setter);
                                        }
                                    });
                                }
                                break;
                            case BACKGROUNG:
                                //发送线程是否是主线程
                                if (Looper.myLooper() == Looper.getMainLooper()) {
                                    executorService.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            invoke(method, key, setter);
                                        }
                                    });
                                } else {
                                    invoke(method, key, setter);
                                }

                                break;
                        }
                    }
                }
            }
        }
    }

    private void invoke(MethodManager method, Object key, Object setter) {
        try {
            method.getMethod().invoke(key, setter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
