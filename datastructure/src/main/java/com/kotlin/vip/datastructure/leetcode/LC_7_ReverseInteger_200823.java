package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/23.
 *
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 输入: 123
 * 输出: 321
 *
 * 输入: -123
 * 输出: -321
 *
 * 输入: 120
 * 输出: 21
 *
 */
public class LC_7_ReverseInteger_200823 {
    private static final int MODE_SHIFT = 30;
    public static final int UNSPECIFIED = 0 << MODE_SHIFT;
    public static final int EXACTLY     = 1 << MODE_SHIFT;
    public static final int AT_MOST     = 2 << MODE_SHIFT;
    private static final int MODE_MASK  = 0x3 << MODE_SHIFT;
    // 00000000 00000000 00000000 00000000  UNSPECIFIED
    // 01000000 00000000 00000000 00000000  EXACTLY
    // 10000000 00000000 00000000 00000000  AT_MOST
    // 11000000 00000000 00000000 00000000  MODE_MASK（Mode 掩码）
    // 00111111 11111111 11111111 11111111  ~MODE_MASK（Size 掩码）

    /**
     * @param size size 和其掩码通过位运算将 size 数据存入低30位
     * @param mode mode 和其掩码通过位运算将 mode 数据存入高2位
     * @return
     */
    public static int makeMeasureSpec(int size, int mode) {
        return (size & ~MODE_MASK) | (mode & MODE_MASK);
    }

    /**
     *
     * @param measureSpec measureSpec 和 Mode 掩码(MODE_MASK) 通过位运算获取 mode 数据
     * @return
     */
    public static int getMode(int measureSpec) {
        return (measureSpec & MODE_MASK);
    }

    /**
     *
     * @param measureSpec measureSpec 和 Size 掩码(~MODE_MASK) 通过位运算获取 size 数据
     * @return
     */
    public static int getSize(int measureSpec) {
        return (measureSpec & ~MODE_MASK);
    }


    public static void main(String[] args) {
        int res = reverse2(1200000);
        System.out.println(res);
    }

    /**
     * public static final int MIN_VALUE = -2147483648;
     * public static final int MAX_VALUE = 2147483647;
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            res = res * 10 + pop;
        }
        return res;
    }

    public static int reverse2(int num) {
        int res = 0;

        while (num != 0) {
            int pop = num % 10;
            num /= 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            } else if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            res = res * 10 + pop;
        }
        return res;
    }

}
