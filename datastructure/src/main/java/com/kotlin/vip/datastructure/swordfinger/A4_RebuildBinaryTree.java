package com.kotlin.vip.datastructure.swordfinger;


import java.util.Arrays;

/**
 * Created by likaiyu on 2020/1/15.
 */
public class A4_RebuildBinaryTree {

    /**
     * 输入二叉树的前序遍历和中序遍历重建改二叉树
     * 递归调用
     */
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        //递归出口
        if (pre == null || in == null || pre.length == 0 || in.length == 0 || pre.length != in.length) {
            return null;
        }

        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return root;
    }
    
    public static TreeNode reConstructBinaryTree2(int[] pre,int[] in){
        if(pre == null || in == null|| pre.length==0||in.length==0){
            return null;
        }
        return reConstruct(pre,0,pre.length-1,in,0,in.length-1);
        
    }

    private static TreeNode reConstruct(int[] pre, int preL, int preR, int[] in, int inL, int inR) {
        if (preL > preR || inL > inR) {
            return null;
        }

        int rootVal = pre[preL];
        int index = 0;
        while (index <= inR && in[index] != rootVal) {
            index++;
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = reConstruct(pre, preL + 1, preL - inL + index, in, inL, index);
        root.right = reConstruct(pre, preL - inL + index + 1, preR, in, index + 1, inR);
        return root;
    }


    static class TreeNode {
        private int e;
        private TreeNode left, right;

        public TreeNode(int e, TreeNode left, TreeNode right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int e) {
            this.e = e;
        }

    }


}
