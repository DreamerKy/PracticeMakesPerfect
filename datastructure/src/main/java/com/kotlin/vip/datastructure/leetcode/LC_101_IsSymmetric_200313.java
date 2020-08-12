package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by likaiyu on 2020/3/13.
 * 对称二叉树
 *      1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 */
public class LC_101_IsSymmetric_200313 {

    private ArrayList<Integer> arrayList = new ArrayList<>();
    private Stack<Integer> stack = new Stack<>();

    /**
     * 该解法未通过测试
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        inOrder(root);
        int[] arr = new int[arrayList.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrayList.get(i);
        }

        boolean flag = false;
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] == arr[arr.length - 1 - i]) {
                flag = true;
            } else {
                flag = false;
            }
        }

        return flag;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        arrayList.add(root.val);
        inOrder(root.right);
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
