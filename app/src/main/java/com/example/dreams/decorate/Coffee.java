package com.example.dreams.decorate;

/**
 * Created by likaiyu on 2019/11/14.
 */
public class Coffee extends Drink {

    @Override
    String showName() {
        return "咖啡";
    }

    @Override
    double calculatePrice() {
        return 30.0;
    }

}
