package com.tsu.app2.rxjava;

public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<T> emitter) throws Exception;

}
