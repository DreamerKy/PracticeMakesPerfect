package com.example.dreams.decorate;

/**
 * Created by likaiyu on 2019/11/14.
 */
public class Milk extends Drink {

    private Drink addParts;

    public Milk(Drink addParts) {
        this.addParts = addParts;
    }

    @Override
    String showName() {
        return this.addParts.showName() + "加点牛奶";
    }

    @Override
    double calculatePrice() {
        double price = this.addParts.calculatePrice();
        return price + 3.0;
    }
}
