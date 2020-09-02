package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/3/15.
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */
public class LC_283_MoveZeroes_移动零_200315 {

    public static void main(String[] args) {
        int[] arr = {1, 0, 5, 0, 9, 6, 0, 8};
        moveZero(arr);
    }

    public static void moveZero(int[] nums) {
        int j = 0;//j碰到靠前的0就停下来，i继续后移来寻找非0元素，找到后交换
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != j) {
                    swap(nums, i, j);
                }
                j++;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            stringBuilder.append(nums[i]);
        }
        System.out.println(stringBuilder.toString());

    }


    /**
     * 最优解
     *
     * @param nums
     */
    public static void moveZeroesOptimal(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != k) {
                    swap(nums, i, k);
                }
                k++;
            }
        }
    }

    /**
     * 此算法没能保证非零元素顺序性
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            if (nums[i] == 0) {
                if (nums[j] != 0) {
                    swap(nums, i, j);
                    i++;
                    j--;
                } else {
                    j--;
                }
            } else {
                i++;
            }
        }
    }

    public static void moveZeros(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }

        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }


    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
