package com.example.dreams;

/**
 * Created by likaiyu on 2019/7/28.
 */

public class MyThreadSopeData {
    private static ThreadLocal<MyThreadSopeData> threadLocalMap = new ThreadLocal<>();

    private MyThreadSopeData() {
    }

    public static MyThreadSopeData getThreadInstance(){
        MyThreadSopeData insatnce = threadLocalMap.get();
        if(insatnce == null){
            insatnce = new MyThreadSopeData();
            threadLocalMap.set(insatnce);
        }
        return insatnce;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyThreadScopeData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}