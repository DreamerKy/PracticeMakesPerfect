package com.example.dreams.neteasehandler;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by likaiyu on 2019/11/14.
 */
public class test {
    public static void main(String[] args) {
//        Looper.perpare();
//        Handler handler = new Handler();
//        handler.sendMessage(new Message(1));
//        Looper.loop();

//        test testobject = new test();

        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.example.dreams.neteasehandler.MessageQueue");
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("方法名--"+method.getName());
                Type[] genericParameterTypes = method.getGenericParameterTypes();
                Type genericReturnType = method.getGenericReturnType();
                Class<?> returnType = method.getReturnType();
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (Type genericParameterType : genericParameterTypes) {
                    System.out.println("类型getGenericParameterTypes--"+genericParameterType);
                }
                System.out.println("genericReturnType--"+genericReturnType);
                System.out.println("returnType--"+returnType);
                for (Class<?> parameterType : parameterTypes) {
                    System.out.println("getParameterTypes--"+parameterType.getName());
                }
                System.out.println("-------------------------------");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void TestMethod(String s){
        System.out.println(s);
    }


}
