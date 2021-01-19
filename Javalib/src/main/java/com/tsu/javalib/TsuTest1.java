package com.tsu.javalib;

public class TsuTest1 implements IShop{
    @Override
    public void action2(String name) {
        System.out.println("action2 : " +name);
    }

    @Override
    public void action1(String name , int age) {
        System.out.println("action1's name : "+name +" , age : "+age);
    }


}
