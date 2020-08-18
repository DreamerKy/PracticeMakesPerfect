package com.example.dreams.typechange;

/**
 * Created by likaiyu on 2020/8/18.
 */

public class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }

    public void work() {
        System.out.println("我是" + name + "，我得工作");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
