package com.kotlin.vip.datastructure.algorithm;

/**
 * Created by liuyubobobo.
 */
public class Util {

    private Util(){}

    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {

        assert n > 0 && rangeL <= rangeR;

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++)
            arr[i] = (int)(Math.random() * (rangeR - rangeL + 1)) + rangeL;
        return arr;
    }

    public static Integer[] generateOrderedArray(int n) {

        assert n > 0;

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++)
            arr[i] = i;
        return arr;
    }

    public boolean isBalance(TreeNode root){
        return isBalanced(root).isBalanced;
    }

    public ReturnNode isBalanced(TreeNode root){
        if(root == null){
            return new ReturnNode(0,true);
        }

        ReturnNode left = isBalanced(root.left);
        ReturnNode right = isBalanced(root.right);
        if(!left.isBalanced || !right.isBalanced || Math.abs(left.height - right.height) > 1){
            return new ReturnNode(0,false);
        }

        return new ReturnNode(Math.max(left.height, right.height) + 1, true);
    }

    public class ReturnNode{
        int height;
        boolean isBalanced;

        public ReturnNode(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
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
