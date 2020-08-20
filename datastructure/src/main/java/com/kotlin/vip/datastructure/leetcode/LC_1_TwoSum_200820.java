package com.kotlin.vip.datastructure.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Practiced by likaiyu on 2020/8/20.
 * Created by likaiyu on 2020/1/23.
 *
 * 给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 *
 */
public class LC_1_TwoSum_200820 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15, 1, 8};
        int[] ints = twoSumUnSortedArray(nums, 9);
        System.out.println(Arrays.toString(ints));
    }


    public static int[] twoSumUnSortedArray(int[] nums,int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("nums can not be null");
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i,map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No result");
    }

    public int[] twoSumUnSortedArr(int[] nums,int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("nums can not be null");
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("can not find result");
    }

    public int[] twoSumm(int[] nums,int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException(" Illegal Argument");
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No Result");
    }


    public int[] twoSum1(int[] numbers, int target) {

        if (numbers.length < 2 || !isSorted(numbers)) {
            throw new IllegalArgumentException("Illegal argument numbers");
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    int[] res = {i + 1, j + 1};
                    return res;
                }
            }
        }
        throw new IllegalStateException("The input has no solution");
    }

    public int[] twoSum2(int[] numbers, int target) {
        if (numbers.length < 2 || !isSorted(numbers)) {
            throw new IllegalArgumentException("Illegal argument numbers");
        }
        for (int i = 0; i < numbers.length; i++) {
            int j = binarySearch(numbers, i + 1, numbers.length - 1, target - numbers[i]);
            if (j != -1) {
                int[] res = {i + 1, j + 1};
                return res;
            }
        }
        throw new IllegalStateException("The input has no solution");
    }

    private int binarySearch(int[] numbers, int l, int r, int target) {
        if (l < 0 || l > numbers.length) {
            throw new IllegalArgumentException("l is out of bound");
        }
        if (r < 0 || r > numbers.length) {
            throw new IllegalArgumentException("r is out of bound");
        }

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (target == numbers[mid]) {
                return mid;
            } else if (target < numbers[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public boolean isSorted2(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public int[] twoSum3(int[] numbers, int target) {
        if (numbers.length < 2 || !isSorted(numbers)) {
            throw new IllegalArgumentException("Illegal argument numbers");
        }

        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                int[] res = {l + 1, r + 1};
                return res;
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }

        throw new IllegalStateException("The input has no solution");
    }


    private boolean isSorted(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return false;
            }
        }
        return true;
    }







}
