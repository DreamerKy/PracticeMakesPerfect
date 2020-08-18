package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Practice by likaiyu on 2020/8/16.
 * Created by likaiyu on 2020/8/9.
 *
 * 给你一个包含 n 个整数的数组 nums，
 * 判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 *
 *答案中不可以包含重复的三元组。
 *
 * [-1, 0, 1, 2, -1, -4]   [-4, -1, -1, 0, 1, 2]
 *
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 */
public class LC_15_3Sum_200816 {

    // 数组为null或者长度小于3，直接放回[]
    // 排序
    // 遍历排序后数组
    //      如果num[i] > 0 ,则其后元素中不可能找到与之和为0
    //      重复元素跳过
    //      令左指针 L=i+1，右指针 R=n−1，当 L<R 时，执行循环：
    //      当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将L,R 移到下一位置，寻找新的解
    //      若和大于 000，说明 nums[R] 太大，R 左移
    //      若和小于 000，说明 nums[L] 太小，L 右移

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        LC_15_3Sum_200816 threeSum = new LC_15_3Sum_200816();
        List<List<Integer>> lists = threeSum.threeSum(nums);
        System.out.println(lists);
    }

    private List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length <= 2) return res;
        Arrays.sort(nums);
        int left;
        int right;
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            left = i + 1;
            right = nums.length - 1;
            int target = -nums[i];
            while (left < right) {
                //去重
                if (nums[left] + nums[right] == target) {
                    res.add(Arrays.asList(nums[left], nums[right], nums[i]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    private List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right + 1] == nums[right]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;//排序后的第一个数都大于0，后面的就不用看了
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;//去重
            }
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }
}
