package com.tsu.app2.rxjava;

/**观察者注册接口
 *
 * @param <T>
 */
public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<T> emitter) throws Exception;

}
