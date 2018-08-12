package com.interview.designpatterns.decorator;

public abstract class Beverage {

    String description = "Unknown Bevarage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
