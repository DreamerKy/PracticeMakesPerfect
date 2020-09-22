package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by likaiyu on 2020/8/15.
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 */
public class LC_102_BInaryTreeLevelOrderTraversal_二叉树的层序遍历_200814 {

    public static void main(String[] args) {
        TreeNode _3 = new TreeNode(3);
        TreeNode _9 = new TreeNode(9);
        TreeNode _20 = new TreeNode(20);
        TreeNode _15 = new TreeNode(15);
        TreeNode _7 = new TreeNode(7);

        _3.left = _9;
        _3.right = _20;
        _20.left = _15;
        _20.right = _7;

        System.out.println(levelOrderII(_3));
    }

    public static List<List<Integer>> levelOrderII(TreeNode root) {
        /*List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(level);
        }*/

        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }



    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> finalResult = new ArrayList<>();
        if (root == null) return finalResult;
        List<TreeNode> subNodes;
        List<TreeNode> levelList = new ArrayList<>();
        levelList.add(root);
        ArrayList<Integer> firstLevel = new ArrayList<>();
        firstLevel.add(root.val);
        finalResult.add(firstLevel);
        subNodes = levelList;
        while (subNodes.size() > 0) {
            subNodes = getSubNodes(subNodes, finalResult);
        }
        return finalResult;
    }

    private static List<TreeNode> getSubNodes(List<TreeNode> list, List<List<Integer>> finalResult) {
        List<TreeNode> subResult = new ArrayList<>();
        for (TreeNode treeNode : list) {
            if (treeNode.left != null) {
                subResult.add(treeNode.left);
            }
            if (treeNode.right != null) {
                subResult.add(treeNode.right);
            }
        }

        if (subResult.size() > 0) {
            List<Integer> levelResult = new ArrayList<>();
            for (TreeNode treeNode : subResult) {
                levelResult.add(treeNode.val);
            }
            finalResult.add(levelResult);
        }
        return subResult;
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
