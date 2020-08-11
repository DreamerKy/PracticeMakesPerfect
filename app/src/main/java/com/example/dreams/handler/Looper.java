package com.example.dreams.handler;

/**
 * Created by likaiyu on 2019/7/18.
 *
 */

public class Looper {
    final MessageQueue messageQueue;
    private static ThreadLocal<Looper> threadLocal = new ThreadLocal<>();

    public Looper(){
        messageQueue = new MessageQueue();
    }

    public static void prepare(){
        if(threadLocal.get()!=null){
            throw new RuntimeException("only one Looper my be created");
        }
        threadLocal.set(new Looper());
    }

    public static Looper mLooper(){
        return threadLocal.get();
    }

    public static void loop(){
        Looper looper = mLooper();

        Message msg;

        for( ; ; ){
            msg = looper.messageQueue.next();
            if(msg == null || msg.target ==null){
                System.out.println("empty message");
                continue;
            }
            msg.target.handleMessage(msg);
        }
    }
}
