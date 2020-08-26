package com.kotlin.vip.datastructure.leetcode;

import java.util.Arrays;

/**
 * Created by likaiyu on 2020/8/25.
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，
 * 计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 输入: 2
 * 输出: [0,1,1]
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 */
public class LC_338_CountingBits_200825 {

    public static void main(String[] args) {
        int[] res = countBits2(40);
        System.out.println(Arrays.toString(res));
    }

    public static int[] countBits2(int num) {
        if(num == 0) return new int[]{0};
        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i >> 1] + (i & 1);
//            res[i] = (i & 1) == 1 ? res[i - 1] + 1 : res[i / 2];
        }
        return res;
    }

    public static int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = getOneCount(i);
        }
        return res;
    }

    private static int getOneCount(int num) {
        int count = 0;
        while(num != 0){
            num = num & (num-1);
            count ++ ;
        }
        return count;
    }
}
