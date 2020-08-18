package com.example.dreams.typechange;

public class Doctor extends Person {

    public Doctor(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println("我是" + name + "，我得工作");
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                '}';
    }
}