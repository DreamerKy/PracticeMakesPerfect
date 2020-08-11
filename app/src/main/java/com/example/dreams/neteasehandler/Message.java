package com.example.dreams.neteasehandler;

/**
 * Created by likaiyu on 2019/11/14.
 */
public class Message {
    public Handler target;
    public Object object;
    public int what;

    public Message() {
    }

    public Message(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Message{" +
                "target=" + target +
                ", object=" + object +
                ", what=" + what +
                '}';
    }
}
