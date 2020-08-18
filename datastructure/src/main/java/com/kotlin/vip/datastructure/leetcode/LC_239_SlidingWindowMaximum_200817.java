package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * Created by likaiyu on 2020/8/17.
 *
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 *
 *
 */
public class LC_239_SlidingWindowMaximum_200817 {

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
//        int[] ints = maxSlidingWindow(nums, 1);
        int[] ints = maxSlidingWindowOptimizer(nums, 3);
        System.out.println(Arrays.toString(ints));
    }

    //[1,3,-1,-3,5,3,6,7]
    public static int[] maxSlidingWindowOptimizer(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;
        int[] res = new int[n - k + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int max_id = 0;
        //处理前 k 个元素，初始化双向队列。
        for (int i = 0; i < k; i++) {
            clean_deque(i, k, deque, nums);
            deque.addLast(i);
            if (nums[i] > nums[max_id]) max_id = i;
        }

        res[0] = nums[max_id];
        //遍历整个数组
        for (int i = k; i < n; i++) {
            clean_deque(i, k, deque, nums);
            //将当前元素添加到双向队列中
            deque.addLast(i);
            //将 deque[0] 添加到输出中。
            res[i - k + 1] = nums[deque.getFirst()];
        }
        return res;
    }
    //[1,3,-1,-3,5,3,6,7]
    private static void clean_deque(int i, int k, ArrayDeque<Integer> deque, int[] nums) {
        //只保留当前滑动窗口中有的元素的索引。
        if(!deque.isEmpty() && i >= deque.getFirst() + k){
            deque.removeFirst();
        }

        //移除比当前元素小的所有元素，它们不可能是最大的。
        while(!deque.isEmpty() && nums[i] > nums[deque.getLast()]){
            deque.removeLast();
        }
    }


    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return new int[0];
        }
        int[] res = new int[nums.length + 1 - k];
        for (int i = 0; i < res.length; i++) {
            int end = i + k - 1;
            findMax(i, end, nums, res);
        }
        return res;
    }

    private static void findMax(int i, int end, int[] nums, int[] res) {
        int max = Integer.MIN_VALUE;
        for (int j = i; j <= end; j++) {
            max = Math.max(max, nums[j]);
        }
        res[i] = max;
    }
}
