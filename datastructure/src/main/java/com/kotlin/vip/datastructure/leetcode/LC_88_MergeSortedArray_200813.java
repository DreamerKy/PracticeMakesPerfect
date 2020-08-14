package com.kotlin.vip.datastructure.leetcode;

import java.util.Arrays;

/**
 * Created by likaiyu on 2020/8/10.
 *
 * 给你两个有序整数数组 nums1 和 nums2，
 * 请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 */
public class LC_88_MergeSortedArray_200813 {

    public static void main(String[] args) {
        int[] nums1 = {4,5,6,0,0,0};
        int[] nums2 = {1,2,3};
        merge2(nums1, 3, nums2, 3);
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1_copy = Arrays.copyOfRange(nums1, 0, m);
        int i = 0;
        int j = 0;
        for (int k = 0; k < nums1.length; k++) {
            if (i < m && j < n) {
                if (nums1_copy[i] <= nums2[j]) {
                    nums1[k] = nums1_copy[i];
                    i++;
                } else {
                    nums1[k] = nums2[j];
                    j++;
                }
            } else if (i < m) {
                System.arraycopy(nums1_copy,i,nums1,i+j,m+n-i-j);
//                for (int ii = i; ii < m; ii++) {
//                    nums1[k] = nums1_copy[ii];
//                    k++;
//                }
            } else if (j < n) {
                System.arraycopy(nums2,j,nums1,i+j,m+n-i-j);
//                for (int jj = j; jj < n; jj++) {
//                    nums1[k] = nums2[jj];
//                    k++;
//                }
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
//        int j = 0;
//        for(int i = m;i<nums1.length;i++) {
//            if (j < n) {
//                nums1[i] = nums2[j];
//                j++;
//            } else {
//                break;
//            }
//        }
        System.arraycopy(nums2,0,nums1,m,n);
        Arrays.sort(nums1);
        System.out.println(Arrays.toString(nums1));
    }

}
