package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/3/12.
 * 二叉树最大深度
 */
public class LC_104_TheDepthOfBinaryTree {

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
