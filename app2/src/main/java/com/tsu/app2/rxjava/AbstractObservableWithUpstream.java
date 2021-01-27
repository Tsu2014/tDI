package com.tsu.app2.rxjava;

/**
 * 装饰者类
 * @param <T>
 * @param <R>
 */
public abstract class AbstractObservableWithUpstream<T , R> extends Observable<R> {

    protected final ObservableSource<T> source;

    public AbstractObservableWithUpstream(ObservableSource<T> source){
        this.source = source;
    }
}
