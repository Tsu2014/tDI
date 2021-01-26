package com.tsu.app2.rxjava;

public interface ObservableSource<T> {

    void subscribe(Observer<T> observer);

}
