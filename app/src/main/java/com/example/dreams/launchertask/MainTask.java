package com.example.dreams.launchertask;

/**
 * Created by likaiyu on 2020/2/19.
 */
public abstract class MainTask extends Task {

    @Override
    public boolean runOnMainThread() {
        return true;
    }
}
