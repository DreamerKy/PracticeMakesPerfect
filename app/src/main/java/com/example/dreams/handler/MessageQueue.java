package com.example.dreams.handler;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by likaiyu on 2019/7/18.
 */

public class MessageQueue {
    Message[] mItems;
    int mPutIndex;

    private int mCount;
    private int mTakeIndex;
    Lock mLock;

    Condition getCondition;
    Condition addCondition;

    public MessageQueue() {
        mItems = new Message[50];
        mLock = new ReentrantLock();
        getCondition = mLock.newCondition();
        addCondition = mLock.newCondition();
    }

    Message next() {
        Message msg = null;
        try {
            mLock.lock();
            while (mCount <= 0) {
                System.out.println("MessageQueue is empty");
                getCondition.await();
            }
            msg = mItems[mTakeIndex];
            mTakeIndex = (++mTakeIndex >= mItems.length) ? 0 : mTakeIndex;
            mCount--;
            addCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }

        return msg;
    }

    public void enqueueMessage(Message message) {
        try {
            mLock.lock();
            while (mCount >= mItems.length) {
                addCondition.await();
            }
            mItems[mPutIndex] = message;
            mPutIndex = (++mPutIndex >= mItems.length) ? 0 : mPutIndex;
            mCount++;
            getCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }

    }

}
