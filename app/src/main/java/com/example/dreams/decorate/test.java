package com.example.dreams.decorate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by likaiyu on 2019/11/14.
 */
public class test {
    public static final int MEASURED_SIZE_MASK = 0x00ffffff;
    public static void main(String[] args) {
        Drink drink = new Coffee();
//        drink = new Milk(drink);
//        drink = new Suger(drink);
//        drink = new Shit(drink);
        drink = new Milk(new Suger(new Shit(drink)));
        double price = drink.calculatePrice();
        String describe = drink.showName();
        System.out.println(String.format("这杯%s星爸爸价格---%s", describe,String.valueOf(price)));
        System.out.println(MEASURED_SIZE_MASK);
//        List<? super B> list = new ArrayList<>();
//        list.add(null);
//        list.add(new B());
//        list.add(new C());
//        list.add(new A());
//        list.add(new Object());
//        B b = list.get(1);

        InetAddress address;
        try {
            address = InetAddress.getByName("testi.phoenixpay.com");
            System.out.println("address : " + address.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }


    static class A {

    }

    static class B extends A{

    }

    static class C extends B{

    }


}
