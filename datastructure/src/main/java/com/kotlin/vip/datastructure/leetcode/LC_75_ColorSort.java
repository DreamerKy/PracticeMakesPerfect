package com.kotlin.vip.datastructure.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by likaiyu on 2020/3/16.
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，
 * 原地对它们进行排序，使得相同颜色的元素相邻，
 * 并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色
 * 示例:
 *
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 */
public class LC_75_ColorSort {

    public void sortColors(int[] nums) {
        if (nums == null) {
            return;
        }
        int length = nums.length;
        if (length == 0) {
            return;
        }
        sort(nums, 0, length - 1);
    }

//    private void sort(int[] nums, int l, int r) {
//        if (l >= r) {
//            return;
//        }
//
//        int p = partition(nums, l, r);
//        sort(nums, l, p - 1);
//        sort(nums, p + 1, r);
//    }

    private int partition(int[] nums, int l, int r) {
        int v = nums[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] <= v) {
                j++;
                swap(nums, j, i);
            }
        }
        swap(nums, l, j);
        return j;
    }

    public void sortColors2(int[] nums) {
        int zero = -1;//[0...zero] == 0
        int two = nums.length;//[two...n-1] ==2
        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, --two);
            } else {
                assert nums[i] == 0;
                swap(nums, i++, ++zero);
            }
        }

    }


    private void sort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l+(r-l)/2;
        sort(nums,l,mid);
        sort(nums,mid+1,r);
        merge(nums,l,r,mid);
    }

    private void merge(int[] nums, int l, int r,int mid){
        int[] temp = Arrays.copyOfRange(nums,l,r+1);
        int j = l;
        int k = mid+1;
        for(int i = l;i<=r;i++){
            if(j>mid){
                nums[i]=temp[k-l];
                k++;
            }else if(k>r){
                nums[i] = temp[j-l];
                j++;
            }else if(temp[j-l]>temp[k-l]){
                nums[i] = temp[k-l];
                k++;
            }else{
                nums[i] = temp[j-l];
                j++;
            }
        }
    }



    public static void main(String[] args) {
        int[] arr = new int[]{2,0,2,1,1,0};
        colorSort(arr);
    }

    public static void colorSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be null");
        }
        int i = 0;
        int j = arr.length-1;
        while(i<=j){
            if(arr[j] == 0){
                swap(arr,i,j);
                i++;
            }
        }



    }









    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
