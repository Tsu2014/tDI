package com.tsu.app2.rxjava;

/**
 * 发射接口，用来发送消息
 * @param <T>
 */
public interface Emitter<T> {

    void onNext(T t);
    void onError(Throwable e);
    void onComplete();


}
