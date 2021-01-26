package com.tsu.app2.rxjava;

/**
 * 抽象观察者
 */
public interface Observer<T> {

    void onSubscribe();
    void onNext(T t);
    void onError(Throwable e);
    void onComplete();

}
