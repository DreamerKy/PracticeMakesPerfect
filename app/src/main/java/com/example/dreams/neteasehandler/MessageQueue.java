package com.example.dreams.neteasehandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by likaiyu on 2019/11/14.
 */
public class MessageQueue {

    private BlockingQueue<Message> blockingQueue = new ArrayBlockingQueue<>(50);

    public void enQueueMessage(Message message) {
        try {
            blockingQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Message next() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
