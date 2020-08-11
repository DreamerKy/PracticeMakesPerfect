package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/3/12.
 * 二叉树是否是平衡二叉树
 * 后序遍历的应用
 */
public class LC_110_IsBalanceTree {

    public boolean isBalanced(TreeNode root) {
        return isBST(root).isBalanced;
    }

    public ReturnNode isBST(TreeNode root) {
        if (root == null) {
            return new ReturnNode(0, true);
        }

        ReturnNode left = isBST(root.left);
        ReturnNode right = isBST(root.right);
        if (!left.isBalanced || !right.isBalanced || Math.abs(left.depth - right.depth) > 1) {
            return new ReturnNode(Math.max(left.depth, right.depth) + 1, false);
        }
        return new ReturnNode(Math.max(left.depth, right.depth) + 1, true);
    }

    private class ReturnNode {
        boolean isBalanced;
        int depth;

        public ReturnNode(int depth, boolean isBalanced) {
            this.depth = depth;
            this.isBalanced = isBalanced;
        }
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
