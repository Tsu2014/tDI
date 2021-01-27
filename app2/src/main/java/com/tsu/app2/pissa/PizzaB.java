package com.tsu.app2.pissa;

public class PizzaB extends Decorator{

    private Pizza pizza;

    public PizzaB(Pizza pizza){
        this.pizza = pizza;
    }

    @Override
    public double getPrice() {
        return pizza.getPrice()+20;
    }

    @Override
    public String getName() {
        return pizza.getName()+"+牛肉";
    }
}
