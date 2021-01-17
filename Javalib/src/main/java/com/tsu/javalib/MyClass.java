package com.tsu.javalib;

import java.lang.reflect.Proxy;

public class MyClass {

    public static void main(String [] args){
        IShop test = new TsuTest1();
        DynamicPurchasing dynamicPurchasing = new DynamicPurchasing(test);
        IShop purchasing = (IShop) Proxy.newProxyInstance(test.getClass().getClassLoader() , new Class[]{IShop.class} , dynamicPurchasing);
        purchasing.action("tsu" , 18);
    }

}