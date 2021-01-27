package com.tsu.app2.pissa;

public abstract class Decorator extends Pizza{

    @Override
    public double getPrice() {
        return this.getPrice();
    }

    public void show(){
        System.out.println(getName()+"-"+getPrice());
    }
}
