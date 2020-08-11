package com.example.dreams;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by likaiyu on 2019/7/28.
 */

public class ShareData {
    private int count = 0;
    private AtomicInteger integer = new AtomicInteger(6);

    public synchronized void increment() {
        count++;
        integer.addAndGet(2);
        System.out.println("increment -- " + count);
    }

    public synchronized void decrement() {
        count--;
        System.out.println("decrement -- " + count);
    }
}
