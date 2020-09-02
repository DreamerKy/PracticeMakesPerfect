package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/3/13.
 * 反转二叉树
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class LC_226_InvertTree_翻转二叉树_200313 {

    public TreeNode invertTree(TreeNode root) {
        //先找递归结束条件（节点为空）
        if (root == null) {
            return null;
        }
        //该层递归要做什么（交换左右节点）
        root.left = invertTree(root.right);
        root.right = invertTree(root.left);
        //要返回什么（返回本节点）
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }






























}
