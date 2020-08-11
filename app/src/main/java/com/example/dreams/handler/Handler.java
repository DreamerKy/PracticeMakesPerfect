package com.example.dreams.handler;

/**
 * Created by likaiyu on 2019/7/18.
 *
 */

public class Handler {
    private Looper mLooper;
    private MessageQueue mQueue;
    public Handler(){
        mLooper = Looper.mLooper();
        mQueue = mLooper.messageQueue;
    }

    public void sendMessage(Message message){
        message.target = this;
        mQueue.enqueueMessage(message);
    }

    public void handleMessage(Message message){

    }

    public void dispatchMessage(Message message){
        handleMessage(message);
    }










}
