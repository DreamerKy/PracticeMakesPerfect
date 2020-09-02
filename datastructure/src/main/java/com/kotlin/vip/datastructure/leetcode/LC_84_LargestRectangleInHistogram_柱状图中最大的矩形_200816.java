package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by likaiyu on 2020/8/16.
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。
 * 每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 *
 */
public class LC_84_LargestRectangleInHistogram_柱状图中最大的矩形_200816 {

    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleAreaOptimizerMore(nums));
    }

    /**
     * 更优解法
     */

    public static int largestRectangleAreaOptimizerMore(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        if (len == 1) return heights[0];
        int[] temp = new int[len + 2];
        System.arraycopy(heights, 0, temp, 1, heights.length);
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < temp.length; i++) {
            while (!stack.isEmpty() && temp[i] < temp[stack.peek()]) {
                int h = temp[stack.pop()];
                res = Math.max(res, h * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        return res;

    }


    /**
     * 优化法
     * 利用栈
     */
    public static int largestRectangleAreaOptimizer(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        if (len == 1) return heights[0];
        int res = 0;
        Stack<Integer> integers = new Stack<>();
        //遍历每一项
        for(int i = 0;i<heights.length;i++) {
            while (!integers.isEmpty() && heights[i] < heights[integers.peek()]) {
                int top = heights[integers.pop()];
                while (!integers.isEmpty() && heights[integers.peek()] == top) {
                    integers.pop();
                }
                int width = 0;
                if (integers.isEmpty()) {
                    width = i;
                } else {
                    width = i - integers.peek() - 1;
                }

                res = Math.max(res, top * width);
            }
            integers.add(i);
        }

        while (!integers.isEmpty()) {
            int top = heights[integers.pop()];
            while (!integers.isEmpty() && heights[integers.peek()] == top) {
                integers.pop();
            }
            int width = 0;
            if (integers.isEmpty()) {
                width = len;
            } else {
                width = len - integers.peek() - 1;
            }

            res = Math.max(res, top * width);
        }















//
//        Deque<Integer> stack = new ArrayDeque<>(len);
//        for (int i = 0; i < len; i++) {
//            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
//                int curHeight = heights[stack.pollLast()];
//                while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
//                    stack.pollLast();
//                }
//
//                int curWidth;
//                if (stack.isEmpty()) {
//                    curWidth = i;
//                } else {
//                    curWidth = i - stack.peekLast() - 1;
//                }
//                res = Math.max(res, curHeight * curWidth);
//            }
//            stack.addLast(i);
//        }
//        while (!stack.isEmpty()) {
//            int curHeight = heights[stack.pollLast()];
//            while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
//                stack.pollLast();
//            }
//
//            int curWidth;
//            if (stack.isEmpty()) {
//                curWidth = len;
//            } else {
//                curWidth = len - stack.peekLast() - 1;
//            }
//            res = Math.max(res, curHeight * curWidth);
//
//        }
        return res;
    }

    /**
     * 暴力法
     *
     * @param heights
     * @return 遍历数组中每一项，要算该柱子勾勒出来的最大面积，就要
     * 向左找高度大于等于该柱子的柱子，碰到低于的就停止
     * 向右找高度大于等于该柱子的柱子，碰到低于的就停止
     */
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int length = heights.length;
        int maxArea = 0;
        for (int i = 0; i < length; i++) {
            int current = heights[i];
            int left = i;
            while (left > 0 && heights[left - 1] >= current) {
                left--;
            }

            int right = i;
            while (right < length-1 && heights[right + 1] >= current) {
                right++;
            }

            int width = right - left + 1;
            maxArea = Math.max(maxArea, current * width);
        }
        return maxArea;
    }
}
