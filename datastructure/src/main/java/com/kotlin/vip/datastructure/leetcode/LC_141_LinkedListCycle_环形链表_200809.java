package com.kotlin.vip.datastructure.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by likaiyu on 2020/8/9.
 *
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos
 * 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 */
public class LC_141_LinkedListCycle_环形链表_200809 {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(0);
        ListNode four = new ListNode(4);
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = two;
//        boolean hasCycle = hasCycle2(head);
        ListNode listNode = hasCycle(head);
        System.out.println(listNode.val);

    }

    /**
     * 快慢指针
     * @param head
     * @return
     */
    public static boolean hasCycle2(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }


    /**
     * hash表解法
     * @param head
     * @return
     */
    public static ListNode hasCycle(ListNode head) {
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
