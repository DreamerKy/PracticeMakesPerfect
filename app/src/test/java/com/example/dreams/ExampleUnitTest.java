package com.example.dreams;

import android.app.Activity;

import com.example.dreams.handler.Handler;
import com.example.dreams.handler.Looper;
import com.example.dreams.handler.Message;

import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static Map<Thread, Integer> threadData = new HashMap<>();
    ThreadLocal<Integer> threadLocal = new ThreadLocal();
    Handler handler;
    @Test
    public void addition_isCorrect(Activity activity) throws Exception {
        WeakReference<Activity> weakReference = new WeakReference<>(activity);
        Activity activity1 = weakReference.get();

        Observable<String> observable0 = Observable.unsafeCreate(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("呵呵");
            }
        });


        Observable<String> observable1 = observable0.subscribeOn(Schedulers.io());
        Observable<String> observable2 = observable1.subscribeOn(Schedulers.newThread());

        Observable<String> observable3 = observable2.observeOn(AndroidSchedulers.mainThread());
        observable3.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        handler = new Handler() {
                            @Override
                            public void handleMessage(Message message) {
                                super.handleMessage(message);

                            }
                        };
                        Looper.loop();
                    }
                }
        ).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj = Thread.currentThread().getName() + "发送消息";
                handler.sendMessage(message);
            }
        }).start();

        /*final Business business = new Business();
        new Thread(
                new Runnable() {

                    @Override
                    public void run() {

                        for (int i = 1; i <= 50; i++) {
                            business.sub(i);
                        }

                    }
                }
        ).start();

        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }*/

        /*for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    threadLocal.set(data);

                    MyThreadSopeData.getThreadInstance().setAge(data);
                    MyThreadSopeData.getThreadInstance().setName("name " + data);

                    System.out.println(Thread.currentThread().getName() + " has put data : " + data);
                    new A().get();
//                    new B().get();
                }
            }).start();

        }*/

       /* ShareData data = new ShareData();

        new Thread(new MyRunnableOne(data)).start();
        new Thread(new MyRunnableTwo(data)).start();
*/
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //ExecutorService threadPool = Executors.newCachedThreadPool();
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            final int task = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 10; j++) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
                    }
                }
            });
        }


    }

    class MyRunnableOne implements Runnable {
        private ShareData data;

        public MyRunnableOne(ShareData data) {
            this.data = data;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        }
    }

    class MyRunnableTwo implements Runnable {
        private ShareData data;

        public MyRunnableTwo(ShareData data) {
            this.data = data;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                data.decrement();
            }
        }
    }

    class A {
        public void get() {
            int data = threadLocal.get();
            System.out.println("A from " + Thread.currentThread().getName() + " data " + data);
        }
    }


    class B {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("B from " + Thread.currentThread().getName() + " data " + data);
        }
    }

//    class B {
//        public void get() {
//            System.out.println("B from " + Thread.currentThread().getName() + " data " + data);
//        }
//    }

    class Business {
        private boolean bShouldSub = true;

        public synchronized void sub(int i) {
            while (!bShouldSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 1; j <= 10; j++) {
                System.out.println("sub thread sequence of " + j + ",loop of " + i);
            }

            bShouldSub = false;
            this.notify();
        }

        public synchronized void main(int i) {
            while (bShouldSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            for (int j = 1; j <= 10; j++) {
                System.out.println("main thread sequence of " + j + ",loop of " + i);
            }
            bShouldSub = true;
            this.notify();
        }

    }


}