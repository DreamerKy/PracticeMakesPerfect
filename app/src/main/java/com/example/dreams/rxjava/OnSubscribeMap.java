package com.example.dreams.rxjava;

/**
 * Created by likaiyu on 2020/1/31.
 */
public class OnSubscribeMap<T,R> implements Observable.OnSubscribe<R> {

    private final Observable<T> source;
    private final Function<? super T, ? extends R> transformer;

    public OnSubscribeMap(Observable<T> source, Function<? super T,? extends R> transformer){
        this.source = source;
        this.transformer = transformer;
    }

    @Override
    public void call(Observer<? super R> observer) {
        MapSubscriber<T,R> parent = new MapSubscriber<T,R>(observer,transformer);
        source.subscribe(parent);
    }

    static final class MapSubscriber<T,R> implements Observer<T>{

        private final Observer<? super R> actual;
        private final Function<? super T, ? extends R> mapper;

        public MapSubscriber(Observer<? super R> actual, Function<? super T,? extends R> mapper){
            this.actual = actual;
            this.mapper = mapper;
        }


        @Override
        public void onSubscribe() {
        }

        @Override
        public void onNext(T item) {
            R apply = mapper.apply(item);
            actual.onNext(apply);
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
















}
