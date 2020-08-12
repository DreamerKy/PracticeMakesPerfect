package com.kotlin.vip.datastructure.leetcode;

import java.util.Arrays;

/**
 * Created by likaiyu on 2020/8/11.
 * <p>
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 */
public class LC_189_RotateArray_200811 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        rotate2(arr, 10);
        System.out.println(Arrays.toString(arr));
    }

    public static void rotate(int[] arr, int k) {
        k = k % arr.length;
        for (int i = 0; i < k; i++) {
            int last = arr[arr.length - 1];
            for (int j = arr.length - 1; j > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[0] = last;
        }
    }

    /**
     * 三次反转法
     * @param arr
     * @param k
     */
    public static void rotate2(int[] arr, int k) {
        k = k % arr.length;
        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
    }

    private static void reverse(int[] arr, int start, int end) {
//        for (int i = start; i < end; i++) {
//            int temp =arr[i];
//            arr[i] = arr[end];
//            arr[end--] = temp;
//        }
        while (start<end){
            int temp =arr[start];
            arr[start++] = arr[end];
            arr[end--] = temp;
        }

    }

}
