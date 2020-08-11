package com.example.dreams.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by likaiyu on 2019/12/19.
 */
public class TraditionalThreadCommunication {

    static String[] ss = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
    static String[] SS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public static void main(String[] args) {
        final Business business = new Business();
        final ConditionBusiness conditionBusiness = new ConditionBusiness();
        System.out.println("began");

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    business.sub(i);
                }
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            business.main(ss[i]);
        }
        */

        //Thread2
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    conditionBusiness.subTwo(ss[i]);
                }
            }
        }).start();
        //Thread3
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    conditionBusiness.subThree(SS[i]);
                }
            }
        }).start();

        //主线程
        for (int i = 0; i < 10; i++) {
            conditionBusiness.main(i);
        }


    }

    static class Business {

        private boolean shouldBeSub = true;

        public synchronized void sub(int i) {
            while (!shouldBeSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("sub  thread " + i);
            shouldBeSub = false;
            this.notify();

        }

        public synchronized void main(String i) {
            while (shouldBeSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("main thread " + i);
            shouldBeSub = true;
            this.notify();
        }
    }

    static class ConditionBusiness {

        Lock lock = new ReentrantLock();
        Condition conditionOne = lock.newCondition();
        Condition conditionTwo = lock.newCondition();
        Condition conditionThree = lock.newCondition();
        private int shouldBeSub = 1;

        public void main(int i) {
            lock.lock();
            try {
                while (shouldBeSub != 1) {
                    try {
                        conditionOne.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(i+" ");
                shouldBeSub = 2;
                conditionTwo.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void subTwo(String i) {
            lock.lock();
            try {
                while (shouldBeSub != 2) {
                    try {
                        conditionTwo.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(i+" ");
                shouldBeSub = 3;
                conditionThree.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }

        public void subThree(String i) {
            lock.lock();
            try {
                while (shouldBeSub != 3) {
                    try {
                        conditionThree.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(i+"\r\n");
                shouldBeSub = 1;
                conditionOne.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }


}

