package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likaiyu on 2020/09/06.
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 *
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 *
 */
public class LC_105_ConstructBinaryTreeFromPrOrderAndInorder从前序与中序遍历序列构造二叉树_200906 {

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println(levelOrder(root));
    }

    private static Map<Integer, Integer> inMap = new HashMap<>();

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0)
            return null;
        int n = preorder.length;
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    public static TreeNode buildTreeHelper(int[] preorder, int prestart, int preend, int[] inorder, int instart, int inend) {
        if (prestart > preend) {
            return null;
        }
        int value = preorder[prestart];
        TreeNode current = new TreeNode(value);
        int rootindex = inMap.get(value);
        int leftNum = rootindex - instart;
        current.left = buildTreeHelper(preorder, prestart + 1, prestart + leftNum, inorder, instart, rootindex - 1);
        current.right = buildTreeHelper(preorder, prestart + leftNum + 1, preend, inorder, rootindex + 1, inend);
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
