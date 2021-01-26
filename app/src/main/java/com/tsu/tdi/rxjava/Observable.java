package com.tsu.tdi.rxjava;

import android.util.Log;

public abstract class Observable<T> {
    private static final String TAG = "Observable";
    //创建型操作
    public static <T> Observable<T>  create(Observable<T> observable){
        return observable;
    }

    public abstract void subscribe(Observer<T> observer);

    public Observable<T> flatMap(){
        Log.d(TAG , "behind : "+hashCode());

        return new Observable<T>() {
            @Override
            public void subscribe(Observer<T> observerC) {
                Observer<T> observerB = new Observer<T>() {
                    @Override
                    public void onNext(T t) {
                        Log.d(TAG , "ObserverB : "+t);
                        //observerC.onNext(t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG , "ObserverB !!! ");
                        //observerC.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //observerC.onError(e);
                    }
                };
                Observable.this.subscribe(observerB);
                Log.d(TAG , "next "+hashCode());
            }
        };
    }

    public <R>Observable<R> map(final Function<T , R> function){
        return new Observable<R>() {
            @Override
            public void subscribe(final Observer<R> observerC) {
                Observer<T> observerB = new Observer<T>() {
                    @Override
                    public void onNext(T t) {
                        R r = function.apply(t);
                        observerC.onNext(r);
                    }

                    @Override
                    public void onComplete() {
                        observerC.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                };
                Observable.this.subscribe(observerB);
            }
        };
    }
}
