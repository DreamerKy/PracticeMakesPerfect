package com.kotlin.vip.datastructure.swordfinger;

/**
 * Created by likaiyu on 2020/1/7.
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，
 * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class A1_FindNumberInTwoDimensionalArray {

    //   1 3 5
    //   2 4 8
    //   6 7 9

    /**
     * 双指针
     * 时间复杂度O(mn)
     * 空间复杂度O(1)
     *
     * @param array
     * @param target
     * @return
     */
    public static boolean find(int[][] array, int target) {

        if (array == null || array.length == 0) {
            return false;
        }
        //定义两个指针
        int row = 0;//行号
        int column = array[0].length - 1;//列号

        //循环的去查找
        while (row < array.length && column >= 0) {
            if (array[row][column] == target) {
                return true;
            }
            if (array[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }

    /**
     * 二分法
     * 时间复杂度O(logmn)
     * 空间复杂度O(1)
     */
    public boolean find2(int[][] array, int target) {
        if (array == null || array.length == 0) {
            return false;
        }
        int left = 0;
        int right = array.length * array[0].length - 1;
        int col = array[0].length;
        while (left <= right) {
            int mid = (left + right) / 2;
            int value = array[mid / col][mid % col];
            if (value == target) {
                return true;
            } else if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;


    }


}
