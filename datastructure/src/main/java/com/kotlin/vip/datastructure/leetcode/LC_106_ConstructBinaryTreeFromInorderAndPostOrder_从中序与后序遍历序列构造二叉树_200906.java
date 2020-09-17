package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likaiyu on 2020/09/06.
 *
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
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
public class LC_106_ConstructBinaryTreeFromInorderAndPostOrder_从中序与后序遍历序列构造二叉树_200906 {

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root = buildTree(inorder, postorder);
        System.out.println(levelOrder(root));
    }

    private static Map<Integer, Integer> inMap = new HashMap<>();

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0)
            return null;
        int n = inorder.length;
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTreeHelper(inorder, 0, n - 1, postorder, 0, n-1);
    }

    public static TreeNode buildTreeHelper(int[] inorder, int instart, int inend, int[] postorder, int poststart, int postend) {
        if (instart > inend) {
            return null;
        }
        int value = postorder[postend];
        TreeNode current = new TreeNode(value);
        //在中序遍历中的根节点索引
        int rootindex = inMap.get(value);
        int rightNum = inend - rootindex;
        current.right = buildTreeHelper(inorder, rootindex +1, inend, postorder, postend - rightNum,postend - 1);
        current.left = buildTreeHelper(inorder, instart, rootindex - 1, postorder, poststart, postend - 1 - rightNum);
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
