package com.kotlin.vip.datastructure.leetcode;

import java.util.Stack;

/**
 * Created by likaiyu on 2020/8/6.
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 */
public class LC_155_Min_Stack_200806 {

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(2);
        stack.push(5);
        stack.push(8);
        stack.push(6);
        stack.pop();
        int top = stack.top();
        int min = stack.getMin();
        System.out.println("top : " + top + " min : " + min);
    }

    static class MinStack {

        Stack<Integer> normalStack;
        Stack<Integer> minStack;

        public MinStack() {
            normalStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            normalStack.push(x);
            if (minStack.isEmpty()) {
                minStack.push(x);
                return;
            }
            if (minStack.peek() > x) {
                minStack.push(x);
            } else {
                minStack.push(minStack.peek());
            }
        }

        public void pop() {
            normalStack.pop();
            minStack.pop();
        }

        public int top() {
            return normalStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

}
