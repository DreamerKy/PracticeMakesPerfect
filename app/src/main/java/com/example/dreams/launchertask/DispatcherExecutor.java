package com.example.dreams.launchertask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by likaiyu on 2020/2/16.
 */
public class DispatcherExecutor {
    private static ThreadPoolExecutor sCPUThreadPoolExecutor;
    private static ExecutorService sIOThreadPoolExecutor;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    public static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 5));
    private static final int MAXIMUM_POOL_SIZE = CORE_POOL_SIZE;
    private static final int KEEP_ALIVE_SECOND = 5;
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<>();
    private static final DefaultThreadFactory sThreadFactory = new DefaultThreadFactory();
    private static final RejectedExecutionHandler sHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            Executors.newCachedThreadPool().execute(r);
        }
    };
    static{
        sCPUThreadPoolExecutor  = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_SECOND,
                TimeUnit.SECONDS,
                sPoolWorkQueue,
                sThreadFactory,
                sHandler
        );
        sCPUThreadPoolExecutor.allowCoreThreadTimeOut(true);
        sIOThreadPoolExecutor = Executors.newCachedThreadPool(sThreadFactory);
    }

    public static ThreadPoolExecutor getCPUExecutor(){
        return sCPUThreadPoolExecutor;
    }

    public static ExecutorService getIOExecutor(){
        return sIOThreadPoolExecutor;
    }

    public static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory(){
            SecurityManager s = System.getSecurityManager();
            group = (s != null)?s.getThreadGroup():Thread.currentThread().getThreadGroup();
            namePrefix = "TaskDispatcherPool-"+poolNumber.getAndIncrement()+"-Thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group,r,namePrefix+threadNumber.getAndIncrement());
            if(t.isDaemon()){
                t.setDaemon(false);
            }
            if(t.getPriority() != Thread.NORM_PRIORITY){
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

}
