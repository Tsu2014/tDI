package com.tsu.injectlib;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Listenerhandler implements InvocationHandler {
    private static final String TAG = "Listenerhandler";
    private Object object;
    private Method myMethod;
    public Listenerhandler(Object object , Method method){
        this.object = object;
        this.myMethod = method;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d(TAG , "object : "+object.getClass().getName()+" , myMd : "+myMethod.getName()+" , method : "+method.getName()+" , args0 : "+args[0].getClass().getName());

        return myMethod.invoke(object);
    }
}
