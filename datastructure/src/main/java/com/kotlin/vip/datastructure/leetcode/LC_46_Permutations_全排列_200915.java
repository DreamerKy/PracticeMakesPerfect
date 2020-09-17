package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by likaiyu on 2020/9/15.
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 */
public class LC_46_Permutations_全排列_200915 {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> res = permute(nums);
        System.out.println(res);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        LinkedList<Integer> path = new LinkedList<>();
        backTrack(nums, 0, path, res);
        return res;
    }

    public static void backTrack(int[] nums, int depth, LinkedList<Integer> path, List<List<Integer>> res){
        //递归结束条件：nums 中的元素全出现在 path 中
        if (nums.length == depth) {
            res.add(new LinkedList<>(path));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(path.contains(nums[i])){
                continue;
            }
            //做选择
            path.add(nums[i]);
            //进入下一层决策树
            backTrack(nums,depth + 1, path, res);
            //取消选择
            path.removeLast();
        }
    }


}
