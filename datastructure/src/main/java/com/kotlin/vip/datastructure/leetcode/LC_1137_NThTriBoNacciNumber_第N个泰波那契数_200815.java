package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/10.
 *
 * 泰波那契序列 Tn 定义如下：
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 *
 *
 */
public class LC_1137_NThTriBoNacciNumber_第N个泰波那契数_200815 {

    public static void main(String[] args) {
        int result = tribonacci(25);
        System.out.println(result);
    }

    public static int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;
        int result = 0;
        int a = 0;
        int b = 1;
        int c = 1;
        for (int i = 0; i < n - 2; i++) {
            result = a + b + c;
            a = b;
            b = c;
            c = result;
        }
        return result;
    }
}
