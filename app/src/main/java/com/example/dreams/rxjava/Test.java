package com.example.dreams.rxjava;

/**
 * Created by likaiyu on 2020/1/31.
 */
public class Test {
    public static void main(String[] args){

        Observable.just(1,2,3,4,5).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return integer+"***";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String item) {
                System.out.println("onNext--"+item);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Observer<? super Integer> observer) {
//                System.out.println("start call");
//                observer.onNext(1);
//                observer.onNext(2);
//                observer.onComplete();
//            }
//        }).map(new Function<Integer, String>() {
//            @Override
//            public String apply(Integer integer) {
//
//                return integer+"***";
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe() {
//                System.out.println("onSubscribe");
//            }
//
//            @Override
//            public void onNext(String item) {
//                System.out.println("onNext--"+item);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("onError");
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onComplete");
//            }
//        });
    }
}
