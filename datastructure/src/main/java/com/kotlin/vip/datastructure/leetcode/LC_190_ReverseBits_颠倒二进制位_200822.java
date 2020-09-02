package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/22.
 *
 * 颠倒给定的 32 位无符号整数的二进制位。
 *
 * 输入: 00000010100101000001111010011100
 * 输出: 00111001011110000010100101000000
 * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 *
 */
public class LC_190_ReverseBits_颠倒二进制位_200822 {

    public static void main(String[] args) {
        int res = reverseBits(43261596);
        System.out.println(res);
    }

    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {

            result = result << 1;
            result |= n & 1;
            n = n >> 1;

        }
        return result;
    }
}
