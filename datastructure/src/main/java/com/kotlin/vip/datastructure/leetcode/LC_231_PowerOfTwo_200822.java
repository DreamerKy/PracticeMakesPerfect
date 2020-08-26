package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/22.
 *
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * 输入: 1
 * 输出: true
 * 解释: 2º = 1
 *
 */
public class LC_231_PowerOfTwo_200822 {

    public static void main(String[] args) {
        boolean res = isPowerOfTwo(-1);
        System.out.println(res);
    }

    public static boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }
}
