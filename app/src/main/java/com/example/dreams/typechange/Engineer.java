package com.example.dreams.typechange;

public class Engineer extends Coder {

    public Engineer(String name) {
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