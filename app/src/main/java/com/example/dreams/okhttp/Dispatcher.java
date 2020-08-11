package com.example.dreams.okhttp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by likaiyu on 2020/1/21.
 */
public class Dispatcher {

    private int maxRequest = 64;
    private int maxRequestPerHost = 5;
    private ExecutorService executorService;

    private Deque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque<>();
    private Deque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque<>();

    public Dispatcher(){}

    public Dispatcher (ExecutorService executorService){
        this.executorService = executorService;
    }

    public void enqueue(RealCall.AsyncCall call){
        System.out.println("请求入队");
        if(runningAsyncCalls.size()<maxRequest && runningCallsForHost(call)<maxRequestPerHost){
            runningAsyncCalls.add(call);
            System.out.println("请求执行");
            executorService().execute(call);
        }else{
            readyAsyncCalls.add(call);
        }
    }

    //线程池
    public ExecutorService executorService(){
        if(executorService == null){
            executorService = new ThreadPoolExecutor(0,
                    Integer.MAX_VALUE,
                    60L,
                    TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread thread = new Thread(r);
                            thread.setName("自定义线程池。。。");
                            thread.setDaemon(false);//不是守护线程
                            return thread;
                        }
                    });

        }
        return executorService;
    }

    private int runningCallsForHost(RealCall.AsyncCall call) {

        int count = 0;
        if(runningAsyncCalls.isEmpty()){
            return 0;
        }

        SocketRequestServer srs = new SocketRequestServer();
        //遍历运行队列里的所有任务，对比host
        for(RealCall.AsyncCall asyncCall:runningAsyncCalls){
            if (srs.getHost(asyncCall.getRequest()).equals(srs.getHost(call.getRequest()))) {
                count++;
            }
        }
        return count;
    }


    public void finish(RealCall.AsyncCall call) {
        System.out.println("请求结束");
        //回收
        runningAsyncCalls.remove(call);
        if(readyAsyncCalls.isEmpty()){
            return;
        }
        for(RealCall.AsyncCall readyAsyncCall :readyAsyncCalls){
            readyAsyncCalls.remove(readyAsyncCall);
            runningAsyncCalls.add(readyAsyncCall);
            executorService().execute(readyAsyncCall);
        }
    }
}
