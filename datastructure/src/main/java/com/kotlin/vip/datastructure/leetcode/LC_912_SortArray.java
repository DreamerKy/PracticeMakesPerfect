package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by likaiyu on 2020/3/15.
 * 给定一个整数数组 nums，将该数组升序排列。
 * 示例 1：
 * <p>
 * 输入：[5,2,3,1]
 * 输出：[1,2,3,5]
 * <p>
 * 示例 2：
 * <p>
 * 输入：[5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 */
public class LC_912_SortArray {

    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};
        sortArray(arr);
    }

    public static List<Integer> sortArray(int[] nums) {
        List<Integer> list = new ArrayList<>();
        mergeSort(nums, 0, nums.length - 1);
//        quickSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }

    public static void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(nums, l, r);
        quickSort(nums, l, p - 1);
        quickSort(nums, p + 1, r);
    }

    private static int partition(int[] nums, int l, int r) {
        int v = nums[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] < v) {
                j++;
                swap(nums, i, j);
            }
        }
        swap(nums, l, j);
        return j;
    }

    private static void mergeSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;

        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    private static void merge(int[] nums, int l, int mid, int r) {
        int[] clone = Arrays.copyOfRange(nums, l, r + 1);
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                nums[k] = clone[j - l];
                j++;
            } else if (j > r) {
                nums[k] = clone[i - l];
                i++;
            } else if (clone[i - l] < clone[j - l]) {
                nums[k] = clone[i - l];
                i++;
            } else {
                nums[k] = clone[j - l];
                j++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
