package com.kotlin.vip.datastructure.leetcode;

/**
 * Practiced by likaiyu on 2020/8/12.
 * Created by likaiyu on 2020/3/12.
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回它的最大深度 3 。
 */
public class LC_104_TheDepthOfBinaryTree_二叉树的最大深度_200812 {

    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth3(root.left) + 1;
        int right = maxDepth3(root.right) + 1;
        return Math.max(left, right);
    }

    public int maxDepth(TreeNode root){
        //找递归出口
        //即节点为null，深度自然为0
        if(root == null){
            return 0;
        }
        //找本级递归要做什么？
        //将每一级都抽象成root+left+right
        //则本级就是要拿到左右子树的深度，从中选交大一个+1就是本级深度
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        //找返回值
        //题目要求最大深度，则本步骤需要返回当前一级树的最大深度
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int maxDepth2(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }

   public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

       public TreeNode(int val) {
           this.val = val;
       }
   }

}
