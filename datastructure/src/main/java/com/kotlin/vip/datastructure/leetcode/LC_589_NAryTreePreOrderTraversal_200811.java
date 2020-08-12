package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likaiyu on 2020/8/11.
 *
 * 给定一个 N 叉树，返回其节点值的前序遍历
 *
 */
public class LC_589_NAryTreePreOrderTraversal_200811 {

    public static void main(String[] args) {
        Node _2 = new Node(2);
        Node _4 = new Node(4);
        Node _5 = new Node(5);
        Node _6 = new Node(6);

        List<Node> nodesTwo = new ArrayList<>();
        nodesTwo.add(_5);
        nodesTwo.add(_6);

        Node _3 = new Node(3,nodesTwo);
        List<Node> nodesOne = new ArrayList<>();
        nodesOne.add(_3);
        nodesOne.add(_2);
        nodesOne.add(_4);
        Node _1 = new Node(1, nodesOne);
        List<Integer> integerList = preOrder(_1);
        System.out.println(integerList);

    }

    public static List<Integer> preOrder(Node root) {
        List<Integer> integerList = new ArrayList<>();
        preOrderInternal(root,integerList);
        return integerList;
    }

    private static void preOrderInternal(Node root, List<Integer> integerList) {
        if (root == null) {
            return;
        }
        integerList.add(root.val);
        if (root.children != null && root.children.size() > 0) {
            for (Node child : root.children) {
                preOrderInternal(child, integerList);
            }
        }
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

}
