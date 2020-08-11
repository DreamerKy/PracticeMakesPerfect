package com.example.dreams.rxjava;

/**
 * Created by likaiyu on 2020/1/31.
 */
public class Observable<T> {
    OnSubscribe onSubscribe;

    private Observable(OnSubscribe onSubscribe){
        this.onSubscribe = onSubscribe;
    }

    /**
     * 静态方法不能访问类中定义的泛型，如果静态方法中数据类型不确定，可以在方法上定义泛型(static后面)。
     * 即上例中<T>就是声名一个泛型T，之后两个T是在使用泛型T。
     * @param onSubscribe
     * @param <T>
     * @return
     */
    public static <T>Observable<T> create(OnSubscribe<? extends T> onSubscribe){
        return new Observable<T>(onSubscribe);
    }


    public static <T> Observable<T> just(final T... t){
        return new Observable<T>(new OnSubscribe<T>() {

            @Override
            public void call(Observer<? super T> observer) {
                for(T t1:t){
                    observer.onNext(t1);
                }
                observer.onComplete();
            }
        });
    }

    public static <T> Observable<T> just(final T t){
        return new Observable<T>(new OnSubscribe<T>() {

            @Override
            public void call(Observer<? super T> observer) {
                observer.onNext(t);
                observer.onComplete();
            }
        });
    }


    void subscribe(Observer<? super T> observer) {
        Observable.subscribe(observer,this);
    }

    static<T> void subscribe(Observer<? super T> observer,Observable<T> observable){
        if(observer == null){
            throw new IllegalArgumentException("subscriber can not be null");
        }
        if(observable.onSubscribe == null){
            throw new IllegalStateException("onSubscribe function can not be null");
        }

        observer.onSubscribe();
        observable.onSubscribe.call(observer);
    }

    /**
     * map变换操作符
     * @param function
     * @param <R>
     * @return
     * T == 上一层传递过来的类型
     * R == 给下一层的类型
     *
     */
    public <R>Observable<R> map(Function<? super T,? extends R> function){
        return new Observable<>(new OnSubscribeMap<T,R>(this,function));
    }


    interface OnSubscribe<T> extends Action1<Observer<? super T>>{

    }

}
