package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by likaiyu on 2020/9/21.
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 *     所有数字（包括目标数）都是正整数。
 *     解集不能包含重复的组合。
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 */
public class LC_40_CombinationSumII_组合总和II_200921 {

    public static void main(String[] args) {
        int[] candidates = {2,5,2,1,2};
        int target = 5;
        List<List<Integer>> result = combinationSumII(candidates, target);
        System.out.println(result);
    }


	public static List<List<Integer>> combinationSumII(int[] candidates, int target){
		List<List<Integer>> result = new ArrayList<>();
		if(candidates == null || candidates.length == 0){
			return result;
		}
		Deque<Integer> path = new ArrayDeque<>();
		Arrays.sort(candidates);
		dfsII(candidates,path,0,candidates.length,target,result);
		return result;
	}

	public static void dfsII(int[] candidates,Deque path,int begin,int end,int target, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList(path));
            return;
        }
        for (int i = begin; i < end; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.addLast(candidates[i]);
            dfsII(candidates, path, i + 1, end, target - candidates[i], result);
            path.removeLast();
        }
    }
}

