package com.tsu.ioclib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ListenerHandler implements InvocationHandler {

    private final String TAG = "ListenerHandler";
    private Object object;
    private Method myMethod;

    public ListenerHandler(Object object , Method myMethod){
        this.object = object;
        this.myMethod = myMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method.invoke( , args);
        return myMethod.invoke(object , args);
    }
}
