package com.tsu.app2.rxjava;

public abstract class BasicFuseableOberver<T , R> implements Observer<T>{

    //观察者
    protected final Observer<R> actual;

    protected BasicFuseableOberver(Observer<R> actual){
        this.actual = actual;
    }

    @Override
    public void onSubscribe() {
        actual.onSubscribe();
    }

    @Override
    public void onError(Throwable e) {
        actual.onError(e);
    }

    @Override
    public void onComplete() {
        actual.onComplete();
    }
}
