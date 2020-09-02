package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/17.
 *
 * 设计实现双端队列。
 * 你的实现需要支持以下操作：
 *
 *      LC_641_DesignCircularDeque_200817(k)：构造函数,双端队列的大小为k。
 *      insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 *      insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 *      deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 *      deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 *      getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 *      getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 *      isEmpty()：检查双端队列是否为空。
 *      isFull()：检查双端队列是否满了。
 *
 */
public class LC_641_DesignCircularDeque_设计循环双端队列_200817 {

    private int capacity;
    private int[] arr;
    private int front;
    private int rear;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public LC_641_DesignCircularDeque_设计循环双端队列_200817(int k) {
        capacity = k+1;
        arr = new int[capacity];
        //头部指向第一个存放元素的位置
        //插入时先减再赋值
        //删除时索引+1（取模）
        front = 0;
        //尾部指向下一个插入元素的位置
        //插入时先赋值在加
        //删除时索引-1（取模）
        rear = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + capacity) % capacity;
        arr[front]  = value;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        arr[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        rear = (rear - 1 + capacity) % capacity;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if(isEmpty()){
            return -1;
        }
        return arr[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return arr[(rear - 1 + capacity) % capacity];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return front == rear;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (front + 1) % capacity == rear;
    }

}
