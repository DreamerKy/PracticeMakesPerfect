package com.example.dreams.handler;

/**
 * Created by likaiyu on 2019/7/28.
 */

public class LooperTwo {

    private MessageQueue queue ;
    ThreadLocal<LooperTwo> threadLocal = new ThreadLocal<>();

    public LooperTwo(){
        queue = new MessageQueue();
    }

    public void prepare(){
        if(threadLocal.get()!=null){
            throw new RuntimeException("only one looper maybe prepared");
        }
        threadLocal.set(new LooperTwo());
    }

    public LooperTwo myLooper(){
        return threadLocal.get();
    }

    public void loop(){
        LooperTwo looperTwo = myLooper();
        Message message;
        for(;;){
            message = looperTwo .queue.next();
            if(message==null || message.target == null){
                continue;
            }
            message.target.handleMessage(message);
        }

    }

}
