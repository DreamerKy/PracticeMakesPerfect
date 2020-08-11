package com.kotlin.vip.datastructure.stack;

/**
 * Created by likaiyu on 2019/12/19.
 */
public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();

}
