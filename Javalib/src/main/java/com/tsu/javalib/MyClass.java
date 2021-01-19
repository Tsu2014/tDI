package com.tsu.javalib;

import java.lang.reflect.Proxy;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

public class MyClass {

    public static void testProxy(){
        IShop test = new TsuTest1();
        DynamicPurchasing dynamicPurchasing = new DynamicPurchasing(test);
        IShop purchasing = (IShop) Proxy.newProxyInstance(test.getClass().getClassLoader() , new Class[]{IShop.class} , dynamicPurchasing);
        purchasing.action1("tsu" , 18);
        purchasing.action2("hehe");
    }

    public static void main(String [] args){
        testProxy();
    }

}