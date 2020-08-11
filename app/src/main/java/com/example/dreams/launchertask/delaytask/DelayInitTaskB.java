package com.example.dreams.launchertask.delaytask;

import com.example.dreams.launchertask.MainTask;

/**
 * Created by likaiyu on 2020/2/19.
 */
public class DelayInitTaskB extends MainTask {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
