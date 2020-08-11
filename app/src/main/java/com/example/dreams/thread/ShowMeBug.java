package com.example.dreams.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：控制多个线程按顺序交替执行
 * 修改程序，使最终输出顺序按以下顺序输出
 * 注：不允许修改sleep时间
 * current thread is PrintThread1
 * current thread is PrintThread2
 * current thread is PrintThread3
 * current thread is PrintThread1
 * current thread is PrintThread2
 * current thread is PrintThread3
 * ...
 */
public class ShowMeBug {
    static boolean flag = true;

    static class PrintThread1 extends Thread {

        private PrintBusiness printBusiness;

        public PrintThread1(PrintBusiness printBusiness) {
            this.printBusiness = printBusiness;
        }

        @Override
        public void run() {
            super.run();
            while (flag) {
                // todo
                printBusiness.printOne("current thread is PrintThread1");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class PrintThread2 extends Thread {

        private PrintBusiness printBusiness;

        public PrintThread2(PrintBusiness printBusiness) {
            this.printBusiness = printBusiness;
        }

        @Override
        public void run() {
            super.run();
            while (flag) {
                // todo
                printBusiness.printTwo("current thread is PrintThread2");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class PrintThread3 extends Thread {

        private PrintBusiness printBusiness;

        public PrintThread3(PrintBusiness printBusiness) {
            this.printBusiness = printBusiness;
        }

        @Override
        public void run() {
            super.run();
            while (flag) {
                // todo
                printBusiness.printThree("current thread is PrintThread3");
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        PrintBusiness printBusiness = new PrintBusiness();
        new PrintThread1(printBusiness).start();
        new PrintThread2(printBusiness).start();
        new PrintThread3(printBusiness).start();

        // 执行5s
        Thread.sleep(5000);
        flag = false;
    }


    static class PrintBusiness {

        Lock lock = new ReentrantLock();
        Condition conditionOne = lock.newCondition();
        Condition conditionTwo = lock.newCondition();
        Condition conditionThree = lock.newCondition();
        private int shouldBeSub = 1;

        public void printOne(String s) {
            lock.lock();
            try {
                while (shouldBeSub != 1) {
                    try {
                        conditionOne.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(s);
                shouldBeSub = 2;
                conditionTwo.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printTwo(String s) {
            lock.lock();
            try {
                while (shouldBeSub != 2) {
                    try {
                        conditionTwo.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(s);
                shouldBeSub = 3;
                conditionThree.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }

        public void printThree(String s) {
            lock.lock();
            try {
                while (shouldBeSub != 3) {
                    try {
                        conditionThree.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(s);
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