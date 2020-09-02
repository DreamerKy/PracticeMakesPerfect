package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/10.
 *
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 *
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
 *
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
 *
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
 *
 *
 */
public class LC_509_FibonacciNumber_斐波那契数_200815 {

    public static void main(String[] args) {
        int result = tribonacci(4);
        System.out.println(result);
    }

    public static int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int result = 0;
        int a = 0;
        int b = 1;
        for (int i = 0; i < n - 1; i++) {
            result = a + b ;
            a = b;
            b = result;
        }
        return result;
    }
}
