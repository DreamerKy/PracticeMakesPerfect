package com.kotlin.vip.datastructure.queue;

import com.kotlin.vip.datastructure.LinkedList;

/**
 * Created by likaiyu on 2019/12/19.
 */
public class LinkedListQueue<E> implements Queue<E> {

    private LinkedList<E> list;

    public LinkedListQueue (){
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void enqueue(Object o) {

    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E getFront() {
        return null;
    }
}
