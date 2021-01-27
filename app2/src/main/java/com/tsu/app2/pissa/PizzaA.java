package com.tsu.app2.pissa;

public class PizzaA extends Decorator{

    private Pizza pizza;

    public  PizzaA(Pizza pizza){
        this.pizza = pizza;
    }

    @Override
    public double getPrice() {
        return pizza.getPrice()+10;
    }

    @Override
    public String getName() {
        return pizza.getName()+"+菠萝";
    }
}
