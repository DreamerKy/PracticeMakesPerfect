package com.example.dreams.launchertask;

import android.content.Context;
import android.os.Process;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * Created by likaiyu on 2020/2/16.
 */
public abstract class Task implements ITask {
    protected  String mTag = getClass().getSimpleName();
    protected Context mContext = TaskDispatcher.getContext();
    protected boolean mIsMainProcess = TaskDispatcher.isMainProcess();
    private volatile boolean mIsWaiting;//是否正在等待
    private volatile boolean mIsRunning;//是否正在执行
    private volatile boolean mIsFinished;//是否执行完成
    private volatile boolean mIsSend;//是否已经被分发
    private CountDownLatch mDepends = new CountDownLatch(dependsOn()==null?0:dependsOn().size());
    /**
     * 当前Task等待，让依赖的先执行
     */
    public void waitToSatisfy(){
        try {
            mDepends.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 依赖的Task执行完一个
     */
    public void satisfy(){
        mDepends.countDown();
    }

    public boolean needRunAsSoon(){
        return false;
    }

    /**
     * Task的默认优先级，运行在主线程则不需要改优先级
     * @return
     */
    @Override
    public int priority() {
        return Process.THREAD_PRIORITY_BACKGROUND;
    }

    @Override
    public ExecutorService runOn() {
        return DispatcherExecutor.getIOExecutor();
    }

    @Override
    public boolean runOnMainThread() {
        return false;
    }

    @Override
    public Runnable getTailRunnable() {
        return null;
    }

    @Override
    public void setTaskCallBack(TaskCallBack callBack) {

    }

    @Override
    public boolean needCall() {
        return false;
    }

    /**
     * 是否只在主进程，默认是
     * @return
     */
    @Override
    public boolean onlyInMainProcess() {
        return true;
    }

    /**
     * 异步线程执行的Task是否需要在被调用await的时候等待
     * @return
     */
    @Override
    public boolean needWait() {
        return false;
    }

    @Override
    public List<Class<? extends Task>> dependsOn() {
        return null;
    }

    public boolean ismIsWaiting() {
        return mIsWaiting;
    }

    public void setmIsWaiting(boolean mIsWaiting) {
        this.mIsWaiting = mIsWaiting;
    }

    public boolean ismIsRunning() {
        return mIsRunning;
    }

    public void setmIsRunning(boolean mIsRunning) {
        this.mIsRunning = mIsRunning;
    }

    public boolean ismIsFinished() {
        return mIsFinished;
    }

    public void setmIsFinished(boolean mIsFinished) {
        this.mIsFinished = mIsFinished;
    }

    public boolean ismIsSend() {
        return mIsSend;
    }

    public void setmIsSend(boolean mIsSend) {
        this.mIsSend = mIsSend;
    }
}
