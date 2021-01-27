package com.tsu.app2.pissa;

public class PizzaC extends Decorator{

    private Pizza pizza;

    public PizzaC(Pizza pizza){
        this.pizza = pizza;
    }

    @Override
    public double getPrice() {
        return pizza.getPrice()+15;
    }

    @Override
    public String getName() {
        return pizza.getName()+"+洋葱";
    }
}
