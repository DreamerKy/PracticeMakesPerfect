package com.example.dreams.launchertask;

import android.os.Process;

/**
 * Created by likaiyu on 2020/2/18.
 */
class DispatchRunnable implements Runnable {
    private Task mTask;
    private TaskDispatcher mTaskDispatcher;

    public DispatchRunnable(Task mTask) {
        this.mTask = mTask;
    }

    public DispatchRunnable(Task task, TaskDispatcher taskDispatcher) {
        this.mTask = task;
        this.mTaskDispatcher = taskDispatcher;
    }

    @Override
    public void run() {
        Process.setThreadPriority(mTask.priority());
        mTask.setmIsWaiting(true);
        mTask.waitToSatisfy();
        mTask.setmIsRunning(true);
        mTask.run();
        Runnable tailRunnable = mTask.getTailRunnable();
        if(tailRunnable != null){
            tailRunnable.run();
        }
        if(!mTask.needCall() || !mTask.runOnMainThread()){
            mTask.setmIsFinished(true);
            if(mTaskDispatcher!=null){
                mTaskDispatcher.satisfyChildren(mTask);
                mTaskDispatcher.markTaskDone(mTask);
            }
        }
    }
}
