package com.tsu.app2.rxjava;

public interface Emitter<T> {

    void onNext(T t);
    void onError(Throwable e);
    void onComplete();


}
