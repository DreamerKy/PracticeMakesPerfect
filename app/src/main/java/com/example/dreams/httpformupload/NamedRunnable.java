package com.example.dreams.httpformupload;

/**
 * Created by likaiyu on 2020/4/25.
 */
public abstract class NamedRunnable implements Runnable {
    @Override
    public void run() {
        execute();
    }
    protected abstract void execute();
}
