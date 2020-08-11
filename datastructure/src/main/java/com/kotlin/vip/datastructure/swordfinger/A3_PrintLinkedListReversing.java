package com.kotlin.vip.datastructure.swordfinger;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by likaiyu on 2020/1/10.
 * 从尾到头打印链表
 * 输入一个链表，从尾到头打印链表每个节点的值
 */
public class A3_PrintLinkedListReversing {

    public static void printListReverse(ListNode headNode){
        ListNode curNode = headNode;
        Stack<ListNode> stack = new Stack<>();
        while(curNode != null){
            stack.push(headNode);
            curNode = curNode.next;
        }
        while(!stack.isEmpty()){
            System.out.println(stack.pop().val);
        }
    }

    public static ArrayList<Integer> printListReverse2(ListNode headNode){
        ArrayList<Integer> list = new ArrayList<>();
        if(headNode != null){
            if(headNode.next!=null){
                list = printListReverse2(headNode.next);
            }
            list.add(headNode.val);
        }
        return list;
    }


}
