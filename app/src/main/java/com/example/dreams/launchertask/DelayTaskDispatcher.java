package com.example.dreams.launchertask;

import android.os.Looper;
import android.os.MessageQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by likaiyu on 2020/2/19.
 */
public class DelayTaskDispatcher {

    private Queue<Task> mQueue = new LinkedList<>();

    private MessageQueue.IdleHandler idleHandler = new MessageQueue.IdleHandler() {
        @Override
        public boolean queueIdle() {
            if(mQueue.size()>0){
                Task task = mQueue.poll();
                new DispatchRunnable(task).run();
            }
            return mQueue.size()>0;
        }
    };

    public DelayTaskDispatcher add(Task task){
        mQueue.add(task);
        return this;
    }

    public void start(){
        Looper.myQueue().addIdleHandler(idleHandler);
    }

}
