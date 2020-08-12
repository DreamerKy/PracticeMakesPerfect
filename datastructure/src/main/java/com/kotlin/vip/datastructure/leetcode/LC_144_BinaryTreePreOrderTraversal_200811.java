package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likaiyu on 2020/8/11.
 *
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 */
public class LC_144_BinaryTreePreOrderTraversal_200811 {

    public static void main(String[] args) {
        TreeNode _1 = new TreeNode(1);
        TreeNode _2 = new TreeNode(2);
        TreeNode _3 = new TreeNode(3);
        _1.right = _2;
        _2.left = _3;
        List<Integer> integerList = preOrder(_1);
        System.out.println(integerList);

    }

    public static List<Integer> preOrder(TreeNode root) {
        List<Integer> integerList = new ArrayList<>();
        preOrderInternal(root,integerList);
        return integerList;
    }

    private static void preOrderInternal(TreeNode root, List<Integer> integerList) {
        if (root == null) {
            return;
        }
        integerList.add(root.val);
        preOrderInternal(root.left,integerList);
        preOrderInternal(root.right,integerList);
    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
