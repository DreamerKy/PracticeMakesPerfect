package com.kotlin.vip.datastructure.leetcode;

/**
 *
 * practiced by likaiyu on 2020/8/13.
 * Created by likaiyu on 2020/8/6.
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class LC_21_MergeTwoSortedLists_合并两个有序链表_200813 {

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(4);
        one.next = two;
        two.next = three;

        ListNode _one = new ListNode(1);
        ListNode _two = new ListNode(3);
        ListNode _three = new ListNode(4);
        _one.next = _two;
        _two.next = _three;

        ListNode listNode = mergeTwoLists2(one, _one);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    private static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        //两个链表都不为空时
        while(cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                prev.next = cur1;
                cur1 = cur1.next;
            }else{
                prev.next = cur2;
                cur2 = cur2.next;
            }
            prev = prev.next;
        }

        //如果一个为空了，就把另一个直接连到后面
        if(cur1 == null){
            prev.next = cur2;
        }else{
            prev.next = cur1;
        }
        return dummy.next;
    }


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode prevHead = new ListNode(-1);
        ListNode prev = prevHead;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                prev.next = cur1;
                cur1 = cur1.next;
            } else {
                prev.next = cur2;
                cur2 = cur2.next;
            }
            prev = prev.next;
        }
        prev.next = cur1 == null ? cur2 : cur1;
        return prevHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
