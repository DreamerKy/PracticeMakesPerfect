package com.example.dreams.okhttp;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by likaiyu on 2020/1/11.
 */
public class ConnectionPool {

    private long keepALive;
    private ArrayDeque<HttpConnection> httpConnectionDeque = null;
    private boolean hasStartClean;

    public ConnectionPool() {
        this(1, TimeUnit.MINUTES);
        httpConnectionDeque = new ArrayDeque<>();
    }

    public ConnectionPool(long keepALive, TimeUnit timeUnit) {
        this.keepALive = timeUnit.toMillis(keepALive);
    }

    private Runnable cleanRunnable = new Runnable() {
        @Override
        public void run() {
            //开启清理工作
            while (true) {
                long nextCheckCleanTime = clean(System.currentTimeMillis());
                if (nextCheckCleanTime == -1) {
                    //结束循环
                    hasStartClean = false;
                    return;
                }
                if (nextCheckCleanTime > 0) {
                    synchronized (ConnectionPool.class) {
                        try {
                            ConnectionPool.this.wait(nextCheckCleanTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    };

    private Executor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r, "ConnectionPool");
                    thread.setDaemon(true);
                    return thread;
                }
            }
    );


    public synchronized void putConnection(HttpConnection httpConnection) {
        //第一次put时就要开启且只在第一次开启清理线程
        if (!hasStartClean) {
            hasStartClean = true;
            threadPoolExecutor.execute(cleanRunnable);
        }
        httpConnectionDeque.add(httpConnection);
    }

    public synchronized HttpConnection getConnection(String host, int port) {
        Iterator<HttpConnection> iterator = httpConnectionDeque.iterator();
        while (iterator.hasNext()) {
            HttpConnection httpConnection = iterator.next();
            if (httpConnection.isConnectionAction(host, port)) {
                iterator.remove();
                return httpConnection;
            }
        }
        return null;
    }

    private long clean(long currentTimeMillis) {
        long idleRecordSave = -1;
        synchronized (this) {
            Iterator<HttpConnection> iterator = httpConnectionDeque.iterator();
            while (iterator.hasNext()) {
                HttpConnection httpConnection = iterator.next();
                //该链接闲置时间
                long idleTime = currentTimeMillis - httpConnection.getLastUseTime();
                if (idleTime > keepALive) {
                    iterator.remove();
                    httpConnection.closeSocket();
                    //继续清理
                    continue;
                }
                if (idleRecordSave < idleTime) {
                    idleRecordSave = idleTime;
                }
            }

            if (idleRecordSave >= 0) {
                return (keepALive - idleRecordSave);
            }
        }
        return idleRecordSave;
    }


}
