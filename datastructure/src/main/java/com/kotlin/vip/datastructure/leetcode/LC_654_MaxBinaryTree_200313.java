package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/3/13.
 * 最大二叉树
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * <p>
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * <p>
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 * <p>
 * 输入：[3,2,1,6,0,5]
 * 输出：返回下面这棵树的根节点：
 * <p>
 * 6
 * /   \
 * 3     5
 * \    /
 * 2  0
 * \
 * 1
 */
public class LC_654_MaxBinaryTree_200313 {

    public TreeNode constructMaxBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length);
    }

    private TreeNode construct(int[] nums, int l, int r) {
        if (l == r) {
            return null;
        }
        int max = findMaxInArray(nums, l, r);
        TreeNode root = new TreeNode(nums[max]);
        //注意边界问题
        root.left = construct(nums, l, max);
        root.right = construct(nums, max + 1, r);

        return root;
    }

    private int findMaxInArray(int[] nums, int l, int r) {
        int maxIndex = l;
        for (int i = l; i < r; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
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
