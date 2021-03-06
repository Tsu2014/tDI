package com.tsu.tdi.rxjava;


/**
 * abstract Obeserver
 * @param <T>
 */
public interface Observer<T> {

    void onNext(T t);
    void onComplete();
    void onError(Throwable e);

}
