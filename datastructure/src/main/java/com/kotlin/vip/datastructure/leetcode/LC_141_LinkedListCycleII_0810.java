package com.kotlin.vip.datastructure.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by likaiyu on 2020/8/10.
 *
 * 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。
 * 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos
 * 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 *
 */
public class LC_141_LinkedListCycleII_0810 {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(0);
        ListNode four = new ListNode(4);
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = two;
        ListNode listNode = detectCycle2(head);
//        ListNode listNode = detectCycle(head);
        System.out.println(listNode.val);

    }

    /**
     * 快慢指针
     * @param head
     * @return
     */
    public static ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode intersect = getIntersect(head);
        ListNode prtl1 = head;
        ListNode prtl2 = intersect;
        while (prtl1 != prtl2) {
            prtl1 = prtl1.next;
            prtl2 = prtl2.next;
        }
        return prtl1;
    }

    /**
     * 获取快慢指针相遇点
     * @param head
     * @return
     */
    private static ListNode getIntersect(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return slow;
            }
        }
        return null;
    }


    /**
     * hash表解法
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        Set<ListNode> nodes = new HashSet<>();
        ListNode current = head;
        while(current.next !=null){
            if(nodes.contains(current)){
                return current;
            }else{
                nodes.add(current);
            }
            current = current.next;
        }
        return null;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
