package com.tsu.injectlib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectEvent {
    //evnet three point
    //dingyuezhe
    String listenerSetter();
    //event
    Class listenerType();
    //process program
    String callbackMethod();
}
