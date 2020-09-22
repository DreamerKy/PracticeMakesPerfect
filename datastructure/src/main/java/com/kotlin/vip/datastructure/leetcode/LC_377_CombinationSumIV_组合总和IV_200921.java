package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by likaiyu on 2020/9/21.
 *
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 *
 * nums = [1, 2, 3]
 * target = 4
 *
 *
 * 所有可能的组合为：
 *
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * 请注意，顺序不同的序列被视作不同的组合。
 *
 * 因此输出为 7。
 *
 *
 */
public class LC_377_CombinationSumIV_组合总和IV_200921 {

    public static int result = 0;

    public static void main(String[] args) {
        int[] candidates = {1, 2, 3};
        int target = 32;
        System.out.println(combinationSum4II(candidates, target));
    }


    /**
     * 动态规划
     * 状态 dp[i] : 给定数组中和为 i 的所有组合个数，为状态就是问题当中问的方式而定义的，因此输出就是最后一个状态 dp[n]
     *
     * 状态转移方程 : dp[i]= dp[i - nums[0]] + dp[i - nums[1]] + dp[i - nums[2]] + ... （当 [] 里面的数 >= 0）
     *
     * 举一个具体的例子：nums=[1, 3, 4], target=7;
     *                dp[7] = dp[6] + dp[4] + dp[3]
     *
     * @param nums
     * @param target
     * @return
     */
    public static int combinationSum4II(int[] nums, int target) {
        int[] dp = new int[target +1 ];
        // TODO: 2020/9/21 不太明白 ？？？
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    /**
     * 回溯(Leetcode 提交超时了。。。)
     * @param nums
     * @param target
     * @return
     */
    public static int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return result;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfsII(nums, path, target);
        return result;
    }

    public static void dfsII(int[] nums, Deque path, int target) {
        if (target == 0) {
            result++;
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(target - nums[i] < 0){
                break;
            }
            path.addLast(nums[i]);
            dfsII(nums, path, target - nums[i]);
            path.removeLast();
        }
    }
}

