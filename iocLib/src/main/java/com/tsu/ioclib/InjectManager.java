package com.tsu.ioclib;

import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjectManager {

    public static void inject(Object activity) {
        injectLayout(activity);
        injectView(activity);
        injectEvent(activity);
    }

    private static void injectLayout(Object object) {
        //setContentView(id)
        Class<?> clazz = object.getClass();
        try {
            InjectLayout annotation = (InjectLayout) clazz.getAnnotation(InjectLayout.class);
            if (annotation != null) {
                int layoutID = annotation.value();

                Method setContentViewMethod = clazz.getMethod("setContentView", Integer.class);
                setContentViewMethod.invoke(object, layoutID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void injectView(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getFields();

        for (Field field : fields) {
            InjectView injectView = field.getAnnotation(InjectView.class);
            if (injectView != null) {
                int resID = injectView.value();
                try {
                    Method findViewByIdMethod = clazz.getMethod("findViewById", Integer.class);
                    View view = (View) findViewByIdMethod.invoke(object, resID);
                    field.setAccessible(true);
                    field.set(object, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectEvent(Object object) {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {         //custom method
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                InjectEvent injectEvent = annotationType.getAnnotation(InjectEvent.class);
                if (injectEvent != null) {
                    //btn.setListener(new View.Listener(){});
                    String listenerSetter = injectEvent.listenerSetter();   //setOnClickListener
                    Class<?> listenerType = injectEvent.listenerType();     //View.OnClickListener.class
                    String callbackMethod = injectEvent.callbackMethod();   //onClick
                    try {
                        ListenerHandler listenerHandler = new ListenerHandler(object ,method);
                        Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader() , new Class[]{listenerType} , listenerHandler);

                        Method valueMethod = annotationType.getDeclaredMethod("value");
                        int [] ids = (int[]) valueMethod.invoke(annotation , null);
                        for( int id : ids){
                            Method findViewById = clazz.getMethod("findViewById" , Integer.class);
                            View view = (View)findViewById.invoke(object , id);

                            //setOnClickListener
                            Method setterMethod = view.getClass().getMethod(listenerSetter , listenerType);
                            setterMethod.invoke(view , proxy);
                        }
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

}
