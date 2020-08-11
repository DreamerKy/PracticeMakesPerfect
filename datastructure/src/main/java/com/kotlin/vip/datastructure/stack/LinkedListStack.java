package com.kotlin.vip.datastructure.stack;

import com.kotlin.vip.datastructure.LinkedList;

/**
 * Created by likaiyu on 2019/12/19.
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;


    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }
}
