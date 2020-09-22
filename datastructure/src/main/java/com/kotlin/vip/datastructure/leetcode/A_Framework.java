package com.kotlin.vip.datastructure.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 常见算法、数据结构遍历方式
 * Created by likaiyu on 2020/9/5.
 */

class A_Framework {

    /**
     * 数组遍历框架，典型线性迭代
     * @param arr
     */
    void traverse(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 迭代访问 arr[i]
        }
    }

    /**
     * 链表 遍历框架，兼具爹迭代和递归结构
     */
    class ListNode{
        int val;
        ListNode next;
    }

    void traverse(ListNode head) {
        for (ListNode p = head; p != null; p = p.next){
            // 迭代访问 p.val
        }
    }

    void traverseII(ListNode head){
        // 递归访问 head.val
        traverse(head.next);
    }

    /**
     * 二叉树遍历框架
     */
    class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode[] adj() {
            return new TreeNode[0];
        }
    }

    void traverse(TreeNode root) {
        traverse(root.left);
        traverse(root.right);
    }

    /**
     * N叉数遍历框架
     */
    class TreeNodeN{
        int val;
        TreeNodeN[] children;
    }

    void traverse(TreeNodeN root){
        for(TreeNodeN child : root.children){
            traverse(child);
        }
    }

    // N叉数遍历又可以扩展为图的遍历，因为图是几棵 N 叉树的结合体


    /**
     * 回溯算法框架
     */

    /*

    result = []
    backtrack(路径, 选择列表):
        if 满足结束条件:
            result.add(路径)
            return

        for 选择 in 选择列表:
        # 做选择
        将该选择从选择列表移除
        路径.add(选择)
        backtrack(路径, 选择列表)
        # 撤销选择
        路径.remove(选择)
        将该选择再加入选择列表

    */

    /**
     * BFS框架
     * 计算起点 start 到终点 target 的最近距离
     */

    int BFS(TreeNode start, TreeNode target) {
        //核心数据结构
        Queue<TreeNode> q = new LinkedList<>();
        //避免走回头路
        Set<TreeNode> visited = new HashSet<>();

        //起点加入队列
        q.offer(start);
        visited.add(start);
        //记录扩散步数
        int step = 0;

        while (!q.isEmpty()) {
            int sz = q.size();
            //将当前队列里的所有节点往四周扩散
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                //判断是否到达终点
                if (cur.val == target.val) {
                    return step;
                }
                //将 cur 的相邻节点加入队列
                for (TreeNode node : cur.adj()) {
                    if (!visited.contains(node)) {
                        q.offer(node);
                        visited.add(node);
                    }
                }
            }
            //更新步数
            step++;
        }
        // 找不到结果
        return -1;
    }









}
