package com.example.dreams.neteasehandler;

/**
 * Created by likaiyu on 2019/11/14.
 */
public class Looper {

    public MessageQueue messageQueue;
    static final ThreadLocal<Looper> mThreadLocal = new ThreadLocal<>();

    public Looper() {
        messageQueue = new MessageQueue();
    }

    public static void perpare() {
        if (mThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        mThreadLocal.set(new Looper());
    }

    public static Looper myLooper() {
        return mThreadLocal.get();
    }

    public static void loop() {
        Looper looper = myLooper();
        MessageQueue messageQueue = looper.messageQueue;

        while (true) {
            Message message = messageQueue.next();
            if (message != null && message.target != null) {
                message.target.dispatchMessage(message);
            }
        }

    }

}
