package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by likaiyu on 2020/9/21.
 *
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 *
 */
public class LC_216_CombinationSumIII_组合总和III_200921 {

    public static void main(String[] args) {
        int target = 9;
        int k = 3;
        List<List<Integer>> result = combinationSumIII(target, k);
        System.out.println(result);
    }


    public static List<List<Integer>> combinationSumIII(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfsII(path, 1, n, result, k);
        return result;
    }

    public static void dfsII(Deque path, int begin,int n, List<List<Integer>> result, int k) {
        if (n == 0 && path.size() == k) {
            result.add(new ArrayList(path));
            return;
        }
        for (int i = begin; i <= 9 ; i++) {
            if (n - i < 0 ) {
                break;
            }
            path.addLast(i);
            dfsII( path, i + 1, n - i, result,k);
            path.removeLast();
        }
    }
}

