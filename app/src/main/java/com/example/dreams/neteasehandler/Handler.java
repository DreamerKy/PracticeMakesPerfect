package com.example.dreams.neteasehandler;

/**
 * Created by likaiyu on 2019/11/14.
 */
public class Handler {

    MessageQueue messageQueue;
    Looper mLooper;

    public Handler() {
        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException("Can't create handler inside thread" + Thread.currentThread() + "that has not called Looper.prepare.");
        }
        messageQueue = mLooper.messageQueue;
    }

    public void sendMessage(Message message) {
        enQueueMessage(message);
    }

    private void enQueueMessage(Message message) {
        message.target = this;
        messageQueue.enQueueMessage(message);
    }

    public void dispatchMessage(Message message) {
        handleMessage(message);
    }

    private void handleMessage(Message message) {

    }

}
