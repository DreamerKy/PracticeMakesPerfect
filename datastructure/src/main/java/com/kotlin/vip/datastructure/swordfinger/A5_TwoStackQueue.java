package com.kotlin.vip.datastructure.swordfinger;


import java.util.Stack;

/**
 * Created by likaiyu on 2020/1/16.
 * 两个栈实现队列
 */
public class A5_TwoStackQueue {

    private Stack<Integer> stackOne = new Stack<>();
    private Stack<Integer> stackTwo = new Stack<>();

    public void enqueue(int e){
        stackOne.push(e);
    }
    public int dequeue() throws Exception {
        if(stackOne.isEmpty()){
            throw new Exception("queue is empty!");
        }
        while(stackTwo.isEmpty()){
            while(!stackOne.isEmpty()){
                stackTwo.push(stackOne.pop());
            }
        }
        Integer value = stackTwo.pop();
        while(!stackTwo.isEmpty()){
            stackOne.push(stackTwo.pop());
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(1/2);
    }

}
