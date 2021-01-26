package com.tsu.app2.rxjava;

public abstract class Observable<T> implements ObservableSource{

    @Override
    public void subscribe(Observer observer) {
        //子类完成
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);

    //创造被观察者的实例
    public static <T>Observable<T> create(ObservableOnSubscribe<T> source){
        return new ObservableCreate(source);
    }
}
