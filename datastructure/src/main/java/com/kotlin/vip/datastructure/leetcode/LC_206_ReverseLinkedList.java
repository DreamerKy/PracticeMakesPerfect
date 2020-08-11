package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/9.
 *
 * 反转一个单链表。
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 */
public class LC_206_ReverseLinkedList {

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        ListNode listNode = reverseLinkedList(head);
        ListNode p = listNode;
        while (p !=null){
            System.out.println(p.val);
            p = p.next;
        }
    }

    // 1->2->3->4->5

    public static ListNode reverseLinkedList(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        ListNode next;
        while (current != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
