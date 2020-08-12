package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/3/14.
 * 合并二叉树
 *
 * 思想：将一棵树t2，合并到另一棵树t1
 * 递归公式：合并当前节点 + 合并左子树 + 合并右子树
 * 终止条件：t1或t1的节点为空
 *
 * <p>
 * 输入:
 * Tree 1                     Tree 2
 * 1                         2
 * / \                       / \
 * 3   2                     1   3
 * /                           \   \
 * 5                             4   7
 * 输出:
 * 合并后的树:
 * 3
 * / \
 * 4   5
 * / \   \
 * 5   4   7
 */
public class LC_617_MergeTwoTrees_200314 {

    //前序遍历的应用
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 1.合并当前节点
        // t1没有，而t2有的节点，直接返回t2
        if (root1 == null) {
            return root2;
        }
        // t1有，而t2没有的节点，t1不变，直接返回
        if (root2 == null) {
            return root1;
        }
        // t1和t2都有的节点，节点值相加，更新t1的值
        root1.val += root2.val;
        // 2.递归合并左子树和右子树
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
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
