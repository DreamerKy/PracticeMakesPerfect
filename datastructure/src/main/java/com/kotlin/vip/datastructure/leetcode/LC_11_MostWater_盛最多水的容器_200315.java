package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/3/15.
 * 给你 n 个非负整数 a1，a2，...，an，
 * 每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，
 * 垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器
 * 可以容纳最多的水。
 * 输入 [1,8,6,2,5,4,8,3,7]
 * 输出 49
 */
public class LC_11_MostWater_盛最多水的容器_200315 {

    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(getMoreWater2(arr));
    }

    public static int getMoreWater2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int j = height.length - 1;
        int max = 0;
        for (int i = 0; i < j; ) {
            int minHeight = height[i] > height[j] ? height[j--] : height[i++];
            max = Math.max(max, minHeight * (j + 1 - i));
        }
        return max;

    }


    public static int getMoreWater(int[] arr) {
        int maxWater = 0;
        for (int i = 0, j = arr.length - 1; i < j; ) {
            int min = arr[i] < arr[j] ? arr[i++] : arr[j--];
            maxWater = Math.max(maxWater, (j + 1 - i) * min);
        }
        return maxWater;
    }


}
