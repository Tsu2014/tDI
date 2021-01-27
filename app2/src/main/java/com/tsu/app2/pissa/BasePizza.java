package com.tsu.app2.pissa;

public class BasePizza extends Pizza{

    @Override
    public String getName() {
        this.name= "Pizza";
        return this.name;
    }

    @Override
    public double getPrice() {
        return 50;
    }
}
