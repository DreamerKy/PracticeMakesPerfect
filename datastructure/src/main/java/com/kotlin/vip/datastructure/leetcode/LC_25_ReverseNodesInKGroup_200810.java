package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/10.
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 *
 */
public class LC_25_ReverseNodesInKGroup_200810 {

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);
        ListNode seven = new ListNode(7);
        ListNode eight = new ListNode(8);
        ListNode nine = new ListNode(9);
        ListNode ten = new ListNode(10);
        ListNode eleven = new ListNode(11);
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        seven.next = eight;
        eight.next = nine;
        nine.next = ten;
        ten.next = eleven;
        ListNode p;
        p = reverseKGroup(head,3);
        while (p !=null){
            System.out.println(p.val);
            p = p.next;
        }
    }


    public static ListNode reverseKGroup(ListNode head,int k) {
        if (head == null || head.next == null) {
            return head;
        }
        //定义假节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //初始指针位置
        ListNode pre = dummy;
        ListNode end = dummy;
        while(end.next!=null){
            for (int i = 0; i < k && end != null; i++) {
                //根据参数k找到了反转区间的尾部
                end = end.next;
            }
            if(end == null){
                //到达链表尾部
                break;
            }
            //记录下一区间的头部位置，方便后面链接链表
            ListNode next = end.next;
            //断开需要反转区间进行反转操作
            end.next = null;
            //记录反转区间链表的开始位置
            ListNode start = pre.next;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = start;
        }
        return dummy.next;
    }

    private static ListNode reverse(ListNode start) {
        if(start == null || start.next == null){
            return start;
        }
        //前一个节点指针
        ListNode pre = null;
        //当前节点指针
        ListNode cur = start;
        //下一个节点指针
        ListNode next;
        while (cur != null) {
            //next指向下一个节点，保存当前节点后面的链表
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
