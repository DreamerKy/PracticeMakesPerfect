package com.example.dreams.launchertask;

import android.os.Process;

import java.util.List;
import java.util.concurrent.Executor;

import androidx.annotation.IntRange;

/**
 * Created by likaiyu on 2020/2/16.
 */
public interface ITask {

    /**
     * 优先级范围，可根据Task重要程度及工作量指定
     */
    @IntRange(from = Process.THREAD_PRIORITY_FOREGROUND,to = Process.THREAD_PRIORITY_LOWEST)
    int priority();

    void run();

    /**
     * Task 执行所在的线程池，可指定，一般默认
     * @return
     */
    Executor runOn();

    /**
     * 依赖关系
     */
    List<Class<? extends Task>> dependsOn();

    /**
     * 异步线程执行的Task是否需要在被调用await的时候等待，默认不需要
     * @return
     */
    boolean needWait();

    /**
     * 是否在主线程执行
     * @return
     */
    boolean runOnMainThread();

    /**
     * 是否只在主线程执行
     * @return
     */
    boolean onlyInMainProcess();

    /**
     * Task主任务执行完之后需要执行的任务
     * @return
     */
    Runnable getTailRunnable();

    void setTaskCallBack(TaskCallBack callBack);

    boolean needCall();


}
