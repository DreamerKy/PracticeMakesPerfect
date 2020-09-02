package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by likaiyu on 2020/9/01.
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:[1,2,2,3,null,null,3]
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 */
public class LC_101_SymmetricTree_对称二叉树_200901 {
//[4,-57,-57,null,67,67,null,null,-97,-97]
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(-2);
        TreeNode right = new TreeNode(-2);
        TreeNode leftLeft = new TreeNode(3);
        TreeNode leftRight = new TreeNode(4);
        TreeNode rightLeft = new TreeNode(4);
        TreeNode rightRight = new TreeNode(3);
        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        right.left = rightLeft;
        right.right = rightRight;

        boolean res = isSymmetricI(root);
        System.out.println(res);
    }

    public static boolean isSymmetricII(TreeNode root) {
        return check(root, root);
    }

    private static boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    public static boolean isSymmetricI(TreeNode root){
        return checkI(root,root);
    }

    private static boolean checkI(TreeNode u, TreeNode v) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(u);
        queue.offer(v);
        while (!queue.isEmpty()) {
            u = queue.poll();
            v = queue.poll();
            if (u == null && v == null) {
                continue;
            }
            if (u == null || v == null || (u.val != v.val)) {
                return false;
            }

            queue.offer(u.left);
            queue.offer(v.right);
            queue.offer(u.right);
            queue.offer(v.left);
        }
        return true;
    }


    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        List<TreeNode> nextLevel = new ArrayList<>();
        List<TreeNode> temp = new ArrayList<>();
        nextLevel.add(root);
        while(!nextLevel.isEmpty()) {
            temp.clear();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < nextLevel.size(); i++) {
                TreeNode treeNode = nextLevel.get(i);
                if (treeNode.left != null) {
                    temp.add(treeNode.left);
                    list.add(String.valueOf(treeNode.left.val));
                } else {
                    list.add("*");
                }
                if (treeNode.right != null) {
                    temp.add(treeNode.right);
                    list.add(String.valueOf(treeNode.right.val));
                } else {
                    list.add("*");
                }
            }
            if (!isSymmetric(list)) {
                return false;
            }
            nextLevel.clear();
            nextLevel.addAll(temp);
        }
        return true;
    }

    private static boolean isSymmetric(List<String> list) {
        int size = list.size();
        int i = 0;
        int j = size - 1;
        while (i < j) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
