package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likaiyu on 2020/8/12.
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 *     节点的左子树只包含小于当前节点的数。
 *     节点的右子树只包含大于当前节点的数。
 *     所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 返回它的最大深度 3 。
 */
public class LC_98_ValidateBinarySearchTree_200812 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(1);
        root.left = left;
        LC_98_ValidateBinarySearchTree_200812 validateBST = new LC_98_ValidateBinarySearchTree_200812();
        boolean validBST = validateBST.isValidBST(root);
        System.out.println(validBST);

    }

    /**
     * 中序遍历时，判断当前节点是否大于中序遍历的前一个节点，
     * 如果大于，说明满足 BST，继续遍历；否则直接返回 false。
     * @param root
     * @return
     */
    long pre = Long.MIN_VALUE;
    private boolean isValidBst(TreeNode root) {
        if (root == null) {
            return true;
        }
        //访问左子树
        if(!isValidBST(root.left)){
            return false;
        }
        //访问当前节点
        if(root.val <= pre){
            return false;
        }
        pre = root.val;
        //访问右子树
        if(!isValidBst(root.right)){
            return false;
        }
        return true;
    }


    List<Integer> nodes = new ArrayList<>();
    public boolean isValidBST(TreeNode root) {

        if(root == null){
            return true;
        }
        inOrder(root);
        return isValid();
    }

    private boolean isValid() {
        for (int i = 1; i < nodes.size(); i++) {
            if (nodes.get(i-1) >= nodes.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(TreeNode root) {
        if(root == null){
            return;
        }
        inOrder(root.left);
        nodes.add(root.val);
        inOrder(root.right);
    }

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

       public TreeNode(int val) {
           this.val = val;
       }
   }

}
