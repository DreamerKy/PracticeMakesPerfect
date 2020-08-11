package com.example.dreams.handler;

/**
 * Created by likaiyu on 2019/7/18.
 *
 */

public class Message {
    public Object obj;
    public Handler target;

    @Override
    public String toString() {
        return "Message{" +
                "obj=" + obj +
                ", target=" + target +
                '}';
    }
}
