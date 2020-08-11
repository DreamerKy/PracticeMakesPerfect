package com.kotlin.vip.datastructure.swordfinger;

import com.kotlin.vip.datastructure.queue.ArrayQueue;
import com.kotlin.vip.datastructure.queue.Queue;

/**
 * Created by likaiyu on 2020/1/22.
 * 两个队列实现栈
 */
public class A6_TwoQueueStack<E> {
    private Queue<E> queue1 = new ArrayQueue<>();
    private Queue<E> queue2 = new ArrayQueue<>();

    public void push(E e){
        queue1.enqueue(e);
    }

    public E pop(){
        //从queue1中出队，除最后一个，其余放入queue2
        E data = null;
        while(!queue1.isEmpty()){
            data = queue1.dequeue();
            if(queue1.isEmpty()){
                break;
            }
            queue2.enqueue(data);
        }
        //获取最后一个出栈元素后，将其余的在放回queue1中
        while (!queue2.isEmpty()){
            queue1.enqueue(queue2.dequeue());
        }
        return data;
    }



}
