package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/3/12.
 * 链表反转
 */
public class LC_24_ReverseLinkedList {

    public ListNode reverseLinkedList(ListNode head) {
        ListNode pre = null, cur = head, next = null;
        while(cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;

    }

    public ListNode reverseLinkedList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = reverseLinkedList2(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }



    public ListNode reverseLinkedListt(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;
        while(cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }



    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        ListNode cur = head;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


















}
