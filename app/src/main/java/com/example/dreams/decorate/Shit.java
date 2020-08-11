package com.example.dreams.decorate;

/**
 * Created by likaiyu on 2019/11/14.
 */
public class Shit extends Drink {

    private Drink addParts;

    public Shit(Drink addParts) {
        this.addParts = addParts;
    }

    @Override
    String showName() {
        return this.addParts.showName() +"来点屎？";
    }

    @Override
    double calculatePrice() {
        double price = this.addParts.calculatePrice();
        return price - 2.0;
    }
}
