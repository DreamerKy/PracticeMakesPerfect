package com.kotlin.vip.datastructure.queue;

/**
 * Created by likaiyu on 2019/12/19.
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
