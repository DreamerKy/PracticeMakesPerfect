package com.kotlin.vip.datastructure.leetcode;

import java.util.Arrays;

/**
 * Created by likaiyu on 2020/8/10.
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *
 *
 */
public class LC_322_CoinChange_零钱兑换_200921 {

    public static void main(String[] args) {
        int[] coins = { 2 };
        int amount = 3;
        int res = coinChange(coins, amount);
        System.out.println(res);
    }

    /**
     * 一般这种求最值得题，可考虑使用动态规划，找到重复子问题的最优子结构
     * 定义 F(i) 为组成金额 i 所需的最小硬币数量
     * 定义 cj 为最后一枚硬币的面额，我们枚举最后一枚硬币需要从F(i-cj)转移过来，再算上枚举这枚硬币数量 1 的贡献，由于要求最少数量，所以F(i) 为前面能转移过来的状态的最小值字啊加上枚举的硬币数量 1
     *
     * coins = [1, 2, 5], amount = 11
     *
     * 当 i==0时无法用硬币组成即为0，当i<0时忽略F(i)
     * F(i) 组成金额i最硬币数量
     * F(0) --> 0
     * F(1) --> min(F(1-1),F(1-2),F(1-5))+1=1
     * F(2) --> min(F(2-1),F(2-2),F(2-5))+1=1
     * F(3) --> min(F(3-1),F(3-2),F(3-5))+1=2
     * F(4) --> min(F(4-1),F(4-2),F(4-5))+1=2
     * .......
     * F(11) --> min(F(11-1),F(11-2),F(11-5))+1=3
     *
     * 通过子问题的最优解得到
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
