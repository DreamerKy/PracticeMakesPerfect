package com.kotlin.vip.viewtouchevent.producerandconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by likaiyu on 2019/10/30.
 */
public class ProducorAndConsumer {

    class SetUp {
        private void main() {
            BlockingQueue queue = new LinkedBlockingQueue();
            Producor producor = new Producor(queue);
            Consumer consumer = new Consumer(queue);
            new Thread(producor).start();
            new Thread(consumer).start();
        }
    }

    class Producor implements Runnable {

        private final BlockingQueue queue;

        Producor(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    queue.put(produce());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private Object produce() {
            return null;
        }
    }

    class Consumer implements Runnable {
        private final BlockingQueue blockingQueue;

        Consumer(BlockingQueue blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    conusme(blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void conusme(Object take) {

        }

    }


}
