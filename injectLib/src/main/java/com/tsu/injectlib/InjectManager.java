package com.tsu.injectlib;


import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjectManager {

    private final static String TAG  = "InjectManager";

    public static void inject(Object object){
        injectLayouts(object);
        injectViews(object);
        injectEvent(object);
    }

    private static void injectEvent(Object object){
        Class<?> clazz = object.getClass();

        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            //get method's annotation
            Annotation[] annotations = method.getAnnotations();
            for(Annotation annotation : annotations){
                Class<? extends Annotation> annotationType = annotation.annotationType();
                InjectEvent injectEvent = annotationType.getAnnotation(InjectEvent.class);
                if(injectEvent != null){
                    Class listenerType = injectEvent.listenerType();
                    String callMethod = injectEvent.callbackMethod();
                    String listenerSetter = injectEvent.listenerSetter();
                    Log.d(TAG , "callMethod : "+callMethod +" , setter : "+listenerSetter+" , listenerType : "+listenerType);
                    Method annoValueMethod = null;
                    try{
                        annoValueMethod = annotationType.getDeclaredMethod("value");
                        int [] ids = (int [])annoValueMethod.invoke(annotation);
                        for(int id : ids){
                            Method findViewById = clazz.getMethod("findViewById" , int.class);
                            View view = (View)findViewById.invoke(object , id);
                            Method setOnListenerMethod = view.getClass().getMethod(listenerSetter , listenerType);   //method2 is setOnClickListener()
                            Listenerhandler listenerhandler = new Listenerhandler(view , method);       // method is action1()
                            Object listener = Proxy.newProxyInstance(listenerType.getClassLoader() , new Class[]{listenerType} , listenerhandler);
                            Log.d(TAG , "listener : "+listener.getClass().getName());
                            setOnListenerMethod.invoke(view , listener);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

        }
    }

    private static void injectLayouts(Object object){
        Class<?> clazz = object.getClass();
        InjectLayout injectLayout = clazz.getAnnotation(InjectLayout.class);
        try{
            Method method = clazz.getMethod("setContentView" , int.class);
            method.setAccessible(true);
            int value = injectLayout.value();
            //Log.d(TAG , "object : "+object.getClass().getName()+" , method : "+method.getName());
            method.invoke(object , value);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private static void injectViews(Object object){
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            InjectView injectView = field.getAnnotation(InjectView.class);
            if(injectView !=null){
                try {
                    Method method = clazz
                            .getMethod("findViewById" , int.class);
                    int value = injectView.value();
                    View view = (View)method.invoke(object , value);
                    field.setAccessible(true);
                    field.set(object , view);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
