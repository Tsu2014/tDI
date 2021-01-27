package com.tsu.app2.rxjava;

/**
 * 被观察者抽象类
 * @param <T>
 */
public abstract class Observable<T> implements ObservableSource{

    @Override
    public void subscribe(Observer observer) {
        //子类完成
        subscribeActual(observer);
    }

    /**
     * 由子类去实现的用于注册的方法
     * @param observer
     */
    protected abstract void subscribeActual(Observer<T> observer);

    /**
     *   创造被观察者的实例
     * */
    public static <T>Observable<T> create(ObservableOnSubscribe<T> source){
        return new ObservableCreate(source);
    }

    /**
     * 创建被观察者
     */
    public<U> Observable<U> map(Function<T , U> function){
        return new ObservableMap<>(this, function);
    }

    /**
     * 被观察者的线程切换
     */
    public final Observable<T> subscribeOn(){
        return new ObservableSubscribeOn<>(this);
    }

    /**
     * 观察者的线程切换
     */
    public final Observable<T> observeOn(){
        return new ObservableObserverOn<>(this);
    }
}
