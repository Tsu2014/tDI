package com.tsu.app2.rxjava;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObservableObserverOn<T> extends AbstractObservableWithUpstream<T , T> {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public ObservableObserverOn(ObservableSource<T> source){
        super(source);

    }


    @Override
    protected void subscribeActual(Observer<T> observer) {
        ObserverOnObserver<T> parent = new ObserverOnObserver<>(observer);
        source.subscribe(parent);

    }

    static final class ObserverOnObserver<T> implements Observer<T>{
        final Observer<T> actual;
        private Handler handler;

        public  ObserverOnObserver(Observer<T> actual){
            this.actual = actual;
            handler = new Handler(Looper.getMainLooper());
        }


        @Override
        public void onSubscribe() {
            this.actual.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    actual.onNext(t);
                }
            });

        }

        @Override
        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        @Override
        public void onComplete() {
            this.actual.onComplete();
        }
    }

}
