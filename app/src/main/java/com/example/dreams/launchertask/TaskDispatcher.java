package com.example.dreams.launchertask;

import android.content.Context;
import android.os.Build;
import android.os.Looper;

import com.example.dreams.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;

/**
 * Created by likaiyu on 2020/2/16.
 */
public class TaskDispatcher {

    private static Context sContext;
    private static boolean sHasInit;
    private static boolean sIsMainProcess;
    private HashMap<Class<? extends Task>, ArrayList<Task>> mDependedHashMap = new HashMap<>();
    private volatile List<Class<? extends Task>> mFinishedTasks = new ArrayList<>();
    private List<Task> mAllTasks = new ArrayList<>();
    private List<Class<? extends Task>> mClassAllTasks = new ArrayList<>();
    private List<Task> mNeedWaitTasks = new ArrayList<>();
    //需要wait的的Task的数量
    private AtomicInteger mNeedWaitCount = new AtomicInteger();
    private volatile List<Task> mMainThreadTasks = new ArrayList<>();
    private List<Future> mFutures = new ArrayList<>();

    public static void init(Context context) {
        if (context != null) {
            sContext = context;
            sHasInit = true;
            sIsMainProcess = Utils.isMainProcess(context);
        }
    }

    public static TaskDispatcher createInstance() {
        if (!sHasInit) {
            throw new RuntimeException("must call TaskDispatcher.init first");
        }
        return new TaskDispatcher();
    }

    public static boolean isMainProcess() {
        return sIsMainProcess;
    }

    public TaskDispatcher addTask(Task task) {
        if (task != null) {
            //收集该task所依赖的task,启动时需要处理
            collectDepends(task);
            mAllTasks.add(task);
            mClassAllTasks.add(task.getClass());
            //非主线程且需要wait的，主线程不需要CountDownLatch
            if(needWait(task)){
                mNeedWaitTasks.add(task);
                mNeedWaitCount.getAndIncrement();
            }
        }
        return this;
    }

    private boolean needWait(Task task) {
        return !task.runOnMainThread() && task.needWait();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @UiThread
    public void start(){
        if(Looper.getMainLooper() != Looper.myLooper()){
            throw new RuntimeException("must be called from UiThread");
        }
        if(mAllTasks.size()>0){
            mAllTasks = TaskSortUtil.getSortResult(mAllTasks,mClassAllTasks);
            sendAndExecuteAsyncTasks();
            executeTaskMain();
        }
    }

    private void executeTaskMain() {
        for (Task task : mAllTasks) {
            if(task.onlyInMainProcess() && !sIsMainProcess){
                markTaskDone(task);
            }else{
                sendTaskReal(task);
            }
            task.setmIsSend(true);
        }
    }

    private void sendTaskReal(Task task) {
        if(task.runOnMainThread()){
            mMainThreadTasks.add(task);
            if(task.needCall()){
                task.setTaskCallBack(new TaskCallBack() {
                    @Override
                    public void call() {
                        // TODO: 2020/2/18
                    }
                });

            }
        }else{
            //直接发送
            Future future = task.runOn().submit(new DispatchRunnable(task,this));
            mFutures.add(future);
        }
    }

    public void cancel(){
        for (Future mFuture : mFutures) {
            mFuture.cancel(true);
        }
    }

    void markTaskDone(Task task) {
        if(needWait(task)){
            mFinishedTasks.add(task.getClass());
            mNeedWaitTasks.remove(task);
            mNeedWaitCount.getAndDecrement();
        }
    }

    private void sendAndExecuteAsyncTasks() {


    }

    private void collectDepends(Task task) {
        if (task.dependsOn() != null && task.dependsOn().size() > 0) {
            for (Class<? extends Task> cls : task.dependsOn()) {
                if (mDependedHashMap.get(cls) == null) {
                    mDependedHashMap.put(cls, new ArrayList<Task>());
                }
                mDependedHashMap.get(cls).add(task);
                if (mFinishedTasks.contains(cls)) {
                    task.satisfy();
                }
            }

        }
    }

    public static Context getContext() {
        return sContext;
    }


    public void satisfyChildren(Task mTask) {
        ArrayList<Task> tasks = mDependedHashMap.get(mTask.getClass());
        if(tasks!=null && tasks.size()>0){
            for (Task task : tasks) {
                task.satisfy();
            }
        }
    }
}
