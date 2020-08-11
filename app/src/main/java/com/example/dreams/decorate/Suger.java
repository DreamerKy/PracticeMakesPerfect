package com.example.dreams.decorate;

/**
 * Created by likaiyu on 2019/11/14.
 */
public class Suger extends Drink {


    private Drink addParts;

    public Suger(Drink addParts) {
        this.addParts = addParts;
    }

    @Override
    String showName() {
        return this.addParts.showName() +"加勺糖";
    }

    @Override
    double calculatePrice() {
        double price = this.addParts.calculatePrice();
        return price + 2.0;
    }
}
