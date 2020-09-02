package com.kotlin.vip.datastructure.leetcode;

/**
 * Practiced by likaiyu on 2020/8/14.
 * Created by likaiyu on 2020/3/14.
 * 二叉树最小深度
 *
 * 这道题的关键是搞清楚递归结束条件
 *
 * 叶子节点的定义是左孩子和右孩子都为 null 时叫做叶子节点
 * 当 root 节点左右孩子都为空时，返回 1
 * 当 root 节点左右孩子有一个为空时，返回不为空的孩子节点的深度
 * 当 root 节点左右孩子都不为空时，返回左右孩子较小深度的节点值
 *
 */
public class LC_111_TheMinDepthOfTree_二叉树的最小深度_200814 {

    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.left != null && root.right != null) {
            return Math.min(minDepth2(root.left), minDepth2(root.right)) + 1;
        } else {
            return (root.left != null ? minDepth2(root.left) : minDepth2(root.right)) + 1;
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int minDepth;
        if (root.left == null && root.right == null) {
            //1.左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回1即可
            return 1;
        } else if (root.left != null && root.right != null) {
            minDepth = Math.min(minDepth(root.left), minDepth(root.right));
        } else if (root.left == null) {
            minDepth = minDepth(root.right);
        } else {
            minDepth = minDepth(root.left);
        }

        return minDepth + 1;
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
