package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likaiyu on 2020/8/6.
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class LC_22_GenerateParentheses_200806 {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
//        _generateParentheses(2 * n, 0, "", result);
        _generateParentheses2(0, 0, n, "", result);
        return result;
    }

    private void _generateParentheses(int length, int pos, String s, List<String> result) {
        System.out.println(s);
        if (pos == length) {
            if (valid(s)) {
                result.add(s);
            }
            return;
        }

        _generateParentheses(length, pos + 1, s + "(", result);
        _generateParentheses(length, pos + 1, s + ")", result);

    }

    /**
     * 优化
     * 左边括号可以随便添加，只要别超标 (left < n)
     * 右边括号必须在  左边数 > 右边数 时才可以 (left > right)
     * left 使用的左括号数
     * right 使用的右括号数
     * n 括号对数
     */
    private void _generateParentheses2(int left, int right, int n, String s, List<String> result) {
        if (left == n && right == n) {
            result.add(s);
            return;
        }
        if (left < n) {
            _generateParentheses2(left + 1, right, n, s + "(", result);
        }
        if (left > right) {
            _generateParentheses2(left, right + 1, n, s + ")", result);
        }
    }

    private boolean valid(String s) {
        int balance = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    public static void main(String[] args) {
        LC_22_GenerateParentheses_200806 generateParentheses = new LC_22_GenerateParentheses_200806();
        List<String> list = generateParentheses.generateParenthesis(2);
        System.out.println(list);
    }
}
