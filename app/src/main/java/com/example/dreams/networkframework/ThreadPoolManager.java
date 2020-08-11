package com.example.dreams.networkframework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by likaiyu on 2019/10/28.
 */
public class ThreadPoolManager {

    private static ThreadPoolManager threadPoolManager = new ThreadPoolManager();
    private ThreadPoolExecutor poolExecutor;

    private LinkedBlockingQueue<Runnable> linkedBlockingQueue;

    private ThreadPoolManager() {
        linkedBlockingQueue = new LinkedBlockingQueue<>();
        poolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                addTask(r);
            }
        });
        poolExecutor.execute(ddThread);
    }

    public static ThreadPoolManager getInstance() {
        return threadPoolManager;
    }

    public Runnable ddThread = new Runnable() {
        Runnable runnable = null;

        @Override
        public void run() {
            while (true) {
                try {
                    runnable = linkedBlockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (runnable != null) {
                    poolExecutor.execute(runnable);
                }
            }
        }
    };

    public void addTask(Runnable runnable) {
        if (runnable != null) {
            try {
                linkedBlockingQueue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
