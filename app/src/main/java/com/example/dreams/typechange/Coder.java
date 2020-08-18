package com.example.dreams.typechange;

public class Coder extends Person {

    public Coder(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println("我是" + name + "，我得工作");
    }

    @Override
    public String toString() {
        return "Coder{" +
                "name='" + name + '\'' +
                '}';
    }
}