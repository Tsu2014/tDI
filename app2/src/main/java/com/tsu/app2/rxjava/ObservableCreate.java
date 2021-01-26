package com.tsu.app2.rxjava;

public class ObservableCreate<T> extends Observable{

    ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source){
        this.source = source;
    }

    @Override
    public void subscribe(Observer observer) {
        super.subscribe(observer);
    }

    @Override
    protected void subscribeActual(Observer observer) {
        //
        CreateEmitter<T> parent = new CreateEmitter<T>(observer);
        observer.onSubscribe();
        try {
            source.subscribe(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static final class CreateEmitter<T> implements Emitter<T>{

        Observer<T> observer;

        public CreateEmitter(Observer<T> observer){
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            observer.onNext(t);
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }
}
