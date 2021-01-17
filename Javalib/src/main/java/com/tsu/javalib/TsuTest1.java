package com.tsu.javalib;

public class TsuTest1 implements IShop{
    @Override
    public void action(String name , int age) {
        System.out.println("TsuTest1's name : "+name +" , age : "+age);
    }
}
