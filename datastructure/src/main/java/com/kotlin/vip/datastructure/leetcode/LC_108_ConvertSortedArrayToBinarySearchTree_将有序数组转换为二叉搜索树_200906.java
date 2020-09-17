package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likaiyu on 2020/09/06.
 *
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 *
 */
public class LC_108_ConvertSortedArrayToBinarySearchTree_将有序数组转换为二叉搜索树_200906 {

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = sortedArrayToBST(nums);
        System.out.println(levelOrder(root));
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int length = nums.length;
        return buildTreeHelper(nums, 0, length - 1);
    }

    public static TreeNode buildTreeHelper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode current = new TreeNode(nums[mid]);
        current.left = buildTreeHelper(nums, left, mid - 1);
        current.right = buildTreeHelper(nums, mid + 1,right);
        return current;
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
