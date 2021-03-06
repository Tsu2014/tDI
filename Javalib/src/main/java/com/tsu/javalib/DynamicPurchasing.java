package com.tsu.javalib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicPurchasing implements InvocationHandler {

    private Object object;

    public DynamicPurchasing(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("action1")){
            System.out.println("I'm here !");
            args[0] = "Tom";
            args[1] = 20;
            Object result = method.invoke(object , args);
            return result;
        }else if(method.getName().equals("action2")){
            System.out.println("h1");
            Object result = method.invoke(object , args);
            return result;
        }
        return null;
    }
}
