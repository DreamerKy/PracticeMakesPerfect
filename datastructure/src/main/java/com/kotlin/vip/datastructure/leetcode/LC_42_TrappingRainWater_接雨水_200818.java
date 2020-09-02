package com.kotlin.vip.datastructure.leetcode;

import java.util.Stack;

/**
 * Created by likaiyu on 2020/8/10.
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水。
 *
 */
public class LC_42_TrappingRainWater_接雨水_200818 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int i = trapOptimizer(nums);
        System.out.println(i);
    }

    /**
     * 优化
     * 使用栈保存每堵墙
     * 遍历墙的高度，如果小于栈顶的墙的高度，说明这里会有积水，我们将该高度入栈，
     * 如果当前高度大于栈顶墙的高度，说明之前定的积水到这里停下，可以计算有多少积水，之后将当前的墙入栈，作为新的积水的墙
     * 
     * 总体的原则就是，
     *     当前高度小于等于栈顶高度，入栈，指针后移。
     *
     *     当前高度大于栈顶高度，出栈，计算出当前墙和栈顶的墙之间水的多少，然后计算当前的高度和新栈的高度的关系，
     *     重复第 2 步。直到当前墙的高度不大于栈顶高度或者栈空，然后把当前墙入栈，指针后移。
     *
     * @param height
     * @return
     */
    public static int trapOptimizer(int[] height) {
        int sum = 0;
        if (height == null || height.length == 0) {
            return sum;
        }
        //用来存放索引
        /*Stack<Integer> integerStack = new Stack<>();
        //当前索引
        int cur = 0;
        while (cur < height.length) {
            while (!integerStack.isEmpty() && height[integerStack.peek()] < height[cur]) {
                int h = height[integerStack.peek()];
                integerStack.pop();
                if (integerStack.isEmpty()) {
                    break;
                }
                int distance = cur - integerStack.peek() - 1;
                sum = sum + (height[cur] - h) * distance;
            }
            integerStack.add(cur);
            cur++;
        }*/

        Stack<Integer> stack = new Stack<>();
        //当前的索引
        int current = 0;
        while (current < height.length) {
            while (!stack.isEmpty() && height[stack.peek()] < height[current]) {
                //要出栈的元素
                int h = height[stack.peek()];
                stack.pop();
                if (stack.isEmpty()) {
                    //如果此时stack为空了，则终止这层循环
                    break;
                }
                //计算盛水宽度
                int width = current - stack.peek() - 1;
                //找左右墙中的小值
                int min = Math.min(height[current], height[stack.peek()]);
                int water = (min - h) * width;
                sum += water;

            }
            stack.add(current);
            current++;
        }
        return sum;
    }

    /**
     * 暴力二
     * 就像暴力求柱状图中，能够勾勒出来的矩形的最大面积。
     * 遍历每个柱子，求该柱子上能存多少水
     * 时间复杂度：O(n²)
     * @param height
     * @return
     */
    public static int trap2(int[] height) {
        int sum = 0;
        if (height == null || height.length == 0) {
            return sum;
        }
        //左右两端的不用考虑，肯定不会有水
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            //找左边最高
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }
            //找右边最高
            int max_right = 0;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }

            int minimum = Math.min(max_left, max_right);
            if (minimum > height[i]) {
                sum = sum + (minimum - height[i]);
            }
        }
        return sum;
    }

    /**
     * 暴力法
     * 时间复杂度O(m*n)
     * 会超时
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int sum = 0;
        if (height == null || height.length == 0) {
            return sum;
        }
        int max = getMaxHeight(height);
        for (int i = 1; i <= max; i++) {
            boolean isStart = false;
            int temp = 0;
            for (int value : height) {
                if (isStart && value < i) {
                    temp++;
                }
                if (value >= i) {
                    sum = sum + temp;
                    temp = 0;
                    isStart = true;
                }
            }
        }
        return sum;
    }

    private static int getMaxHeight(int[] height) {
        int max = 0;
        for (int value : height) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
