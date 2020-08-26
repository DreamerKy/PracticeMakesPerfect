package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/20.
 *
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数
 *
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 *
 */
public class LC_191_NumberOfOneBits_200820 {

    public static void main(String[] args) {
        binaryToDecimal("10101100");
//        int i = hammingWeight2(-3 );
//        System.out.println(i);

        int i = 0;
        int i1 = i++;//i=1 i1=0
        int i2 = ++i;//i=2 i2=2
        int i3 = i++;//i=3 i3=2
        int i4 = ++i;//i=4 i4=4

        System.out.println(i);

    }



    public static void binaryToDecimal(int n) {
        String s = Integer.toBinaryString(n);
        int i = Integer.parseInt(s);
        System.out.println(i);
    }

    /**
     * "10101100" --> 172
     * @param s
     */
    public static void binaryToDecimal(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res <<= 1;
            if (s.charAt(i) == '1') {
                res |= 1;
            }
        }
//        int res = Integer.parseInt(s, 2);
        System.out.println(res);
    }

    public static int hammingWeight2(int n) {
        int res = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                res++;
            }
            // n >>= 1;
            mask <<= 1;
        }
        return res;
    }

        public static int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                n = n & (n - 1);
                res++;
            }
            return res;
        }
}
