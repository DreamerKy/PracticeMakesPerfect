package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/3/14.
 * 删除排序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 输入: 1->1->2
 * 输出: 1->2
 */
public class LC_83_DeleteDuplicatesInList_删除排序链表中的重复元素_200314 {

    public ListNode deleteDuplicates(ListNode head) {

        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next ==null){
            return head;
        }
        head.next = deleteDuplicates2(head.next);
        if(head.val == head.next.val){
            head =head.next;
        }
        return head;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}


