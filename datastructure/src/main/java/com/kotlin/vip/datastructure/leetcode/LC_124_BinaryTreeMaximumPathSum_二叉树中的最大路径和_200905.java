package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/9/5.
 *
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 输入：[1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出：6
 *
 * 输入：[-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出：42
 *
 *
 */
public class LC_124_BinaryTreeMaximumPathSum_二叉树中的最大路径和_200905 {

    public static int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        TreeNode root_left = new TreeNode(9);
        TreeNode root_right = new TreeNode(20);
        TreeNode root_right_left = new TreeNode(15);
        TreeNode root_right_right = new TreeNode(7);
        root.left = root_left;
        root.right = root_right;
        root.right.left = root_right_left;
        root.right.right = root_right_right;

        int res = maxPathSum(root);
        System.out.println(res);
    }

    public static int maxPathSum(TreeNode root) {
        maxPathSumInternal(root);
        return maxSum;
    }

    public static int maxPathSumInternalII(TreeNode node) {
        if (node == null) return 0;
        int leftMax = Math.max(maxPathSumInternalII(node.left), 0);
        int rightMax = Math.max(maxPathSumInternalII(node.right), 0);
        int currentMax = node.val + leftMax + rightMax;
        maxSum = Math.max(maxSum, currentMax);
        return node.val + Math.max(leftMax, rightMax);
    }


    /**
     *  -10
     *  / \
     * 9  20
     *   /  \
     *  15   7
     * @param node
     * @return
     */
    private static int maxPathSumInternal(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftMax = Math.max(maxPathSumInternal(node.left), 0);
        int rightMax = Math.max(maxPathSumInternal(node.right), 0);
        int nodeMax = node.val + leftMax + rightMax;

        maxSum = Math.max(maxSum, nodeMax);
        return node.val + Math.max(leftMax, rightMax);

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
