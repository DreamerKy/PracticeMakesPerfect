package com.example.dreams;

import android.util.LruCache;

import java.util.UUID;

/**
 * Created by likaiyu on 2020/4/9.
 */

public class test {


    public static void main(String[] args) {
//        String str1 = "abcd";
//        String str2 = new String("abcd");
//        System.out.println("str1 hashCode:"+str1.hashCode());
//        System.out.println("str2 hashCode:"+str2.hashCode());
//        System.out.println(str1.equals(str2));

//        Child child = new Child();
//        System.out.println(Child.str);

    }
}




class Parent {
    public static final String str = "hello parent";

    public Parent() {
        System.out.println("parent constructor");
    }

    static {
        System.out.println("parent static block");
    }

    {
        System.out.println("parent block");
    }
}

class Child extends Parent {
    public static String str2 = "hello child";

    public Child() {
        System.out.println("child constructor");
    }

//    public static final String str = UUID.randomUUID().toString();
    static {
        System.out.println("child static block");
    }

    {
        System.out.println("child block");
    }
}